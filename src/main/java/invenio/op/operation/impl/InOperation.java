package invenio.op.operation.impl;

import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class InOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "in";

	public static final Class[] EXPECTED_TYPES = {
		Object.class,
		Collection.class
	};
	
	public InOperation() {
		super(null, OP_NAME);
	}
	
	public InOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		return ((Collection)args[1]).contains(args[0]);
	}
	
}
