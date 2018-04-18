package invenio.wf.item.visual;

import invenio.UserSession;
import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.util.AlgorithmManager;
import invenio.wf.VisualItem;

public class AlgorithmResultDisplayItem extends VisualItem {
	
	private final static Class[] inputDescriptor = {AlgorithmResult.class};
	
	public AlgorithmResultDisplayItem() {
		super(inputDescriptor);
	}
	
	@Override
	public boolean isInternallyUpdateable() {
		return false;
	}

	@Override
	public boolean isExternallyUpdateable() {
		return true;
	}
	
	@Override
	public boolean update() {
		AlgorithmResult result = (AlgorithmResult) getInput(0);
		
		AlgorithmManager algorithmManager = UserSession.getInstance().getActiveVisualGraphSession().getAlgorithmManager();
		algorithmManager.processResults(result);
		return true;
	}

	@Override
	public Object getOutput() {
		return null;
	}
}
