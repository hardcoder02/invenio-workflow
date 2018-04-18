package invenio.visual;

import java.io.IOException;

import invenio.action.layout.MarkovLayout;
import invenio.data.InvenioGraph;
import invenio.data.bridge.GraphTranslater;
import invenio.io.file.SparseGraphFileReader;

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
import prefuse.action.layout.graph.RadialTreeLayout;
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

public class Example {
	 public static void test(String[] argv) {
	        
	        // -- 1. load the data ------------------------------------------------
	        
	        // load the socialnet.xml file. it is assumed that the file can be
	        // found at the root of the java classpath
	        Graph graph = null;
	        try {
	        	InvenioGraph invGraph = readFromSparseGraphFile();
	        	graph = GraphTranslater.translate(invGraph, GraphTranslater.FULL_TRANSLATION);
//	            graph = new GraphMLReader().readGraph("/socialnet.xml");
	        } catch ( Exception e ) {
	            e.printStackTrace();
	            System.err.println("Error loading graph. Exiting...");
	            System.exit(1);
	        }
	        
	        
	        // -- 2. the visualization --------------------------------------------
	        
	        // add the graph to the visualization as the data group "graph"
	        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
	        Visualization vis = new Visualization();
	        vis.add("graph", graph);
	        vis.setInteractive("graph.edges", null, false);
	        
	        // -- 3. the renderers and renderer factory ---------------------------
	        
	        // draw the "name" label for NodeItems
//	        LabelRenderer r = new LabelRenderer("name");
//	        r.setRoundedCorner(8, 8); // round the corners
	        ShapeRenderer r = new ShapeRenderer(); 
	        
	        // create a new default renderer factory
	        // return our name label renderer as the default for all non-EdgeItems
	        // includes straight line edges for EdgeItems by default
	        vis.setRendererFactory(new DefaultRendererFactory(r));
	        
	        
	        // -- 4. the processing actions ---------------------------------------
	        
	        // create our nominal color palette
	        // pink for females, baby blue for males
	        int[] palette = new int[] {
	            ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255)
	        };
	        // map nominal data values to colors using our provided palette
//	        DataColorAction fill = new DataColorAction("graph.nodes", "gender",
//	                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
	        ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
//	        ColorAction fill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.gray(200));
	        ColorAction fill = new RandomColorAction("graph.nodes", "", Constants.NOMINAL, VisualItem.FILLCOLOR);
	        // use black for node text
	        ColorAction text = new ColorAction("graph.nodes",
	                VisualItem.TEXTCOLOR, ColorLib.gray(0));
	        // use light grey for edges
	        ColorAction edges = new ColorAction("graph.edges",
	                VisualItem.STROKECOLOR, ColorLib.gray(200));
	        
	        // create an action list containing all color assignments
	        ActionList color = new ActionList();
	        color.add(stroke);
	        color.add(fill);
	        color.add(text);
	        color.add(edges);
	        
	        // create an action list with an animated layout
//	        ActionList layout = new ActionList(Activity.INFINITY);
	        ActionList layout = new ActionList(2000);
	        layout.add(new ForceDirectedLayout("graph"));
	        layout.add(new RepaintAction());
	        
	        // add the actions to the visualization
	        vis.putAction("color", color);
	        vis.putAction("layout", layout);
	        
	        
	        // -- 5. the display and interactive controls -------------------------
	        
	        Display d = new Display(vis);
	        d.setSize(720, 500); // set display size
	        // drag individual items around
	        d.addControlListener(new DragControl());
	        // pan with left-click drag on background
	        d.addControlListener(new PanControl()); 
	        // zoom with right-click drag
	        d.addControlListener(new ZoomControl());
	        
	        // -- 6. launch the visualization -------------------------------------
	        
	        // create a new window to hold the visualization
	        JFrame frame = new JFrame("prefuse example");
	        // ensure application exits when window is closed
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(d);
	        frame.pack();           // layout components in window
	        frame.setVisible(true); // show the window
	        
	        // assign the colors
	        vis.run("color");
	        // start up the animated layout
	        vis.run("layout");
	        
	    }
	
	
    public static void main(String[] argv) {
        
        // -- 1. load the data ------------------------------------------------
        
        // load the socialnet.xml file. it is assumed that the file can be
        // found at the root of the java classpath
        Graph graph = null;
        try {
        	InvenioGraph invGraph = readFromSparseGraphFile();
			graph = GraphTranslater.translate(invGraph, 0);
//            graph = new GraphMLReader().readGraph("/socialnet.xml");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println("Error loading graph. Exiting...");
            System.exit(1);
        }
        
//        TupleSet nodes = graph.getNodes();
//        Iterator i = nodes.tuples();
//        while (i.hasNext()) {
//        	BridgeTuple t = (BridgeTuple) i.next();
//        	t.
//        }
        // -- 2. the visualization --------------------------------------------
        
        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
        Visualization vis = new Visualization();
        vis.add("graph", graph);
        vis.setInteractive("graph.edges", null, false);
        
        // -- 3. the renderers and renderer factory ---------------------------
        
        // draw the "name" label for NodeItems
        LabelRenderer r = new LabelRenderer("primary_location_id");
        r.setRoundedCorner(8, 8); // round the corners
//        ShapeRenderer r = new ShapeRenderer();

        
        // create a new default renderer factory
        // return our name label renderer as the default for all non-EdgeItems
        // includes straight line edges for EdgeItems by default
        vis.setRendererFactory(new DefaultRendererFactory(r));
        
        
        // -- 4. the processing actions ---------------------------------------
        
        RandomVisibilityAction visibility = new RandomVisibilityAction("graph.nodes", "primary_location_id");
        
        
        // create our nominal color palette
        // pink for females, baby blue for males
        int[] palette = new int[] {
            ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255)
        };
        // map nominal data values to colors using our provided palette
//        DataColorAction fill = new DataColorAction("graph.nodes", "gender",
//                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
        ColorAction fill = new RandomColorAction("graph.nodes", "sex_code", Constants.NODE_AND_EDGE_TRAVERSAL, VisualItem.FILLCOLOR);
        // use black for node text
        ColorAction text = new ColorAction("graph.nodes",
                VisualItem.TEXTCOLOR, ColorLib.gray(0));
        // use light grey for edges
        ColorAction edges = new ColorAction("graph.edges",
                VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        // create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(fill);
        color.add(stroke);
        color.add(text);
        color.add(edges);
        
        // create an action list with an animated layout
        ActionList layout = new ActionList(2000);
        layout.add(new ForceDirectedLayout("graph"));
//        layout.add(new FixedCircleLayout("graph.nodes"));
//        layout.add(new RadialTreeLayout("graph"));
//        layout.add(new MarkovLayout("graph.nodes"));
        layout.add(new RepaintAction());
        
        // add the actions to the visualization
        vis.putAction("visibility", visibility);
        vis.putAction("color", color);
        vis.putAction("layout", layout);
        
        
        // -- 5. the display and interactive controls -------------------------
        
        Display d = new Display(vis);
        d.setSize(720, 500); // set display size
        // drag individual items around
        d.addControlListener(new DragControl());
        // pan with left-click drag on background
        d.addControlListener(new PanControl()); 
        // zoom with right-click drag
        d.addControlListener(new ZoomControl());
        
        // -- 6. launch the visualization -------------------------------------
        
        // create a new window to hold the visualization
        JFrame frame = new JFrame("prefuse example");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(d);
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
        
        
//        vis.run("visibility");
        // assign the colors
        vis.run("color");
        // start up the animated layout
        vis.run("layout");
    }
    
    public static InvenioGraph readFromSparseGraphFile() throws IOException {
		SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_1990.sgf");
		
		InvenioGraph invGraph = reader.readGraph();
		reader.close();
		return invGraph;
    }
}


