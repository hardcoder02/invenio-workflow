package invenio.op.operation.directed;

import invenio.data.InvenioElement;
import invenio.data.InvenioVertex;

import java.util.HashSet;
import java.util.Set;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class TreeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "tree";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class
	};
	
	public TreeOperation() {
		super(null, OP_NAME);
	}
	
	public TreeOperation(String id) {
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
		
		Set<InvenioElement> tree = DirectedUtils.getTree(v);
		
		return tree;
	}

}
