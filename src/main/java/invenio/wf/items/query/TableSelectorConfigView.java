package invenio.wf.items.query;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class TableSelectorConfigView extends JPanel {
	private TableSelectorController controller;
	
	private JLabel columnLabel;
	private JSpinner columnSpinner;
	
	private JLabel rowLabel;
	private JSpinner rowSpinner;
	
	public TableSelectorConfigView() {
		init();
	}
	
	protected void setController(TableSelectorController newController) {
		if (controller != null) {
			// cleanup
		}
		controller = newController;
		if (controller != null) {
			// setup
		}
	}
	
	protected void init() {
		add( getColumnLabel() );
		add( getColumnSpinner() );
		
		add( getRowLabel() );
		add( getRowSpinner() );
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
		}
		return rowSpinner;
	}
}
