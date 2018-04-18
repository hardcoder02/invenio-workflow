package invenio.wf.items.query;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QueryTextPanel extends JPanel {
	
	JScrollPane queryScrollPane = null;
	JTextArea queryTextArea;
	
	public QueryTextPanel() {
		init();
	}

	private void init() {
		setLayout( new BorderLayout() );
		add(getQueryScrollPane(), BorderLayout.CENTER);
	}

	private JScrollPane getQueryScrollPane() {
		if (queryScrollPane == null) {
			queryScrollPane = new JScrollPane(getQueryTextArea(),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		}
		return queryScrollPane;
	}

	private JTextArea getQueryTextArea() {
		if (queryTextArea == null) {
			queryTextArea = new JTextArea();
		}
		return queryTextArea;
	}
	
	
	public String getQuery() {
		return getQueryTextArea().getText();
	}
	
	public void setQuery( String q ) {
		getQueryTextArea().setText(q);
	}

}