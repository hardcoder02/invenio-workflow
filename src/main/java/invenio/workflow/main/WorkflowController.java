package invenio.workflow.main;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

import invenio.wf.BaseEdgeItem;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.Connection;
import invenio.wf.EdgeItem;
import invenio.wf.Node;
import invenio.wf.Workflow;
import invenio.wf.WorkflowFactory;
import invenio.wf.WorkflowItemResolver;
import invenio.workflow.swing.WorkflowEditor;


public class WorkflowController {
	private final WorkflowEditor editor;
	private final Workflow workflow; 
	
	public WorkflowController(WorkflowEditor editor) {
		this.editor = editor;
		this.workflow = WorkflowFactory.createWorkflow("Test Workflow");
		init();
	}
	
	private void init() {
		mxGraph g = editor.getGraphComponent().getGraph();
		mxIEventListener list = new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				System.out.println("" + sender);
				System.out.println(evt.getName());
			}
		};
		//g.addListener(null, list);
		
		mxIEventListener list2 = new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				Object[] cells = ((Object[])evt.getProperty("cells"));
				
				for (int i = 0; i < cells.length; i++) {
					mxCell cell = (mxCell)cells[i];
					mxCell source = (mxCell)evt.getProperty("source");
					mxCell target = (mxCell)evt.getProperty("target");
					
					if ( source == null || target == null) {
						System.out.println("Added vertex" + cell.getValue());
						addNode( cell );
					}
					else {
						System.out.println("Added edge" + cell.getValue() + " from " + source.getValue() + " to " + target.getValue());
						addEdge( cell, source, target );
					}
				}
			}
		};
		g.addListener(mxEvent.CELLS_ADDED, list2);
		
		
		mxIEventListener list3 = new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				Object[] cells = ((Object[])evt.getProperty("cells"));
				
				for (int i = 0; i < cells.length; i++) {
					mxCell cell = (mxCell)cells[i];
					mxCell source = (mxCell)evt.getProperty("source");
					mxCell target = (mxCell)evt.getProperty("target");
					
					if ( !cell.isEdge() ) {
						//System.out.println("Removed vertex" + cell.getValue());
						removeNode( cell );
					}
					else {
						//System.out.println("Removed edge" + cell.getValue() + " from " + source.getValue() + " to " + target.getValue());
						removeEdge( cell );
					}
				}
			}
		};
		g.addListener(mxEvent.CELLS_REMOVED, list3);
	}
	
	private void addNode(mxCell cell) {
		BaseWorkflowItem item = WorkflowItemResolver.getInstance().getNodeItem( "" + cell.getValue() );
		Node node = workflow.addNode( item );
		item.setCell(cell);
		cell.setValue( node );
	}
	
	private void addEdge(mxCell cell, mxCell source, mxCell target) {
		Node sNode = (Node)source.getValue();
		Node tNode = (Node)target.getValue();
		
		EdgeItem item = new EdgeItem();
		Connection con = workflow.addConnection(sNode, tNode, item);
		item.setCell(cell);
		cell.setValue( con );
	}
	
	private void removeNode(mxCell cell) {
		Node node = (Node) cell.getValue();
		workflow.removeNode( node );
	}
	
	private void removeEdge(mxCell cell) {
		Connection c = (Connection) cell.getValue();
		workflow.removeConnection(c);
	}
}
