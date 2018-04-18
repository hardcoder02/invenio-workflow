package invenio.wf.items.vis.query;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import qng.tabular.Schema;
import qng.tabular.Table;
import qng.tabular.Tuple;

public class QueryResultTableModel extends AbstractTableModel implements TableModel {
	private Table table;
	
	public QueryResultTableModel() {
	}
	
	public void setTable(Table table) {
		this.table = table;
		fireTableDataChanged();
	}
	
	protected String[] getColumnNames() {
		if ( table == null)
			return new String[0];
		else {
			Schema schema = table.getSchema();
			return schema.getColumnNames();
		}
	}
	
	@Override
	public int getColumnCount() {
		int cnt = getColumnNames().length;
		return cnt;
	}

	@Override
	public int getRowCount() {
		if (table == null)
			return 0;
		int size = table.getSize();
		return size;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		if (table == null)
			return null;
		
		Tuple t = table.getTuples().get(rowIndex);
		return t.getValue(colIndex);
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return getColumnNames()[column];
	}
}
