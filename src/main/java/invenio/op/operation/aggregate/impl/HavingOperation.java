package invenio.op.operation.aggregate.impl;

import invenio.op.operation.impl.OperationConstants;

import java.util.ArrayList;
import java.util.List;

import qng.client.QueryException;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.QueryExecutor;
import qng.core.executor.Util;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.tabular.AggregateModifiableTable;
import qng.tabular.AggregateTuple;
import qng.tabular.Tuple;

public class HavingOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "having";

	public static final Class[] EXPECTED_TYPES = {
		AggregateModifiableTable.class,
		CompiledQuery.class
	};
	
	public HavingOperation() {
		super(null, OP_NAME);
	}
	
	public HavingOperation(String id) {
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
		AggregateModifiableTable table = (AggregateModifiableTable) args[0];
		
		CompiledQuery query = (CompiledQuery) args[1];
		if ( !Util.isAggregate( query.getQueryTree(), query.getQueryTree().root() ))
			throw new QueryException("Exception in operator " + getName() + ": aggregate clause is expected");
		
		QueryExecutor exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
		
		List<AggregateTuple> deletedTuples = new ArrayList<AggregateTuple>();
		for (AggregateTuple t : table.getTuples() ) {
			getContext().setVariable( OperationConstants.VAR_TUPLE, t);
			AggregateExecutor e = new AggregateExecutor( (CompiledElementExecutor) exec );
			Object res = e.execute(query, getContext()).getResultValue();
			
			if (res == null) {
				deletedTuples.add(t);
				continue;
			}
			if ( ! (res instanceof Boolean) )
				throw new QueryException("Exception in operator " + getName() + 
						": expected boolean expression result, but actual type is: " + res.getClass().getName() );
			
			if ( (Boolean)res == false)
				deletedTuples.add(t);
		}
		
		for (Tuple t : deletedTuples)
			table.removeTuple(t);
		return table;
	}

}
