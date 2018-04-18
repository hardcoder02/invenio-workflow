package invenio.op.operation.impl;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class DegreeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "degree";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class
	};
	
	public DegreeOperation() {
		super(null, OP_NAME);
	}
	
	public DegreeOperation(String id) {
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
		
		InvenioVertex v = (InvenioVertex) args[0];
		
		return v.degree();
	}
}
