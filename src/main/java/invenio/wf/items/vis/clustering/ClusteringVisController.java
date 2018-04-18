package invenio.wf.items.vis.clustering;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.visual.DisplayShape;
import invenio.visual.GraphSession;
import invenio.visual.RandomColorAction;
import invenio.visual.RandomVisibilityAction;
import invenio.visual.VisualGraphSession;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.log.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import prefuse.Constants;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class ClusteringVisController extends AbstractController<InvenioGraph> implements ItemController<InvenioGraph> {
	
	private static Logger log = Logger.getInstance();
	
	private ClusteringResultController resultController;
	
	public ClusteringVisController() {
		initContoller();
	}
	
	protected void initContoller() {
		ClusteringResultView shape = new ClusteringResultView();
		resultController = new ClusteringResultController();
		resultController.setShape(shape);
		shape.setController(resultController);
	}

	@Override
	public boolean hasConfigView() {
		return false;
	}

	@Override
	public boolean hasResultView() {
		return true;
	}

	@Override
	public JComponent getConfigView() {
		return null;
	}

	@Override
	public JComponent getResultView() {
		if (resultController != null) {
			return resultController.getShape();
		}
		else
			return null;
	}

	
	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		if (inputs == null || inputs.length < 1)
			return null;
		
		GroupingAlgorithmResult r = (GroupingAlgorithmResult)inputs[0];
		
		resultController.updateModel(r);
		
		return null;
	}
	
}
