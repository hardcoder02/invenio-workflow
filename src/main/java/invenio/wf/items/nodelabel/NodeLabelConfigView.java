package invenio.wf.items.nodelabel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NodeLabelConfigView extends JPanel {
	private NodeLabelController controller;
	
	public static enum NODE_LABEL_ALGORITHMS {
		BASE,
		MAJORITY,
		J48
	}
	
	private JComboBox algorithmComboBox;
	
	private JLabel paramLabel;
	private JScrollPane paramScrollPane;
	private JTextArea paramTextArea;
	
	private JButton openButton;
	
	public NodeLabelConfigView() {
		init();
	}

	private void init() {
		add( getAlgorithmComboBox() );
		add( getParamLabel() );
		add( getParamScrollPane() );
		
		add( getOpenButton() );
	}
	
	protected void setController(NodeLabelController newController) {
		if (controller != null) {
			// cleanup
			getOpenButton().removeActionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getOpenButton().addActionListener(controller);
		}
	}
	
	protected JComboBox getAlgorithmComboBox() {
		if ( algorithmComboBox == null ) {
			algorithmComboBox = new JComboBox(NODE_LABEL_ALGORITHMS.values());
		}
		return algorithmComboBox;
	}
	
	protected JLabel getParamLabel() {
		if ( paramLabel == null ) {
			paramLabel = new JLabel("Parameters:");
		}
		return paramLabel;
	}
	
	protected JScrollPane getParamScrollPane() {
		if ( paramScrollPane == null ) {
			paramScrollPane = new JScrollPane(getParamTextArea(),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		}
		return paramScrollPane;
	}
	
	protected JTextArea getParamTextArea() {
		if ( paramTextArea == null ) {
			paramTextArea = new JTextArea(20, 80);
		}
		return paramTextArea;
	}
	
	protected JButton getOpenButton() {
		if ( openButton == null ) {
			openButton = new JButton("Open...");
		}
		return openButton;
	}
	
}
