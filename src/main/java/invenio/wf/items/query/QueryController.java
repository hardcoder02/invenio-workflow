package invenio.wf.items.query;

import invenio.data.InvenioGraph;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.parser.TextToLogicalExpressionParser;
import invenio.op.parser.XMLToLogicalExpressionParser;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.log.Logger;

import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.DefaultContext;
import qng.tabular.Table;
import qng.tabular.Tuple;

public class QueryController extends AbstractController<QueryResult>
	implements ItemController<QueryResult> {
	
	private static Logger log = Logger.getInstance();
	
	private QueryConfigController configController;
	
	public QueryController() {
		initController();
	}
	
	protected void initController() {
		QueryConfigView configShape = new QueryConfigView();
		configController = new QueryConfigController();
		configController.setShape(configShape);
		configShape.setController(configController);
	}
	
	@Override
	public boolean hasConfigView() {
		return true;
	}

	@Override
	public boolean hasResultView() {
		return false;
	}

	@Override
	public QueryConfigView getConfigView() {
		if (configController != null) {
			return configController.getShape();
		}
		else
			return null;
	}

	@Override
	public JComponent getResultView() {
		return null;
	}
	

	@Override
	public QueryResult updateResult(Object[] inputs) {
		log.log(this, "parsing query...");
		QueryConfigBackgroundModel configBgModel = configController.updateBackgroundModel();
		
		if ( configBgModel == null)
			return null;
		
		String query = configBgModel.getQuery();
		if ( query == null)
			return null;
		
		Map<String, InvenioGraph> graphMap = new HashMap<String, InvenioGraph>();
		for ( Object o : inputs ) {
			if ( o != null && (o instanceof InvenioGraph) ) {
				InvenioGraph g = (InvenioGraph) o;
				graphMap.put(g.getName(), g);
			}
		}

		try{
			Table t = runQuery(graphMap, query);
			log.log(this, "Query executed, number of rows: " + t.getSize());
			
			QueryResult result = new QueryResult();
			result.setQuery(query);
			result.setTable(t);
			if ( configBgModel.isPackageCell())
				result.setCellObject( packageCell( configBgModel, t) );
			return result;
		}
		catch(Exception e){
			return null;
		}
	}

	protected Table runQuery(Map<String, InvenioGraph> graphMap, String query) {
		QueryConfigView configView = null;
		try {
			configView = getConfigView();
			if (configView != null)
				configView.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			QueryParser<String, LogicalExpression> parser = null;
			
			// XML query
			if ( query.trim().length() > 4 && query.trim().substring(0, 5).equalsIgnoreCase("<?XML") )
				parser = new XMLToLogicalExpressionParser();
			else
				parser = new TextToLogicalExpressionParser();
			
			LogicalExpression exp = parser.parse(query);
			
			CompiledQuery<CompiledElement> comp = QueryService.compileExpression(exp);
		
			Context ctx = new DefaultContext();
			ctx.setVariable(OperationConstants.VAR_GRAPH_STORE, graphMap);
			
			return (Table) QueryService.executeQuery(comp, ctx).getResultValue();
		}
		catch (QueryException ex) {
			JOptionPane.showMessageDialog(configView, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		finally {
			if (configView != null)
				configView.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	
	
	private Object packageCell(QueryConfigBackgroundModel configBgModel, Table t) {
		if (t == null || !configBgModel.isPackageCell() )
			return null;
		
		int row = configBgModel.getRow();
		int col = configBgModel.getColumn();
		log.log(this, "selecting from table, column=" + col + ", row=" + row + "...");
		
		Tuple tuple = t.getTuples().get(row);
		Object ret = tuple.getValue(col);
		log.log(this, "value selected: " + ret);
		return ret;

	}

	
	
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == displayButton) {
			displayGraph();
		}
		else if(e.getSource() == copyClipboardButton) {
			copyToClipboard();
		}
		else if(e.getSource() == executeButton) {
			String q = queryPanel.getQuery();
			Table t = runQuery( q );
			if ( t!= null )
				displayResult( t, q );
		}
		else if(e.getSource() == cancelButton) {
			if(queryDialog != null) {
				//queryDialog.setVisible(false);
				queryDialog.dispose();
			}
		}
	}

	private void displayGraph() {
		if (resultPanel == null || resultPanel.getSelectedData() == null) {
			JOptionPane.showMessageDialog(queryDialog, "Please make a selection");
			return;
		}
		
		Object selData = resultPanel.getSelectedData();
		if ( ! (selData instanceof InvenioGraph) ) {
			JOptionPane.showMessageDialog(queryDialog, "Please select a field that represents a graph");
			return;
		}
		
		GraphSession session = new GraphSession( ((InvenioGraph) selData));
		UserSession.getInstance().addAndActivateGraphSessionInNewDisplay(session);
	}
	
	private void copyToClipboard() {
		if (resultPanel == null) {
			JOptionPane.showMessageDialog(queryDialog, "Nothing to copy");
			return;
		}
		
		TableModel mod = resultPanel.getModel();
		if ( mod == null ) {
			return;
		}
		
		CSVOut csv = new CSVOut();
		StringBuffer sb = new StringBuffer();
		
		String[] cols = new String[mod.getColumnCount()];
		for (int i = 0; i < cols.length; i++)
			cols[i] = mod.getColumnName(i);
		csv.writeLine(sb, cols);
		
		for (int i = 0; i < mod.getRowCount(); i++) {
			String[] line = new String[cols.length];
			for (int j = 0; j < cols.length; j++) {
				Object val = mod.getValueAt(i, j);
				line[j] = (val == null) ? null : val.toString();
			}
			csv.writeLine(sb, line);
		}
		
		String selection = sb.toString();
		StringSelection data = new StringSelection(selection);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(data, data);
	}
	


	*/
	
}
