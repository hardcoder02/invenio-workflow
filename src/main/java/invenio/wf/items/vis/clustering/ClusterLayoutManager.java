package invenio.wf.items.vis.clustering;

import invenio.action.layout.WeightedForceDirectedLayout;
import invenio.algorithms.GroupingAlgorithmResult;
import invenio.visual.FixedCircleLayout;
import invenio.visual.LayoutManager;
import prefuse.action.layout.Layout;

public class ClusterLayoutManager implements LayoutManager {
	
//	private GroupingAlgorithmResult grouping = null;
	
	public static final String[] LAYOUT_VALUES = {	"Force Layout", "Circle Layout", "Cluster Layout" };

	public final static int FORCE_LAYOUT = 0;
	public final static int CIRCLE_LAYOUT = 1;
	public final static int CLUSTER_LAYOUT = 2;
	
	public String[] getLayouts() {
		return LAYOUT_VALUES;
	}
	
	public Layout getLayout(int layoutType, Object extra, String groupName) {

		Layout layout = null;
		
		if(layoutType == CLUSTER_LAYOUT) {
			layout = new ClusterCircleLayout(groupName, 500);
			((ClusterCircleLayout)layout).setGroupingResult((GroupingAlgorithmResult)extra);
		}
		else if(layoutType == FORCE_LAYOUT) {
			layout = new WeightedForceDirectedLayout(groupName);
		}
		else if(layoutType == CIRCLE_LAYOUT) {
			layout = new FixedCircleLayout(groupName + ".nodes", 500);
		} 

		return layout;
	}
}
