package invenio.op.operation.impl;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: hint regarding conf attribute name
public class IsVertexOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isVertex";
	
	public static final Class[] EXPECTED_TYPES = {
		Object.class
	};
	
	public IsVertexOperation() {
		super(null, OP_NAME);
	}
	
	public IsVertexOperation(String id) {
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
		
		if (args[0] instanceof InvenioVertex)
			return true;
		else
			return false;
	}
}
