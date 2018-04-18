package invenio.wf.items.vis.clustering.compare;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class ClusteringCompareVisItem extends BaseWorkflowItem {
	private final static String NAME = "Cluster Compare Visualization";
	
	private final static Class[] DESCRIPTOR =
		{GroupingAlgorithmResult.class, GroupingAlgorithmResult.class};
	
	private final ClusteringCompareVisController controller = new ClusteringCompareVisController();
	
	public ClusteringCompareVisItem() {
		this(NAME);
	}
	
	public ClusteringCompareVisItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public ClusteringCompareVisItem(String name, String key) {
		super(name, key, DESCRIPTOR);
	}
	
	@Override
	public boolean isInternallyUpdateable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternallyUpdateable() {
		return true;
	}

	@Override
	public ItemController getController() {
		return controller;
	}
}
