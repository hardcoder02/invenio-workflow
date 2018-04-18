package invenio.wf;

public abstract class BaseEdgeItem extends BaseItem implements ConnectionItem {
	private Object cell;

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}
}
