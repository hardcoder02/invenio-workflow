package invenio.wf;

import java.util.Set;

public interface Workflow {
	Set<Node> getStartNodes();
	Set<Node> getEndNodes();
	
	Set<Node> getIncomingNodes(Node node);
	Set<Node> getOutgoingNodes(Node node);
	
	Node addNode(NodeItem item);
	void removeNode(Node n);
	
	Connection addConnection(Node from, Node to, ConnectionItem ci);
	void removeConnection(Connection c);
	
	boolean isSuspended();
}
