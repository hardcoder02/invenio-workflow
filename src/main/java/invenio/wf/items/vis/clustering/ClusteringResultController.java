package invenio.wf.items.vis.clustering;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.matching.GraphComparator;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;


// TODO: stop actions before updating them
public class ClusteringResultController implements ListSelectionListener, ItemListener {
	
	protected ClusteringResultModel model;
	protected ClusteringResultView shape;
	protected ClusteringGraphSession graphController;
	
	public ClusteringResultController() {
		model = new ClusteringResultModel();
		graphController = new ClusteringGraphSession();
	}
	
	public void setShape(ClusteringResultView shape) {
		this.shape = shape;
		graphController.setShape( shape.getGraphShape() );
	}
	
	public ClusteringResultView getShape() {
		return shape;
	}
	
	public ClusteringGraphSession getGraphController() {
		return graphController;
	}
	
	public void updateModel(GroupingAlgorithmResult grouping){ 
		InvenioGraph g = grouping.getElementResultGraph();
		graphController.updateModel(g);
		
//		DefaultListModel model = new DefaultListModel();
//		model.clear();
//		int i = 0;
//		for (InvenioElementSet set : grouping.getGroupElementSet().getSets() ) {
//			model.add(i, set.getName());
//			i++;
//		}
//		
//		shape.getClusterSelectList().setModel(model);
		
		setupColoring(grouping);
		
		updateClusteringTable(grouping);
		updateStats(grouping);
		
		updateClusterSelection();
		
		graphController.setGrouping(grouping);
//		// TODO: move
//		ClusterCircleLayout l = new ClusterCircleLayout("graph.nodes");
//		l.setGroupingResult(grouping);
//		graphController.setLayout(l);
	}
	
	
	private void setupColoring(GroupingAlgorithmResult grouping) {
		
		ElementSetsMultiColorAction colorAction = new ElementSetsMultiColorAction("graph.nodes", VisualItem.FILLCOLOR);
		
		int i = 0;
		for (InvenioElementSet set : grouping.getGroupElementSet().getSets() ) {
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			
			colorAction.getColorLookup().addSet(set, ColorLib.rgb(r, g, b));
		}		
		
//		graphController.getPainter().removeAction("fill");
		graphController.getPainter().putAction("fill", colorAction, true);
//		graphController.getPainter().runAction("fill");
		graphController.repaintVis();
	}

	private void updateStats(GroupingAlgorithmResult grouping) {
		int minSize = Integer.MAX_VALUE;
		int maxSize = -1;
		int totalSize = 0;
		
		int i = 0;
		for (InvenioElementSet set : grouping.getGroupElementSet().getSets() ) {
			int size = set.getNumVertices();
			if (minSize > size)
				minSize = size;
			if (maxSize < size)
				maxSize = size;
			totalSize += size;
			i++;
		}
		
		shape.setMinLabelValue("" + minSize);
		shape.setMaxLabelValue("" + maxSize);
		shape.setAvgLabelValue( String.format(
				"Value: %1$.2f", (double)totalSize / (double) i) );
	}
	

	
	protected void updateClusteringTable(GroupingAlgorithmResult grouping) {
		ClusterTableModel tm = (ClusterTableModel) shape.getClusterSelectTable().getModel();
		
		tm.setSets(grouping.getGroupElementSet());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == shape.getClusterSelectTable().getSelectionModel())
			updateClusterSelection();
		
	}
	
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();

	    if (source == shape.getShowAllCheckBox() ) {
	        updateShowAll();
	    }
	}

	private void updateClusterSelection() {
		int[] row = shape.getClusterSelectTable().getSelectedRows();
		
		ElementSetsSizeUpAction sizeUpAction = new ElementSetsSizeUpAction("graph", 3);
		sizeUpAction.getMembership().clearSets();
		
		for (int i = 0; i < row.length; i++) {
			ClusterTableModel tm = (ClusterTableModel) shape.getClusterSelectTable().getModel();
			InvenioElementSet set = tm.getInvenioElementSet(row[i]);
			sizeUpAction.getMembership().addSet(set);
		}
		
		ElementSetsVisibilityAction visAction = graphController.createElementSetsVisibilityAction();
		visAction.setMembership(sizeUpAction.getMembership());

		graphController.getPainter().putAction("sizeUp", sizeUpAction, true);

		graphController.getPainter().putAction("visibility", visAction, true);
		
		graphController.repaintVis();
	}
	
	private void updateShowAll() {
		boolean showAll = shape.getShowAllCheckBox().isSelected();
		
		ElementSetsVisibilityAction visAction = (ElementSetsVisibilityAction) graphController.getPainter().getAction("visibility");
		visAction.setShowAll(showAll);

		graphController.getPainter().putAction("visibility", visAction, true);
		graphController.repaintVis();
	}

}
