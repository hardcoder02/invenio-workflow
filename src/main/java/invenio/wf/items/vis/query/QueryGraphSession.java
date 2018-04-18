package invenio.wf.items.vis.query;

import invenio.visual.LabelRenderer;
import invenio.visual.VisualGraphSession;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class QueryGraphSession extends VisualGraphSession {
	
	// color
    private ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
    private TempBehaviorColorAction fill = new TempBehaviorColorAction("graph.nodes", VisualItem.FILLCOLOR);
    private ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
    private ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
    

    public QueryGraphSession() {
//    	setLayoutManager(new ClusterLayoutManager());
//    	LabelRenderer r = new invenio.visual.LabelRenderer("id");
//    	prefuse.render.LabelRenderer r = new prefuse.render.LabelRenderer("jung.io.PajekNetReader.LABEL");
    	LabelRenderer r = new LabelRenderer("id");
    	r.setRoundedCorner(8, 8); // round the corners
    	setRenderer( r );
    }
    
	protected void resetPainterToDefault(){
		// create an action list containing all color assignments
        painter.putAction("stroke", stroke);
        painter.putAction("fill", fill);
        painter.putAction("text", text);
        painter.putAction("edges", edges);
//        painter.putAction("enlarge", new SizeAction("graph.nodes", 3));
	}
	

}
