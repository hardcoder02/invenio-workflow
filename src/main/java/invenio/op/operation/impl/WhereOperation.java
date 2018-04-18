package invenio.op.operation.impl;

import java.util.ArrayList;
import java.util.List;

import qng.client.QueryException;
import qng.core.executor.QueryExecutor;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.tabular.ModifiableTable;
import qng.tabular.Tuple;

// TODO: process attributes
public class WhereOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "where";

	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class,
		CompiledQuery.class
	};
	
	public WhereOperation() {
		super(null, OP_NAME);
	}
	
	public WhereOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		ModifiableTable table = (ModifiableTable) args[0];
		CompiledQuery query = (CompiledQuery) args[1];
		QueryExecutor exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
		
		long startTime = System.currentTimeMillis();
		System.out.println("Starting execution of: " + getName());
		
		List<Integer> deletedTuples = new ArrayList<Integer>();
		int curIndex = -1;
		for (Tuple t : table.getTuples() ) {
			curIndex++;
			getContext().setVariable( OperationConstants.VAR_TUPLE, t);
			Object res = exec.execute(query, getContext()).getResultValue();
			
			if (res == null) {
				deletedTuples.add(curIndex);
				continue;
			}
			if ( ! (res instanceof Boolean) )
				throw new QueryException("Exception in operator " + getName() + 
						": expected boolean expression result, but actual type is: " + res.getClass().getName() );
			
			if ( (Boolean)res == false)
				deletedTuples.add(curIndex);
		}
		
		for (int i = deletedTuples.size() - 1; i >= 0; i--)
			table.removeTuple(deletedTuples.get(i));
		
		// TODO: remove old implementation
		//---------- replacing with new implementation - above. Remove if new implementation works ---------//
//		List<Tuple> deletedTuples = new ArrayList<Tuple>();
//		for (Tuple t : table.getTuples() ) {
//			getContext().setVariable( OperationConstants.VAR_TUPLE, t);
//			Object res = exec.execute(query, getContext()).getResultValue();
//			
//			if (res == null) {
//				deletedTuples.add(t);
//				continue;
//			}
//			if ( ! (res instanceof Boolean) )
//				throw new QueryException("Exception in operator " + getName() + 
//						": expected boolean expression result, but actual type is: " + res.getClass().getName() );
//			
//			if ( (Boolean)res == false)
//				deletedTuples.add(t);
//		}
//		
//		for (Tuple t : deletedTuples)
//			table.removeTuple(t);
		//---------- end of old implementation ------------------------------------------------------------//
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Finished execution of " + getName() + " in (msec): " + duration);
		
		return table;
	}

}
