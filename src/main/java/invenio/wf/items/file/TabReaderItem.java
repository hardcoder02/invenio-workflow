package invenio.wf.items.file;

import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class TabReaderItem extends BaseWorkflowItem {
	private final static String NAME = "Tab Reader";
	
	private final TabReaderController controller = new TabReaderController();
	
	public TabReaderItem() {
		this(NAME);
	}
	
	public TabReaderItem(String name) {
		super(name, name + nextKey(name));
	}
	
	public TabReaderItem(String name, String key) {
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
