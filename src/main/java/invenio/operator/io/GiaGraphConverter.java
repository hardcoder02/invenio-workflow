package invenio.operator.io;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.DuplicateElementException;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.UnsupportedFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import linqs.gia.feature.Feature;
import linqs.gia.feature.schema.Schema;
import linqs.gia.graph.DirectedEdge;
import linqs.gia.graph.Edge;
import linqs.gia.graph.Graph;
import linqs.gia.graph.GraphItem;
import linqs.gia.graph.Node;
import linqs.gia.util.SimplePair;
import edu.uci.ics.jung.utils.UserData;
import edu.uci.ics.jung.utils.UserDataContainer;

public class GiaGraphConverter {
	// this actually should be called "key", but Constants.KEY_DATA = "key" already,
	// instead of Constants.KEY_DATA = "key_attribute", because it corresponds
	// to getKeyAttribute()
	//
	// !!! SEE SimpleKeyVertexTransformationFunction, SimpleKeyEdgeTransformationFunction, same problem
	public static final String DEFAULT_KEY_ATTRIBUTE = "key_value";
	
	public InvenioGraph convertfromGia(Graph giaGraph) throws DataFormatException {
		InvenioGraph invGraph = new InvenioGraph();

		importGraph(invGraph, giaGraph);
		Map<String, InvenioVertex> vertexMap = importVertices(invGraph, giaGraph);
		importEdges(invGraph, giaGraph, vertexMap);
		
		return invGraph;

	}

	protected void importGraph(InvenioGraph invGraph, Graph giaGraph) {
		invGraph.setName(giaGraph.getID().toString());

		// TODO: add complete GraphID to invGraph
		importUserData(invGraph, giaGraph.getSchema());
	}

	protected Map<String, InvenioVertex> importVertices(InvenioGraph invGraph,
			Graph giaGraph) throws DuplicateElementException {
		Map<String, InvenioVertex> vertexMap = new HashMap<String, InvenioVertex>(
				giaGraph.numNodes());

		Iterator<Node> nodeIter = giaGraph.getNodes();

		while (nodeIter.hasNext()) {
			Node n = nodeIter.next();
			String id = n.getID().toString();

			if (vertexMap.containsKey(id)) {
				throw new DuplicateElementException(
						"Duplicate vertex with id: " + id);
			}

			InvenioVertex invVertex = new InvenioVertex();

			// TODO: or setKeyAttribute(n.getID().getSchemaID()); ?
			invVertex.setKeyAttribute(DEFAULT_KEY_ATTRIBUTE);
			invVertex.setKey(id);
			invVertex.setName(id);
			invVertex.setInfoLabel(id);

			importUserData(invVertex, n.getSchema());

			invGraph.addVertex(invVertex);
			vertexMap.put(id, invVertex);
		}

		// TODO: add relation? See DatabaseGraphImporter
		return vertexMap;
	}

	public void importEdges(InvenioGraph invGraph, Graph giaGraph, Map<String, InvenioVertex> vertexMap)
						throws DataFormatException {
		Iterator<Edge> edgeIter = giaGraph.getEdges();

		while (edgeIter.hasNext()) {
			Edge e = edgeIter.next();
			String id = e.getID().toString();
			
			InvenioEdge invEdge = createEdge(e, vertexMap);
			
			// TODO: or setKeyAttribute(e.getID().getSchemaID()); ?
			invEdge.setKeyAttribute(DEFAULT_KEY_ATTRIBUTE);
			invEdge.setKey(id);
			invEdge.setName(id);
			invEdge.setInfoLabel(id);

			importUserData(invEdge, e.getSchema());

			invGraph.addEdge(invEdge);
		}
		
		// TODO: add relation? See DatabaseGraphImporter
	}

	protected void importUserData(UserDataContainer udc, Schema schema) {
		Iterator<SimplePair<String, Feature>> features = schema
				.getAllFeatures();

		while (features.hasNext()) {
			SimplePair<String, Feature> pair = features.next();
			udc.addUserDatum(pair.getFirst(), pair.getSecond(), UserData.SHARED);
		}
	}
	
	protected InvenioEdge createEdge(Edge e, Map<String, InvenioVertex> vertexMap) throws DataFormatException {
		if (e instanceof DirectedEdge) {
			DirectedEdge eDir = (DirectedEdge) e;
			if ( eDir.numSourceNodes() != 1
					|| eDir.numTargetNodes() != 1 )
				throw new UnsupportedFormatException(
						"Directed edge with id=" + e.getID() + " must have exactly 1 source and 1 target node");
			
			Node sourceNode = eDir.getSourceNodes().next();
			Node targetNode = eDir.getTargetNodes().next();
						
			return new InvenioDirectedEdge( getVertex( vertexMap, sourceNode.getID().toString()),
					getVertex( vertexMap, targetNode.getID().toString()) );
		}
		else {
			List<Node> nodes = getNodesAsList( e.getIncidentGraphItems() );
			
			if ( nodes.size() < 1 || nodes.size() > 2)
				throw new UnsupportedFormatException(
						"Undirected edge with id=" + e.getID() + " must have exactly 1 or 2 incident nodes");
			
			Node sourceNode = nodes.get(0);
			Node targetNode = (nodes.size() == 1) ? nodes.get(0) : nodes.get(1);
			
			return new InvenioEdge( getVertex( vertexMap, sourceNode.getID().toString()),
					getVertex( vertexMap, targetNode.getID().toString()));
		}
	}
	
	/**
	 * 
	 * @param itemIter Iterator over a Collection of Nodes
	 * @return
	 * @throws DataFormatException if any element of itemIter is not instanceof Node
	 */
	protected List<Node> getNodesAsList(Iterator<GraphItem> itemIter) throws DataFormatException {
		List<Node> result = new ArrayList<Node>();
		while (itemIter.hasNext()) {
			GraphItem item = itemIter.next();
			if (item instanceof Node)
				result.add( (Node)item );
			else
				throw new DataFormatException("GraphItem with id=" + item.getID()+ " must be an instance of Node");
		}
		
		return result;
	}
	
	protected InvenioVertex getVertex(Map<String, InvenioVertex> vertexMap, String vertexId) throws MissingElementException {
		if ( !vertexMap.containsKey(vertexId) )
			throw new MissingElementException("Vertex with id=" + vertexId + " does not exist");
		
		return vertexMap.get(vertexId);
	}
}
