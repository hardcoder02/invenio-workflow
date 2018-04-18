package invenio.wf.items.query;

import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.log.Logger;

import javax.swing.JComponent;

import qng.tabular.Table;
import qng.tabular.Tuple;

public class TableSelectorController extends AbstractController<Object>
	implements ItemController<Object> {
	
	private static Logger log = Logger.getInstance();
	
	private TableSelectorConfigView configView;
	

	public TableSelectorController() {
		initController();
	}
	
	protected void initController() {
		configView = new TableSelectorConfigView();
		configView.setController(this);
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
	public JComponent getConfigView() {
		return configView;
	}

	@Override
	public JComponent getResultView() {
		return null;
	}
	

	
	@Override
	public Object updateResult(Object[] inputs) {
		int column = (Integer) configView.getColumnSpinner().getValue();
		int row = (Integer) configView.getRowSpinner().getValue();
		
		log.log(this, "selecting from table, column=" + column + ", row=" + row + "...");
		
		if (inputs == null || inputs.length < 1 || !( inputs[0] instanceof Table) )
			return null;
		Table t = (Table) inputs[0];
		Tuple tuple = t.getTuples().get(row);
		Object ret = tuple.getValue(column);
		log.log(this, "value selected: " + ret);
		return ret;
	}
}
