package invenio.op.operation.directed;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: hint regarding conf attribute name
public class IsEdgeDirectedOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "isEdgeDirected";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioEdge.class
	};
	
	public IsEdgeDirectedOperation() {
		super(null, OP_NAME);
	}
	
	public IsEdgeDirectedOperation(String id) {
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
		
		if (args[0] instanceof InvenioDirectedEdge)
			return true;
		else
			return false;
	}
}
