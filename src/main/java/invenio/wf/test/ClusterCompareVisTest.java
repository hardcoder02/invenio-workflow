package invenio.wf.test;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.wf.item.algorithm.cluster.EdgeBetweennessClusterer;
import invenio.wf.item.algorithm.cluster.KMeansClusterer2;
import invenio.wf.items.vis.clustering.compare.ClusteringCompareResultController;
import invenio.wf.items.vis.clustering.compare.ClusteringCompareResultView;
import invenio.wf.util.IOUtils;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ClusterCompareVisTest {
	public static final String PAJEK_FILE_NAME = "C:/Workspace/Invenio/invenio.main/DawsonCreekSeason1.net";
	
//	public static final String SPARSE_FILE_NAME = "dolphins_1990.sgf";
	public static final String SPARSE_FILE_NAME = "C:/Workspace/Invenio/invenio.main/dawsons_creek.sgf";

	public static final String SIMPLE_FILE_NAME = "C:/Workspace/Invenio/DolphinData/Karate/karate.txt";

//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.DIRECTED.cites-fixed.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.DIRECTED.cites-fixed.tab";	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.DIRECTED.cites-fixed.tab";
	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.NODE.dolphin.gt.small.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.UNDIRECTED.seenwith.small.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.NODE.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.EDGE.tab";
	
	public static final String GRAPH_NAME = "Graph";
			
	 public static void main(String[] argv) {

		 	
	        // -- 1. load the data ------------------------------------------------
		 	InvenioGraph invGraph = getGraph();
	        
		 	// run clustering
		 	GroupingAlgorithmResult result1 = executeKMeans(invGraph);
		 	GroupingAlgorithmResult result2 = executeEdgeBetweenness(invGraph);
		 	
		 	// set up visualization
	        ClusteringCompareResultController controller = setupVis(result1, result2);
	        
	        
	        // -- 6. launch the visualization -------------------------------------
	        showFrame(controller.getShape());
	        
	 }
	
	 
	 private static void showFrame( JComponent shape ) {
		// create a new window to hold the visualization
	    JFrame frame = new JFrame("prefuse example");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( shape );
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
	 }
	 
	 
	 private static GroupingAlgorithmResult executeEdgeBetweenness(InvenioGraph invGraph) {
		EdgeBetweennessClusterer clusterer = new EdgeBetweennessClusterer();
		
		Object[] parameters = new Object[2];
		parameters[0] = 20;
		parameters[1] = "cluster";
		
		clusterer.setGraph(invGraph);
	 	clusterer.setParameters(parameters);
	 	
	 	return (GroupingAlgorithmResult) clusterer.execute();
	 }
	 
	 
	 private static GroupingAlgorithmResult executeKMeans(InvenioGraph invGraph) {
		KMeansClusterer2 clusterer = new KMeansClusterer2();
		
		Object[] parameters = new Object[4];
		parameters[0] = 100;
		parameters[1] = 0.1;
		parameters[2] = 2;
		parameters[3] = "cluster";
		
		clusterer.setGraph(invGraph);
	 	clusterer.setParameters(parameters);
	 	
	 	return (GroupingAlgorithmResult) clusterer.execute();
	 }
	 
	 
	 private static ClusteringCompareResultController setupVis(GroupingAlgorithmResult r1, GroupingAlgorithmResult r2) {
        ClusteringCompareResultView shape = new ClusteringCompareResultView();
        ClusteringCompareResultController resultController = new ClusteringCompareResultController();
        resultController.setShape(shape);
        shape.setController(resultController);
        resultController.updateModel(r1, r2);
        
		return resultController;
	}




	public static InvenioGraph getGraph() {
		 InvenioGraph invGraph = null;
	     try {
//			invGraph = IOUtils.readFromSparseGraphFile( SPARSE_FILE_NAME );
//		    invGraph = IOUtils.readFromTabFile( NODE_FILE, EDGE_FILE );
//			invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
			invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
		    
		    invGraph.setName(GRAPH_NAME);
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         System.err.println("Error loading graph. Exiting...");
	         System.exit(1);
	     }
	     return invGraph;
	 }
}


