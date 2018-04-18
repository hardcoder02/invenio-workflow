package invenio.wf.items.vis.nodelabel;

import invenio.operator.data.CategoricalFeature;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class DetailTableModel extends AbstractTableModel implements TableModel {

	private final List<MatrixCell> cells = new ArrayList<MatrixCell>();
	
	public DetailTableModel() {
	}
	
	public void setCells(List<MatrixCell> newCells) {
		cells.clear();
		cells.addAll(newCells);
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return getTotalCellSize();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		
		BaseEntry entry = getBaseEntry(rowIndex);
		switch (colIndex) {
        	case 0:  return entry.getId();
        	case 1:  return entry.getFeature(0).getSelectedValue();
        	case 2:  return entry.getFeature(1).getSelectedValue();
        	case 3:  return entry.getFeature(2).getSelectedValue();
        	case 4:  return entry.getFeature(0);
        	case 5:  return entry.getFeature(1);
        	case 6:  return entry.getFeature(2);
        	default: return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
	    	case 0:  return "id";
	    	case 1:  return "Gt";
	    	case 2:  return "Model1";
	    	case 3:  return "Model2";
	    	case 4:  return "Gt-distr";
	    	case 5:  return "Model1-distr";
	    	case 6:  return "Model2-distr";
	    	default: return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex < 4)
			return String.class;
		else
			return CategoricalFeature.class;
	}

	private int getTotalCellSize() {
		int size = 0;
		for (MatrixCell c : cells) {
			size += c.size();
		}
		
		return size;
	}
	
	public BaseEntry getBaseEntry(int index) {
		for (MatrixCell c : cells) {
			if ( index < c.size() )
				return c.get(index);
			else
				index -= c.size();
		}
		throw new ArrayIndexOutOfBoundsException("Entry does not exist");
	}
}
