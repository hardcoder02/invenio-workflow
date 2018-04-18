package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class AbsoluteValueOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "absoluteValue";

	public static final Class[] EXPECTED_TYPES = {
		Number.class
	};
	
	public AbsoluteValueOperation() {
		super(null, OP_NAME);
	}
	
	public AbsoluteValueOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null )
			return null;
		
		if ( args[0] instanceof Integer )
			return Math.abs( ((Integer)args[0]).intValue() );
		else
			return Math.abs( ((Number)args[0]).doubleValue() );
	}
}
