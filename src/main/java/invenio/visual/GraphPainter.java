package invenio.visual;

import invenio.action.DynamicSizeAction;
import invenio.action.EdgeSpringAction;
import invenio.action.EdgeSpringCalculator;
import invenio.action.NodeMassAction;
import invenio.action.NodeMassCalculator;
import invenio.action.SizeUpAction;
import invenio.action.layout.ConcentricCircleLayout;
import invenio.action.layout.GroupingLayout;
import invenio.action.layout.MarkovLayout;
import invenio.action.layout.WeightedForceDirectedLayout;
import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;
import invenio.data.bridge.BridgeTuple;
import invenio.data.expression.DegreePredicate;
import invenio.data.expression.TupleTypePredicate;
import invenio.event.GraphPainterEvent;
import invenio.event.GraphPainterListener;
import invenio.event.GraphSessionEvent;
import invenio.event.GraphSessionListener;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.EventListenerList;

import prefuse.Visualization;
import prefuse.action.Action;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.ShapeAction;
import prefuse.action.assignment.StrokeAction;
import prefuse.action.assignment.VisibilityAction;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.Layout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.BalloonTreeLayout;
import prefuse.action.layout.graph.FruchtermanReingoldLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.action.layout.graph.SquarifiedTreeMapLayout;
import prefuse.activity.Activity;
import prefuse.activity.ActivityListener;
import prefuse.data.expression.AndPredicate;
import prefuse.data.expression.NotPredicate;
import prefuse.data.expression.Predicate;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.render.Renderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class GraphPainter implements GraphSessionListener, ActivityListener {
	
	protected VisualGraphSession m_visualGraphSession;
	
	protected String visualGraph;
	protected String nodes;
	protected String edges;
	
	List<VisualRule> rules;
	int layoutType;
	boolean isAnimated;
	int animationSkip;
	List<VisualRule> defaultRules;
	List<VisualRule> selectionHighlightRules;
	List<VisualRule> highlightRules;
	
	int degreeFilter;
	Predicate degreePredicate;
//	Predicate superNodePredicate;

	DefaultRendererFactory renderer;
		
	ColorAction nodeColorAction;
	ColorAction nodeBorderColorAction;
	ColorAction nodeTextColorAction;
	DynamicSizeAction nodeSizeAction;
	QuickFilterSet filterSet;
	SizeUpAction nodeSizeUpAction;
	ShapeAction nodeShapeAction;
	private VisibilityAction nodeVisibilityAction;
	//brianVisibilityFilter nodeVisibilityAction;
	NodeMassAction nodeMassAction;
	
	ColorAction edgeColorAction;
	DynamicSizeAction edgeSizeAction;
	SizeUpAction edgeSizeUpAction;
	StrokeAction edgeStrokeAction;
	private VisibilityAction edgeVisibilityAction;
	//brianVisibilityFilter edgeVisibilityAction;
	EdgeSpringAction edgeSpringAction;
		
	public final static int FORCE_LAYOUT = 0;
	public final static int TREE_LAYOUT = 1;
	public final static int CIRCLE_LAYOUT = 2;
	public final static int RANDOM_LAYOUT = 3;
	public final static int BALLOON_TREE_LAYOUT = 4;
	public final static int RADIAL_TREE_LAYOUT = 5;
	public final static int SQUARIFIED_TREE_MAP_LAYOUT = 6;
	public final static int FRUCHTERMAN_REINGOLD_LAYOUT = 7;
	public final static int DYNAMIC_CLUSTER_LAYOUT = 8;
	public final static int MARKOV_LAYOUT = 9;
	public final static int CC_CIRCLE_LAYOUT = 10;
	
	final String REPAINT_ACTION = "_repaint_action";
	Predicate groupPredicate;
	
	public GraphPainter(VisualGraphSession visSession, int layout) {
		
		m_visualGraphSession = visSession;
		
//		visualGraph = visSession.getVisualGraphName();
		nodes = visualGraph.concat(".nodes");
		edges = visualGraph.concat(".edges");
		
		setLayout(layout);
		
		rules = new ArrayList<VisualRule>();
		defaultRules = new ArrayList<VisualRule>();
		
		selectionHighlightRules = new ArrayList<VisualRule>();
		selectionHighlightRules.add(new VisualRule(null, VisualRule.NODE_SIZEUP_RULE, 1.0));
		selectionHighlightRules.add(new VisualRule(null, VisualRule.EDGE_SIZEUP_RULE, 2.0));

		highlightRules = new ArrayList<VisualRule>();
		highlightRules.add(new VisualRule(null, VisualRule.NODE_SIZEUP_RULE, 1.0));
		highlightRules.add(new VisualRule(null, VisualRule.NODE_BORDERCOLOR_RULE, Color.BLACK));
		highlightRules.add(new VisualRule(null, VisualRule.EDGE_SIZEUP_RULE, 1.0));
		highlightRules.add(new VisualRule(null, VisualRule.EDGE_COLOR_RULE, Color.BLACK));
		
		nodeColorAction = new ColorAction(nodes, VisualItem.FILLCOLOR, ColorLib.color(Color.BLACK));
		nodeBorderColorAction = new ColorAction(nodes, VisualItem.STROKECOLOR, ColorLib.color(Color.BLACK));
		nodeTextColorAction = new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.gray(255));
		nodeSizeUpAction = new SizeUpAction(nodes, 0.0);
		nodeSizeAction = new DynamicSizeAction(nodes, 1.0);
		nodeShapeAction = new ShapeAction(nodes, 0);
		nodeVisibilityAction = new VisibilityAction(nodes, true);
		nodeMassAction = new NodeMassAction(nodes);
		
		edgeColorAction = new ColorAction(edges, VisualItem.STROKECOLOR, ColorLib.color(Color.BLACK));
		edgeSizeAction = new DynamicSizeAction(edges, 1.0);
		edgeSizeUpAction = new SizeUpAction(edges, 0.0);
		edgeStrokeAction = new StrokeAction(edges);
		edgeVisibilityAction = new VisibilityAction(edges, true);
		edgeSpringAction = new EdgeSpringAction(edges);
		
		groupPredicate = visSession.getVisualGraphPredicate();
	
		degreeFilter = 0;
		animationSkip = 200;
		
		renderer = (DefaultRendererFactory)UserSession.getInstance().getVisualization().getRendererFactory();
		//renderer.setDefaultEdgeRenderer(new StripColorEdgeRenderer());

		filterSet = new QuickFilterSet(this);
		UserSession.getInstance().getVisualization().putAction(getRepaintActionName(), new RepaintAction());
		
		repaintGraph();
	}
	
	public void graphSessionChanged(GraphSessionEvent e) {
		if(e.getEventType() == GraphSessionEvent.GRAPH_SELECTION_CHANGED && e.getSource() instanceof GraphSelection) {
			removeRules(selectionHighlightRules);
			if(!((GraphSelection)e.getSource()).isHighlighted()) return;
			if(((GraphSelection)e.getSource()).isTrivial()) return;
			if(!m_visualGraphSession.getDisplaySession().isVisible()) return;
			Predicate p = ((GraphSelection)e.getSource()).getPredicate();
			Iterator<VisualRule> rules = selectionHighlightRules.iterator();
			while(rules.hasNext())
				rules.next().setPredicate(p);
			
			addRules(selectionHighlightRules);
			quickRepaintGraph();
		}
	}

	public void setHighlight(Predicate p){
		removeRules(selectionHighlightRules);
		removeRules(highlightRules);
		Iterator<VisualRule> rules = highlightRules.iterator();
		while(rules.hasNext())
			rules.next().setPredicate(p);
		
		addRules(highlightRules);
		quickRepaintGraph();
	}
	
	public void removeHighlight(){
		removeRules(highlightRules);
		
//		Iterator<VisualRule> rules = selectionHighlightRules.iterator();
//		while(rules.hasNext()){
//			if(rules.next().getPredicate() != null){
//				addRules(selectionHighlightRules);
//				return;
//			}
//		}
	}
	
	public void setDegreeFilter(int value) {
		degreeFilter = (value < 0 ? 0 : value);
		
		repaintGraph();
	}
	
	public int getDegreeFilter() {
		return degreeFilter;
	}
	
	public QuickFilterSet getFilterSet(){
		return filterSet;
	}
	
	public VisualRule getRule(int index) {
		return rules.get(index);
	}
	
	public void addRule(VisualRule rule) {		
		addRule(rule, true);
	}
	
	public void addRule(VisualRule rule, boolean addFront) {
		if(rule.getRuleType() == VisualRule.NODE_LABEL_RULE) {
			rule.setPredicate(new AndPredicate(new TupleTypePredicate(NodeItem.class),
								new AndPredicate(groupPredicate, rule.getPredicate())));
		}
		else if(rule.getRuleType() == VisualRule.EDGE_RENDERER_RULE) {
			System.out.println("Setting edge renderer rule predicate...");
			rule.setPredicate(new AndPredicate(new TupleTypePredicate(EdgeItem.class),
								new AndPredicate(groupPredicate, rule.getPredicate())));
		}
		else if(rule.getRuleType() == VisualRule.LAYOUT_RULE) {
			System.out.println("adding visual rule for layout");
			//setLayout(TREE_LAYOUT);//, rule.m_propertyValue);
			setLayout((Integer) rule.getPropertyValue());
		}
		
		rules.add(0, rule);
		addRule(rule.getPredicate(), rule.getPropertyValue(), rule.getRuleType(), addFront);
		repaintGraph();
	}
	
	public void addRules(List<VisualRule> rules) {
		addRules(rules, true);
	}
	
	public void addRules(List<VisualRule> rules, boolean addFront) {
		Iterator<VisualRule> iter = rules.iterator();
		while(iter.hasNext())
			addRule(iter.next(), addFront);
	}
	
	public VisualRule removeRule(int index) {
		VisualRule rule = getRule(index);
		removeRule(rule.getPredicate(), rule.getRuleType());
		
		return rules.remove(index);
	}

	public VisualRule removeRule(VisualRule rule) {
		
		for(int i = 0; i < rules.size(); i++)
			if(rules.get(i) == rule)
				return removeRule(i);
		
		return null;
	}

	public void removeRules(List<VisualRule> rules) {
		Iterator<VisualRule> iter = rules.iterator();
		while(iter.hasNext())
			removeRule(iter.next());
	}
	
	public void setDefaultRules(List<VisualRule> rules) {
		this.removeRules(defaultRules);
		
		defaultRules = rules;
		
		addRules(rules, false);
	}
	/*
	public void setVisible(Display disp, boolean visible) {
		
		if(!visible) {
			disp.setPredicate(new BooleanLiteral(false));
			if(!UserSession.getInstance().isActiveGraphSession(graph.getName()))
				pauseAnimation();
		}
		else {
			disp.setPredicate(groupPredicate);
			if(isAnimated)
				runAnimation();
		}
	}
	*/
	public void setLayout(int layoutType) {
		setLayout(layoutType, null);
	}
	
	public void setLayout(int layoutType, Object extra) {
		this.layoutType = layoutType;

		Visualization vis = UserSession.getInstance().getVisualization();
		Layout layout = null;
		
		if(layoutType == FORCE_LAYOUT) {
			layout = new WeightedForceDirectedLayout(visualGraph);
			
		}
		else if(layoutType == TREE_LAYOUT) {
			//System.out.println("entering setting tree layout");
			//Brian - Setting tree orientation as top to bottom. Numerical values are defaults from NodeLinkTreeLayout
			layout = new NodeLinkTreeLayout(visualGraph, prefuse.Constants.ORIENT_TOP_BOTTOM,20,25,15);
			//layout = new NodeLinkTreeLayout(((NodeItem)extra).getGraph(), prefuse.Constants.ORIENT_TOP_BOTTOM,20,25,15);
			((NodeLinkTreeLayout)layout).setRootNodeOffset(10.0);
			if(extra != null && extra instanceof NodeItem){
				//System.out.println("setting root in tree");
				((NodeLinkTreeLayout)layout).setLayoutRoot((NodeItem)extra);
			}
		}
		else if(layoutType == CIRCLE_LAYOUT) {
			layout = new CircleLayout(visualGraph);
		} 
		else if(layoutType == RANDOM_LAYOUT) {
			layout = new RandomLayout(visualGraph);
		}
		else if(layoutType == BALLOON_TREE_LAYOUT) {
			layout = new BalloonTreeLayout(visualGraph);
		}
		else if(layoutType == RADIAL_TREE_LAYOUT) {
			layout = new RadialTreeLayout(visualGraph);
		}
		else if(layoutType == SQUARIFIED_TREE_MAP_LAYOUT) {
			layout = new SquarifiedTreeMapLayout(visualGraph);
		}
		else if(layoutType == FRUCHTERMAN_REINGOLD_LAYOUT) {
			layout = new FruchtermanReingoldLayout(visualGraph);
		}
		else if(layoutType == DYNAMIC_CLUSTER_LAYOUT) {
			layout = new GroupingLayout(visualGraph);
		}
		else if(layoutType == MARKOV_LAYOUT) {
			layout = new MarkovLayout(visualGraph);
		}
		else if(layoutType == CC_CIRCLE_LAYOUT) {
			layout = new ConcentricCircleLayout(visualGraph);
		}		
		System.out.println("Layout created");
		
		isAnimated = false;
		
		System.out.println("Canceling old layout");
		vis.cancel(getLayoutActionName());
		System.out.println("Queuing up new layout");
		vis.putAction(getLayoutActionName(), layout);

		System.out.println("Running new layout animation");
		runAnimation(getLayoutAnimationSkip(layoutType), false);

		fireGraphPainterEvent(new GraphPainterEvent(this, GraphPainterEvent.LAYOUT_CHANGED));		
	}
	
	public void setLayout(Layout layout, int layoutType) {
		
		Visualization vis = UserSession.getInstance().getVisualization();
		
		System.out.println("Canceling old layout");
		vis.cancel(getLayoutActionName());
		System.out.println("Queuing up new layout");
		vis.putAction(getLayoutActionName(), layout);
	
		System.out.println("Running new layout animation");
		runAnimation(getLayoutAnimationSkip(layoutType), false);
	
		fireGraphPainterEvent(new GraphPainterEvent(this, GraphPainterEvent.LAYOUT_CHANGED));		
	}

	public int getLayout() {
		return layoutType;
	}
	
	long getLayoutAnimationSkip(int layout) {
		switch(layout) {
			case FORCE_LAYOUT:
				return animationSkip;
			case MARKOV_LAYOUT:
				return 1;
			case CC_CIRCLE_LAYOUT:
				return 1;
			case TREE_LAYOUT:
			case CIRCLE_LAYOUT:
			case RANDOM_LAYOUT:
			case BALLOON_TREE_LAYOUT:
			case RADIAL_TREE_LAYOUT:
			case SQUARIFIED_TREE_MAP_LAYOUT:
			case FRUCHTERMAN_REINGOLD_LAYOUT:
			case DYNAMIC_CLUSTER_LAYOUT:
				return 0;
		}
		return 0;
	}
	
	public void runAnimation(long duration, boolean showAnimation) {
		Visualization vis = UserSession.getInstance().getVisualization();
		vis.cancel(getLayoutActionName());
		
		Action layoutAction = vis.getAction(getLayoutActionName());
		
		layoutAction.setDuration(duration);
		layoutAction.setStepTime(showAnimation ? 40 : 0);
		layoutAction.addActivityListener(this);
		
		isAnimated = (duration == Activity.INFINITY);
		
		System.out.println("Layout scheduled to run immediately");
		vis.run(getLayoutActionName());
	}
	
	public void runAnimation() {
		runAnimation(getLayoutAnimationSkip(layoutType), true);
	}
	
	public void runAnimation(boolean runStep) {
		long duration = getLayoutAnimationSkip(layoutType);
		runAnimation((duration == 0 || runStep ? duration : Activity.INFINITY), true);
	}
	
	public void pauseAnimation() {
		UserSession.getInstance().getVisualization().cancel(getLayoutActionName());
		isAnimated = false;
	}
	
	public boolean isAnimated() {
		return isAnimated;
	}
	
	String getLayoutActionName() {
		return "animate_" + visualGraph;
	}
	
	String getRepaintActionName() {
		return "repaint_" + visualGraph;
	}
	
	String getFullPaintActionName() {
		return "fullpaint_" + visualGraph;
	}

//	Predicate getSuperNodesHiddenPredicate() {
////		System.out.println("Paul: so... are we still using the whole InvenioSuperVertex thing...?");
//		
////		List<InvenioElement> nodes = new LinkedList<InvenioElement>();
//		InvenioElementSet nodes = new InvenioElementSet();
//		Object[] vertices = graph.getSuperVertices();
//		InvenioSuperVertex v;
//		
//		for(int i = 0; i < vertices.length; i++) {
//			if(!(vertices[i] instanceof InvenioSuperVertex)) continue;
//			
//			v = (InvenioSuperVertex)vertices[i];
//			
//			if(v.isExpanded() && !v.isCoexistable())
//				nodes.addElement(v);
//			else if(!v.isExpanded()) {
//				addSuperVertexChildrenToHiddenList(v, nodes);
//			}
//		}
//		
//		return new SetPredicate(nodes);
//	}
//	
//	public void addSuperVertexChildrenToHiddenList(InvenioSuperVertex v, InvenioElementSet nodes) {
//		
////		nodes.addAll(v.getChildVertices());
//		Iterator<InvenioVertex> iter = v.getChildVertices().iterator();
//		InvenioVertex child;
//		
//		while(iter.hasNext()) {
//			child = iter.next();
//			nodes.addElement(child);	//new line
//			if(child instanceof InvenioSuperVertex) {
//				addSuperVertexChildrenToHiddenList((InvenioSuperVertex)child, nodes);
//			}
//		}
//	}
	
	void addRule(Predicate p, Object val, int modType, boolean addFront) {
		Color c;
		double d;
		int i;
		String s;
		boolean b;
		
		switch(modType) {
			case(VisualRule.NODE_COLOR_RULE):
				c = (Color)val;
				if(addFront) nodeColorAction.addFront(p, ColorLib.color(c));
				else nodeColorAction.add(p, ColorLib.color(c));
				break;
			case(VisualRule.NODE_SIZEUP_RULE):
				d = ((Double)val).doubleValue();
				if(addFront) nodeSizeUpAction.addFront(p, d);
				break;
			case(VisualRule.NODE_SIZE_RULE):
				if(addFront) nodeSizeAction.addFront(p, val);
				else nodeSizeAction.add(p, val);
				break;
			case(VisualRule.NODE_SHAPE_RULE):
				i = ((Integer)val).intValue();
				if(addFront) nodeShapeAction.addFront(p, i);
				else nodeShapeAction.add(p, i);
				break;
			case(VisualRule.NODE_BORDERCOLOR_RULE):
				c = (Color)val;
				if(addFront) nodeBorderColorAction.addFront(p, ColorLib.color(c));
				else nodeBorderColorAction.add(p, ColorLib.color(c));
				break;
			case(VisualRule.NODE_VISIBILITY_RULE):
				b = ((Boolean)val).booleanValue();
				if(addFront) nodeVisibilityAction.addFront(p, b);
				else nodeVisibilityAction.add(p, b);
				break;
			case(VisualRule.NODE_LABEL_RULE):
				s = (String)val;
				if(addFront) renderer.addFront(p, new LabelRenderer(s));
				else renderer.add(p, new LabelRenderer(s));
				break;
			case(VisualRule.NODE_MASS_RULE):
				if(addFront) nodeMassAction.addFront(p, (NodeMassCalculator)val);
				else nodeMassAction.add(p, (NodeMassCalculator)val);
				break;
			case(VisualRule.EDGE_COLOR_RULE):
				c = (Color)val;
				if(addFront) edgeColorAction.addFront(p, ColorLib.color(c));
				else edgeColorAction.add(p, ColorLib.color(c));
				break;
			case(VisualRule.EDGE_SIZEUP_RULE):
				d = ((Double)val).doubleValue();
				if(addFront) edgeSizeUpAction.addFront(p, d);
				break;
			case(VisualRule.EDGE_SIZE_RULE):
				if(addFront) edgeSizeAction.addFront(p, val);
				else edgeSizeAction.add(p, val);
				break;
			case(VisualRule.EDGE_DASH_RULE):
				if(addFront) edgeStrokeAction.addFront(p, (BasicStroke)val);
				else edgeStrokeAction.add(p, (BasicStroke)val);
				break;
			case(VisualRule.EDGE_VISIBILITY_RULE):
				b = ((Boolean)val).booleanValue();
				if(addFront) edgeVisibilityAction.addFront(p, b);
				else edgeVisibilityAction.add(p, b);
				break;
			case(VisualRule.EDGE_SPRING_RULE):
				if(addFront) edgeSpringAction.addFront(p, (EdgeSpringCalculator)val);
				else edgeSpringAction.add(p, (EdgeSpringCalculator)val);
				break;
			case(VisualRule.EDGE_RENDERER_RULE):
				if(addFront) renderer.addFront(p, (Renderer)val);
				else renderer.add(p, (Renderer)val);
				break;
		}
		
		repaintGraph();
	}
	
	void removeRule(Predicate p, int modType) {
		
		switch(modType) {
			case(VisualRule.NODE_COLOR_RULE):
				nodeColorAction.remove(p);
				break;
			case(VisualRule.NODE_SIZEUP_RULE):
				nodeSizeUpAction.remove(p);
				break;
			case(VisualRule.NODE_SIZE_RULE):
				nodeSizeAction.remove(p);
				break;
			case(VisualRule.NODE_SHAPE_RULE):
				nodeShapeAction.remove(p);
				break;
			case(VisualRule.NODE_BORDERCOLOR_RULE):
				nodeBorderColorAction.remove(p);
				break;
			case(VisualRule.NODE_VISIBILITY_RULE):
				nodeVisibilityAction.remove(p);
				break;
			case(VisualRule.NODE_MASS_RULE):
				nodeMassAction.remove(p);
				break;
			case(VisualRule.NODE_LABEL_RULE):
				renderer.remove(p);
				break;
			case(VisualRule.EDGE_COLOR_RULE):
				edgeColorAction.remove(p);
				break;
			case(VisualRule.EDGE_SIZEUP_RULE):
				edgeSizeUpAction.remove(p);
				break;
			case(VisualRule.EDGE_SIZE_RULE):
				edgeSizeAction.remove(p);
				break;
			case(VisualRule.EDGE_DASH_RULE):
				edgeStrokeAction.remove(p);
				break;
			case(VisualRule.EDGE_VISIBILITY_RULE):
				edgeVisibilityAction.remove(p);
				break;
			case(VisualRule.EDGE_SPRING_RULE):
				edgeSpringAction.remove(p);
				break;
			case(VisualRule.EDGE_RENDERER_RULE):
				renderer.remove(p);
				break;
		}
		
		repaintGraph();
	}

	public void repaintGraph() {
		
		Visualization vis = UserSession.getInstance().getVisualization();
			
		ActionList paint = new ActionList(vis, 0);
		
		updateFilters();
		
		paint.add(nodeColorAction);
		paint.add(nodeBorderColorAction);
		paint.add(nodeTextColorAction);
		paint.add(nodeSizeAction);
		paint.add(nodeSizeUpAction);
		paint.add(nodeShapeAction);
		paint.add(nodeVisibilityAction);
		paint.add(nodeMassAction);
		
		paint.add(edgeColorAction);
		paint.add(edgeSizeAction);
		paint.add(edgeSizeUpAction);
		paint.add(edgeStrokeAction);
		paint.add(edgeVisibilityAction);
		paint.add(edgeSpringAction);
		
		vis.setRendererFactory(renderer);
		
		vis.putAction(getFullPaintActionName(), paint);

		vis.run(getFullPaintActionName());
		vis.run(getRepaintActionName());
	}
	
	public void quickRepaintGraph() {
		UserSession.getInstance().getVisualization().run(getRepaintActionName());
	}
	
	private void updateFilters() {
		if(degreePredicate != null)
			nodeVisibilityAction.remove(degreePredicate);
		//briannodeVisibilityAction.cancel();
//		if(superNodePredicate != null)
//			nodeVisibilityAction.remove(superNodePredicate);
			//briannodeVisibilityAction.cancel();
		filterSet.updateFilters(nodeVisibilityAction, edgeVisibilityAction);
		
		if(degreeFilter > 0) {
			degreePredicate = new NotPredicate(new DegreePredicate(degreeFilter));
			nodeVisibilityAction.addFront(degreePredicate, false);
		}
		else
			degreePredicate = null;
		
//		if(graph.getSuperVertices().length > 0) {
//			superNodePredicate = getSuperNodesHiddenPredicate();
//			nodeVisibilityAction.addFront(superNodePredicate, false);
//		}
//		else
//			superNodePredicate = null;
	}
	
	public void activityCancelled(Activity a) {
		UserSession.getInstance().getVisualization().run(getRepaintActionName());
		a.removeActivityListener(this);
	}
	public void activityFinished(Activity a) {
		UserSession.getInstance().getVisualization().run(getRepaintActionName());
		a.removeActivityListener(this);
	}
	
	public void activityStarted(Activity a) {}
	public void activityStepped(Activity a) {
		if(isAnimated)
			UserSession.getInstance().getVisualization().run(getRepaintActionName());			
	}
	public void activityScheduled(Activity a) {}
	
	//------------------------ GraphPainterListener methods -----------------------------
	
	private EventListenerList listenerList = new EventListenerList();
    
	public void addGraphPainterListener(GraphPainterListener listener) {
		listenerList.add(GraphPainterListener.class, listener);
	}

	public void removeGraphPainterListener(GraphPainterListener listener) {
		listenerList.remove(GraphPainterListener.class, listener);
	}
    
	public void fireGraphPainterEvent(GraphPainterEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i+=2) {
			if (listeners[i] == GraphPainterListener.class) {
				((GraphPainterListener)listeners[i+1]).graphPainterChanged(evt);
			}
		}
	}
	
	public boolean getItemVisibility(VisualItem item) {
		if(item instanceof BridgeTuple) {
			if(((BridgeTuple)item).getElement() instanceof InvenioVertex)
				return nodeVisibilityAction.getVisibility(item);
			if(((BridgeTuple)item).getElement() instanceof InvenioEdge)
				return edgeVisibilityAction.getVisibility(item);
		}
		return false;
	}

}
