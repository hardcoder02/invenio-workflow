package invenio.wf;

public abstract class WorkflowManager {
	private static WorkflowManager manager;
	
	public abstract Workflow addWorkflow(String name, Workflow wf);
	public abstract Workflow getWorkflow(String name);
	public abstract boolean hasWorkflow(String name);
	public abstract Workflow removeWorkflow(String name);
	
	public abstract void executeWorkflow(Node node, boolean propagate);
	
	public static WorkflowManager getInstance() {
		if (manager == null) {
			manager = new WorkflowManagerImpl();
		}
		return manager;
	}
	
	protected WorkflowExecutor getWorkflowExecutor() {
		return new WorkflowExecutorImpl();
	}
}
