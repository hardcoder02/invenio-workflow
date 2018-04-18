package invenio.wf.items.vis.clustering;

import invenio.data.InvenioElementSet;
import invenio.data.InvenioGroupElementSet;
import invenio.wf.items.vis.nodelabel.Matrix;
import invenio.wf.items.vis.nodelabel.MatrixCell;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ClusterTableModel extends AbstractTableModel implements TableModel {
	private final String[] COLUMN_NAMES = {"Cluster", "Size"};
	private final List<InvenioElementSet> sets = new ArrayList<InvenioElementSet>();
	
	public ClusterTableModel() {
		
	}
	
	public void setSets(InvenioGroupElementSet ges) {
		sets.clear();
		for (InvenioElementSet set : ges.getSets() ) {
			sets.add(set);
		}
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return sets.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		InvenioElementSet set = sets.get(rowIndex);
		switch (colIndex) {
	    	case 0:  return set.getName();
	    	case 1:  return set.getNumVertices();
	    	default: return null;
		}
	}
	
	public InvenioElementSet getInvenioElementSet(int index) {
		return sets.get(index);
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
}
