package invenio.op.operation.impl;

import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class IsIncidentOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isIncident";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioEdge.class
	};
	
	public IsIncidentOperation() {
		super(null, OP_NAME);
	}
	
	public IsIncidentOperation(String id) {
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
		
		InvenioVertex v = (InvenioVertex) args[0];
		InvenioEdge e = (InvenioEdge) args[1];
		
		return v.isIncident(e);
	}
}
