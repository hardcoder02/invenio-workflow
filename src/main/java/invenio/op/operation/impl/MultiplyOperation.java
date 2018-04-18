package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class MultiplyOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "multiply";

	public static final Class[] EXPECTED_TYPES = {
		Number.class,
		Number.class
	};
	
	public MultiplyOperation() {
		super(null, OP_NAME);
	}
	
	public MultiplyOperation(String id) {
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
		
		if ( args[0] instanceof Integer && args[1] instanceof Integer)
			return (Integer)args[0] * (Integer) args[1];
		else
			return ((Number)args[0]).doubleValue() * ((Number) args[1]).doubleValue();
	}
	
}
