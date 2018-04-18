package invenio.wf.items.vis.clustering.compare;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class CCMatrixTableModel extends AbstractTableModel implements TableModel {

	private final CCMatrix matrix;
	
	public CCMatrixTableModel(CCMatrix matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public int getColumnCount() {
		return matrix.numColumns();
	}

	@Override
	public int getRowCount() {
		return matrix.numRows();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		return matrix.getCell(rowIndex, colIndex);
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return matrix.getXSet(column).getName();
	}
	
	@Override
	public Class<?> getColumnClass(int arg0) {
		return CCMatrixCell.class;
	}
	
	public CCMatrixCell getCell(int rowIndex, int colIndex) {
		return matrix.getCell(rowIndex, colIndex);
	}
}
