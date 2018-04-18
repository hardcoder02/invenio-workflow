package invenio.main;

import invenio.UserSession;
import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.data.bridge.BridgeConstants;
import invenio.data.bridge.BridgeTuple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.UndirectedSparseEdge;

import prefuse.Visualization;
import prefuse.data.Schema;
import prefuse.data.Table;

public class TestImportPrefuseGraph {

	public final static String JUNG_TUPLE = BridgeConstants.JUNG_TUPLE;
	public final static String JUNG_SCHEMA = BridgeConstants.JUNG_SCHEMA;

	public static void refreshPrefuseGraph(prefuse.data.Graph prefuseGraph, Visualization vis, InvenioGraph jungGraph) {
		
		Set fullVertexSet = jungGraph.getVertices();
		Set fullEdgeSet = jungGraph.getEdges();
		Set newNodeSet = new LinkedHashSet();
		Set newEdgeSet = new LinkedHashSet();
		
		BridgeTuple tuple;
//		InvenioElement element;
		for(Iterator<BridgeTuple> tuplesIter = prefuseGraph.tuples(); tuplesIter.hasNext(); ){
			tuple = tuplesIter.next();
			if(!tuple.isValid()){
				System.out.println("removed tuple!");
				prefuseGraph.removeTuple(tuple);
			}
//			element = tuple.getElement();
//			if(element == null || (!jungGraph.getEdges().contains(element) && !jungGraph.getVertices().contains(element)))
//				prefuseGraph.removeTuple(tuple);
		}
		for(Iterator<BridgeTuple> tuplesIter = vis.items(); tuplesIter.hasNext(); ){
			tuple = tuplesIter.next();
			if(!tuple.isValid()){
				System.out.println("removed visual item!");
				prefuseGraph.removeTuple(tuple);
			}
		}
		
		Iterator iter = fullVertexSet.iterator();
		InvenioVertex v;
		
		while(iter.hasNext()) {
			v = (InvenioVertex)iter.next();
			if(v.getPrefuseTable() == null)
				newNodeSet.add(v);
		}
		
		iter = fullEdgeSet.iterator();
		Object o;
		UndirectedSparseEdge u;
		InvenioEdge e;
		
		while(iter.hasNext()) {
			o = iter.next();
			if(o instanceof UndirectedSparseEdge){
				u = (UndirectedSparseEdge)o;
				e = new InvenioEdge((Vertex)u.getEndpoints().getFirst(), (Vertex)u.getEndpoints().getSecond());
				e.importUserData(u);
			}
			else
				e = (InvenioEdge)o;
			if(e.getPrefuseTable() == null)
				newEdgeSet.add(e);
		}
		
		Table nodeTable = prefuseGraph.getNodeTable();
		Table edgeTable = prefuseGraph.getEdgeTable();
		
		int nodeTableSize = nodeTable.getRowCount();
		int edgeTableSize = edgeTable.getRowCount();
		
		nodeTable.addRows(newNodeSet.size());
		edgeTable.addRows(newEdgeSet.size());
		
		Map<Integer, Schema> schemas = getSchemas(jungGraph.getVertices());
		
		Iterator nodes = newNodeSet.iterator();
		
		for(int i = 0; nodes.hasNext(); i++) {
			v = (InvenioVertex)nodes.next();
			nodeTable.set(nodeTableSize + i, JUNG_TUPLE, v);
			nodeTable.set(nodeTableSize + i, JUNG_SCHEMA, getSchema(v, schemas));
			v.setPrefuseTable(nodeTable);
			v.setPrefuseTableIndex(nodeTableSize + i);
		}
		
		schemas = getSchemas(jungGraph.getEdges());
		
		Iterator edges = newEdgeSet.iterator();
		InvenioVertex a, b;
		
		for(int i = 0; edges.hasNext(); i++) {
			
			e = (InvenioEdge)edges.next();
			a = (InvenioVertex)e.getEndpoints().getFirst();
			b = (InvenioVertex)e.getEndpoints().getSecond();
			
			edgeTable.setInt(edgeTableSize + i, prefuse.data.Graph.DEFAULT_SOURCE_KEY, findVertex(a, nodeTable));
			edgeTable.setInt(edgeTableSize + i, prefuse.data.Graph.DEFAULT_TARGET_KEY, findVertex(b, nodeTable));
			edgeTable.set(edgeTableSize + i, JUNG_TUPLE, e);
			edgeTable.set(edgeTableSize + i, JUNG_SCHEMA, getSchema(e, schemas));
			e.setPrefuseTable(edgeTable);
			e.setPrefuseTableIndex(edgeTableSize + i);
		}
	}
	
	public static prefuse.data.Graph buildPrefuseGraph(InvenioGraph jungGraph) {
		
		Set nodeSet = jungGraph.getVertices();
		Set edgeSet = jungGraph.getEdges();
		
		Table nodeTable = new Table();
		Table edgeTable = new Table();
		
		nodeTable.addColumn(JUNG_TUPLE, InvenioVertex.class);
		nodeTable.addColumn(JUNG_SCHEMA, Schema.class);

		edgeTable.addColumn(prefuse.data.Graph.DEFAULT_SOURCE_KEY, int.class);
		edgeTable.addColumn(prefuse.data.Graph.DEFAULT_TARGET_KEY, int.class);
		edgeTable.addColumn(JUNG_TUPLE, InvenioEdge.class);
		edgeTable.addColumn(JUNG_SCHEMA, Schema.class);
		
		nodeTable.addRows(nodeSet.size());
		edgeTable.addRows(edgeSet.size());
		
		HashMap<Integer, Schema> schemas = new LinkedHashMap<Integer, Schema>();
		
		Iterator nodes = nodeSet.iterator();
		InvenioVertex v;
		
		for(int i = 0; nodes.hasNext(); i++) {
			v = (InvenioVertex)nodes.next();
			nodeTable.set(i, JUNG_TUPLE, v);
			nodeTable.set(i, JUNG_SCHEMA, getSchema(v, schemas));
			v.setPrefuseTable(nodeTable);
			v.setPrefuseTableIndex(i);
		}
		
		schemas = new LinkedHashMap<Integer, Schema>();
		
		Iterator edges = edgeSet.iterator();
		UndirectedSparseEdge u;
		InvenioEdge e;
		InvenioVertex a, b;
		
		for(int i = 0; edges.hasNext(); i++) {
			
			u = (UndirectedSparseEdge)edges.next();
			if(u instanceof InvenioEdge)
				e = (InvenioEdge)u;
			else{
				e = new InvenioEdge((Vertex)u.getEndpoints().getFirst(), (Vertex)u.getEndpoints().getSecond());
				e.importUserData(u);
			}
			a = (InvenioVertex)e.getEndpoints().getFirst();
			b = (InvenioVertex)e.getEndpoints().getSecond();
			
			edgeTable.setInt(i, prefuse.data.Graph.DEFAULT_SOURCE_KEY, findVertex(a, nodeTable));
			edgeTable.setInt(i, prefuse.data.Graph.DEFAULT_TARGET_KEY, findVertex(b, nodeTable));
			edgeTable.set(i, JUNG_TUPLE, e);
			edgeTable.set(i, JUNG_SCHEMA, getSchema(e, schemas));
			e.setPrefuseTable(edgeTable);
			e.setPrefuseTableIndex(i);
		}
		
		return new prefuse.data.Graph(nodeTable, edgeTable, false);
	}
	
	protected static Map<Integer, Schema> getSchemas(Set elements) {
		
		Map<Integer, Schema> schemas = new LinkedHashMap<Integer, Schema>();
		Iterator iter = elements.iterator();
		InvenioElement e;
		Object o;
		UndirectedSparseEdge u;

		while(iter.hasNext()) {
			o = iter.next();
			if(o instanceof UndirectedSparseEdge){
				u = (UndirectedSparseEdge)o;
				e = new InvenioEdge((Vertex)u.getEndpoints().getFirst(), (Vertex)u.getEndpoints().getSecond());
				e.importUserData(u);
			}
			else
				e = (InvenioElement)o;
			getSchema(e, schemas);
		}
		
		return schemas;
	}
	
	protected static Schema getSchema(InvenioElement e, Map<Integer, Schema> schemas) {
		
		Iterator keys = e.getUserDatumKeyIterator();
		List a = new LinkedList();
		
		while(keys.hasNext()) {
			a.add(keys.next());
		}
		
		Object[] k = a.toArray();
		Arrays.sort(k);
		int hashCode = Arrays.hashCode(k);
		
		if(schemas.containsKey(hashCode))
			return schemas.get(hashCode);
		
		Schema s = new Schema(k.length);
		
		for(int i = 0; i < k.length; i++)
			s.addColumn(k[i].toString(), e.getUserDatum(k[i]).getClass());
		
		schemas.put(hashCode, s);
		
		return s;
	}
	
	
	protected static int findVertex(InvenioVertex vertex, Table nodeTable) {
		for(int i = 0; i < nodeTable.getRowCount(); i++) {
			if(vertex == nodeTable.get(i, JUNG_TUPLE))
				return i;
		}
		
		return -1;
	}
}
