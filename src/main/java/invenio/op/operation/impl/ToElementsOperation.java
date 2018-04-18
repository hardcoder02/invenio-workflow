package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;

import java.util.HashSet;
import java.util.Set;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: process attributes
public class ToElementsOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "toElements";

	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class
	};
	
	public ToElementsOperation() {
		super(null, OP_NAME);
	}
	
	public ToElementsOperation(String id) {
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
		
		return createElements(graph);
	}

	private Set<InvenioElement> createElements(InvenioGraph graph) throws QueryException {
		Set<InvenioElement> elements = new HashSet<InvenioElement>();
		
		elements.addAll( graph.getVertices() );
		elements.addAll( graph.getEdges() );
		
		return elements;
	}
}
