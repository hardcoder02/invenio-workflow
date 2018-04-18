package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class NotOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "not";

	public static final Class[] EXPECTED_TYPES = {
		Boolean.class
	};
	
	public NotOperation() {
		super(null, OP_NAME);
	}
	
	public NotOperation(String id) {
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
		
		return ! ((Boolean)args[0]). booleanValue();
	}
	
}
