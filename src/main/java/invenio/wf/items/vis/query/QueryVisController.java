package invenio.wf.items.vis.query;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.items.query.QueryResult;
import invenio.wf.items.vis.clustering.ClusteringResultController;
import invenio.wf.items.vis.clustering.ClusteringResultView;
import invenio.wf.log.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QueryVisController extends AbstractController<Object>
		implements ItemController<Object> {
	
	private static Logger log = Logger.getInstance();
	
	private QueryResultController resultController;
	
	public QueryVisController() {
		initContoller();
	}
	
	protected void initContoller() {
		QueryResultView shape = new QueryResultView();
		resultController = new QueryResultController();
		resultController.setShape(shape);
		shape.setController(resultController);
	}

	@Override
	public boolean hasConfigView() {
		return false;
	}

	@Override
	public boolean hasResultView() {
		return true;
	}

	@Override
	public JComponent getConfigView() {
		return null;
	}

	@Override
	public JComponent getResultView() {
		if (resultController != null) {
			return resultController.getShape();
		}
		else
			return null;
	}

	
	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		if (inputs == null || inputs.length != 1)
			return null;
		
		QueryResult qRes = (QueryResult) inputs[0];

		resultController.updateModel(qRes);
		
		return null;
	}
}
