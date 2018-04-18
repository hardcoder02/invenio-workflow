package invenio.wf;

import java.util.Set;

class WorkflowExecutorImpl implements WorkflowExecutor {

	@Override
	public void executeWorkflow(Node node, boolean propagate) {
		if ( !node.getWorkflow().isSuspended() ) {
			initWorkflow(node);
			updateNodeUnsuspended(node);
		}
		else
			updateNodeSuspended(node);
	}
	
	private void initWorkflow(Node node) {
		initNode(node);
	}

	private void initNode(Node node) {
		node.getNodeItem().setPreviouslyProcessed(false);
		
		Set<Node> incoming = node.getIncomingNodes();
	
		for (Node n : incoming) {
			initNode(n);
		}
		
	}

	private Object updateNodeSuspended(Node node) {
		NodeItem item = node.getNodeItem();
		
		if (item.isPreviouslyProcessed())
			return item.getOutput();
		
		if ( item.isSuspended() ) {
			item.resume();
		}
		else {
			Set<Node> incoming = node.getIncomingNodes();
			Object[] results = new Object[incoming.size()];
			
			int i = 0;
			for (Node n : incoming) {
				results[i] = updateNodeSuspended(n);
				if ( n.getNodeItem().isSuspended() )
					return null;
			}
			
			
			item.clearInputs();
			setInput(item, results);
			item.update();
		}
		
		if (node.getWorkflow().isSuspended()) {
			return null;
		}
		else {
			item.setPreviouslyProcessed(true);
			return item.getOutput();
		}
	}
	
	private Object updateNodeUnsuspended(Node node) {
		NodeItem item = node.getNodeItem();
		
		if ( item.isPreviouslyProcessed() ) {
			return item.getOutput();
		}
		
		Set<Node> incoming = node.getIncomingNodes();
		Object[] results = new Object[incoming.size()];
		
		int i = 0;
		for (Node n : incoming) {
			results[i] = updateNodeUnsuspended(n);
			i++;
			if ( n.getNodeItem().isSuspended() )
				return null;
		}
		
		if (node.getWorkflow().isSuspended()) {
			return null;
		}
		else {
			item.clearInputs();
			setInput(item, results);
			item.update();
			if ( item.isSuspended() )
				return null;
			item.setPreviouslyProcessed(true);
			return item.getOutput();
		}
	}
	
	private void setInput(NodeItem item, Object[] input) {
		for (int i = 0; i < input.length; i++) {
			item.setInput(i, input[i]);
		}
	}

}
