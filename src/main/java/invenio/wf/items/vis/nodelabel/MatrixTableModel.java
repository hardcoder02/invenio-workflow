package invenio.wf.items.vis.nodelabel;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MatrixTableModel extends AbstractTableModel implements TableModel {

	private final Matrix matrix;
	private int curSlice = 0;
	
	public MatrixTableModel(Matrix matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public int getColumnCount() {
		return matrix.getHeader().length;
	}

	@Override
	public int getRowCount() {
		return matrix.getSlice(curSlice).size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		return matrix.getSlice(curSlice).getCell(rowIndex, colIndex);
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return matrix.getHeader()[column];
	}
	
	@Override
	public Class<?> getColumnClass(int arg0) {
		return MatrixCell.class;
	}

	public void activateSlice(int sliceIndex) {
		if (sliceIndex < 0 || sliceIndex > matrix.size() )
			throw new ArrayIndexOutOfBoundsException("Slice count: " + matrix.size() + ", cannot activate slice: " + sliceIndex);
		
		curSlice = sliceIndex;
		fireTableDataChanged();
	}
	
	public MatrixCell getCell(int rowIndex, int colIndex) {
		return matrix.getSlice(curSlice).getCell(rowIndex, colIndex);
	}
}
