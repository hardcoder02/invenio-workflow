package invenio.wf.item.algorithm;

import invenio.algorithms.AlgorithmResult;
import invenio.wf.BaseWorkflowItem;

public abstract class AlgorithmExecutorItem extends BaseWorkflowItem {
	private final Class algorithm;

	private AlgorithmResult result;
	
	
	public AlgorithmExecutorItem(String name, Class algorithm, Class[] inputDescriptor) {
		super(name, name + nextKey(name), inputDescriptor);
		this.algorithm = algorithm;
	}
	
	@Override
	public boolean isInternallyUpdateable() {
		return false;
	}

	@Override
	public boolean isExternallyUpdateable() {
		return true;
	}

}
