package invenio.wf.items.vis.query;

import invenio.wf.items.query.QueryController;

import javax.swing.JButton;
import javax.swing.JPanel;

public class QueryResultButtonPanel extends JPanel {
	private String tabName;
	private QueryController controller;
	
	private JButton selectButton;
	private JButton copyClipboardButton;
	private JButton closeButton;
	
	public QueryResultButtonPanel(String tabName) {
		this.tabName = tabName;
		init();
	}
	
	protected void setController(QueryController newController) {
		if (controller != null) {
			// cleanup
			selectButton.removeActionListener(controller);
			copyClipboardButton.removeActionListener(controller);
			closeButton.removeActionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			selectButton.addActionListener(controller);
			copyClipboardButton.addActionListener(controller);
			closeButton.addActionListener(controller);
		}
	}
	
	protected void init() {
//		addResultButtonPanel( tabName );
//	}
//	
//	protected JPanel addResultButtonPanel(String tabName) {
//		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//		
//		panel.add(Box.createHorizontalGlue());
//		panel.add(Box.createHorizontalGlue());
//		panel.add(getSelectButton());
//		panel.add(Box.createHorizontalGlue());
//		panel.add(getCopyClipboardButton());
//		panel.add(Box.createHorizontalGlue());
//		panel.add(getCloseButton());
//		panel.add(Box.createHorizontalGlue());
//		panel.add(Box.createHorizontalGlue());
		
		
		add(getSelectButton());
		add(getCopyClipboardButton());
		add(getCloseButton());
		
//		return panel;
	}
	
	protected JButton getSelectButton() {
		if ( selectButton == null) {
			selectButton = new JButton("Select result");
			selectButton.setActionCommand(tabName);
		}
		return selectButton;
	}
	
	protected JButton getCopyClipboardButton() {
		if ( copyClipboardButton == null) {
			copyClipboardButton = new JButton("Copy to clipboard");
			copyClipboardButton.setActionCommand(tabName);
		}
		return copyClipboardButton;
	}
	
	protected JButton getCloseButton() {
		if ( closeButton == null) {
			closeButton = new JButton("Close tab");
			closeButton.setActionCommand(tabName);
		}
		return closeButton;
	}
}
