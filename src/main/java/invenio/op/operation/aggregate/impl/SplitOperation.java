package invenio.op.operation.aggregate.impl;

import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.NotExistsException;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

public class SplitOperation extends AbstractOperation implements
		CompiledOperation {
	
	private ModifiableTable table;
	private String selectedColumn = null;
	
	public final static String OP_NAME = "split";

	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class,
		String.class
	};
	
	public SplitOperation() {
		super(null, OP_NAME);
	}
	
	public SplitOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}	

	
	@Override
	public boolean isAggregate() {
		return true;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		cleanup();
		parseArguments(args);
		
		ModifiableTable outTable = prepareTable();
		
		processTable( outTable );
		
		return outTable;
	}
	
	private void processTable( ModifiableTable outTable ) throws QueryException {
		// process table
		
		for (Tuple t : table.getTuples() ) {
			Object sel = t.getValue( selectedColumn );
			if ( sel != null && (sel instanceof Collection) ) {
				// requires splitting
				for ( Object o : (Collection)sel) {
					splitCopy( o, t, outTable.addTuple());
				}
			}
			else {
				// single value
				copy( t, outTable.addTuple());
			}			
		}
	}
	

	private ModifiableTable prepareTable() throws QueryException {
		ModifiableTable outTable = TableFactory.createTable();
		
		Schema s = table.getSchema();
		
		for ( int i = 0; i < s.getNumberOfColumns(); i++ ) {
			String colName = s.getColumnName(i);
			if ( colName.equals( selectedColumn) )
				outTable.addColumn( selectedColumn, Object.class );
			else
				outTable.addColumn( colName, s.getDeclaredType( i ) );
		}
		
		return outTable;
	}

	private void parseArguments(Object[] args) throws QueryException {
		table = (ModifiableTable) args[0];
		
		if (args[1] == null)
			throw new QueryException("Exception in operator " + getName() + 
					": column name is required" );
		else {
			selectedColumn = (String) args[1];
			if ( !table.getSchema().hasColumn( selectedColumn ) )
				throw new QueryException("Exception in operator " + getName() + 
						": unrecognized column: " + selectedColumn);
		}
	}
	
	private void cleanup() {
		table = null;
		selectedColumn = null;
	}
	
	private void splitCopy( Object newVal, Tuple from, Tuple to) throws QueryException {
		Schema s = from.getSchema();
		try {
			int selIndex = s.getColumnIndex( selectedColumn );
			
			for ( int i = 0; i < s.getNumberOfColumns(); i++) {
				if ( i == selIndex )
					to.setValue( i , newVal);
				else
					to.setValue( i, from.getValue( i ));
			}
		}
		catch (NotExistsException ex) {
			// should not happen
			throw new QueryException( "Column name: " + selectedColumn + " not found", ex);
		}
	}
	
	private void copy(Tuple from, Tuple to) {
		Schema s = from.getSchema();
		for ( int i = 0; i < s.getNumberOfColumns(); i++)
			to.setValue( i, from.getValue( i ));
	}	

}
