package invenio.wf.items.vis.nodelabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;

import invenio.operator.data.CategoricalFeature;
import invenio.visual.DisplayShape;
import invenio.wf.ui.components.VerticalTableHeaderCellRenderer;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class LabelResultView extends JPanel {
	
	private LabelResultController controller;
	
	private static final String[] MATRIX_CHOICE_VALUES = {"GT vs Model 1", "GT vs Model 2", "Model 1 vs Model 2"};
	
	private JSplitPane mainSplitPane;
	private JSplitPane tableSplitPane;

	private JPanel detailPanel;
	private JPanel colorPanel;
	private JScrollPane detailScrollPane;
	private JTable detailTable;
	
	private JPanel statPanel;
	private JLabel nodeCountLabel;
	private JLabel nodeCountValueLabel;
	private JLabel edgeCountLabel;
	private JLabel edgeCountValueLabel;
	private JLabel accuracy1Label;
	private JLabel accuracy1ValueLabel;
	private JLabel accuracy2Label;
	private JLabel accuracy2ValueLabel;
	
	private JPanel matrixPanel;
	private JLabel matrixChoiceLabel;
	private JComboBox matrixChoiceComboBox;
	private JCheckBox matrixHideCheckBox;
	
	private JScrollPane matrixScrollPane;
	private JTable matrixTable;
	
	private DisplayShape graphShape;
	
	public LabelResultView() {
		init();
	}

	private void init() {
		//Create a split pane with the two scroll panes in it.
		add ( getMainSplitPane() );
		
//		setLayout(new BorderLayout());
//		add( getMatrixTable(), BorderLayout.SOUTH);
//		add( getDetailTable(), BorderLayout.WEST);
//		add( getGraphShape(), BorderLayout.CENTER);
	}
	
	public void setController(LabelResultController newController) {
		graphShape.setController( (newController == null) ? null : newController.getGraphController() );
		
		if (controller != null) {
			// cleanup
			getMatrixChoiceComboBox().removeActionListener(controller);
			getMatrixHideCheckBox().removeItemListener(controller);
			getMatrixTable().getSelectionModel().removeListSelectionListener(controller);
			getMatrixTable().getColumnModel().getSelectionModel().removeListSelectionListener(controller);
			getDetailTable().getSelectionModel().removeListSelectionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getMatrixChoiceComboBox().addActionListener(controller);
			getMatrixHideCheckBox().addItemListener(controller);
			getMatrixTable().getSelectionModel().addListSelectionListener(controller);
	        getMatrixTable().getColumnModel().getSelectionModel().addListSelectionListener(controller);
			getDetailTable().getSelectionModel().addListSelectionListener(controller);
		}
	}
	
	protected JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                getTableSplitPane(), getGraphShape());
//			splitPane.setOneTouchExpandable(true);
			mainSplitPane.setDividerLocation(150);

//			//Provide minimum sizes for the two components in the split pane
//			Dimension minimumSize = new Dimension(100, 50);
//			listScrollPane.setMinimumSize(minimumSize);
//			pictureScrollPane.setMinimumSize(minimumSize);
		}
		return mainSplitPane;
	}
	
	protected JSplitPane getTableSplitPane() {
		if ( tableSplitPane == null) {
			tableSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
					getDetailPanel(), getMatrixPanel() );
		}
		return tableSplitPane;
	}
	
	protected JPanel getMatrixPanel() {
		if (matrixPanel == null) {
			matrixPanel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.insets = new Insets(4, 4, 4, 4);
			c.gridx = 0;
			c.gridy = 0;
			matrixPanel.add( getMatrixChoiceLabel(), c );
			
			c.gridx = 1;
			matrixPanel.add( getMatrixChoiceComboBox(), c );
			
			c.gridx = 2;
			matrixPanel.add( getMatrixHideCheckBox(), c );
						
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.weighty = 1;
			c.gridwidth = GridBagConstraints.REMAINDER;
			matrixPanel.add( getMatrixScrollPane(), c);
		}
		return matrixPanel;
	}
	
	protected JLabel getMatrixChoiceLabel() {
		if ( matrixChoiceLabel == null ) {
			matrixChoiceLabel = new JLabel("Choose graphs:");
		}
		return matrixChoiceLabel;
	}
	
	protected JComboBox getMatrixChoiceComboBox() {
		if ( matrixChoiceComboBox == null ) {
			matrixChoiceComboBox = new JComboBox(MATRIX_CHOICE_VALUES);
			matrixChoiceComboBox.setEditable(false);
			matrixChoiceComboBox.setActionCommand("UPDATE_SLICE");
		}
		return matrixChoiceComboBox;
	}
	
	protected JCheckBox getMatrixHideCheckBox() {
		if ( matrixHideCheckBox == null ) {
			matrixHideCheckBox = new JCheckBox("Hide other nodes", false);
		}
		return matrixHideCheckBox;
	}
	
	protected JScrollPane getMatrixScrollPane() {
		if ( matrixScrollPane == null ) {
			matrixScrollPane = new JScrollPane(getMatrixTable(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return matrixScrollPane;
	}
	
	protected JTable getMatrixTable() {
		if ( matrixTable == null ) {
			matrixTable = new JTable();
			matrixTable.setFillsViewportHeight(false);
			matrixTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			matrixTable.setCellSelectionEnabled(true);
			matrixTable.setDefaultRenderer(MatrixCell.class, new HeatMapColorRenderer(false));
		}
		return matrixTable;
	}

	protected JPanel getDetailPanel() {
		if (detailPanel == null) {
			detailPanel = new JPanel();
			detailPanel.setLayout(new BorderLayout());
			detailPanel.add( getColorPanel(), BorderLayout.NORTH);
			detailPanel.add( getDetailScrollPane(), BorderLayout.CENTER);
			detailPanel.add( getStatPanel(), BorderLayout.SOUTH );
		}
		return detailPanel;
	}
	

	protected JPanel getColorPanel() {
		if (colorPanel == null) {
			colorPanel = new JPanel();
			//Titled borders
			TitledBorder border = BorderFactory.createTitledBorder("Legend");
			colorPanel.setBorder(border);
			colorPanel.setLayout( new FlowLayout( FlowLayout.LEFT) );
		}
		return colorPanel;
	}
	
	protected JScrollPane getDetailScrollPane() {
		if ( detailScrollPane == null ) {
			detailScrollPane = new JScrollPane(getDetailTable(),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return detailScrollPane;
	}
	
	protected JTable getDetailTable() {
		if ( detailTable == null ) {
			detailTable = new JTable();
			detailTable.setFillsViewportHeight(true);
			detailTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			detailTable.setCellSelectionEnabled(true);
			detailTable.setDefaultRenderer(CategoricalFeature.class, new CategoricalFeatureRenderer(true));
		}
		return detailTable;
	}
	
	protected JPanel getStatPanel() {
		if (statPanel == null) {
			statPanel = new JPanel(new GridBagLayout());
			//Titled borders
			TitledBorder border = BorderFactory.createTitledBorder("Stats");
			statPanel.setBorder(border);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.insets = new Insets(4, 4, 4, 4);
			c.gridx = 0;
			c.gridy = 0;
			statPanel.add( getNodeCountLabel(), c );
			
			c.gridx = 1;
			statPanel.add( getNodeCountValueLabel(), c );
			
			c.gridx = 0;
			c.gridy = 1;
			statPanel.add( getEdgeCountLabel(), c );
			
			c.gridx = 1;
			statPanel.add( getEdgeCountValueLabel(), c );
			
			c.gridx = 0;
			c.gridy = 2;
			statPanel.add( getAccuracy1Label(), c );
			
			c.gridx = 1;
			statPanel.add( getAccuracy1ValueLabel(), c );
			
			c.gridx = 0;
			c.gridy = 3;
			statPanel.add( getAccuracy2Label(), c );
			
			c.gridx = 1;
			statPanel.add( getAccuracy2ValueLabel(), c );
		}
		return statPanel;
	}
	
	protected JLabel getNodeCountLabel() {
		if ( nodeCountLabel == null ) {
			nodeCountLabel = new JLabel("Node count:");
		}
		return nodeCountLabel;
	}
	
	protected JLabel getNodeCountValueLabel() {
		if ( nodeCountValueLabel == null ) {
			nodeCountValueLabel = new JLabel("");
		}
		return nodeCountValueLabel;
	}
	
	protected JLabel getEdgeCountLabel() {
		if ( edgeCountLabel == null ) {
			edgeCountLabel = new JLabel("Edge count:");
		}
		return edgeCountLabel;
	}
	
	protected JLabel getEdgeCountValueLabel() {
		if ( edgeCountValueLabel == null ) {
			edgeCountValueLabel = new JLabel("");
		}
		return edgeCountValueLabel;
	}
	
	protected JLabel getAccuracy1Label() {
		if ( accuracy1Label == null ) {
			accuracy1Label = new JLabel("Model 1 accuracy:");
		}
		return accuracy1Label;
	}
	
	protected JLabel getAccuracy1ValueLabel() {
		if ( accuracy1ValueLabel == null ) {
			accuracy1ValueLabel = new JLabel("");
		}
		return accuracy1ValueLabel;
	}
	
	protected JLabel getAccuracy2Label() {
		if ( accuracy2Label == null ) {
			accuracy2Label = new JLabel("Model 2 accuracy:");
		}
		return accuracy2Label;
	}
	
	protected JLabel getAccuracy2ValueLabel() {
		if ( accuracy2ValueLabel == null ) {
			accuracy2ValueLabel = new JLabel("");
		}
		return accuracy2ValueLabel;
	}
	
	
	protected DisplayShape getGraphShape() {
		if ( graphShape == null ) {
			graphShape = new DisplayShape();
		}
		return graphShape;
	}
	
	protected void updateColorPanel(String[] categories) {
		getColorPanel().removeAll();
		Color[] colors = FeatureToColorMapper.createColorArray(categories.length);
		
		for (int i = 0; i < categories.length; i++) {
			String cat = categories[i];
			getColorPanel().add(new JLabel(cat));
			JPanel p = new JPanel();
			p.setBackground( colors[i] );
			getColorPanel().add(p);
		}
	}

}
