package invenio.op.operation.directed;

import invenio.data.DirectedEdge;
import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.op.operation.impl.OperatorUtils;
import invenio.operator.data.StringFeature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import qng.client.QueryException;

// TODO: replace invenio.data.DirectedEdge with jung DirectedEdge
public class DirectedUtils {
	public static void addDirectedEdges(InvenioGraph graph, String pkAttr, String fkAttr) throws QueryException {
		final Map<String, InvenioVertex> id2Vertex = mapIdToVertex(graph, pkAttr);
		createDirectedEdges(graph, id2Vertex, fkAttr);
	}
	
	protected static Map<String, InvenioVertex> mapIdToVertex(InvenioGraph graph, String pkAttr) throws QueryException {
		final Map<String, InvenioVertex> id2Vertex = new HashMap<String, InvenioVertex>();
		for (Object o : graph.getVertices()) {
			InvenioVertex v = (InvenioVertex) o;
			
			StringFeature f = OperatorUtils.getAsStringFeature( v, pkAttr, true );
			String id = f.getValue();
			if ( id == null )
				throw new QueryException("Null id for vertex: " + v + " with id attribute: " + pkAttr);
			id2Vertex.put(id, v);
		}
		
		return id2Vertex;
	}
	
	protected static void createDirectedEdges(InvenioGraph graph, Map<String, InvenioVertex> id2Vertex, String fkAttr) throws QueryException {
		for (Object o : graph.getVertices()) {
			InvenioVertex v = (InvenioVertex) o;
			
			StringFeature f = OperatorUtils.getAsStringFeature( v, fkAttr, true );
			String fk = f.getValue();
			if ( fk != null && !fk.isEmpty() ) {
				if ( !id2Vertex.containsKey(fk) )
					// TODO: fix
					System.out.println("Foreign key: " + fk + " for vertex: " + v + " not found. Skipping vertex.");
					//throw new QueryException("Foreign key: " + fk + " for vertex: " + v + " not found.");
				else {
					InvenioVertex vParent = id2Vertex.get(fk);
					InvenioDirectedEdge e = new InvenioDirectedEdge(vParent, v);
					graph.addEdge(e);
				}
			}
		}
	}
	
	public static Set<InvenioVertex> getChildren(InvenioVertex v) {
		Set<InvenioVertex> result = new HashSet<InvenioVertex>();
		
		Iterator iter = v.getOutEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof DirectedEdge) {
				DirectedEdge e = (DirectedEdge) o;
				if ( v.equals(e.getSource()) )
					result.add( (InvenioVertex) e.getDest() );
			}
		}
		
		return result;
	}
	
	public static boolean hasChildren(InvenioVertex v) {
		Iterator iter = v.getOutEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof DirectedEdge) {
				DirectedEdge e = (DirectedEdge) o;
				if ( v.equals(e.getSource()) )
					return true;
			}
		}
		
		return false;
	}
	
	public static Set<InvenioVertex> getParents(InvenioVertex v) {
		Set<InvenioVertex> result = new HashSet<InvenioVertex>();
		
		Iterator iter = v.getInEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof DirectedEdge) {
				DirectedEdge e = (DirectedEdge) o;
				if ( v.equals(e.getDest()) )
					result.add( (InvenioVertex) e.getSource() );
			}
		}
		
		return result;
	}
	
	public static boolean hasParents(InvenioVertex v) {
		Iterator iter = v.getInEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof DirectedEdge) {
				DirectedEdge e = (DirectedEdge) o;
				if ( v.equals(e.getDest()) )
					return true;
			}
		}
		
		return false;
	}
	
	public static Set<InvenioVertex> getSiblings(InvenioVertex v) {
		Set<InvenioVertex> result = new HashSet<InvenioVertex>();
		
		for (InvenioVertex vParent : getParents(v)) {
			result.addAll( getChildren(vParent) );
		}
		
		result.remove( v );
		return result;
	}
	
	public static Set<InvenioVertex> getRoots(InvenioGraph graph) {
		return getRoots(graph, true);
	}
	
	public static Set<InvenioVertex> getRoots(InvenioGraph graph, boolean includeSingleNodes) {
		Set<InvenioVertex> result = new HashSet<InvenioVertex>();
		
		for (Object o : graph.getVertices() ) {
			InvenioVertex v = (InvenioVertex) o;
			if ( hasParents(v) )
				continue;
			// vertex is a root, possibly without children
			if ( includeSingleNodes || hasChildren(v) )
				result.add(v);
		}
		
		return result;
	}
	
	public static int getTreeDepth(InvenioVertex root) {
		int curDepth = 0;
		for (InvenioVertex child : getChildren(root) ) {;
			int childDepth = getTreeDepth(child) + 1;
			if ( childDepth > curDepth )
				curDepth = childDepth;
		}
		
		return curDepth;
	}
	
	public static int getTreeSize(InvenioVertex root, HashMap<String, Integer> cache) {
		int curCount = 1;
		String key = "" + root.getKey();
		if (cache.containsKey(key))
			return cache.get(key);
		else {
			for (InvenioVertex child : getChildren(root) ) {;
				curCount += getTreeSize( child, cache );
			}
			cache.put(key,curCount);
		}
		return curCount;
	}
	
	public static int getTreeSize(InvenioVertex root) {
		int curCount = 1;
		for (InvenioVertex child : getChildren(root) ) {;
			curCount += getTreeSize( child );
		}
		
		return curCount;
	}
	
	public static Set<InvenioElement> getTree(InvenioVertex root) {
		Set<InvenioElement> elements = new HashSet<InvenioElement>();
		
		getSubTree(root, elements);
		
		return elements;
	}
	
	public static void getSubTree(InvenioVertex root, Set<InvenioElement> elements) {
		elements.add(root);
		
		Iterator iter = root.getOutEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof InvenioDirectedEdge) {
				InvenioDirectedEdge e = (InvenioDirectedEdge) o;
				if ( root.equals(e.getSource()) ) {
					elements.add( e );
					getSubTree( (InvenioVertex) e.getDest(), elements );
				}
			}
		}
	}
	
	public static Set<InvenioVertex> getAdjacentVerticesUndirected(InvenioVertex v) {
		Set<InvenioVertex> result = new HashSet<InvenioVertex>();
		
		Iterator iter = v.getIncidentEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof InvenioEdge && !(o instanceof DirectedEdge)) {
				InvenioEdge e = (InvenioEdge) o;
				result.add( (InvenioVertex) e.getOpposite(v) );
			}
		}
		
		return result;
	}
	
	public static Set<InvenioElement> getEgoNetUndirected(InvenioVertex v) {
		Set<InvenioElement> result = new HashSet<InvenioElement>();
		
		Iterator iter = v.getIncidentEdges().iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( o != null && o instanceof InvenioEdge && !(o instanceof DirectedEdge)) {
				InvenioEdge e = (InvenioEdge) o;
				result.add( (InvenioVertex) e.getOpposite(v) );
				result.add( e );
			}
		}
		result.add(v);
		
		return result;
	}
}
