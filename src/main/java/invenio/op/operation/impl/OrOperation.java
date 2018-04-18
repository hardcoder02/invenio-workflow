package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: is it correct that result is null even if only 1 operand is null?
public class OrOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "or";

	public static final Class[] EXPECTED_TYPES = {
		Boolean.class,
		Boolean.class
	};
	
	public OrOperation() {
		super(null, OP_NAME);
	}
	
	public OrOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if (args[0] == null || args[1] == null)
			return null;
		
		return ((Boolean)args[0]).booleanValue() || ((Boolean) args[1]).booleanValue();
	}
	
}
