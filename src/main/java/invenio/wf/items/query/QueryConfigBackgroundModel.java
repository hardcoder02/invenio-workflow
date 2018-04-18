package invenio.wf.items.query;

public class QueryConfigBackgroundModel {
	private String query;
	private boolean packageCell = false;
	private int row;
	private int column;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isPackageCell() {
		return packageCell;
	}
	public void setPackageCell(boolean packageCell) {
		this.packageCell = packageCell;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}
