package invenio.wf.items.vis.nodelabel;

import invenio.wf.items.vis.nodelabel.MatrixSlice.SliceStats;
import invenio.wf.test.HeatMapTest;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class HeatMapColorRenderer extends JLabel
                           implements TableCellRenderer {
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;
    
    public HeatMapColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object cell,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {

        if (cell != null) {
        	MatrixCell newCell = (MatrixCell)cell;

	        SliceStats stats = newCell.getSlice().getStats();
	        Color newColor = HeatMapTest.dataPointToColor(newCell.size(), 0, stats.getMaxEntries());
	        setBackground(newColor);
	        setText( "" + newCell.size() );
	        if (isBordered) {
	            if (isSelected) {
	                if (selectedBorder == null) {
	                    selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
	                                              table.getSelectionBackground());
	                }
	                setBorder(selectedBorder);
	            } else {
	                if (unselectedBorder == null) {
	                    unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
	                                              table.getBackground());
	                }
	                setBorder(unselectedBorder);
	            }
	        }
	        
	        float percentCell = (float) newCell.size() / stats.getTotalEntries();
	        setToolTipText( String.format(
	        		"Share: %1$.2f", percentCell) );
        }
        return this;
    }

}
