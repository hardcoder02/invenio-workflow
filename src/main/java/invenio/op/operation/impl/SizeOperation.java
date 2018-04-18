package invenio.op.operation.impl;

import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class SizeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "size";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class
	};
	
	public SizeOperation() {
		super(null, OP_NAME);
	}
	
	public SizeOperation(String id) {
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
		
		return ((Collection)args[0]).size();
	}
	
}
