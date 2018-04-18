package invenio.wf.items.vis.query;

public class QueryResultModel {
	private QueryResultTableModel queryResultTableModel;
	private InvenioElementTableModel invenioElementTableModel;
	
	public QueryResultTableModel getQueryResultTableModel() {
		return queryResultTableModel;
	}

	public void setQueryResultTableModel(QueryResultTableModel queryResultTableModel) {
		this.queryResultTableModel = queryResultTableModel;
	}

	public InvenioElementTableModel getInvenioElementTableModel() {
		return invenioElementTableModel;
	}

	public void setInvenioElementTableModel(
			InvenioElementTableModel invenioElementTableModel) {
		this.invenioElementTableModel = invenioElementTableModel;
	}
	
	
}
