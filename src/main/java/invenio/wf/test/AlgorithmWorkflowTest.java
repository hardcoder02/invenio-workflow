package invenio.wf.test;

import invenio.wf.Node;
import invenio.wf.NodeItem;
import invenio.wf.Workflow;
import invenio.wf.WorkflowFactory;
import invenio.wf.WorkflowManager;
import invenio.wf.item.algorithm.AlgorithmExecutorItem;
import invenio.wf.item.visual.AlgorithmParameterPanelItem;
import invenio.wf.item.visual.AlgorithmResultDisplayItem;

public class AlgorithmWorkflowTest {
	public void testAlgorithmWorkflow(Class algorithm) {
		Workflow wf = createWorkflow(algorithm);
		
		WorkflowManager manager = WorkflowManager.getInstance();
		for (Node n : wf.getEndNodes() )
			manager.executeWorkflow(n, false);
	}
	
	protected Workflow createWorkflow(Class algorithm) {
		Workflow wf = WorkflowFactory.createWorkflow("Algorithm workflow: " + algorithm.getName());
		
		Node dialog = wf.addNode(getAlgorithmParameterPanelItem(algorithm));
		Node algExec = wf.addNode(getAlgorithmExecutorItem(algorithm));
		Node algDisplay = wf.addNode(getAlgorithmResultDisplayItem());
		
		wf.addConnection(dialog, algExec, null);
		wf.addConnection(algExec, algDisplay, null);
		
		return wf;
	}

	protected NodeItem getAlgorithmParameterPanelItem(Class algorithm) {
		return new AlgorithmParameterPanelItem(algorithm);
	}
	
	private NodeItem getAlgorithmExecutorItem(Class algorithm) {
		return new AlgorithmExecutorItem(algorithm);
	}
	
	private NodeItem getAlgorithmResultDisplayItem() {
		return new AlgorithmResultDisplayItem();
	}
}
