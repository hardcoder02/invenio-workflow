package invenio.wf.items.file;

import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;

public class FileImportItem extends BaseWorkflowItem {
	private final static String NAME = "File Import";
	
	private final FileImportController controller = new FileImportController();
	
	public FileImportItem() {
		this(NAME);
	}
	
	public FileImportItem(String name) {
		super(name, name + nextKey(name));
	}
	
	public FileImportItem(String name, String key) {
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
