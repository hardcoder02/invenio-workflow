package invenio.wf.test;

import invenio.data.InvenioGraph;
import invenio.wf.util.IOUtils;

import com.dd.invenio.gia.NodeLabelExperiment;

public class NodeLabelTest {
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
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.NODE.tab";
	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.EDGE.tab";
	
	public static final String GRAPH_NAME = "Graph";
		
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment-neighbours.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment-bayes.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-cora/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-citeseer/experiment.cfg";
	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment-bayes.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE = "C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment-neighbours.cfg";
	
	 public static void main(String[] argv) {
		 	
		 	
	        // -- 1. load the data ------------------------------------------------
		 	InvenioGraph invGraph = getGraph();
//		 	InvenioGraph invGraph1 = getGraph();
//		 	InvenioGraph invGraph2 = getGraph();
	        
		 	// run node labeling twice
//		 	InvenioGraph result1 = executeNodeLabeling(invGraph1, NODE_LABEL_CONFIG_FILE1);
//		 	InvenioGraph result2 = executeNodeLabeling(invGraph1, NODE_LABEL_CONFIG_FILE2);
		 	
//		 	// set up visualization
//	        LabelResultController controller = setupVis(invGraph, result1, result2);
//	        
//	        
//	        // -- 6. launch the visualization -------------------------------------
//	        showFrame(controller.getShape());
	        
		 	
		 	InvenioGraph result = executeNodeLabeling(invGraph, NODE_LABEL_CONFIG_FILE);
		 	System.out.println("result is:" + result);
	 }
	
	 private static InvenioGraph executeNodeLabeling(InvenioGraph invGraph, String configFile) {
		NodeLabelExperiment e = new NodeLabelExperiment();
		e.setInputGraph(invGraph);
		e.setParametersFile( configFile );
		e.runExperiment();
	 	return e.getOutputGraph();
	 }

	public static InvenioGraph getGraph() {
		 InvenioGraph invGraph = null;
		 try {
//			invGraph = IOUtils.readFromSparseGraphFile( SPARSE_FILE_NAME );
		    invGraph = IOUtils.readFromTabFile( NODE_FILE, EDGE_FILE );
//		    invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
//		    invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
		    
		    invGraph.setName(GRAPH_NAME);
		 } catch ( Exception e ) {
			 e.printStackTrace();
			 System.err.println("Error loading graph. Exiting...");
			 System.exit(1);
		 }
		 return invGraph;
	}

}


