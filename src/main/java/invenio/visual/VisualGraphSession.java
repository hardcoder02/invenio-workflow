package invenio.visual;

import invenio.data.InvenioGraph;
import invenio.data.bridge.GraphTranslater;
import invenio.event.GraphSessionEvent;
import invenio.event.GraphSessionListener;
import invenio.ui.controller.MainFrameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.EventListenerList;

import prefuse.Constants;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.Renderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class VisualGraphSession implements ActionListener {

//	protected ArrayList<VisualRuleSet> m_visualHistory;
//	protected String m_visualGraphName;

//	protected DynamicTimeModel m_timeModel;
//	protected AlgorithmHighlighter m_algorithmHighlighter;
//	protected ArrayList<AlgorithmResult> m_algorithmHistory;
//	
//	protected AlgorithmManager m_algorithmManager;
	
	protected GraphSession model;
	protected DisplayShape m_displayShape;
	protected LayoutManager layoutManager = new DefaultLayoutManager();
	protected Painter painter = new Painter();
	protected Renderer renderer = new ShapeRenderer();
	
	public VisualGraphSession() {
		model = new GraphSession();
		listenerList = new EventListenerList();
	}

	// TODO: verify that old Display is recycled
	public void setShape(DisplayShape shape) {
		this.m_displayShape = shape;
		updateShapeLayoutManager();
	}
	
	public String getLayoutActionName() {
		return "layout";
	}
	
	public String getRepaintActionName() {
		return "repaint";
	}
	
	public void repaintVis() {
		Visualization vis = getVisualization();
		if (vis != null) {
			vis.removeAction( getRepaintActionName() );
			vis.putAction( getRepaintActionName(), new RepaintAction() );
			vis.run( getRepaintActionName() );
		}
	}
	
	public void updateModel(InvenioGraph invGraph){
		model.setInvenioGraph(invGraph);
		model.setPrefuseGraph( GraphTranslater.translate(invGraph, GraphTranslater.FULL_TRANSLATION) );
		
		Visualization vis = new Visualization();
		model.setVisualization(vis);
		vis.add("graph", model.getPrefuseGraph());
		vis.setInteractive("graph.edges", null, false);
		
		
		setRenderer(renderer);
//      LabelRenderer r = new LabelRenderer("dolphin_name");
//      r.setRoundedCorner(8, 8); // round the corners
//        ShapeRenderer r = new ShapeRenderer(); 
//        vis.setRendererFactory(new DefaultRendererFactory(r));
        
        resetPainterToDefault();
        
        // create an action list with an animated layout
        setLayout( new ForceDirectedLayout("graph"));

        
        
        if (m_displayShape != null) {
        	m_displayShape.updateDisplay();
        }
        
    	painter.setVisualization(vis);
    	painter.runAll();
	}
	
	protected void resetPainterToDefault() {
		RandomVisibilityAction visibility = new RandomVisibilityAction("graph.nodes", "location");
        
        ColorAction stroke = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(200));
//        ColorAction fill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.gray(200));
        ColorAction fill = new RandomColorAction("graph.nodes", "location", Constants.NOMINAL, VisualItem.FILLCOLOR);
        // use black for node text
        ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
        // use light grey for edges
        ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        painter.putAction("stroke", stroke);
        painter.putAction("fill", fill);
        painter.putAction("text", text);
        painter.putAction("edges", edges);
	}

	public void actionPerformed(ActionEvent e) {
		if ( "UPDATE_LAYOUT".equals(e.getActionCommand()) ) {
			setLayoutFromComboBox();
		}
	}
	
	protected void setLayoutFromComboBox() {
		int selIndex = m_displayShape.getLayoutComboBox().getSelectedIndex();
		Layout layout = layoutManager.getLayout(selIndex, getExtraObject(), "graph");
		setLayout(layout);
	}
	
	protected Object getExtraObject() {
		return null;
	}
	
	
	public void setLayout(Layout l) {
		getVisualization().cancel( getLayoutActionName() );
		getVisualization().removeAction( getLayoutActionName() );
		
        ActionList layout = new ActionList(2000);
        layout.add(l);
        layout.add(new RepaintAction());
        
		getVisualization().putAction(getLayoutActionName(), layout);
		getVisualization().run(getLayoutActionName());
	}

	
	public DisplayShape getDisplaySession() {
		return m_displayShape;
	}
	
	public GraphSession getGraphSession() {
		return model;
	}
	
	public Visualization getVisualization() {
		return model.getVisualization();
	}
	
	public LayoutManager getLayoutManager() {
		return layoutManager;
	}

	public void setLayoutManager(LayoutManager layoutManager) {
		this.layoutManager = layoutManager;
		updateShapeLayoutManager();
	}
	
	protected void updateShapeLayoutManager() {
		if (m_displayShape != null && layoutManager != null)
			m_displayShape.getLayoutComboBox().setModel(new DefaultComboBoxModel( layoutManager.getLayouts() ));
	}
	
	public Painter getPainter() {
		return painter;
	}
	
	public void setPainter(Painter p) {
		if (painter != null)
			painter.setVisualization(null);
		
		painter = p;
		if (painter != null)
			painter.setVisualization( getVisualization() );
	}
	
	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
		Visualization vis = getVisualization();
		if (vis != null) {
			vis.setRendererFactory( new DefaultRendererFactory(renderer) );
		}
	}
	
	
	//------------------------ Ignore for now -----------------------------//	

/*	
	VisualGraphSession(GraphSession graphSession){
		m_graphSession = graphSession;
		m_visualHistory = new ArrayList<VisualRuleSet>();
	}
	void setDisplaySession(DisplaySession displaySession){
		setDisplaySession(displaySession, GraphPainter.FORCE_LAYOUT);
	}
	void setDisplaySession(DisplaySession displaySession, int layout){
		m_displaySession = displaySession;
		m_graphPainter = new GraphPainter(this, layout);
		m_visualGraphPredicate = new InGroupPredicate(getVisualGraphName());
	}
*/

	

	/**
	 * Add a {@link VisualRuleSet} to this GraphSession such that it will 
	 * affect the way the GraphSession's {@link GraphPainter} renders the
	 * graph.
	 */
	public void addVisualRuleSet(VisualRuleSet item) {
////		m_visualHistory.add(item);
//		m_graphPainter.addRules(item.getRules());
//		fireGraphSessionEvent(new GraphSessionEvent(this, GraphSessionEvent.VISUAL_HISTORY_CHANGED));
	}
	
	/**
	 * Remove the {@link VisualRuleSet} at the specified index in a list from 
	 * this GraphSession, so that it no longer affects how the {@link 
	 * GraphPainter} renders this graph.
	 * 
	 * @param index the index at which the {@link VisualRuleSet} to be removed
	 * resides, in this GraphSession's visual rule history.
	 */
	public void removeVisualRuleSet(VisualRuleSet item) {
////		m_visualHistory.remove(item);
//		m_graphPainter.removeRules(item.getRules());
//		
//		fireGraphSessionEvent(new GraphSessionEvent(this, GraphSessionEvent.VISUAL_HISTORY_CHANGED));
	}
	
	//------------------------ GraphSessionListener methods -----------------------------//
	
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



}
