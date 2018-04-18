package invenio.op.operation.impl;

import invenio.data.InvenioVertex;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.StringFeature;

import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class IsAdjacentByIdOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isAdjacentById";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		Object.class
	};
	
	public IsAdjacentByIdOperation() {
		super(null, OP_NAME);
	}
	
	public IsAdjacentByIdOperation(String id) {
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
		Object id = args[1];
		
		Collection<InvenioVertex> neighbours = v1.getNeighbors();
		for (InvenioVertex v : neighbours)
			if ( getVal( id ).equals( v.getKey() ))
				return true;
		
		return false;
	}

	private Object getVal( Object o) {
		if (o instanceof NumericFeature)
			return ((NumericFeature)o).getValue();
		else if (o instanceof StringFeature)
			return ((StringFeature)o).getValue();
		else
			return o;
	}
}
