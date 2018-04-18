package invenio.op.operation.impl;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class IsAdjacentOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isAdjacent";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public IsAdjacentOperation() {
		super(null, OP_NAME);
	}
	
	public IsAdjacentOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	
	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null || args[1] == null)
			return null;
		
		InvenioVertex v1 = (InvenioVertex) args[0];
		InvenioVertex v2 = (InvenioVertex) args[1];
		
		return v1.isNeighborOf(v2);
	}
}
