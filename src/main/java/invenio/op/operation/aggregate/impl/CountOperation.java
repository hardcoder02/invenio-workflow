package invenio.op.operation.aggregate.impl;

import java.util.List;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: Replace List with Collection?
public class CountOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "count";

	public static final Class[] EXPECTED_TYPES = {
		List.class,
	};
	
	public CountOperation() {
		super(null, OP_NAME);
	}
	
	public CountOperation(String id) {
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
		return ((List)args[0]).size();
	}
	
}
