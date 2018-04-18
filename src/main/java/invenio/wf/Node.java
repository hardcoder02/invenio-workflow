package invenio.wf;

import java.util.Set;

public interface Node {
	Workflow getWorkflow();
	NodeItem getNodeItem();
	
	Set<Node> getIncomingNodes();
	Set<Node> getOutgoingNodes();
}
