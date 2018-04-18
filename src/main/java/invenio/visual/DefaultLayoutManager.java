package invenio.visual;

import invenio.action.layout.ConcentricCircleLayout;
import invenio.action.layout.GroupingLayout;
import invenio.action.layout.MarkovLayout;
import invenio.action.layout.WeightedForceDirectedLayout;
import prefuse.action.layout.Layout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.BalloonTreeLayout;
import prefuse.action.layout.graph.FruchtermanReingoldLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.action.layout.graph.SquarifiedTreeMapLayout;
import prefuse.visual.NodeItem;

public class DefaultLayoutManager implements LayoutManager {
	
	public static final String[] LAYOUT_VALUES = {	"Force Layout", "Tree Layout", "Circle Layout", "Random Layout", "Balloon Tree Layout",
		"Radial Tree Layout", "Squarified Tree Map Layout", "Fruchterman Reingold Layout", "Dynamic Cluster Layout", "Markov Layout", "CC Circle Layout"};
	
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
	
	public String[] getLayouts() {
		return LAYOUT_VALUES;
	}
	
	public Layout getLayout(int layoutType, Object extra, String groupName) {

		Layout layout = null;
		
		if(layoutType == FORCE_LAYOUT) {
			layout = new WeightedForceDirectedLayout(groupName);
		}
		else if(layoutType == TREE_LAYOUT) {
			layout = new NodeLinkTreeLayout(groupName, prefuse.Constants.ORIENT_TOP_BOTTOM,20,25,15);
			((NodeLinkTreeLayout)layout).setRootNodeOffset(10.0);
			if(extra != null && extra instanceof NodeItem){
				((NodeLinkTreeLayout)layout).setLayoutRoot((NodeItem)extra);
			}
		}
		else if(layoutType == CIRCLE_LAYOUT) {
			layout = new FixedCircleLayout(groupName + ".nodes", 500);
		} 
		else if(layoutType == RANDOM_LAYOUT) {
			layout = new RandomLayout(groupName);
		}
		else if(layoutType == BALLOON_TREE_LAYOUT) {
			layout = new BalloonTreeLayout(groupName);
		}
		else if(layoutType == RADIAL_TREE_LAYOUT) {
			layout = new RadialTreeLayout(groupName);
		}
		else if(layoutType == SQUARIFIED_TREE_MAP_LAYOUT) {
			layout = new SquarifiedTreeMapLayout(groupName);
		}
		else if(layoutType == FRUCHTERMAN_REINGOLD_LAYOUT) {
			layout = new FruchtermanReingoldLayout(groupName);
		}
		else if(layoutType == DYNAMIC_CLUSTER_LAYOUT) {
			layout = new GroupingLayout(groupName);
		}
		else if(layoutType == MARKOV_LAYOUT) {
			layout = new MarkovLayout(groupName);
		}
		else if(layoutType == CC_CIRCLE_LAYOUT) {
			layout = new ConcentricCircleLayout(groupName);
		}		
		
		return layout;
	}
}
