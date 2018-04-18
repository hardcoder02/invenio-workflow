package invenio.op.operation.impl;

import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class IsEmptyOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isEmpty";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class
	};
	
	public IsEmptyOperation() {
		super(null, OP_NAME);
	}
	
	public IsEmptyOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if (args[0] == null)
			return null;
		
		return ((Collection)args[0]).isEmpty();
	}
	
}
