package invenio.op.operation.impl;

import invenio.data.InvenioEdge;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: hint regarding conf attribute name
public class IsEdgeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isEdge";
	
	public static final Class[] EXPECTED_TYPES = {
		Object.class
	};
	
	public IsEdgeOperation() {
		super(null, OP_NAME);
	}
	
	public IsEdgeOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null)
			return null;
		
		if (args[0] instanceof InvenioEdge)
			return true;
		else
			return false;
	}
}
