package invenio.wf.items.query;

import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class QueryItem extends BaseWorkflowItem {
	private final static String NAME = "Query";
	private final static Class[] DESCRIPTOR =
		{InvenioGraph.class, InvenioGraph.class, InvenioGraph.class, InvenioGraph.class, InvenioGraph.class};
	
	private final QueryController controller = new QueryController();
	
	public QueryItem() {
		this(NAME);
	}
	
	public QueryItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public QueryItem(String name, String key) {
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
