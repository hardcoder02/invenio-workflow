package invenio.op.operation.impl;

import java.util.HashSet;
import java.util.Set;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class AdjacentIdsOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "adjacentIds";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class
	};
	
	public AdjacentIdsOperation() {
		super(null, OP_NAME);
	}
	
	public AdjacentIdsOperation(String id) {
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
		
		Set<InvenioVertex> neighbours = v.getNeighbors();
		Set<Object> ids = new HashSet<Object>();
		for (InvenioVertex n : neighbours)
			ids.add( n.getKey() );
		
		return ids;
	}
}
