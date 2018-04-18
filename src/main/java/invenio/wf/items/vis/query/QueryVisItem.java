package invenio.wf.items.vis.query;

import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;
import invenio.wf.items.query.QueryResult;

public class QueryVisItem extends BaseWorkflowItem {
	private final static String NAME = "Query Visualization";
	
	private final static Class[] DESCRIPTOR =
		{QueryResult.class};
	
	private final QueryVisController controller = new QueryVisController();
	
	public QueryVisItem() {
		this(NAME);
	}
	
	public QueryVisItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public QueryVisItem(String name, String key) {
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
