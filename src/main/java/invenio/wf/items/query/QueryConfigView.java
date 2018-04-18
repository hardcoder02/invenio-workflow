package invenio.wf.items.query;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class QueryConfigView extends JPanel {
	
	private QueryConfigController controller;
	
	private QueryTextPanel queryPanel;

	private JPanel queryButtonPanel;
	
	private JButton saveButton;
	private JButton openButton;

	private JCheckBox packageCheckBox;
	
	private JLabel columnLabel;
	private JSpinner columnSpinner;
	
	private JLabel rowLabel;
	private JSpinner rowSpinner;
	

	
	public QueryConfigView() {
		init();
	}
	
	protected void init() {
		setLayout( new BorderLayout() );
		setPreferredSize(new Dimension(1200, 900));
		//setLocation(200, 100);
		
		add(getQueryPanel( ), BorderLayout.CENTER);
		add(getQueryButtonPanel(), BorderLayout.SOUTH);		
	}
	
	protected void setController(QueryConfigController newController) {
		if (controller != null) {
			// cleanup
			getOpenButton().removeActionListener(controller);
			getSaveButton().removeActionListener(controller);
			getPackageCheckBox().removeItemListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getOpenButton().addActionListener(controller);
			getSaveButton().addActionListener(controller);
			getPackageCheckBox().addItemListener(controller);
		}
	}
	
	
	protected QueryTextPanel getQueryPanel() {
		if ( queryPanel == null ) {
			queryPanel = new QueryTextPanel( );
		}
		return queryPanel;
	}
	
	protected JPanel getQueryButtonPanel() {
		if (queryButtonPanel == null) {
			queryButtonPanel = new JPanel();
			queryButtonPanel.setLayout(new BoxLayout(queryButtonPanel, BoxLayout.X_AXIS));
			//Titled borders
			TitledBorder border = BorderFactory.createTitledBorder("Controls");
			queryButtonPanel.setBorder(border);
			
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getOpenButton());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getSaveButton());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getPackageCheckBox());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getColumnLabel());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getColumnSpinner());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getRowLabel());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(getRowSpinner());
			queryButtonPanel.add(Box.createHorizontalGlue());
			queryButtonPanel.add(Box.createHorizontalGlue());
		}
		
		return queryButtonPanel;
	}
	
	protected JButton getOpenButton() {
		if (openButton == null) {
			openButton = new JButton("Open query...");
		}
		return openButton;
	}
	
	protected JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Save as...");
		}
		return saveButton;
	}
	
	protected JCheckBox getPackageCheckBox() {
		if ( packageCheckBox == null ) {
			packageCheckBox = new JCheckBox("Extract cell", false);
		}
		return packageCheckBox;
	}
	
	protected JLabel getColumnLabel() {
		if ( columnLabel == null ) {
			columnLabel = new JLabel("Select column:");
		}
		return columnLabel;
	}
	
	protected JSpinner getColumnSpinner() {
		if ( columnSpinner == null ) {
			SpinnerModel model =
			        new SpinnerNumberModel(0, //initial value
			                               0, //min
			                               Integer.MAX_VALUE, //max
			                               1);                //step
			
			columnSpinner = new JSpinner(model);
			columnSpinner.setEnabled(false);
		}
		return columnSpinner;
	}
	
	protected JLabel getRowLabel() {
		if ( rowLabel == null ) {
			rowLabel = new JLabel("Select row:");
		}
		return rowLabel;
	}
	
	protected JSpinner getRowSpinner() {
		if ( rowSpinner == null ) {
			SpinnerModel model =
			        new SpinnerNumberModel(0, //initial value
			                               0, //min
			                               Integer.MAX_VALUE, //max
			                               1);                //step
			
			rowSpinner = new JSpinner(model);
			rowSpinner.setEnabled(false);
		}
		return rowSpinner;
	}
}
