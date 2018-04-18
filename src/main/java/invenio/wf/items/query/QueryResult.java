package invenio.wf.items.query;

import qng.tabular.Table;

public class QueryResult {
	private Table table;
	private Object cellObject;
	private String query;
	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public Object getCellObject() {
		return cellObject;
	}
	public void setCellObject(Object cellObject) {
		this.cellObject = cellObject;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(query).append("\n");
		sb.append(cellObject).append("\n");
		sb.append(table);
		
		return sb.toString();
	}
}
