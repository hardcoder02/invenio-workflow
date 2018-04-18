package invenio.wf.items.vis.nodelabel;

import invenio.data.InvenioGraph;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.items.query.QueryResult;
import invenio.wf.log.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

public class LabelVisualizationController extends AbstractController<InvenioGraph>
		implements ItemController<InvenioGraph> {
	
	private static Logger log = Logger.getInstance();
	
	private LabelResultController resultController;
	
	public LabelVisualizationController() {
		initContoller();
	}
	
	protected void initContoller() {
		LabelResultView shape = new LabelResultView();
		resultController = new LabelResultController();
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
		if (inputs == null || inputs.length != 3)
			return null;
		
		InvenioGraph gt = getGraph(inputs[0]);
		InvenioGraph g1 = getGraph(inputs[1]);
		InvenioGraph g2 = getGraph(inputs[2]);
		
		// TODO: (high priority) remove hack!
		Map<String, InvenioGraph> map = new HashMap<String, InvenioGraph>();
		map.put(gt.getName(), gt);
		map.put(g1.getName(), g1);
		map.put(g2.getName(), g2);
		
		resultController.updateModel(map.get("gtNew"), map.get("g1New"), map.get("g2New"));
		
		return null;
	}
	
	private InvenioGraph getGraph(Object o) {
		if (o == null)
			return null;
		
		if (o instanceof InvenioGraph)
			return (InvenioGraph) o;
		
		if (o instanceof QueryResult) {
			QueryResult qRes = (QueryResult) o;
			Object resObject = qRes.getCellObject();
			if (resObject instanceof InvenioGraph)
				return (InvenioGraph) resObject;
		}
		
		return null;
	}
}
