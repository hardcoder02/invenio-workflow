package invenio.wf;

public class WorkflowFactory {
	public static Workflow createWorkflow() {
		return new WorkflowImpl();
	}
	
	public static Workflow createWorkflow(String name) {
		return new WorkflowImpl(name);
	}
}
