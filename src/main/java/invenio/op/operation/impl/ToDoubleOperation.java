package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class ToDoubleOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "toDouble";

	public static final Class[] EXPECTED_TYPES = {
		Number.class
	};
	
	public ToDoubleOperation() {
		super(null, OP_NAME);
	}
	
	public ToDoubleOperation(String id) {
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
		
		return ((Number)args[0]).doubleValue();
	}
}