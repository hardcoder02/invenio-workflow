package invenio.op.operation.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.DuplicateException;
import qng.tabular.ModifiableTable;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

// TODO: process attributes
public class FromOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "from";
	public static enum TYPE {
		GRAPH,
		ELEMENT,
		VERTEX,
		EDGE,
		ATTRIBUTE
	}
	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class,
		TYPE.class,
		String.class
	};
	
	public FromOperation() {
		super(null, OP_NAME);
	}
	
	public FromOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		InvenioGraph g = (InvenioGraph) args[0];
		TYPE t = (TYPE) args[1];
		String alias = (String) args[2];
		
		try {
			switch (t) {
				case GRAPH: return processGraph(g, alias);
				case ELEMENT: return processElement(g, alias);
				case VERTEX: return processNode(g, alias);
				case EDGE: return processEdge(g, alias);
				case ATTRIBUTE: return processAttribute(g, alias);
			}
		}
		catch (DuplicateException e) {
			throw new QueryException(e);
		}
		// should not happen
		throw new QueryException("Unrecognized TYPE: " + t);
	}

	protected Object processAttribute(InvenioGraph g, String alias) throws DuplicateException {
		return null;
	}

	protected Object processEdge(InvenioGraph g, String alias) throws DuplicateException {
		ModifiableTable t = TableFactory.createTable();
		t.addColumn(alias, InvenioEdge.class);
		
		// TODO: set?
//		Set edges = g.getEdges();
		List<InvenioEdge> edges = g.getEdges();
		Iterator iter = edges.iterator();
		
		while (iter.hasNext()) {
			Tuple tuple = t.addTuple();
			tuple.setValue(0, iter.next());
		}
		return t;
	}

	protected Object processNode(InvenioGraph g, String alias) throws DuplicateException {
		ModifiableTable t = TableFactory.createTable();
		t.addColumn(alias, InvenioVertex.class);
		
		// TODO: set?
//		Set vertices = g.getVertices();
		List<InvenioVertex> vertices = g.getVertices();
		Iterator iter = vertices.iterator();
		
		while (iter.hasNext()) {
			Tuple tuple = t.addTuple();
			tuple.setValue(0, iter.next());
		}
		return t;
	}

	protected Object processElement(InvenioGraph g, String alias) throws DuplicateException {
		ModifiableTable t = TableFactory.createTable();
		t.addColumn(alias, InvenioElement.class);
		
		// TODO: set?
//		Set vertices = g.getVertices();
		List<InvenioVertex> vertices = g.getVertices();
		Iterator iter = vertices.iterator();
		
		while (iter.hasNext()) {
			Tuple tuple = t.addTuple();
			tuple.setValue(0, iter.next());
		}
		
		// TODO: set?
//		Set edges = g.getEdges();
		List<InvenioEdge> edges = g.getEdges();
		iter = edges.iterator();
		
		while (iter.hasNext()) {
			Tuple tuple = t.addTuple();
			tuple.setValue(0, iter.next());
		}
		
		return t;
	}

	protected Object processGraph(InvenioGraph g, String alias) throws DuplicateException {
		ModifiableTable t = TableFactory.createTable();
		t.addColumn(alias, InvenioGraph.class);
		Tuple tuple = t.addTuple();
		tuple.setValue(0, g);
		return t;
	}
	
}
