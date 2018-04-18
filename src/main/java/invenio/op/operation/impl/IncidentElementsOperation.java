package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class IncidentElementsOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "incidentElements";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioElement.class
	};
	
	public IncidentElementsOperation() {
		super(null, OP_NAME);
	}
	
	public IncidentElementsOperation(String id) {
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
		
		InvenioElement e = (InvenioElement) args[0];
		
		return e.getIncidentElements();
	}
}
