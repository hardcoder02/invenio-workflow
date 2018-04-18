package invenio.wf.test;

import java.io.IOException;

import invenio.data.InvenioGraph;
import invenio.data.bridge.GraphTranslater;
import invenio.io.file.SparseGraphFileReader;
import invenio.operator.data.DataFormatException;
import invenio.visual.DisplayShape;
import invenio.visual.VisualGraphSession;
import invenio.wf.items.vis.clustering.ClusterLayoutManager;

import javax.swing.JFrame;

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

public class SimpleVisTest {
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.DIRECTED.cites-fixed.tab";
	
	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.NODE.paper.tab";
	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.DIRECTED.cites-fixed.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.NODE.dolphin.gt.small.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.UNDIRECTED.seenwith.small.tab";
	
	public static final String GRAPH_NAME = "Graph";

	
	 public static void main(String[] argv) {
	        
	        // -- 1. load the data ------------------------------------------------
	        
	        // load the socialnet.xml file. it is assumed that the file can be
	        // found at the root of the java classpath
	        Graph graph = null;
	        InvenioGraph invGraph = null;
	        try {
//	        	invGraph = readFromSparseGraphFile();
	        	invGraph = readFromTabFile();
	        	
	        } catch ( Exception e ) {
	            e.printStackTrace();
	            System.err.println("Error loading graph. Exiting...");
	            System.exit(1);
	        }
	        
	        DisplayShape shape = new DisplayShape();
	        VisualGraphSession resultController = new VisualGraphSession();
	        resultController.setShape(shape);
	        shape.setController(resultController);
	        resultController.updateModel(invGraph);
	        
	        
	        
	        // -- 6. launch the visualization -------------------------------------
	        
	        // create a new window to hold the visualization
	        JFrame frame = new JFrame("prefuse example");
	        // ensure application exits when window is closed
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(resultController.getDisplaySession());
	        frame.pack();           // layout components in window
	        frame.setVisible(true); // show the window
	        
	    }
	
	
    public static InvenioGraph readFromSparseGraphFile() throws IOException {
		SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_1990.sgf");
		
		InvenioGraph invGraph = reader.readGraph();
		reader.close();
		return invGraph;
    }
    
	public static InvenioGraph readFromTabFile() throws IOException, DataFormatException	{
		InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, NODE_FILE, EDGE_FILE);
		g.setName( GRAPH_NAME );
		return g;
	}
}


