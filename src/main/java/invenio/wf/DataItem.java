package invenio.wf;


public class DataItem<R> extends BaseNodeItem {
	private final R value;
	
	public DataItem(R value) {
		super();
		this.value = value;
	}
	
	@Override
	public boolean isInternallyUpdateable() {
		return false;
	}

	@Override
	public boolean isExternallyUpdateable() {
		return false;
	}

	@Override
	public boolean update() {
		// TODO: or throw UnsupportedOperationException?
		return false;
	}

	@Override
	public R getOutput() {
		return value;
	}

}
