package invenio.op.operation.aggregate.impl;

import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.Pair;
import invenio.operator.data.StringFeatureImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import qng.client.QueryException;
import qng.core.executor.QueryExecutor;
import qng.core.executor.Util;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledElementQuery;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.DefaultCompiledElementQuery;
import qng.tabular.AggregateModifiableTable;
import qng.tabular.AggregateModifiableTuple;
import qng.tabular.AggregateTuple;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

public class GroupByOperation extends AbstractOperation implements
		CompiledOperation {
	
	private ModifiableTable table;
	private Set<String> originalColumns = new HashSet<String>();
	
	private List<Pair<String, CompiledQuery>> selections = new ArrayList<Pair<String, CompiledQuery>>();
	private Set<String> selectedColumns = new HashSet<String>();
	private Set<String> selectedExpressions = new HashSet<String>();
	
	public final static String OP_NAME = "groupBy";

	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class
	};
	
	public GroupByOperation() {
		super(null, OP_NAME);
	}
	
	public GroupByOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}	

	@Override
	public boolean hasExactNumberOfArguments() {
		return false;
	}
	
	@Override
	public boolean isAggregate() {
		return true;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		cleanup();
		parseArguments(args);
		
		QueryExecutor exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
		
		AggregateModifiableTable outTable = prepareTable();
		
		processTable( outTable, exec);
		
		return outTable;
	}
	
	private void processTable( AggregateModifiableTable outTable, QueryExecutor exec) throws QueryException {
		// process table
		
		final Map<GroupByEntry, AggregateTuple> outerTuples = new HashMap<GroupByEntry, AggregateTuple>();
		
		for (Tuple t : table.getTuples() ) {
			Object[] values = new Object[ selections.size() ];
			int i = 0;
			for ( Pair<String, CompiledQuery> p : selections) {
				if (p.second == null) {
					values[i] = t.getValue( p.first );
				}
				else {
					getContext().setVariable( OperationConstants.VAR_TUPLE, t);
					Object res = exec.execute(p.second, getContext()).getResultValue();
					values[i] = res;
				}
				i++;
			}
			GroupByEntry entry = new GroupByEntry( values );
			
			AggregateTuple tuple = null;
			if ( outerTuples.containsKey( entry ) )
				tuple = outerTuples.get( entry );
			else {
				tuple = outTable.addTuple();
				for (int j = 0; j < values.length; j++) {
					tuple.setValue( selections.get(j).first, values[j]);
				}
				outerTuples.put( entry, tuple);
			}
			
			Tuple newTuple = outTable.addAggregatedTuple( tuple );
			Schema newSchema = newTuple.getSchema();
			for ( String colName : newSchema.getColumnNames() ) {
				newTuple.setValue( colName, t.getValue( colName ) );
			}
		}
	}
	
	private AggregateModifiableTable prepareTable() throws QueryException {
		AggregateModifiableTable outTable = TableFactory.createAggregateTable();
		
		for ( Pair<String, CompiledQuery> p : selections ) {
			if ( p.second == null )
				outTable.addColumn( p.first, table.getSchema().getDeclaredType( p.first ));
			else
				outTable.addColumn( p.first, Object.class );
		}
		
		Schema originalSchema = table.getSchema();
		for ( int i = 0; i < originalSchema.getNumberOfColumns(); i++ ) {
			String colName = originalSchema.getColumnName( i );
			if ( !selectedColumns.contains( colName ) ) {
				outTable.addAggregatedColumn( colName, originalSchema.getDeclaredType( i ) );
			}
		}
		
		return outTable;
	}

	private void parseArguments(Object[] args) throws QueryException {
		table = (ModifiableTable) args[0];
		
		for ( String c : table.getSchema().getColumnNames() )
			originalColumns.add( c );
		
		if ( args.length < 2 ) {
			throw new QueryException("Exception in operator " + getName() + 
					": at least one column is needed" );
		}
		
		for (int i = 1; i < args.length; i++) {
			if ( args[i] instanceof String )
				addColumn( (String) args[i] );
			else if ( args[i] instanceof CompiledQuery ) {
				if ( i < args.length - 1 && args[i+1] instanceof String ) {
					addExpression( (String) args[i+1], (CompiledQuery) args[i] );
					i++;
				}
				else
					throw new QueryException("Exception in operator " + getName() + 
							": missing alias for expression: " + args[i] );
			}
			else
				throw new QueryException("Exception in operator " + getName() + 
						": unexpected type: " + args[i].getClass().getName() );
		}
	}
	
	private void addColumn(String colName) throws QueryException {
		if ( !originalColumns.contains( colName) )
			throw new QueryException("Exception in operator " + getName() + 
					": unrecognized column: " + colName);
		
		if ( selectedColumns.contains( colName ) || selectedExpressions.contains( colName ) )
			throw new QueryException("Exception in operator " + getName() + 
					": duplicate selection: " + colName );
		
		selections.add( new Pair<String, CompiledQuery> (colName, null) );
		selectedColumns.add( colName );
	}
	
	private void addExpression(String name, CompiledQuery q) throws QueryException {
		// check that the CompiledQuery is not aggregate
		if ( Util.isAggregate( q.getQueryTree(), q.getQueryTree().root() ) )
			throw new QueryException( "Exception in operator " + getName() + 
					": aggregate expressions not allowed: " + name);
		
		// no need to check if selectedColumns contains name, because it is a subset of originalColumns
		if ( originalColumns.contains( name ) || selectedExpressions.contains( name ) )
			throw new QueryException("Exception in operator " + getName() + 
					": duplicate selection: " + name );

		selections.add( new Pair<String, CompiledQuery> (name, q) );
		selectedExpressions.add( name );
	}

	private void cleanup() {
		table = null;
		originalColumns.clear();
		selections.clear();
		selectedColumns.clear();
		selectedExpressions.clear();
	}
	
	private final static class GroupByEntry {
		private final Object[] values;
		
		private GroupByEntry(Object[] values) throws NullPointerException {
			if (values == null)
				throw new NullPointerException("GroupByEntry values array should not be null");
			this.values = values;
		}
		
		private int getSize() {
			return values.length;
		}
		
		private Object getValue(int idx) {
			return values[idx];
		}

		@Override
		public boolean equals(Object obj) {
			if ( !(obj instanceof GroupByEntry) )
				return false;
			else {
				GroupByEntry other = (GroupByEntry) obj;
				if ( values.length != other.values.length )
					return false;
				for (int i = 0; i < values.length; i++)
					if ( !areEqual( values[i], other.values[i] ) )
						return false;
				return true;
			}
		}

		// TODO re-implement using Compare Operator
		private boolean areEqual(Object v1, Object v2) {
			if ( v1 == null && v2 == null )
				return true;
			else if ( (v1 == null && v2 != null) || (v1 != null && v2 == null) )
				return false;
			return v1.equals(v2);
		}

		@Override
		public int hashCode() {
			int hash = 0;
			for (int i = 0; i < values.length; i++) {
				if ( values[i] != null)
					hash += values[i].hashCode();
			}
			return hash;
		}
		
		
	}

}
