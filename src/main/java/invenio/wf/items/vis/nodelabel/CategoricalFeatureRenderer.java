package invenio.wf.items.vis.nodelabel;

import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.wf.items.vis.nodelabel.MatrixSlice.SliceStats;
import invenio.wf.test.HeatMapTest;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class CategoricalFeatureRenderer extends JComponent
                           implements TableCellRenderer {
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;
    Color[] colors = null;
    CategoricalFeature feature = null;
    int verticalInset = 1;
    int horizontalInset = 2;
    int barGap = 2;

    public CategoricalFeatureRenderer(boolean isBordered) {
        this.isBordered = isBordered;
//        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object feature,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {


        	this.feature = (CategoricalFeature)feature;

//        if (feature != null) {
        	//setText(f.toString());

//	        SliceStats stats = newCell.getSlice().getStats();
//	        Color newColor = HeatMapTest.dataPointToColor(newCell.size(), 0, stats.getMaxEntries());
//	        setBackground(newColor);
//	        setText( "" + newCell.size() );
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
	        
	        if (this.feature != null) {
		        CategoricalFeatureDescriptor fd = this.feature.getFeatureDescriptor();
		        double[] probs = this.feature.getProbabilities();
		        int selIndex = this.feature.getSelectedIndex();
		        
		        StringBuffer sb = new StringBuffer();
		        sb.append("<html>");
		        for (int i = 0; i < probs.length; i++) {
		        	if (i > 0)
		        		sb.append("<br/>");
		        	if (i == selIndex)
		        		sb.append( "<strong>");
		        	sb.append( fd.getCategory( i ) );
		        	sb.append( String.format(": %1$.2f", probs[i]));
		        	if (i == selIndex)
		        		sb.append( "</strong>");
		        }
		        sb.append("</html>");
		        
		        setToolTipText( sb.toString() );
        }
        return this;
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (feature == null)
			return;
		
		final int xStart = horizontalInset;
		final int xEnd = getWidth() - horizontalInset;
		final int yStart = verticalInset;
		final int yEnd = getHeight() - verticalInset;
		final int height = getHeight() - 2 * verticalInset;
		final int width = getWidth() - 2 * horizontalInset;
		
		final double[] probs = feature.getProbabilities();
		Color[] colors = FeatureToColorMapper.createColorArray( probs.length );
		
		double barWidth = (width - (probs.length - 1) * barGap ) / probs.length;
		if (barWidth < 1)
			barWidth = 1;
		
		for (int i = 0; i < probs.length; i++) {
			double p = probs[i];
			double barStartX = xStart + (barWidth + barGap) * i;
			double barHeight = (int) (height * p);
			if (barHeight < 1 && p > 0)
				barHeight = 1;
			double barStartY = yStart + height - barHeight;
			
			g.setColor( colors[i] );
			g.fillRect( (int)barStartX, (int) barStartY, (int)barWidth, (int) barHeight );
		}
	}
    
    

}
