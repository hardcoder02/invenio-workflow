package invenio.wf.items.vis.clustering.compare;

import invenio.data.InvenioElementSet;

import java.util.Arrays;

public class CCMatrix {
	private final CCMatrixCell[][] cells;
	private final InvenioElementSet[] xSets;
	private final InvenioElementSet[] ySets;
	
	public CCMatrix(InvenioElementSet[] xSets, InvenioElementSet[] ySets) {
		this.xSets = Arrays.copyOf(xSets, xSets.length);
		this.ySets = Arrays.copyOf(ySets, ySets.length);
		this.cells = new CCMatrixCell[ySets.length][xSets.length];
		
		for (int row = 0; row < ySets.length; row++) {
			for (int col = 0; col < xSets.length; col++) {
				cells[row][col] = new CCMatrixCell();
			}
		}
	}
	
	public int numRows() {
		return ySets.length;
	}
	
	public int numColumns() {
		return xSets.length;
	}
	
	public InvenioElementSet getXSet(int i) {
		return xSets[i];
	}
	
	public InvenioElementSet getYSet(int i) {
		return ySets[i];
	}
	
	public CCMatrixCell getCell(int row, int column) {
		return cells[row][column];
	}
}
