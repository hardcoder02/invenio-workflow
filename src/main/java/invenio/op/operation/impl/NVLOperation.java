package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class NVLOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "nvl";

	public static final Class[] EXPECTED_TYPES = {
		Object.class,
		Object.class
	};
	
	public NVLOperation() {
		super(null, OP_NAME);
	}
	
	public NVLOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if (args[0] != null)
			return args[0];
		else
			return args[1];
	}
}
