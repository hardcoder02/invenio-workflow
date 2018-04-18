package invenio.wf;

import invenio.wf.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class WorkflowManagerImpl extends WorkflowManager {
	private final Map<String, Workflow> workflows = new HashMap<String, Workflow>();
	
	private static Logger log = Logger.getInstance();
	
	WorkflowManagerImpl() {}
	
	@Override
	public Workflow addWorkflow(String name, Workflow wf) {
		return workflows.put(name, wf);
	}

	@Override
	public Workflow getWorkflow(String name) {
		return workflows.get(name);
	}

	@Override
	public boolean hasWorkflow(String name) {
		return workflows.containsKey(name);
	}

	@Override
	public Workflow removeWorkflow(String name) {
		return workflows.remove(name);
	}

	@Override
	public void executeWorkflow(Node node, boolean propagate) {
		log.log(this, "Workflow execution started...");
		getWorkflowExecutor().executeWorkflow(node, propagate);
		log.log(this, "Workflow execution complete.");
	}
}
