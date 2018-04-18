package invenio.wf.items.vis.query;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.wf.items.query.QueryResult;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import qng.tabular.Table;


// TODO: stop actions before updating them
public class QueryResultController implements ListSelectionListener {
	
	protected QueryResultModel model;
	protected QueryResultView shape;
	protected QueryGraphSession graphController;
	
	public QueryResultController() {
		model = new QueryResultModel();
		graphController = new QueryGraphSession();
	}
	
	public void setShape(QueryResultView shape) {
		this.shape = shape;
		graphController.setShape( shape.getGraphShape() );
	}
	
	public QueryResultView getShape() {
		return shape;
	}
	
	public QueryGraphSession getGraphController() {
		return graphController;
	}
	
	public void updateModel(QueryResult qRes){
		Table table = null;
		String query = "";
		
		if (qRes != null) {
			if (qRes.getQuery() != null)
				query = qRes.getQuery();
			table = qRes.getTable();
		}
//		graphController.updateModel(g);
		
		getShape().getQueryTextArea().setText(query);
		
		// query result table
		QueryResultTableModel qResModel = new QueryResultTableModel();
		qResModel.setTable( table );
		model.setQueryResultTableModel(qResModel);
		shape.getResultTable().setModel(qResModel);
		
		// element table
		InvenioElementTableModel ieTableModel = new InvenioElementTableModel();
		model.setInvenioElementTableModel(ieTableModel);
		shape.getElementTable().setModel(ieTableModel);
				
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		 if (e.getValueIsAdjusting())
	            return;
		if (e.getSource() == shape.getResultTable().getSelectionModel() ||
				e.getSource() == shape.getResultTable().getColumnModel().getSelectionModel() )
			updateResultTableSelection();
	}

	private void updateResultTableSelection() {
		int col = shape.getResultTable().getSelectedColumn();
		int row = shape.getResultTable().getSelectedRow();
		
		if ( col >= 0 && row >= 0 ) {
			Object selObj = model.getQueryResultTableModel().getValueAt(row, col);

			if ( selObj instanceof InvenioGraph)
				setGraph( (InvenioGraph) selObj);
			else if ( selObj instanceof InvenioElement )
				setElement( (InvenioElement) selObj);
		}
	}

	private void setElement(InvenioElement invElement) {
		shape.showPanel( QueryResultView.ELEMENT_PANEL);
		model.getInvenioElementTableModel().setInvenioElement(invElement);
		
	}

	private void setGraph(InvenioGraph graph) {
		shape.showPanel( QueryResultView.GRAPH_PANEL);
		graphController.updateModel(graph);

	}
	
}
