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
public class ToGraphOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "toGraph";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class,
		String.class
	};
	
	public ToGraphOperation() {
		super(null, OP_NAME);
	}
	
	public ToGraphOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		Collection<InvenioElement> elements = (Collection<InvenioElement>) args[0];
		if (elements == null)
			return null;
		
		return createGraph(elements, (String) args[1]);
	}

	private InvenioGraph createGraph(Collection<InvenioElement> elements, String graphName) throws QueryException {
		InvenioGraph g = new InvenioGraph(graphName);
		
		Map<InvenioVertex, InvenioVertex> oldToNewVertex = new HashMap<InvenioVertex, InvenioVertex>();
		
		// first pass - vertices
		for ( Object o : elements ) {
			if ( o instanceof InvenioVertex ) {
				InvenioVertex oldV = (InvenioVertex) o;
				InvenioVertex newV = copyVertex( oldV, g );
				oldToNewVertex.put(oldV, newV);
			}
			else if ( o instanceof InvenioEdge ) {
				// skip
			}
			else
				throw new QueryException("Exception in operator " + getName() + ": unexpected entity: " + o);
		}
		
		// second pass - edges
		for ( Object o : elements ) {
			if ( o instanceof InvenioEdge ) {
				InvenioEdge oldE = (InvenioEdge) o;
				InvenioEdge newE = copyEdge( oldE, oldToNewVertex, g );
			}
		}

		return g;
	}

	private InvenioEdge copyEdge(InvenioEdge oldE, Map<InvenioVertex, InvenioVertex> oldToNewVertex,
				InvenioGraph g) {

		edu.uci.ics.jung.utils.Pair p = oldE.getEndpoints();
		InvenioVertex vFrom = oldToNewVertex.get( p.getFirst() );
		InvenioVertex vTo = oldToNewVertex.get( p.getSecond() );
		
		// endpoints not found
		if (vFrom == null || vTo == null)
			return null;
		
		InvenioEdge newE = new InvenioEdge(vFrom, vTo);
		newE.importInvenioDataFromEdge( oldE );
		newE.importUserData( oldE );
		g.addEdge(newE);
		return newE;
	}

	private InvenioVertex copyVertex(InvenioVertex oldV, InvenioGraph g) {
        InvenioVertex newV = new InvenioVertex();
        newV.importInvenioDataFromVertex( oldV );
        newV.importUserData(oldV);
        
        // TODO: finish (copy attributes)
        newV.setKeyAttribute(oldV.getKeyAttribute());
		newV.setKey( (oldV.getKey() != null) ? oldV.getKey().toString() : null);
		newV.setName(oldV.getName());
		newV.setInfoLabel(oldV.getInfoLabel());
		
        g.addVertex( newV );
        return newV;
	}
}
