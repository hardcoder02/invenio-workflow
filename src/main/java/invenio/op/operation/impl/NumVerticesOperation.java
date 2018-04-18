package invenio.op.operation.impl;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.Constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: process attributes
public class NumVerticesOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "numEdges";

	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class,
	};
	
	public NumVerticesOperation() {
		super(null, OP_NAME);
	}
	
	public NumVerticesOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		InvenioGraph graph = (InvenioGraph) args[0];
		if (graph == null)
			return null;
		
		return graph.numEdges();
	}

}
