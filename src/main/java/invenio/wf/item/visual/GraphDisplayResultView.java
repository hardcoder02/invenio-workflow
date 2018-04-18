package invenio.wf.item.visual;

import invenio.GraphSession;
import invenio.UserSession;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.data.bridge.GraphTranslater;
import invenio.main.TestMainFrameController;
import invenio.ui.MainFrame;
import invenio.ui.controller.MainFrameController;
import invenio.visual.RandomColorAction;
import invenio.wf.items.file.FileImportController;
import invenio.wf.items.query.QueryController;
import invenio.wf.items.vis.query.QueryResultButtonPanel;
import invenio.wf.items.vis.query.QueryResultPanel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledQuery;
import qng.tabular.Table;

public class GraphDisplayResultView extends JPanel {
	
	private GraphDisplayController controller;
	
	private JTabbedPane resultTabbedPane;
	private JPanel graphPane;

	private GraphSession lastGraphSession;
	private JComponent lastDisplay;
	
	public GraphDisplayResultView() {
		init();
	}
	
	protected void setController(GraphDisplayController newController) {
		if (controller != null) {
			// cleanup
		}
		controller = newController;
		if (controller != null) {
			// setup
			// TODO: manage QueryResultButtonPanel's controller
		}
	}
	
	protected void init() {
//		add( getResultTabbedPane() );

		setPreferredSize(new Dimension(1200, 900));
//		setLocation(200, 100);
	}
	
	protected JTabbedPane getResultTabbedPane() {
		if ( resultTabbedPane == null ) {
			resultTabbedPane = new JTabbedPane();
		}
		
		return resultTabbedPane;
	}
	
	protected void addGraph(InvenioGraph g) {
		
		if (lastDisplay != null)
			remove(lastDisplay);
		
		lastDisplay = createResultPane(g);
		add( lastDisplay );
		revalidate();
		repaint();
		
	}
	
	protected JComponent createResultPane(InvenioGraph g) {
		Graph graph = null;
        try {
        	graph = GraphTranslater.translate(g, GraphTranslater.FULL_TRANSLATION);
//            graph = new GraphMLReader().readGraph("/socialnet.xml");
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        
        
        // -- 2. the visualization --------------------------------------------
        
        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
        Visualization vis = new Visualization();
        vis.add("graph", graph);
        vis.setInteractive("graph.edges", null, false);
        
        // -- 3. the renderers and renderer factory ---------------------------
        
        // draw the "name" label for NodeItems
//        LabelRenderer r = new LabelRenderer("name");
//        r.setRoundedCorner(8, 8); // round the corners
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
//        DataColorAction fill = new DataColorAction("graph.nodes", "gender",
//                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
//        ColorAction fill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.gray(200));
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
//        ActionList layout = new ActionList(Activity.INFINITY);
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
        
        
        // assign the colors
        vis.run("color");
        // start up the animated layout
        vis.run("layout");
        return d;
	}
	
//	protected JPanel createResultPane(InvenioGraph g) {
//		UserSession uSession = UserSession.getInstance();
//		lastGraphSession = new GraphSession(g);
//		//uSession.addAndActivateGraphSessionInNewDisplay(lastGraphSession);
//		uSession.addAndActivateGraphSession(lastGraphSession);
//		return TestMainFrameController.getInstance().getGraphPanel();
//	}
	
	protected void removeGraph() {
//		if (lastGraphSession != null) {
//			UserSession uSession = UserSession.getInstance();
//			//uSession.removeGraphSession(lastGraphSession);
//			getResultTabbedPane().revalidate();
//			getResultTabbedPane().repaint();
//		}
		
	}
}
