package invenio.wf.test;

import java.io.IOException;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.data.bridge.GraphTranslater;
import invenio.io.file.PajekFileReader;
import invenio.io.file.SparseGraphFileReader;
import invenio.operator.data.DataFormatException;
import invenio.visual.DisplayShape;
import invenio.visual.VisualGraphSession;
import invenio.wf.item.algorithm.cluster.EdgeBetweennessClusterer;
import invenio.wf.item.algorithm.cluster.KMeansClusterer2;
import invenio.wf.items.file.SimpleFileReader;
import invenio.wf.items.vis.clustering.ClusterLayoutManager;
import invenio.wf.items.vis.clustering.ClusteringResultController;
import invenio.wf.items.vis.clustering.ClusteringResultView;
import invenio.wf.items.vis.nodelabel.LabelResultController;
import invenio.wf.items.vis.nodelabel.LabelResultView;
import invenio.wf.util.IOUtils;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.dd.invenio.gia.NodeLabelExperiment;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class NodeLabelVisTest {
	public static final String PAJEK_FILE_NAME = "C:/Workspace/Invenio/invenio.main/DawsonCreekSeason1.net";
	
//	public static final String SPARSE_FILE_NAME = "dolphins_1990.sgf";
	public static final String SPARSE_FILE_NAME = "C:/Workspace/Invenio/invenio.main/dawsons_creek.sgf";
	
	public static final String SIMPLE_FILE_NAME = "C:/Workspace/Invenio/DolphinData/Karate/karate.txt";
	
	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.NODE.paper.tab";
	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.DIRECTED.cites-fixed.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.DIRECTED.cites-fixed.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.DIRECTED.cites-fixed.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.NODE.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/workflow/synthetic/synthetic.EDGE.tab";
	
	public static final String GRAPH_NAME = "Graph";
		
//	public static final String NODE_LABEL_CONFIG_FILE1 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE2 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment-neighbours.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE3 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-dolphinWithLocation/experiment-bayes.cfg";
	public static final String NODE_LABEL_CONFIG_FILE4 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-cora/experiment.cfg";
	public static final String NODE_LABEL_CONFIG_FILE5 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-cora/experiment-bayes.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE6 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-citeseer/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE7 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE8 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment-bayes.cfg";
//	public static final String NODE_LABEL_CONFIG_FILE9 = "C:/Workspace/Invenio/InvenioWorkflow/resource/SampleFiles/NodeLabelExperiment-synthetic/experiment-neighbours.cfg";
	
	 public static void main(String[] argv) {

		 	
	        // -- 1. load the data ------------------------------------------------
		 	InvenioGraph invGraph = getGraph();
		 	InvenioGraph invGraph1 = getGraph();
		 	InvenioGraph invGraph2 = getGraph();
	        
		 	// run node labeling twice
		 	InvenioGraph result1 = executeNodeLabeling(invGraph1, NODE_LABEL_CONFIG_FILE4);
		 	InvenioGraph result2 = executeNodeLabeling(invGraph2, NODE_LABEL_CONFIG_FILE5);
		 	
		 	// set up visualization
	        LabelResultController controller = setupVis(invGraph, result1, result2);
	        
	        
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
	 
	 private static LabelResultController setupVis(InvenioGraph gt, InvenioGraph g1, InvenioGraph g2) {
        LabelResultView shape = new LabelResultView();
        LabelResultController resultController = new LabelResultController();
        resultController.setShape(shape);
        shape.setController(resultController);
        resultController.updateModel(gt, g1, g2);
        
		return resultController;
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
//			invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
//			invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
		    
		    invGraph.setName(GRAPH_NAME);
		 } catch ( Exception e ) {
			 e.printStackTrace();
			 System.err.println("Error loading graph. Exiting...");
			 System.exit(1);
		 }
		 return invGraph;
	}

}


