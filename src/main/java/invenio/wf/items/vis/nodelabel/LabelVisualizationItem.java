package invenio.wf.items.vis.nodelabel;

import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class LabelVisualizationItem extends BaseWorkflowItem {
	private final static String NAME = "Label Visualization";
	
	private final static Class[] DESCRIPTOR =
		{InvenioGraph.class, InvenioGraph.class, InvenioGraph.class};
	
	private final LabelVisualizationController controller = new LabelVisualizationController();
	
	public LabelVisualizationItem() {
		this(NAME);
	}
	
	public LabelVisualizationItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public LabelVisualizationItem(String name, String key) {
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
