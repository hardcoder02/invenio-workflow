package invenio.wf.items.vis.query;

import invenio.data.InvenioElement;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.MissingElementException;
import invenio.wf.util.FeatureUtils;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class InvenioElementTableModel extends AbstractTableModel implements TableModel {
	private InvenioElement element;
	private ComparableFeatureContainer container;
	
	public InvenioElementTableModel() {
	}
	
	public void setInvenioElement(InvenioElement element) {
		this.element = element;
		container = null;
		if ( element != null) {
			try {
				container = FeatureUtils.getFeatureContainer(element, false);
			}
			catch (DataFormatException ex) {// won't happen
				ex.printStackTrace();
			}
		}
		fireTableDataChanged();
	}
	
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (container != null) {
			int size = container.getSchema().size();
			return size;
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		if (container == null)
			return null;
		
		// attribute name
		if (colIndex == 0) {
			return container.getSchema().getFeatureDescriptor( rowIndex ).getName();
		}
		else if (colIndex == 1){
			// attribute value
			if (container.hasFeature( rowIndex)) {
				try {
					return container.getFeature( rowIndex );
				}
				catch (MissingElementException ex) {	// won't happen
					ex.printStackTrace();
					return null;
				}
			}
			else
				return null;
		}
		else if (colIndex == 2) {
			// attribute type
//			try {
				return container.getSchema().getFeatureDescriptor( rowIndex ).getTypeDesc();
//			}
//			catch (MissingElementException ex) {	// won't happen
//				ex.printStackTrace();
//				return null;
//			}
		}
		else {
			throw new IndexOutOfBoundsException("Column: " + colIndex + " does not exist");
		}
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
        case 0:  return "Attribute";
        case 1:  return "Value";
        case 2:  return "Type";
        default: throw new IndexOutOfBoundsException("Column: " + column + " does not exist");
		}
	}
}
