package invenio.wf.items.file;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TabReaderConfigView extends JPanel {
	private TabReaderController controller;
	
	private JLabel nodeFileNameLabel;
	private JTextField nodeFileNameTextField;
	private JButton nodeOpenButton;
	
	private JLabel edgeFileNameLabel;
	private JTextField edgeFileNameTextField;
	private JButton edgeOpenButton;
	
	private JLabel graphNameLabel;
	private JTextField graphNameTextField;
	
	
	public TabReaderConfigView() {
		init();
	}

	private void init() {
		add( getNodeFileNameLabel() );
		add( getNodeFileNameTextField() );
		add( getNodeOpenButton() );
		
		add( getEdgeFileNameLabel() );
		add( getEdgeFileNameTextField() );
		add( getEdgeOpenButton() );
		
		add( getGraphNameLabel() );
		add( getGraphNameTextField() );
		
	}
	
	protected void setController(TabReaderController newController) {
		if (controller != null) {
			// cleanup
			getNodeOpenButton().removeActionListener(controller);
			getEdgeOpenButton().removeActionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getNodeOpenButton().addActionListener(controller);
			getEdgeOpenButton().addActionListener(controller);
		}
	}
	
	protected JLabel getNodeFileNameLabel() {
		if ( nodeFileNameLabel == null ) {
			nodeFileNameLabel = new JLabel("Node file:");
		}
		return nodeFileNameLabel;
	}
	
	protected JTextField getNodeFileNameTextField() {
		if ( nodeFileNameTextField == null ) {
			nodeFileNameTextField = new JTextField("Enter file name or click button");
		}
		return nodeFileNameTextField;
	}
	
	protected JButton getNodeOpenButton() {
		if ( nodeOpenButton == null ) {
			nodeOpenButton = new JButton("Open...");
		}
		return nodeOpenButton;
	}
	
	protected JLabel getEdgeFileNameLabel() {
		if ( edgeFileNameLabel == null ) {
			edgeFileNameLabel = new JLabel("Edge file:");
		}
		return edgeFileNameLabel;
	}
	
	protected JTextField getEdgeFileNameTextField() {
		if ( edgeFileNameTextField == null ) {
			edgeFileNameTextField = new JTextField("Enter file name or click button");
		}
		return edgeFileNameTextField;
	}
	
	protected JButton getEdgeOpenButton() {
		if ( edgeOpenButton == null ) {
			edgeOpenButton = new JButton("Open...");
		}
		return edgeOpenButton;
	}
	
	protected JLabel getGraphNameLabel() {
		if ( graphNameLabel == null ) {
			graphNameLabel = new JLabel("Graph name:");
		}
		return graphNameLabel;
	}
	
	protected JTextField getGraphNameTextField() {
		if ( graphNameTextField == null ) {
			graphNameTextField = new JTextField("g1");
		}
		return graphNameTextField;
	}
}
