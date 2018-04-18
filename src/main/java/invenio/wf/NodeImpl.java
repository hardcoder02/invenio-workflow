package invenio.wf;

import invenio.data.InvenioVertex;

import java.util.Set;

public class NodeImpl implements Node {
	private final InvenioVertex v;
	private final NodeItem item;
	private final Workflow workflow;
	
	NodeImpl(NodeItem item, InvenioVertex v, Workflow wf) {
		this.v = v;
		this.item = item;
		this.workflow = wf;
	}
	
	InvenioVertex getInvenioVertex() {
		return v;
	}

	@Override
	public Workflow getWorkflow() {
		return workflow;
	}

	@Override
	public NodeItem getNodeItem() {
		return item;
	}

	@Override
	public Set<Node> getIncomingNodes() {
		return workflow.getIncomingNodes(this);
	}

	@Override
	public Set<Node> getOutgoingNodes() {
		return workflow.getOutgoingNodes(this);
	}
	
	public String toString() {
		return (item == null) ? "" : item.toString();
	}
}
