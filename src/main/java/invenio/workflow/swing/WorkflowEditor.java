package invenio.workflow.swing;

import invenio.workflow.main.WorkflowController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import org.w3c.dom.Document;

import com.jidesoft.docking.DockingManager;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class WorkflowEditor extends BasicGraphEditor
{
	public final DockingManager dockingManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7007225006753337933L;

	/**
	 * 
	 */
	public WorkflowEditor(DockingManager dockingManager)
	{
		super("Invenio", new CustomGraphComponent(new mxGraph()));
		this.dockingManager = dockingManager;

		// Creates a single shapes palette
		EditorPalette dataPalette = insertPalette("Data");
		EditorPalette algorithmPalette = insertPalette("Algorithm");
		EditorPalette visPalette = insertPalette("Visualization");
		//graphOutline.setVisible(false);

		mxCell fileImportTemplate = new mxCell("File Import", new mxGeometry(0, 0,
				50, 50), "image;image=/com/mxgraph/examples/swing/images/server.png");
//		fileImportTemplate.getGeometry().setAlternateBounds(
//				new mxRectangle(0, 0, 140, 25));
		fileImportTemplate.setVertex(true);

		dataPalette
				.addTemplate(
						"File Import",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/server.png")),
										fileImportTemplate);
		dataPalette
				.addTemplate(
						"Tab Reader",
						new ImageIcon(
								GraphEditor.class
										.getResource("/com/mxgraph/examples/swing/images/server.png")),
				"roundImage;image=/com/mxgraph/examples/swing/images/server.png",
				50, 50, "Tab Reader");
		
		algorithmPalette
			.addTemplate(
					"Node Label",
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/mxgraph/examples/swing/images/gear.png")),
					"roundImage;image=/com/mxgraph/examples/swing/images/gear.png",
					50, 50, "Node Label");

		algorithmPalette
		.addTemplate(
				"Query",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/dude3.png")),
				"roundImage;image=/com/mxgraph/examples/swing/images/dude3.png",
				50, 50, "Query");
		
		algorithmPalette
			.addTemplate(
					"Table Selector",
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/mxgraph/examples/swing/images/package.png")),
					"roundImage;image=/com/mxgraph/examples/swing/images/package.png",
					50, 50, "Table Selector");
		
		algorithmPalette
			.addTemplate(
					"Edge Betweenness",
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/mxgraph/examples/swing/images/package.png")),
					"roundImage;image=/com/mxgraph/examples/swing/images/package.png",
					50, 50, "Edge Betweenness");
		
		algorithmPalette
			.addTemplate(
				"K-Means",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/package.png")),
				"roundImage;image=/com/mxgraph/examples/swing/images/package.png",
				50, 50, "K-Means");
		
		visPalette
			.addTemplate(
					"Graph Display",
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/mxgraph/examples/swing/images/workplace.png")),
					"roundImage;image=/com/mxgraph/examples/swing/images/workplace.png",
					50, 50, "Graph Display");
		
		visPalette
			.addTemplate(
				"Query Result",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/workplace.png")),
				"roundImage;image=/com/mxgraph/examples/swing/images/workplace.png",
				50, 50, "Query Result");
		
		visPalette
			.addTemplate(
				"Cluster Visualization",
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/mxgraph/examples/swing/images/workplace.png")),
				"roundImage;image=/com/mxgraph/examples/swing/images/workplace.png",
				50, 50, "Cluster Visualization");
		
		visPalette
			.addTemplate(
					"Label Visualization",
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/mxgraph/examples/swing/images/workplace.png")),
					"roundImage;image=/com/mxgraph/examples/swing/images/workplace.png",
					50, 50, "Label Visualization");
		
		WorkflowController controller = new WorkflowController(this);
		
//		getGraphComponent().getGraph().setCellsResizable(false);
//		getGraphComponent().setConnectable(true);
//		getGraphComponent().getGraphHandler().setCloneEnabled(false);
//		getGraphComponent().getGraphHandler().setImagePreview(false);

		// Prefers default JComponent event-handling before mxCellHandler handling
		//getGraphComponent().getGraphHandler().setKeepOnTop(false);

//		mxGraph graph = getGraphComponent().getGraph();
//		Object parent = graph.getDefaultParent();
//		graph.getModel().beginUpdate();
//		try
//		{
//			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Customers",
//					20, 20, 200, 280);
//			v1.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
//			mxCell v2 = (mxCell) graph.insertVertex(parent, null, "Orders",
//					280, 20, 200, 280);
//			v2.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
//		}
//		finally
//		{
//			graph.getModel().endUpdate();
//		}
	}

	/**
	 * 
	 */
	protected void installToolBar()
	{
		add(new SchemaEditorToolBar(this, JToolBar.HORIZONTAL),
				BorderLayout.NORTH);
	}

	/**
	* 
	*/
	public static class CustomGraphComponent extends mxGraphComponent
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6833603133512882012L;

		/**
		 * 
		 * @param graph
		 */
		public CustomGraphComponent(mxGraph graph)
		{
			super(graph);
			
			getGraph().setCellsResizable(false);
			setConnectable(true);
			getGraphHandler().setCloneEnabled(false);
			getGraphHandler().setImagePreview(true);

			// Sets switches typically used in an editor
			setPageVisible(false);
			setGridVisible(false);
			setToolTips(true);
			getConnectionHandler().setCreateTarget(true);

			// Loads the defalt stylesheet from an external file
			mxCodec codec = new mxCodec();
			Document doc = mxUtils.loadDocument(GraphEditor.class.getResource(
					"/com/mxgraph/examples/swing/resources/default-style.xml")
					.toString());
			codec.decode(doc.getDocumentElement(), graph.getStylesheet());

			// Sets the background to white
			getViewport().setOpaque(true);
			getViewport().setBackground(Color.WHITE);
		}

		/**
		 * Overrides drop behaviour to set the cell style if the target
		 * is not a valid drop target and the cells are of the same
		 * type (eg. both vertices or both edges). 
		 */
//		public Object[] importCells(Object[] cells, double dx, double dy,
//				Object target, Point location)
//		{
//			if (target == null && cells.length == 1 && location != null)
//			{
//				target = getCellAt(location.x, location.y);
//
//				if (target instanceof mxICell && cells[0] instanceof mxICell)
//				{
//					mxICell targetCell = (mxICell) target;
//					mxICell dropCell = (mxICell) cells[0];
//
//					if (targetCell.isVertex() == dropCell.isVertex()
//							|| targetCell.isEdge() == dropCell.isEdge())
//					{
//						mxIGraphModel model = graph.getModel();
//						model.setStyle(target, model.getStyle(cells[0]));
//						graph.setSelectionCell(target);
//
//						return null;
//					}
//				}
//			}
//
//			return super.importCells(cells, dx, dy, target, location);
//		}

	}

	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		//WorkflowEditor editor = new WorkflowEditor();
		//editor.createFrame(new WorkflowEditorMenuBar(editor)).setVisible(true);
	}

}
