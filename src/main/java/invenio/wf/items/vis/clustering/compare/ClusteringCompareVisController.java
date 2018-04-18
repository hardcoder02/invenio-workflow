package invenio.wf.items.vis.clustering.compare;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.items.query.QueryResult;
import invenio.wf.log.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

public class ClusteringCompareVisController extends AbstractController<InvenioGraph>
		implements ItemController<InvenioGraph> {
	
	private static Logger log = Logger.getInstance();
	
	private ClusteringCompareResultController resultController;
	
	public ClusteringCompareVisController() {
		initContoller();
	}
	
	protected void initContoller() {
		ClusteringCompareResultView shape = new ClusteringCompareResultView();
		resultController = new ClusteringCompareResultController();
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
		if (inputs == null || inputs.length != 2)
			return null;
		

		GroupingAlgorithmResult r0 = (GroupingAlgorithmResult)inputs[0];
		GroupingAlgorithmResult r1 = (GroupingAlgorithmResult)inputs[1];
		
		// TODO: (high priority) remove hack!
		InvenioGraph g0 = r0.getElementResultGraph();
		InvenioGraph g1 = r1.getElementResultGraph();
		
		Map<String, GroupingAlgorithmResult> map = new HashMap<String, GroupingAlgorithmResult>();
		map.put(g0.getName(), r0);
		map.put(g1.getName(), r1);
		
		resultController.updateModel(map.get("g0"), map.get("g1"));
		
		return null;
	}
}
