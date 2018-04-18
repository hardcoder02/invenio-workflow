package invenio.wf.items.vis.clustering.compare;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.matching.GraphComparator;
import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.DataFormatException;
import invenio.visual.VisualGraphSession;
import invenio.wf.items.vis.clustering.ClusterTableModel;
import invenio.wf.items.vis.clustering.ElementSetsMultiColorAction;
import invenio.wf.items.vis.clustering.ElementSetsSizeUpAction;
import invenio.wf.items.vis.clustering.ElementSetsVisibilityAction;
import invenio.wf.items.vis.clustering.compare.ClusterComparisonProcessor.ClusterIntersect;
import invenio.wf.items.vis.nodelabel.MatrixCell;
import invenio.wf.items.vis.nodelabel.MatrixSlice.SliceStats;
import invenio.wf.test.HeatMapTest;
import invenio.wf.ui.components.VerticalTableHeaderCellRenderer;
import invenio.wf.vis.actions.ElementLookup;
import invenio.wf.vis.actions.LookupMultiColorAction;
import invenio.wf.vis.actions.LookupSizeUpAction;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;


// TODO: stop actions before updating them
public class ClusteringCompareResultController implements ListSelectionListener//, ActionListener, ItemListener 
{
	
	private static final int DEFAULT_FILL_COLOR = Color.WHITE.getRGB();
	private static final int DEFAULT_FILL_XONLY_COLOR = Color.RED.getRGB();
	private static final int DEFAULT_FILL_YONLY_COLOR = Color.GREEN.getRGB();
	private static final int DEFAULT_FILL_XY_BOTH_COLOR = Color.BLUE.getRGB();
	
	protected ClusteringCompareResultModel model;
	protected ClusteringCompareResultView shape;
	protected ClusteringCompareGraphSession graphController0;
	protected ClusteringCompareGraphSession graphController1;
	
	public ClusteringCompareResultController() {
		model = new ClusteringCompareResultModel();
		graphController0 = new ClusteringCompareGraphSession();
		graphController1 = new ClusteringCompareGraphSession();
	}
	
	public void setShape(ClusteringCompareResultView shape) {
		this.shape = shape;
		graphController0.setShape( shape.getGraphShape0() );
		graphController1.setShape( shape.getGraphShape1() );
	}
	
	public ClusteringCompareResultView getShape() {
		return shape;
	}
	
	public ClusteringCompareGraphSession getGraphController0() {
		return graphController0;
	}
	
	public ClusteringCompareGraphSession getGraphController1() {
		return graphController1;
	}
	
	public void updateModel(GroupingAlgorithmResult r0, GroupingAlgorithmResult r1){
		InvenioGraph g0 = r0.getElementResultGraph();
		graphController0.updateModel(g0);
		
		InvenioGraph g1 = r1.getElementResultGraph();
		graphController1.updateModel(g1);
		
//		try {
			ClusterComparisonProcessor processor = new ClusterComparisonProcessor();

			CCBaseTable table = processor.createBaseTable(g0);
			CCMatrix matrix = processor.createMatrix(r0, r1);
			
			model.setBaseTable(table);
			model.setMatrix(matrix);
			
			CCBaseTable table1 = processor.createBaseTable(g1);
			model.setBaseTable1(table1);
			
			// matrix table
			CCMatrixTableModel mtModel = new CCMatrixTableModel(matrix);
			model.setMatrixTableModel(mtModel);
			shape.getMatrixTable().setModel(mtModel);
			
			// detail table
//			CCDetailTableModel dtModel = new CCDetailTableModel();
//			model.setDtModel(dtModel);
//			shape.getDetailTable().setModel(dtModel);
//			updateDetailTableModel();
			
//			// color, selection, visibility
//			setupColoring(0, 1, 0);
			updateSelection();
//			updateHideNodes(0);
//			
//			// stats and categories
//			model.setNumVertices( gt.numVertices() );
//			model.setNumEdges( gt.numEdges() );
//			model.setAccuracy1( processor.getAccuracy(table, 0, 1) );
//			model.setAccuracy2( processor.getAccuracy(table, 0, 2) );
//			updateCategories();
//			updateStats();
//		}
//		catch (DataFormatException ex) {
//			ex.printStackTrace();
//		}
		
//		updateClusteringTable(grouping);
//		updateStats(grouping);
//		
//		updateClusterSelection();
//		
		graphController0.setGrouping(r0);
		graphController1.setGrouping(r1);
	}
	
//	private void updateDetailTableModel() {
//		// assume single cell selection
//		int col = shape.getMatrixTable().getSelectedColumn();
//		int row = shape.getMatrixTable().getSelectedRow();
//		
//		List<CCMatrixCell> cells = new ArrayList<CCMatrixCell>();
//		
//		// no selection
//		if (col < 0 || row < 0) {
//			int size = model.getMatrixTableModel().getRowCount();
//			for (int i = 0; i < size; i++) {
//				for (int j = 0; j < size; j++) {
//					CCMatrixCell c = (CCMatrixCell) model.getMatrixTableModel().getCell(i, j);
//					cells.add(c);
//				}
//			}
//		}
//		else {
//			CCMatrixCell c = (CCMatrixCell) model.getMatrixTableModel().getCell(row, col);
//			cells.add(c);
//		}
//		
//		model.getDtModel().setCells(cells);
//	}
	
//	public void itemStateChanged(ItemEvent e) {
//	    Object source = e.getItemSelectable();
//
//	    if (source == shape.getMatrixHideCheckBox() ) {
//	        updateHideNodes( 0 );
//	    }
//	}
//	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == shape.getMatrixTable().getSelectionModel() ||
				e.getSource() == shape.getMatrixTable().getColumnModel().getSelectionModel() ) {
			if (e.getValueIsAdjusting()) {
                return;
            }
//			updateDetailTableModel();
			updateSelection();
//			updateHideNodes( 0 );
		}
//		else 
//		if (e.getSource() == shape.getDetailTable().getSelectionModel()) {
//			updateSelection( 0 );
//			updateHideNodes( 0 );
//		}
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if ( "UPDATE_SLICE".equals(e.getActionCommand()) ) {
//			int sliceIndex = shape.getMatrixChoiceComboBox().getSelectedIndex();
//			model.getMatrixTableModel().activateSlice(
//					sliceIndex
//				);
//			
//			TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
//			Enumeration<TableColumn> columns = shape.getMatrixTable().getColumnModel().getColumns();
//			while (columns.hasMoreElements()) {
//				columns.nextElement().
//					setHeaderRenderer(headerRenderer);
//			}
//			
//			
////			JTable table = shape.getMatrixTable();
////			int width = 0;
////			for (int col = 0; col < table.getColumnCount(); col++) {
////			     JComponent comp = headerRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
////			     width = Math.max (comp.getPreferredSize().width, width);
////			 }
////			
////			columns = shape.getMatrixTable().getColumnModel().getColumns();
////			while (columns.hasMoreElements()) {
////				columns.nextElement().
////					setPreferredWidth(width);
////			}
//			
//			updateDetailTableModel();
//			int indexFrom = (sliceIndex == 2) ? 1 : 0;
//			int indexTo = (sliceIndex == 0) ? 1 : 2;
//			setupColoring(indexFrom, indexTo, 0);
//			updateSelection( 0 );
//			updateHideNodes( 0 );
//		}
//		
//	}
	
//	private void setupColoring(int indexFrom, int indexTo, int graphIndex) {
//		CCBaseTable baseTable = model.getBaseTable();
//		
//		LookupMultiColorAction colorAction = new LookupMultiColorAction("graph.nodes", VisualItem.FILLCOLOR);
//		
//		for ( CCBaseEntry entry : baseTable.values() ) {
//			InvenioElement invElement = entry.getVertex( graphIndex );
//			CategoricalFeature f1 = entry.getFeature(indexFrom);
//			CategoricalFeature f2 = entry.getFeature(indexTo);
//		    
//			Color c = CategoricalFeatureColorGradient.getColor(f1, f2);
//			colorAction.getColorLookup().add(invElement, c.getRGB() );
//		}
//		
//		graphController.getPainter().putAction("fill", colorAction, true);
//		graphController.repaintVis();
//	}
//	
	private void updateSelection() {
		
		/// color
		LookupMultiColorAction colorAction = new LookupMultiColorAction("graph.nodes", VisualItem.FILLCOLOR);
		colorAction.setDefaultColor( DEFAULT_FILL_COLOR );
		
		// assume single cell selection
		int matrixCol = shape.getMatrixTable().getSelectedColumn();
		int matrixRow = shape.getMatrixTable().getSelectedRow();
		if ( matrixCol >= 0 && matrixRow >= 0 ) {
			addCell( matrixRow, matrixCol, colorAction.getColorLookup(), model.getBaseTable() );
		}

		graphController0.getPainter().putAction("fill", colorAction, true);

		
		LookupMultiColorAction colorAction1 = new LookupMultiColorAction("graph.nodes", VisualItem.FILLCOLOR);
		colorAction1.setDefaultColor( DEFAULT_FILL_COLOR );
		
		// assume single cell selection
		if ( matrixCol >= 0 && matrixRow >= 0 ) {
			addCell( matrixRow, matrixCol, colorAction1.getColorLookup(), model.getBaseTable1() );
		}

		graphController1.getPainter().putAction("fill", colorAction1, true);

		
		
		/// size
		LookupSizeUpAction sizeUpAction = new LookupSizeUpAction("graph", 3);
		
		// selected nodes in matrix table
		if ( matrixCol >= 0 && matrixRow >= 0 ) {
			addCell( matrixRow, matrixCol, sizeUpAction.getLookup(), model.getBaseTable() );
		}
		
		LookupSizeUpAction sizeUpAction1 = new LookupSizeUpAction("graph", 3);
		
		// selected nodes in matrix table
		if ( matrixCol >= 0 && matrixRow >= 0 ) {
			addCell( matrixRow, matrixCol, sizeUpAction1.getLookup(), model.getBaseTable1() );
		}
		
		graphController0.getPainter().putAction("sizeUp", sizeUpAction, true);
		graphController1.getPainter().putAction("sizeUp", sizeUpAction1, true);
		
		graphController0.repaintVis();
		graphController1.repaintVis();
	}

	//	
//	private void updateCategories() {
//		for (CCBaseEntry entry : model.getBaseTable().values() ) {
//			CategoricalFeature f = entry.getFeature(0);
//			shape.updateColorPanel(f.getFeatureDescriptor().getCategories());
//			return;
//		}
//	}
//	
//	private void updateStats() {
//		shape.getNodeCountValueLabel().setText( "" + model.getNumVertices() );
//		shape.getEdgeCountValueLabel().setText( "" + model.getNumEdges() );
//		shape.getAccuracy1ValueLabel().setText( String.format(
//				"%1$.2f", model.getAccuracy1()  ) );
//		shape.getAccuracy2ValueLabel().setText( String.format(
//				"%1$.2f", model.getAccuracy2()  ) );
//	}
//
//	
//	private void updateHideNodes( int graphIndex) {
//		boolean hideNodes = shape.getMatrixHideCheckBox().isSelected();
//		
//		LookupVisibilityAction visAction = new LookupVisibilityAction("graph.nodes");
//		visAction.setShowAll( !hideNodes);
//		LookupEdgeVisibilityAction visEdgeAction = new LookupEdgeVisibilityAction("graph.edges");
//		visEdgeAction.setShowAll( !hideNodes);
//		
//		// assume single cell selection
//		int matrixCol = shape.getMatrixTable().getSelectedColumn();
//		int matrixRow = shape.getMatrixTable().getSelectedRow();
//		int detailCol = shape.getDetailTable().getSelectedRow();
//		
//		// selected nodes in detail table
//		if ( detailCol >= 0 ) {
//			CCBaseEntry be = model.getDtModel().getBaseEntry( detailCol );
//			visAction.getLookup().add( be.getVertex( graphIndex ), null );
//		}
//		else {
//			// selected nodes in matrix table
//			if ( matrixCol >= 0 && matrixRow >= 0 ) {
//				CCMatrixCell c = (CCMatrixCell) model.getMatrixTableModel().getCell(matrixRow, matrixCol);
//				addCell( c, visAction.getLookup(), graphIndex);
//			}
//		}
//		
//		// update edgeVisibility
//		visEdgeAction.setLookup( visAction.getLookup() );
//		
//		graphController.getPainter().putAction("visibility", visAction, true);
//		graphController.getPainter().putAction("visibility.edge", visEdgeAction, true);
//		graphController.repaintVis();
//	}
//	
	
	private void addCell( int row, int col, ElementLookup lookup, CCBaseTable baseTable  ) {
		// TODO: instead of CCBaseTable lookup, use new lookup
		//CCBaseTable baseTable = model.getBaseTable();
		CCMatrix matrix = model.getMatrix();

		InvenioElementSet setX = matrix.getXSet(col);
		InvenioElementSet setY = matrix.getYSet(row);
		ClusterIntersect intersect = ClusterComparisonProcessor.intersectClusters(setX, setY);
		
		for (String key : intersect.xOnly) {
			lookup.add( baseTable.get( key ), DEFAULT_FILL_XONLY_COLOR );
		}
		for (String key : intersect.yOnly) {
			lookup.add( baseTable.get( key ), DEFAULT_FILL_YONLY_COLOR );
		}
		for (String key : intersect.xyBoth) {
			lookup.add( baseTable.get( key ), DEFAULT_FILL_XY_BOTH_COLOR );
		}
	}
	
	
//	private void setupColoring(GroupingAlgorithmResult grouping) {
//		
//		ElementSetsMultiColorAction colorAction = new ElementSetsMultiColorAction("graph.nodes", VisualItem.FILLCOLOR);
//		
//		int i = 0;
//		for (InvenioElementSet set : grouping.getGroupElementSet().getSets() ) {
//			int r = (int) (Math.random() * 256);
//			int g = (int) (Math.random() * 256);
//			int b = (int) (Math.random() * 256);
//			
//			colorAction.getColorLookup().addSet(set, ColorLib.rgb(r, g, b));
//		}		
//		
//		graphController.getPainter().removeAction("fill");
//		graphController.getPainter().putAction("fill", colorAction);
//		graphController.getPainter().runAction("fill");
//		graphController.repaintVis();
//	}
//
//	private void updateStats(GroupingAlgorithmResult grouping) {
//		int minSize = Integer.MAX_VALUE;
//		int maxSize = -1;
//		int totalSize = 0;
//		
//		int i = 0;
//		for (InvenioElementSet set : grouping.getGroupElementSet().getSets() ) {
//			int size = set.getNumVertices();
//			if (minSize > size)
//				minSize = size;
//			if (maxSize < size)
//				maxSize = size;
//			totalSize += size;
//			i++;
//		}
//		
//		shape.setMinLabelValue(minSize);
//		shape.setMaxLabelValue(maxSize);
//		shape.setAvgLabelValue( (double)totalSize / (double) i);
//	}
//	
//
//	
//	protected void updateClusteringTable(GroupingAlgorithmResult grouping) {
//		ClusterTableModel tm = (ClusterTableModel) shape.getClusterSelectTable().getModel();
//		
//		tm.setSets(grouping.getGroupElementSet());
//	}
//
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		if (e.getSource() == shape.getClusterSelectTable().getSelectionModel())
//			updateClusterSelection();
//		
//	}
//	
//	public void itemStateChanged(ItemEvent e) {
//	    Object source = e.getItemSelectable();
//
//	    if (source == shape.getShowAllCheckBox() ) {
//	        updateShowAll();
//	    }
//	}
//
//	private void updateClusterSelection() {
//		int[] row = shape.getClusterSelectTable().getSelectedRows();
//		
//		ElementSetsSizeUpAction sizeUpAction = new ElementSetsSizeUpAction("graph", 3);
//		sizeUpAction.getMembership().clearSets();
//		
//		for (int i = 0; i < row.length; i++) {
//			ClusterTableModel tm = (ClusterTableModel) shape.getClusterSelectTable().getModel();
//			InvenioElementSet set = tm.getInvenioElementSet(row[i]);
//			sizeUpAction.getMembership().addSet(set);
//		}
//		
//		ElementSetsVisibilityAction visAction = graphController.createElementSetsVisibilityAction();
//		visAction.setMembership(sizeUpAction.getMembership());
//
//		graphController.getPainter().putAction("sizeUp", sizeUpAction, true);
//
//		graphController.getPainter().putAction("visibility", visAction, true);
//		
//		graphController.repaintVis();
//		
////		int[] row = shape.getClusterSelectTable().getSelectedRows();
////		
////		ElementSetsColorAction colorAction = graphController.createElementSetsFillAction();
////		colorAction.getMembership().clearSets();
////		
////		for (int i = 0; i < row.length; i++) {
////			ClusterTableModel tm = (ClusterTableModel) shape.getClusterSelectTable().getModel();
////			InvenioElementSet set = tm.getInvenioElementSet(row[i]);
////			colorAction.getMembership().addSet(set);
////		}
////		
//////		ElementSetsVisibilityAction visAction = graphController.getElementSetsVisibilityAction();
//////		visAction.setMembership(colorAction.getMembership());
////		ElementSetsPredicate p = graphController.getElementSetsPredicate();
////		p.setMembership(colorAction.getMembership());
////		
//////		graphController.repaintVisibility();
////		graphController.setElementSetsPredicate(p);
//////		graphController.repaintColor();
////		
////		graphController.getPainter().removeAction("fill");
////		graphController.getPainter().putAction("fill", colorAction);
////		graphController.getPainter().runAction("fill");
////		graphController.repaintVis();
//	}
//	
//	private void updateShowAll() {
//		boolean showAll = shape.getShowAllCheckBox().isSelected();
//		
//		ElementSetsVisibilityAction visAction = (ElementSetsVisibilityAction) graphController.getPainter().getAction("visibility");
//		visAction.setShowAll(showAll);
//
//		graphController.getPainter().putAction("visibility", visAction, true);
//		graphController.repaintVis();
//	}

}
