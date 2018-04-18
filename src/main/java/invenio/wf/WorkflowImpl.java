package invenio.wf;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;

import java.util.HashSet;
import java.util.Set;

import edu.uci.ics.jung.utils.UserData;

public class WorkflowImpl implements Workflow {
	public final static String USER_DATUM_NODE_KEY = "workflow_node";
	public final static String USER_DATUM_CONNECTION_KEY = "workflow_connection";
	private final InvenioGraph graph;
	
	WorkflowImpl() {
		 graph = new InvenioGraph(true);
	}
	
	WorkflowImpl(String name) {
		 graph = new InvenioGraph(name, true);
	}
	
	@Override
	public Set<Node> getStartNodes() {
		final Set<Node> result = new HashSet<Node>();
		final Set<InvenioVertex> vertices = graph.getVertices();
		
		for (InvenioVertex v : vertices) {
			if ( v.inDegree() <= 0 )
				result.add( getNode(v) );
		}
		return result;
	}

	@Override
	public Set<Node> getEndNodes() {
		final Set<Node> result = new HashSet<Node>();
		final Set<InvenioVertex> vertices = graph.getVertices();
		
		for (InvenioVertex v : vertices) {
			if ( v.outDegree() <= 0 )
				result.add( getNode(v) );
		}
		return result;
	}
	
	@Override
	public Set<Node> getIncomingNodes(Node node) {
		final Set<Node> result = new HashSet<Node>();
		InvenioVertex target = getVertex(node);
		
		final Set<InvenioVertex> vertices = target.getPredecessors();
		
		for (InvenioVertex v : vertices) {
			result.add( getNode(v) );
		}
		return result;
	}

	@Override
	public Set<Node> getOutgoingNodes(Node node) {
		final Set<Node> result = new HashSet<Node>();
		InvenioVertex target = getVertex(node);
		
		final Set<InvenioVertex> vertices = target.getSuccessors();
		
		for (InvenioVertex v : vertices) {
			result.add( getNode(v) );
		}
		return result;
	}

	@Override
	public Node addNode(NodeItem item) {
		InvenioVertex v = new InvenioVertex();
		
		Node n = new NodeImpl(item, v, this);
		setNode(v, n);
		
		graph.addVertex(v);
		
		return n;
	}

	@Override
	public void removeNode(Node n) {
		InvenioVertex v = getVertex(n);
		graph.removeVertex(v);
	}
	
	@Override
	public Connection addConnection(Node from, Node to, ConnectionItem ci) {
		InvenioVertex vFrom = getVertex(from);
		InvenioVertex vTo = getVertex(to);
		
		InvenioDirectedEdge e = new InvenioDirectedEdge(vFrom, vTo);
		Connection c = new ConnectionImpl(ci, e);
		
		graph.addEdge(e);
		return c;
	}

	@Override
	public void removeConnection(Connection c) {
		InvenioDirectedEdge e = getEdge(c);
		graph.removeEdge(e);
	}
	
	@Override
	public boolean isSuspended() {
		Set<InvenioVertex> vertices = graph.getVertices();
		for ( InvenioVertex v :  vertices ) {
			if (getNode(v).getNodeItem().isSuspended())
				return true;
		}

		return false;
	}

	private static InvenioVertex getVertex(Node n) {
		NodeImpl ni = getNodeImpl(n);
		return ni.getInvenioVertex();
	}
	
	private static InvenioDirectedEdge getEdge(Connection c) {
		ConnectionImpl ci = getConnectionImpl(c);
		return ci.getInvenioDirectedEdge();
	}
	
	private static Node getNode(InvenioVertex v) {
		return (Node) v.getUserDatum( USER_DATUM_NODE_KEY );
	}
	
	private static void setNode(InvenioVertex v, Node n) {
		v.setUserDatum(USER_DATUM_NODE_KEY, n, UserData.SHARED);
	}
	
	private static NodeImpl getNodeImpl(Node n) throws IllegalArgumentException {
		if (! (n instanceof NodeImpl) )
			throw new IllegalArgumentException("Unexpected Node impplementation");
		return ( (NodeImpl) n);
	}
	
	private static ConnectionImpl getConnectionImpl(Connection c) throws IllegalArgumentException {
		if (! (c instanceof ConnectionImpl) )
			throw new IllegalArgumentException("Unexpected Connection impplementation");
		return ( (ConnectionImpl) c);
	}
}
