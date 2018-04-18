package invenio.wf;

import java.util.Arrays;

import invenio.algorithms.AlgorithmResult;


public class AlgorithmItem extends BaseNodeItem {
	
	public AlgorithmItem() {
		super();
	}
	
	public AlgorithmItem(Class[] inputDescriptor) {
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
		return false;
	}

	@Override
	public AlgorithmResult getOutput() {
		return null;
	}

}
