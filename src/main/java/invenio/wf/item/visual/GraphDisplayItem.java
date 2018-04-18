package invenio.wf.item.visual;

import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;
import invenio.wf.items.file.TabReaderController;

public class GraphDisplayItem extends BaseWorkflowItem {
	private final static String NAME = "Graph Display";
	
	private final static Class[] DESCRIPTOR =
		{InvenioGraph.class};
	
	private final GraphDisplayController controller = new GraphDisplayController();
	
	public GraphDisplayItem() {
		this(NAME);
	}
	
	public GraphDisplayItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public GraphDisplayItem(String name, String key) {
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
