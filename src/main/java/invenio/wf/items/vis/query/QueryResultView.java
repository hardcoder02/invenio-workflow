package invenio.wf.items.vis.query;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import invenio.visual.DisplayShape;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class QueryResultView extends JPanel {
	public static final String GRAPH_PANEL = "GRAPH";
	public static final String ELEMENT_PANEL = "ELEMENT";
	public static final String ATTRIBUTE_PANEL = "ATTRIBUTE";
	
	private QueryResultController controller;
	
	private JSplitPane mainSplitPane;
	private JSplitPane tableSplitPane;
	
	private JScrollPane resultScrollPane;
	private JTable resultTable;
	
	private JScrollPane queryScrollPane;
	private JTextArea queryTextArea;
	
	private JPanel cardPanel;
	private DisplayShape graphShape;
	private JPanel elementPanel;
	private JScrollPane elementScrollPane;
	private JTable elementTable;
	
	public QueryResultView() {
		init();
	}
	
	protected void init() {
		setLayout(new BorderLayout());
		add ( getMainSplitPane(), BorderLayout.CENTER );
	}
	
	
	public void setController(QueryResultController newController) {
		
		getGraphShape().setController( (newController == null) ? null : newController.getGraphController() );
		
		if (controller != null) {
			// cleanup
			getResultTable().getSelectionModel().removeListSelectionListener(controller);
			getResultTable().getColumnModel().getSelectionModel().removeListSelectionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getResultTable().getSelectionModel().addListSelectionListener(controller);
			getResultTable().getColumnModel().getSelectionModel().addListSelectionListener(controller);
		}
	}
	
	protected JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                getTableSplitPane(), getQueryScrollPane());
			mainSplitPane.setDividerLocation(400);

//			//Provide minimum sizes for the two components in the split pane
//			Dimension minimumSize = new Dimension(100, 50);
//			listScrollPane.setMinimumSize(minimumSize);
//			pictureScrollPane.setMinimumSize(minimumSize);
		}
		return mainSplitPane;
	}
	
	protected JSplitPane getTableSplitPane() {
		if ( tableSplitPane == null) {
			tableSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					getResultScrollPane(), getCardPanel() );
			tableSplitPane.setDividerLocation(200);
		}
		return tableSplitPane;
	}


	

//	protected void addQueryResult(Table t, String q) {
//		String tabName = "Query" + (++counter);
//		getResultTabbedPane().add( tabName, createResultPane(tabName, t, q) );
//		getResultTabbedPane().revalidate();
//		getResultTabbedPane().repaint();
//	}
//		
//	protected JPanel createResultPane(String tabName, Table t, String q) {
//		JPanel result = new JPanel();
//		result.setLayout( new BorderLayout() );
//		
//		result.add(createResultSplitPane(t, q), BorderLayout.CENTER);
////		result.add(createResultButtonPanel( tabName ), BorderLayout.SOUTH);
//		
//		return result;
//	}
	
	
	
	private JScrollPane getResultScrollPane() {
		if (resultScrollPane == null) {
			resultScrollPane = new JScrollPane(getResultTable(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		}
		return resultScrollPane;
	}

	protected JTable getResultTable() {
		if (resultTable == null) {
			resultTable = new JTable();
			resultTable.setFillsViewportHeight(true);
			resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			resultTable.setColumnSelectionAllowed(false);
			resultTable.setRowSelectionAllowed(false);
			resultTable.setCellSelectionEnabled(true);
		}
		return resultTable;
	}
	
	protected JScrollPane getQueryScrollPane() {
		if (queryScrollPane == null) {
			queryScrollPane = new JScrollPane(getQueryTextArea(),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
			);
		}
		return queryScrollPane;
	}
	
	protected JTextArea getQueryTextArea() {
		if (queryTextArea == null) {
			queryTextArea = new JTextArea();
			queryTextArea.setEditable(false);
		}
		return queryTextArea;
	}
	
//	protected JPanel createResultButtonPanel(String tabName) {
//		QueryResultButtonPanel panel = new QueryResultButtonPanel(tabName);
//		panel.setController(controller);
//		
//		return panel;
//	}
	
	protected JPanel getCardPanel() {
		if (cardPanel == null) {
			cardPanel = new JPanel(new CardLayout());
			cardPanel.add( getGraphShape(), GRAPH_PANEL);
			cardPanel.add( getElementPanel(), ELEMENT_PANEL);
		}
		return cardPanel;
	}
	
	protected JPanel getElementPanel() {
		if (elementPanel == null) {
			elementPanel = new JPanel(new BorderLayout());
			elementPanel.add( getElementScrollPane(), BorderLayout.CENTER);
		}
		return elementPanel;
	}
	
	private JScrollPane getElementScrollPane() {
		if (elementScrollPane == null) {
			elementScrollPane = new JScrollPane(getElementTable(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return elementScrollPane;
	}

	protected JTable getElementTable() {
		if (elementTable == null) {
			elementTable = new JTable();
			elementTable.setFillsViewportHeight(true);
			elementTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			elementTable.setCellSelectionEnabled(true);
		}
		return elementTable;
	}
	
	protected DisplayShape getGraphShape() {
		if ( graphShape == null ) {
			graphShape = new DisplayShape();
		}
		return graphShape;
	}
	
	protected void showPanel(String panelName) {
		CardLayout cl = (CardLayout)(getCardPanel().getLayout());
	    cl.show(getCardPanel(), panelName);
	}
}
