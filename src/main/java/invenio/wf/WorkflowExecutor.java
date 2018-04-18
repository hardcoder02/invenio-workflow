package invenio.wf;

public interface WorkflowExecutor {
	void executeWorkflow(Node node, boolean propagate);
}
