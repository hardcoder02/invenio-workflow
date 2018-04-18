package invenio.operator.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QueryPanel extends JPanel {
	private String query;
	
	JScrollPane queryScrollPane = null;
	JTextArea queryTextArea;
	
	public QueryPanel(String query) {
		super();
		this.query = query;
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
			queryTextArea = new JTextArea(query);
		}
		return queryTextArea;
	}
	
	
	public String getQuery() {
		if (queryTextArea != null) {
			return queryTextArea.getText();
		}
		else
			return null;
	}
	
	public void setQuery( String q ) {
		if (queryTextArea != null)
			queryTextArea.setText(q);
	}

}