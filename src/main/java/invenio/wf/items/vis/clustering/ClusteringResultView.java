package invenio.wf.items.vis.clustering;

import invenio.visual.DisplayShape;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class ClusteringResultView extends JPanel {

	private ClusteringResultController controller;
	
	private JPanel infoPanel;
	private JLabel maxSizeLabel;
	private JLabel minSizeLabel;
	private JLabel avgSizeLabel;
	
	private JPanel clusterPanel;
	private JLabel clusterSelectLabel;
	private JScrollPane clusterSelectScrollPane;
	private JList clusterSelectList;
	
	private JTable clusterSelectTable;
	
	private JCheckBox showAllCheckbox;
	
	private DisplayShape graphShape;
	
	
	public ClusteringResultView() {
		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		add( getClusterPanel(), BorderLayout.WEST );
		add( getShowAllCheckBox(), BorderLayout.SOUTH );

		add( getGraphShape(), BorderLayout.CENTER );
	}
	
	public void setController(ClusteringResultController newController) {
		
		getGraphShape().setController( (newController == null) ? null : newController.getGraphController() );
		
		if (controller != null) {
			// cleanup
			getClusterSelectTable().getSelectionModel().removeListSelectionListener(controller);
			getShowAllCheckBox().removeItemListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getClusterSelectTable().getSelectionModel().addListSelectionListener(controller);
			getShowAllCheckBox().addItemListener(controller);
		}
	}
	
	private JPanel getInfoPanel() {
		if (infoPanel == null) {
			infoPanel = new JPanel();
			//Titled borders
			TitledBorder border = BorderFactory.createTitledBorder("Stats");
			infoPanel.setBorder(border);
			infoPanel.add(getMinSizeLabel());
			infoPanel.add(getMaxSizeLabel());
			infoPanel.add(getAvgSizeLabel());
		}
		return infoPanel;
	}

	protected JLabel getMaxSizeLabel() {
		if ( maxSizeLabel == null ) {
			maxSizeLabel = new JLabel("Max Size:");
		}
		return maxSizeLabel;
	}
	
	protected JLabel getMinSizeLabel() {
		if ( minSizeLabel == null ) {
			minSizeLabel = new JLabel("Min Size:");
		}
		return minSizeLabel;
	}
	
	protected JLabel getAvgSizeLabel() {
		if ( avgSizeLabel == null ) {
			avgSizeLabel = new JLabel("Avg Size:");
		}
		return avgSizeLabel;
	}
	
	protected JPanel getClusterPanel() {
		if (clusterPanel == null) {
			clusterPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			clusterPanel.add( getInfoPanel(), c );
			
			c.gridy = 1;
			clusterPanel.add( getClusterSelectLabel(), c );
			
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weighty = 1;
			clusterPanel.add( getClusterSelectScrollPane(), c );
		}
		return clusterPanel;
	}
	
	protected JLabel getClusterSelectLabel() {
		if ( clusterSelectLabel == null ) {
			clusterSelectLabel = new JLabel("Select cluster:");
		}
		return clusterSelectLabel;
	}
	
	protected JScrollPane getClusterSelectScrollPane() {
		if ( clusterSelectScrollPane == null ) {
			clusterSelectScrollPane = new JScrollPane(clusterPanel.add( getClusterSelectTable() ),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return clusterSelectScrollPane;
	}
	
	protected JList getClusterSelectList() {
		if ( clusterSelectList == null ) {
			clusterSelectList = new JList();
		}
		return clusterSelectList;
	}
	
	protected JTable getClusterSelectTable() {
		if (clusterSelectTable == null) {
			clusterSelectTable = new JTable(new ClusterTableModel() );
			clusterSelectTable.setFillsViewportHeight(true);
			clusterSelectTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			clusterSelectTable.setRowSelectionAllowed(true);
		}
		return clusterSelectTable;
	}
	
	protected JCheckBox getShowAllCheckBox() {
		if ( showAllCheckbox == null ) {
			showAllCheckbox = new JCheckBox("Show all nodes", true);
		}
		return showAllCheckbox;
	}
	
	protected DisplayShape getGraphShape() {
		if ( graphShape == null ) {
			graphShape = new DisplayShape();
		}
		return graphShape;
	}

	public void setMinLabelValue(String minSize) {
		getMinSizeLabel().setText("Min size: " + minSize);
	}
	
	public void setMaxLabelValue(String maxSize) {
		getMaxSizeLabel().setText("Max size: " + maxSize);
	}
	
	public void setAvgLabelValue(String avgSize) {
		getAvgSizeLabel().setText("Avg size: " + avgSize);
	}

}
