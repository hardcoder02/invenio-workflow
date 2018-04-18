package invenio.visual;

import invenio.algorithms.util.AlgorithmManager;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.data.bridge.GraphTranslater;
import invenio.event.GraphSessionEvent;
import invenio.event.GraphSessionListener;
import invenio.event.InvenioGraphEvent;
import invenio.event.InvenioGraphListener;
import invenio.ui.controller.MainFrameController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.event.EventListenerList;

import prefuse.Visualization;
import prefuse.data.expression.Predicate;
import prefuse.util.ui.JPrefuseTable;
import prefuse.visual.expression.InGroupPredicate;

/**
 * The GraphSession class stores all the data that is used in managing a graph
 * that is eventually shown to and manipulated by a user.  In doing this, the
 * GraphSession needs to have control of an {@link InvenioGraph}, a 
 * corresponding {@link prefuse.data.Graph} and {@link JPrefuseTable}, the
 * graph's {@link GraphSelection} and {@link GraphPainter}, an {@link 
 * AlgorithmManager} for the graph, and lists of the graph's visual change
 * history and algorithm history.
 */
public class GraphSession implements InvenioGraphListener, Serializable {

//	protected HashMap<InvenioVertex, Integer> indexer = new  HashMap<InvenioVertex, Integer>();
	protected InvenioGraph m_invenioGraph;
	protected prefuse.data.Graph m_prefuseGraph;
	protected Visualization m_visualization;

//	protected VisualGraphSession controller;
	
//	protected GraphSelection m_graphSelection;
//	protected Predicate m_visualGraphPredicate;
	
//	protected int m_translationType;
//	protected JPrefuseTable m_prefuseTableComponent;
//	protected String rGraphName;

	
	public GraphSession() {
		
	}

	/**
	 * @return the InvenioGraph that this GraphSession is controlling.
	 */
	public InvenioGraph getGraph() {
		return m_invenioGraph;
	}
	
	/**
	 * @return the {@link prefuse.data.Graph} that corresponds to the 
	 * {@link InvenioGraph} that this GraphSession is controlling.
	 */
	public prefuse.data.Graph getPrefuseGraph() {
		return m_prefuseGraph;
	}
	
	public Visualization getVisualization() {
		return m_visualization;
	}
	
	public void setInvenioGraph(InvenioGraph invenioGraph) {
		this.m_invenioGraph = invenioGraph;
	}

	public void setPrefuseGraph(prefuse.data.Graph prefuseGraph) {
		this.m_prefuseGraph = prefuseGraph;
	}

	public void setVisualization(Visualization visualization) {
		this.m_visualization = visualization;
	}


	
	
	/*
	public GraphSession(InvenioGraph graph, int translationType) {
		try {
			m_invenioGraph = graph;
//			m_invenioGraph.setGraphSession(this);
	
			m_prefuseGraph = GraphTranslater.translate(m_invenioGraph, translationType);
//			if (m_invenioGraph.hasRGraph())
//				createRGraph();
//			createIndex();
	
//			System.out.println ("Created R Graph!");
			
	//		m_graphSelection.addGraphSessionListener(m_graphPainter);
	
//			m_prefuseTableComponent = new JPrefuseTable(m_prefuseGraph.getNodeTable());
			
			m_invenioGraph.addGraphChangeListener(this);
			listenerList = new EventListenerList();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public GraphSession(InvenioGraph graph) {
		this(graph, GraphTranslater.FULL_TRANSLATION);
	}

	
//	public void setController(VisualGraphSession newController) {
//		controller = newController;
//		m_graphSelection = new GraphSelection();
//		m_visualGraphPredicate = new InGroupPredicate( );
//	}

//	public Predicate getVisualGraphPredicate() {
//		return m_visualGraphPredicate;
//	}
	
*/
/*
	
    protected void createRGraph() {
//    	try {
//            Rengine RE = Invenio.RE;
//
//            rGraphName = "g" + m_invenioGraph.hashCode() + "";
//            Invenio.RE.eval(rGraphName + " = graph.empty(directed=" + (m_invenioGraph.isDirected() ? "TRUE" : "FALSE") + ")");
//            //Invenio.RE.eval(rGraphName + " = graph.empty(directed=" + (m_graph.isDirected() ? "TRUE" : "FALSE") + ")");
//            int numVertices = 0;
//
//            //Add vertices to the R graph
//            Iterator vIter = m_invenioGraph.getVertices().iterator();
//            while (vIter.hasNext()) {
//                InvenioVertex v = (InvenioVertex) vIter.next();
//                //Add vertex to the R graph
//                RE.eval(rGraphName + " = add.vertices(" + rGraphName + ", 1)");
//                if (numVertices == 0) RE.eval("V(" + rGraphName + ")$name= '" + v.getID() + "'");
//                RE.eval("V(" + rGraphName + ")$name[" + (++numVertices) + "] = '" + v.getID() + "'");
//                //System.out.println(rGraphName + " = add.vertices(" + rGraphName + ", 1)");
//                //System.out.println("V(" + rGraphName + ")$name[" + (numVertices) + "] = '" + v.getID() + "'");
//
//            }
//            System.out.println ("Done adding vertices");
//            boolean weighted = false;
//            boolean first = true;
//            int edgeCount = 0;
//            int accCount = 0;
//            boolean bulk = false;
//            String addEdgeCmd = ""; 
//            //Add edges to the R graph
//            Iterator eIter = m_invenioGraph.getEdges().iterator();
//            
//            //See if you need to add edges in bulk operations
//            if (m_invenioGraph.getEdges().size() > 5000) {
//            	int factor = m_invenioGraph.getEdges().size()/50;
//            	 while (eIter.hasNext()) {
// 	                Edge e = (Edge) eIter.next();
// 	               
// 	                UndirectedSparseEdge edg = (UndirectedSparseEdge) e;
// 	            	if (first && (edg instanceof InvenioEdge) && ((InvenioEdge) edg).hasWeight()) weighted = true;
// 	            		
// 	                Iterator it = e.getIncidentVertices().iterator();
// 	                InvenioVertex v1 = null, v2 = null;
// 	                try {
// 		                v1 = (InvenioVertex) it.next();
// 		                v2 = (InvenioVertex) it.next();
// 	                } catch (Exception ex) {
// 	                	v2 = v1;
// 	                	//System.out.println ("Cannot find 2 vertices for edge: " + e + "\n Error:" + ex);
// 	                }
// 	
// 	                //Get the indices of the vertices to add
// 	                int vIndex1 = (RE.eval("which(V(" + rGraphName + ")$name == '" + v1.getID() + "')").asInt()) - 1;
// 	                int vIndex2 = (RE.eval("which(V(" + rGraphName + ")$name == '" + v2.getID() + "')").asInt()) - 1;
// 	
// 	               
// 	                if (accCount == 0) addEdgeCmd = rGraphName + "= add.edges(" + rGraphName + ", c(";
// 	                else addEdgeCmd += ",";
// 	               
// 	                addEdgeCmd += vIndex1 + "," + vIndex2;
// 	                accCount++;
// 	                
// 	                if (accCount >= factor) {
// 	                	accCount = 0;
// 	                	 //Add edge to the R graph
// 	                	addEdgeCmd += "))";
// 	                	RE.eval ( addEdgeCmd );
// 	                	//System.out.println (addEdgeCmd );
// 	   	               
// 	                }
// 	                //if (weighted) //Add edge attribute for weight
// 	                //	RE.eval(rGraphName + " = set.edges.attribute(" + rGraphName + ", 'weight', " + edgeCount + ":" + edgeCount + ", " + 
// 	                //			(edg instanceof InvenioEdge ? ((InvenioEdge) edg).getWeight() : 0)  + ")");
// 	                
// 	                edgeCount++;
// 	               
// 	            }
//            	
//            	 if (accCount >0) {
//            		 addEdgeCmd += "))";	 
//            		//Add edge to the R graph
//	                RE.eval ( addEdgeCmd );
//	                //System.out.println (addEdgeCmd + "-" + edgeCount);	   	               
//	             }
//            } else {
//	            while (eIter.hasNext()) {
//	                Edge e = (Edge) eIter.next();
//	               
//	                UndirectedSparseEdge edg = (UndirectedSparseEdge) e;
//	                
//	            	if (first && (edg instanceof InvenioEdge) && ((InvenioEdge) edg).hasWeight()) weighted = true;
//	            		
//	                Iterator it = e.getIncidentVertices().iterator();
//	                InvenioVertex v1 = null, v2 = null;
//	                try {
//		                v1 = (InvenioVertex) it.next();
//		                v2 = (InvenioVertex) it.next();
//	                } catch (Exception ex) {
//	                	v2 = v1;
//	                	//System.out.println ("Cannot find 2 vertices for edge: " + e + "\n Error:" + ex);
//	                }
//	
//	                //Get the indices of the vertices to add
//	                int vIndex1 = (RE.eval("which(V(" + rGraphName + ")$name == '" + v1.getID() + "')").asInt()) - 1;
//	                int vIndex2 = (RE.eval("which(V(" + rGraphName + ")$name == '" + v2.getID() + "')").asInt()) - 1;
//	
//	                addEdgeCmd = rGraphName + "= add.edges(" + rGraphName + ", c(" + vIndex1 + "," + vIndex2 + "))";
//	                //if (weighted) //Add edge attribute for weight
//	                //	RE.eval(rGraphName + " = set.edges.attribute(" + rGraphName + ", 'weight', " + edgeCount + ":" + edgeCount + ", " + 
//	                //			(edg instanceof InvenioEdge ? ((InvenioEdge) edg).getWeight() : 0)  + ")");
//	                
//	                edgeCount++;
//	                //System.out.println (addEdgeCmd );
//	                //Add edge to the R graph
//	                RE.eval ( addEdgeCmd );
//	            }
//    		}
//            System.out.println ("Done adding edges");
//            //RE.eval("print(length(V(" + rGraphName +")))");
//            //RE.eval("print(length(E(" + rGraphName +")))");
//            //RE.eval("plot(" + rGraphName +")");
//    	} catch (Exception e) {
//    		System.out.print("Error in createGraph(): " + e);
//    	}

    }
	
//	public void addVisualGraphSession(VisualGraphSession visSession){
//		m_visualGraphSessions.add(visSession);
//		m_graphSelection.addGraphSessionListener(visSession.getGraphPainter());
//	}
	
	
	
	/**
	 * @return the current {@link GraphSelection}, i.e. all the nodes and edges
	 * that are currently selected in this GraphSession's graph, as represented
	 * by the {@link GraphSelection} class.
	 * 
	 * @see GraphSelection
	 */
//	public GraphSelection getGraphSelection() {
//		return m_graphSelection;
//	}


	/**
	 * @return the name of this GraphSession
	 */
//	public String getName() {
//		return m_invenioGraph.getName();
//	}
	
	/**
	 * Synchronizes this GraphSession's {@link prefuse.data.Graph} with the
	 * {@link InvenioGraph} it controls.
	 */
//	public void refreshPrefuseGraph() {
//		ImportPrefuseGraph.refreshPrefuseGraph(m_prefuseGraph, m_invenioGraph);
//		UserSession.getInstance().getVisualization().repaint();
//	}

	
	/**
	 * This method fulfills the {@link InvenioGraphListener} interface that the
	 * GraphSession implements.  The method checks to see if sets in the graph
	 * have changed, in which case it will fire an appropriate {@link
	 * GraphSessionEvent} to the {@link MainFrameController}.  It will then 
	 * cause a refresh of the Prefuse graph using {@link 
	 * #refreshPrefuseGraph()}.
	 * 
	 * @see #refreshPrefuseGraph()
	 */
	public void graphChanged(InvenioGraphEvent e) {
//		if(e.changedSets())
//			fireGraphSessionEvent(new GraphSessionEvent(this, GraphSessionEvent.GRAPH_SETS_CHANGED));
//		refreshPrefuseGraph();
	}

    	
	//------------------------ GraphSessionListener methods -----------------------------
	
	protected EventListenerList listenerList;
    
	/**
	 * Specify a listener class that will monitor this GraphSession for
	 * {@link GraphSessionEvent}s that are fired.  The listener for this needs
	 * to be a {@link GraphSessionListener}, which is currently implemented
	 * by the {@link MainFrameController} class. 
	 * 
	 * @param listener the listener that will listen to this GraphSession for
	 * {@link GraphSessionEvent}s.
	 */
	public void addGraphSessionListener(GraphSessionListener listener) {
		listenerList.add(GraphSessionListener.class, listener);
	}

	/**
	 * Remove a {@link GraphSessionListener} from this GraphSession.
	 * 
	 * @param listener the {@link GraphSessionListener} to remove.
	 */
	public void removeGraphSessionListener(GraphSessionListener listener) {
		listenerList.remove(GraphSessionListener.class, listener);
	}
    
	/**
	 * If a {@link GraphSessionEvent} has been fired by one of the methods
	 * in this class (i.e. they invoke this method), this method will handle it 
	 * by invoking the graphSessionChanged(...) method of all the current 
	 * listeners of this class. 
	 * 
	 * @param evt the {@link GraphSessionEvent} that has transpired and needs
	 * to be handled by all the listeners of this GraphSession.
	 */
	private void fireGraphSessionEvent(GraphSessionEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i+=2) {
			if (listeners[i] == GraphSessionListener.class) {
				((GraphSessionListener)listeners[i+1]).graphSessionChanged(evt);
			}
		}
	}
	
//    public String getRGraphName() {
//        return rGraphName;
//    }	
    
//    protected void createIndex () {
//    	indexer.clear();
//
//    	InvenioGraph g = getGraph();
//    	Iterator it = g.getVertices().iterator();
//    	while (it.hasNext()) {
//    		InvenioVertex iv = (InvenioVertex) it.next();
//    		indexer.put(iv, iv.degree());
//    	}
//    }

}
