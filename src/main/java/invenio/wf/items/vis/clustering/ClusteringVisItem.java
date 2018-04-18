package invenio.wf.items.vis.clustering;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class ClusteringVisItem extends BaseWorkflowItem {
	private final static String NAME = "Cluster Visualization";
	
	private final static Class[] DESCRIPTOR =
		{GroupingAlgorithmResult.class};
	
	private final ClusteringVisController controller = new ClusteringVisController();
	
	public ClusteringVisItem() {
		this(NAME);
	}
	
	public ClusteringVisItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public ClusteringVisItem(String name, String key) {
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
