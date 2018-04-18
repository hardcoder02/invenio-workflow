package invenio.wf.items.query;

import qng.tabular.Table;
import invenio.data.InvenioGraph;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class TableSelectorItem extends BaseWorkflowItem {
	private final static String NAME = "Table Selector";
	private final static Class[] DESCRIPTOR =
		{Table.class};
	
	private final TableSelectorController controller = new TableSelectorController();
	
	public TableSelectorItem() {
		this(NAME);
	}
	
	public TableSelectorItem(String name) {
		super(name, name + nextKey(name), DESCRIPTOR);
	}
	
	public TableSelectorItem(String name, String key) {
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
