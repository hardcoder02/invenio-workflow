package invenio.wf.items.vis.clustering.compare;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.visual.LabelRenderer;
import invenio.visual.VisualGraphSession;
import invenio.wf.items.vis.clustering.ClusterLayoutManager;
import invenio.wf.items.vis.clustering.ElementSetsColorAction;
import invenio.wf.items.vis.clustering.ElementSetsPredicate;
import invenio.wf.items.vis.clustering.ElementSetsVisibilityAction;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class ClusteringCompareGraphSession extends VisualGraphSession {
	private GroupingAlgorithmResult grouping;
	
	// color
    private ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
    private ElementSetsColorAction fill = new ElementSetsColorAction("graph.nodes", VisualItem.FILLCOLOR);
    private ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
    private ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
    
    private ElementSetsPredicate predicate = new ElementSetsPredicate();

    public ClusteringCompareGraphSession() {
    	setLayoutManager(new ClusterLayoutManager());
    	LabelRenderer r = new invenio.visual.LabelRenderer("id");
//    	prefuse.render.LabelRenderer r = new prefuse.render.LabelRenderer("jung.io.PajekNetReader.LABEL");
    	r.setRoundedCorner(8, 8); // round the corners
    	setRenderer( r );
    }
    
	protected void resetPainterToDefault(){
		// create an action list containing all color assignments
        painter.putAction("stroke", stroke);
//        painter.putAction("fill", fill);
        painter.putAction("text", text);
        painter.putAction("edges", edges);
//        painter.putAction("enlarge", new SizeAction("graph.nodes", 3));
	}
	
	public ElementSetsColorAction createElementSetsFillAction() {
		return new ElementSetsColorAction("graph.nodes", VisualItem.FILLCOLOR);
	}
	
	public ElementSetsVisibilityAction createElementSetsVisibilityAction() {
		return new ElementSetsVisibilityAction("graph");
	}
	
	
	protected Object getExtraObject() {
		return grouping;
	}
	
//	public void repaintColor() {
//		getVisualization().removeAction("color");
//		getVisualization().putAction("color", color);
//		getVisualization().run("color");
//		getVisualization().run("repaint");
//	}
//
//	public void repaintVisibility() {
//		getVisualization().removeAction("visibility");
//		getVisualization().putAction("visibility", visibility);
//		getVisualization().run("visibility");
//		
////		getVisualization().removeAction("layout");
////		getVisualization().putAction("layout", layout);
////		getVisualization().run("layout");
//		
//		getVisualization().run("repaint");
//		
//	}

	public ElementSetsPredicate getElementSetsPredicate() {
		return predicate;
	}
	
	public void setElementSetsPredicate(ElementSetsPredicate p) {
		this.predicate = p;
		getDisplaySession().getDisplay().setPredicate(p);
	}

	public void setGrouping(GroupingAlgorithmResult grouping) {
		this.grouping = grouping;		
	}
}
