package invenio.workflow.swing;

import invenio.wf.BaseWorkflowItem;
import invenio.workflow.swing.EditorActions.HistoryAction;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.handler.mxSelectionCellsHandler;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

public class EditorPopupMenu extends JPopupMenu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132749140550242191L;

	public EditorPopupMenu(WorkflowEditor editor)
	{
		boolean selected = !editor.getGraphComponent().getGraph()
				.isSelectionEmpty();

		add(
				editor.bind(mxResources.get("start"), new CustomActions.
						ExecToThisAction("execToThis", editor.dockingManager),
						"/com/mxgraph/examples/swing/images/cut.gif"))
				.setEnabled(selected);
		
		add(
				editor.bind(mxResources.get("stop"), null,
						"/com/mxgraph/examples/swing/images/cut.gif"))
				.setEnabled(selected);
		
		addSeparator();
		
		add(
				editor.bind(mxResources.get("edit"), new CustomActions.
						EditAction("edit", editor.dockingManager),
					"/com/mxgraph/examples/swing/images/doubleellipse.png"))
				.setEnabled(selected);
		
		add(
				editor.bind(mxResources.get("view"), new CustomActions.
						ViewAction("view", editor.dockingManager),
					"/com/mxgraph/examples/swing/images/doubleellipse.png"))
				.setEnabled(selected);

		mxCell selCell = (mxCell)editor.getGraphComponent().getGraph().getSelectionCell();
		BaseWorkflowItem item = WorkflowUtils.getItem(selCell);
		if ( item != null && item.getController().hasConfigView() ) {
			add(
					editor.bind("config", new CustomActions.EditorAction("Edit properties", editor.dockingManager),
						"/com/mxgraph/examples/swing/images/doubleellipse.png"))
					.setEnabled(true);
		}
		
		if ( item != null && item.getController().hasResultView() ) {
			add(
					editor.bind("result", new CustomActions.ResultAction("View Result", editor.dockingManager),
						"/com/mxgraph/examples/swing/images/doubleellipse.png"))
					.setEnabled(true);
		}
				
//		add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
//				"/com/mxgraph/examples/swing/images/undo.gif"));
//
//		addSeparator();
//
//		add(
//				editor.bind(mxResources.get("cut"), TransferHandler
//						.getCutAction(),
//						"/com/mxgraph/examples/swing/images/cut.gif"))
//				.setEnabled(selected);
//		add(
//				editor.bind(mxResources.get("copy"), TransferHandler
//						.getCopyAction(),
//						"/com/mxgraph/examples/swing/images/copy.gif"))
//				.setEnabled(selected);
//		add(editor.bind(mxResources.get("paste"), TransferHandler
//				.getPasteAction(),
//				"/com/mxgraph/examples/swing/images/paste.gif"));
//
//		addSeparator();
//
//		add(
//				editor.bind(mxResources.get("delete"), mxGraphActions
//						.getDeleteAction(),
//						"/com/mxgraph/examples/swing/images/delete.gif"))
//				.setEnabled(selected);
//
//		addSeparator();
//
//		// Creates the format menu
//		JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));
//
//		EditorMenuBar.populateFormatMenu(menu, editor);
//
//		// Creates the shape menu
//		menu = (JMenu) add(new JMenu(mxResources.get("shape")));
//
//		EditorMenuBar.populateShapeMenu(menu, editor);
//
//		addSeparator();
//
//		add(
//				editor.bind(mxResources.get("edit"), mxGraphActions
//						.getEditAction())).setEnabled(selected);
//
//		addSeparator();
//
//		add(editor.bind(mxResources.get("selectVertices"), mxGraphActions
//				.getSelectVerticesAction()));
//		add(editor.bind(mxResources.get("selectEdges"), mxGraphActions
//				.getSelectEdgesAction()));
//
//		addSeparator();
//
//		add(editor.bind(mxResources.get("selectAll"), mxGraphActions
//				.getSelectAllAction()));
	}

}
