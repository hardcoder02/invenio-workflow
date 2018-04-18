package invenio.wf.items.nodelabel;

import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class NodeLabelItem extends BaseWorkflowItem {
	private final static String NAME = "Node Label";
	private final static Class[] DESCRIPTOR =
		{InvenioGraph.class};
	
	private final NodeLabelController controller = new NodeLabelController();
	
	public NodeLabelItem() {
		this(NAME);
	}
	
	public NodeLabelItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public NodeLabelItem(String name, String key) {
		super(name, key);
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
