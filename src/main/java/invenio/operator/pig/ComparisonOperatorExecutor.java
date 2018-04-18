package invenio.operator.pig;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.ConfigurationException;
import invenio.operator.OperatorExecutionException;
import invenio.operator.comparison.Util;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DataMapper;
import invenio.operator.data.InvenioDataMapper;
import invenio.operator.data.MissingElementException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uci.ics.jung.utils.UserData;

public class ComparisonOperatorExecutor {
	private List<UnaryOperator> unaryOps = new ArrayList<UnaryOperator>();
	private List<BinaryOperator> binaryOps = new ArrayList<BinaryOperator>();
	
	public void addUnaryOperator(UnaryOperator op) {
		unaryOps.add(op);
	}
	
	public void addBinaryOperator(BinaryOperator op) {
		binaryOps.add(op);
	}
	
	public InvenioGraph execute(InvenioGraph g1, InvenioGraph g2) throws OperatorExecutionException, DataFormatException {
		
		validate(g1, g2);
		InvenioGraph g = createInvenioGraph(g1, g2);
		
		Map<String, InvenioElement> nodeMapG2 = Util.createElementMap( g2.getVertices() );
		Map<String, InvenioElement> edgeMapG2 = Util.createElementMap( g2.getEdges() );
		Map<String, InvenioVertex> nodeMapG = new HashMap<String, InvenioVertex>();
		
		// nodes
		for ( Object o1 : g1.getVertices() ) {
			InvenioVertex v1 = (InvenioVertex) o1;
			InvenioVertex v2 = null;
			String key = Util.getElementKey(v1);
			if ( !nodeMapG2.containsKey( key ) )
					throw new MissingElementException("Vertex with ID=" + key + "not found in g2");
			v2 = (InvenioVertex) nodeMapG2.get( key );
			
			InvenioVertex v = createInvenioVertex(v1, v2, g);
			nodeMapG.put( Util.getElementKey(v), v);
			
			executeUnary( v1 );
			executeUnary( v2 );
			executeBinary( v1, v2, v);
		}
		
		
		// edges
		for ( Object o1 : g1.getEdges() ) {
			InvenioEdge e1 = (InvenioEdge) o1;
			InvenioEdge e2 = null;
			String key = Util.getElementKey(e1);
			if ( !edgeMapG2.containsKey( key ) )
					throw new MissingElementException("Edge with ID=" + key + "not found in g2");
			e2 = (InvenioEdge) edgeMapG2.get( key );
			validate(e1, e2);
			
			InvenioEdge e = createInvenioEdge(e1, e2, g, nodeMapG);
			
			executeUnary( e1 );
			executeUnary( e2 );
			executeBinary( e1, e2, e);
		}
		return g;
	}
	
	protected void executeUnary(InvenioElement e) throws DataFormatException, OperatorExecutionException {
		for (UnaryOperator op : unaryOps) {
			op.execute( Util.getElementKey( e ), getContainer( e ) );
		}
	}
	
	protected void executeBinary(InvenioElement e1, InvenioElement e2, InvenioElement e)
				throws DataFormatException, OperatorExecutionException {
		for (BinaryOperator op : binaryOps) {
			op.execute( Util.getElementKey( e ), getContainer( e1 ),
					Util.getElementKey( e2 ), getContainer( e2 ), getContainer( e ));
		}
	}
	
	protected ComparableFeatureContainer getContainer(InvenioElement e) throws DataFormatException {
		if ( e.containsInvenioDatumKey( Constants.CONTAINER_ATTRIBUTE ) )
			return (ComparableFeatureContainer) e.getInvenioDatum( Constants.CONTAINER_ATTRIBUTE );
		else
			throw new MissingElementException(
					"Cannot find feature container for invenio element with id: " + Util.getElementKey(e));
	}
	
	protected void validate(InvenioGraph g1, InvenioGraph g2) throws DataFormatException, OperatorExecutionException {
		if (g1 == null || g2 == null)
			throw new ConfigurationException("Graphs cannot be null");
		
		if (g1.numVertices() != g2.numVertices())
			throw new DataFormatException("Graphs cannot be aligned: different number of vertices");
		
		if (g1.numEdges() != g2.numEdges())
			throw new DataFormatException("Graphs cannot be aligned: different number of edges");
	}
	
	protected void validate(InvenioEdge e1, InvenioEdge e2) throws DataFormatException, OperatorExecutionException {
		if (e1 == null || e2 == null)
			throw new ConfigurationException("One of the edges is null");
		
		String id11, id12, id21, id22;
		id11 = Util.getElementKey( e1.getSourceVertex() );
		id12 = Util.getElementKey( e1.getTargetVertex() );
		id21 = Util.getElementKey( e2.getSourceVertex() );
		id22 = Util.getElementKey( e2.getTargetVertex() );
		
		if (id11 == null || id12 == null || id21 == null || id22 == null)
			throw new DataFormatException("Id cannot be null");
		
		if ( !(id11.equals(id21) && id12.equals(id22)) &&
				! (id11.equals(id22) && id12.equals(id21)) )
			throw new DataFormatException(
					"Graphs cannot be aligned: edge with same id has different endpoints: " + Util.getElementKey(e1) );
		
	}
	
	protected InvenioGraph createInvenioGraph(InvenioGraph g1, InvenioGraph g2) {
		return new InvenioGraph("Compared_" + g1.getName() + "-" + g2.getName());
	}
	
	protected InvenioVertex createInvenioVertex(InvenioVertex v1, InvenioVertex v2, InvenioGraph g) {
		InvenioVertex v = new InvenioVertex();
		
		String nodeId = Util.getElementKey(v1);
		v.setKeyAttribute(Constants.DEFAULT_KEY_ATTRIBUTE);
		v.setKey(nodeId);
		v.setName(nodeId);
		v.setInfoLabel(nodeId);
		
		g.addVertex(v);
		
		DataMapper dm = new InvenioDataMapper(v);
		ComparableFeatureContainer c = new ComparableFeatureContainer(dm);
		v.addInvenioDatum(Constants.CONTAINER_ATTRIBUTE, c, UserData.SHARED);
		
		return v;
	}
	
	protected InvenioEdge createInvenioEdge(
			InvenioEdge e1, InvenioEdge e2, InvenioGraph g, Map<String, InvenioVertex> nodeMapG) throws MissingElementException {
		
		String fromId = Util.getElementKey( e1.getSourceVertex() );
		String toId = Util.getElementKey( e1.getTargetVertex() );
		String edgeId = Util.getElementKey(e1);
		if ( !nodeMapG.containsKey(fromId) || !nodeMapG.containsKey(toId))
			throw new MissingElementException("Cannot find vertices for edge with id: " + edgeId);
		
		InvenioVertex fromV = nodeMapG.get( fromId);
		InvenioVertex toV = nodeMapG.get( toId);
		InvenioEdge edge = new InvenioEdge(fromV, toV);
		
		edge.setKeyAttribute(Constants.DEFAULT_KEY_ATTRIBUTE);
		edge.setKey(edgeId);
		edge.setName(edgeId);
		edge.setInfoLabel(edgeId);
		
		g.addEdge(edge);
		
		DataMapper dm = new InvenioDataMapper(edge);
		ComparableFeatureContainer c = new ComparableFeatureContainer(dm);
		edge.addInvenioDatum(Constants.CONTAINER_ATTRIBUTE, c, UserData.SHARED);
		
		return edge;
	}
}