package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.data.InvenioVertex;

import java.util.HashSet;
import java.util.Set;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class EgoNetOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "egoNet";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class
	};
	
	public EgoNetOperation() {
		super(null, OP_NAME);
	}
	
	public EgoNetOperation(String id) {
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
		
		Set<InvenioElement> egoNet = new HashSet<InvenioElement>();
		egoNet.addAll( v.getIncidentEdges() );
		egoNet.addAll( v.getNeighbors() );
		egoNet.add( v );
		
		return egoNet;
	}

}
