package invenio.op.operation.directed;

import java.util.HashMap;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class TreeSizeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "treeSize";

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class
	};
	
	public TreeSizeOperation() {
		super(null, OP_NAME);
	}
	
	public TreeSizeOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		InvenioVertex root = (InvenioVertex) args[0];
		
//		return DirectedUtils.getTreeSize(root, new HashMap<String, Integer>());
		return DirectedUtils.getTreeSize(root);
		
	}
}
