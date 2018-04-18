package invenio.workflow.swing;

import com.mxgraph.model.mxCell;

import invenio.wf.BaseWorkflowItem;
import invenio.wf.Node;

public class WorkflowUtils {
	public static BaseWorkflowItem getItem(mxCell cell) {
		if (cell == null)
			return null;
		Node node = (Node) cell.getValue();
		if (node == null)
			return null;
		return (BaseWorkflowItem) node.getNodeItem();
	}
	
	public static Node getNode(mxCell cell) {
		if (cell == null)
			return null;
		Node node = (Node) cell.getValue();
		return node;
	}
}
