package invenio.op.operation.impl;

import invenio.op.operation.aggregate.impl.AggregateExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import qng.client.QueryException;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.QueryExecutor;
import qng.core.executor.Util;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.DuplicateException;
import qng.core.query.NotExistsException;
import qng.tabular.AggregateModifiableTable;
import qng.tabular.AggregateTable;
import qng.tabular.AggregateTuple;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.Table;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

// TODO: change expression column types to more precise than object
// TODO: shift columns to reflect order of selections
public class SelectOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String SELECT_ALL = "*";
	
	private ModifiableTable table;
	private boolean isAggregateTable;
	private AggregateModifiableTable aggTable;
	private Set<String> originalColumns = new HashSet<String>();
	private Set<String> originalAggregatedColumns = new HashSet<String>();
	
	private boolean isAggregateQuery;
	private List<Pair<String, CompiledQuery>> selections = new ArrayList<Pair<String, CompiledQuery>>();
	private Set<String> selectedColumns = new HashSet<String>();
	private Set<String> selectedExpressions = new HashSet<String>();
	
	private boolean selectAll = false;
	
	public final static String OP_NAME = "select";

	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class
	};
	
	public SelectOperation() {
		super(null, OP_NAME);
	}
	
	public SelectOperation(String id) {
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
	public Object doExecute(Object[] args) throws QueryException {
		long startTime = System.currentTimeMillis();
		System.out.println("Starting execution of: " + getName());
		
		cleanup();
		parseArguments(args);
		
		QueryExecutor exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
		
		if (selectAll)
			return table;
		
		// prepare table
		prepareTable();
		
		
		// process table
		processTable( exec );
		
		
		// remove unnecessary columns
		Table result = postProcessTable();
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Finished execution of " + getName() + " in (msec): " + duration);
		
		return result;
	}

	private Table postProcessTable() {
		if ( !isAggregateQuery ) {
			if ( !isAggregateTable ) {
				originalColumns.removeAll( selectedColumns );
				for ( String s : originalColumns )
					table.removeColumn( s );
				
				return table;
			}
			else {	// table is aggregate
				originalColumns.removeAll( selectedColumns );
				for ( String s : originalColumns )
					aggTable.removeColumn( s );
				
				return aggTable;
			}
		}
		else {	// aggregate query
			if ( !isAggregateTable ) {
				return aggTable;
			}
			else {	// table is aggregate
				originalColumns.removeAll( selectedColumns );
				for ( String s : originalColumns )
					aggTable.removeColumn( s );
				
				return aggTable;
			}
		}
		
	}

	private void processTable(QueryExecutor exec) throws QueryException {
		if ( !isAggregateQuery ) {
			if ( !isAggregateTable ) {
				for (Tuple t : table.getTuples() ) {
					for ( Pair<String, CompiledQuery> p : selections) {
						if (p.second != null) {
							getContext().setVariable( OperationConstants.VAR_TUPLE, t);
							Object res = exec.execute(p.second, getContext()).getResultValue();
							t.setValue( p.first, res);
						}
					}
				}
			}
			else {	// table is aggregate
				for (Tuple t : aggTable.getTuples() ) {
					for ( Pair<String, CompiledQuery> p : selections) {
						if (p.second != null) {
							getContext().setVariable( OperationConstants.VAR_TUPLE, t);
							Object res = exec.execute(p.second, getContext()).getResultValue();
							t.setValue( p.first, res);
						}
					}
				}
			}
		}
		else {	// aggregate query
			for (Tuple t : aggTable.getTuples() ) {
				for ( Pair<String, CompiledQuery> p : selections) {
					if (p.second != null) {
						CompiledQuery q = p.second;
						if ( !Util.isAggregate( q.getQueryTree(), q.getQueryTree().root() ) ) {
							getContext().setVariable( OperationConstants.VAR_TUPLE, t);
							Object res = exec.execute(p.second, getContext()).getResultValue();
							t.setValue( p.first, res);
						}
						else {
							getContext().setVariable( OperationConstants.VAR_TUPLE, t);
							AggregateExecutor e = new AggregateExecutor( (CompiledElementExecutor) exec );
							Object res = e.execute(p.second, getContext()).getResultValue();
							t.setValue( p.first, res);
						}
					}
				}
			}
		}
		
	}

	private void prepareTable() throws DuplicateException {
		if ( !isAggregateQuery ) {
			if ( !isAggregateTable ) {
				for ( Pair<String, CompiledQuery> p : selections )
					if ( p.second != null )
						table.addColumn( p.first, Object.class);
			}
			else {	// table is aggregate
				for ( Pair<String, CompiledQuery> p : selections )
					if ( p.second != null )
						aggTable.addColumn( p.first, Object.class);
			}
		}
		else {	// aggregate query
			if ( !isAggregateTable ) {
				aggTable = TableFactory.createAggregateTable();
				for ( Pair<String, CompiledQuery> p : selections )
					if ( p.second != null )
						aggTable.addColumn( p.first, Object.class);
				
				Schema s = table.getSchema();
				for ( int i = 0; i < s.getNumberOfColumns(); i++ ) {
					aggTable.addAggregatedColumn( s.getColumnName(i), s.getDeclaredType(i));
				}
				AggregateTuple outT = aggTable.addTuple();
				for (Tuple t : table.getTuples() ) {
					Tuple newT = aggTable.addAggregatedTuple(outT);
					final int size = t.getSchema().getNumberOfColumns();
					for (int i = 0; i < size; i++)
						newT.setValue( i , t.getValue( i ) );
				}
				
			}
			else {	// table is aggregate
				for ( Pair<String, CompiledQuery> p : selections )
					if ( p.second != null )
						aggTable.addColumn( p.first, Object.class);
			}
		}
		
		
	}

	private void parseArguments(Object[] args) throws QueryException {
		table = (ModifiableTable) args[0];
		
		if (table instanceof AggregateModifiableTable) {
			isAggregateTable = true;
			aggTable = (AggregateModifiableTable) table;
		}
		
		for ( String c : table.getSchema().getColumnNames() )
			originalColumns.add( c );
		
		if ( isAggregateTable ) {
			for ( String c : aggTable.getSchema().getAggregatedSchema().getColumnNames() )
				originalAggregatedColumns.add( c );
		}
		
		if ( args.length == 2 && (args[1] instanceof String) && SELECT_ALL.equalsIgnoreCase( (String) args[1] ) ) {
			selectAll = true;
			return;
		}
		
		for (int i = 1; i < args.length; i++) {
			if ( args[i] instanceof String ) {
				selections.add( new Pair<String, CompiledQuery>( (String) args[i], null ) );
			}
			else if ( args[i] instanceof CompiledQuery ) {
				if ( i < args.length - 1 && args[i+1] instanceof String ) {
					CompiledQuery q = (CompiledQuery) args[i];
					selections.add( new Pair<String, CompiledQuery>( (String) args[i+1], q ) );
					if ( Util.isAggregate( q.getQueryTree(), q.getQueryTree().root() ))
						isAggregateQuery = true;
					i++;
				}
				else
					throw new QueryException("Exception in operator " + getName() + 
							": missing alias for expression: " + args[i] );
			}
			else
				throw new QueryException("Exception in operator " + getName() + 
						": unexpected selection type: " + args[i].getClass().getName() );
		}
		
		for ( Pair<String, CompiledQuery> p : selections ) {
			if ( p.second == null )
				addColumn( p.first );
			else
				addExpression( p.first, p.second);
		}
	}
	
	private void addColumn(String colName) throws QueryException {
		Set<String> orCol;
		Set<String> orAggCol;
		
		if ( !isAggregateQuery ) {
			orCol = originalColumns;
			orAggCol = originalAggregatedColumns;
		}
		else {	// aggregatedQuery
			if ( isAggregateTable ) {
				orCol = originalColumns;
				orAggCol = originalAggregatedColumns;
			}
			else {
				orCol = new HashSet<String>();
				orAggCol = originalColumns;
			}	
		}
		
		if ( !orCol.contains( colName) )
			throw new QueryException("Exception in operator " + getName() + 
					": unrecognized column: " + colName);
		
		if ( orAggCol.contains( colName ) )
			throw new QueryException("Exception in operator " + getName() + 
					": invalid selection of aggregate column: " + colName);
		
		if ( selectedColumns.contains( colName ) || selectedExpressions.contains( colName ) )
			throw new QueryException("Exception in operator " + getName() + 
					": duplicate selection: " + colName );
	
		selectedColumns.add( colName );
	}
	
	private void addExpression(String name, CompiledQuery q) throws QueryException {
		Set<String> orCol;
		Set<String> orAggCol;
		
		if ( !isAggregateQuery ) {
			orCol = originalColumns;
			orAggCol = originalAggregatedColumns;
		}
		else {	// aggregatedQuery
			if ( isAggregateTable ) {
				orCol = originalColumns;
				orAggCol = originalAggregatedColumns;
			}
			else {
				orCol = new HashSet<String>();
				orAggCol = originalColumns;
			}	
		}
		
		// no need to check if selectedColumns contains name, because it is a subset of originalColumns
		if ( orCol.contains( name ) || orAggCol.contains( name ) || selectedExpressions.contains( name ) )
			throw new QueryException("Exception in operator " + getName() + 
					": duplicate selection: " + name );

		selectedExpressions.add( name );
	}

	private void cleanup() {
		table = null;
		isAggregateTable = false;
		aggTable = null;
		originalColumns.clear();
		originalAggregatedColumns.clear();
		isAggregateQuery = false;
		selections.clear();
		selectedColumns.clear();
		selectedExpressions.clear();
		selectAll = false;
	}

}
