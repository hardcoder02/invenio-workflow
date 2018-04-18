package invenio.wf.items.vis.nodelabel;


public class MatrixSlice {
	private final MatrixCell[][] cells;
	private SliceStats stats;
	
	
	public MatrixSlice(int size) {
		this.cells = new MatrixCell[size][size];
		
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells.length; col++) {
				cells[row][col] = new MatrixCell(this);
			}
		}
	}
	
	public MatrixCell getCell(int row, int column) {
		return cells[row][column];
	}
	
	public int size() {
		return cells.length;
	}
	
	void invalidateStats() {
		stats = null;
	}
	
	public SliceStats getStats() {
		if (stats == null) {
			stats = new SliceStats();
			
			for (int row = 0; row < cells.length; row++) {
				for (int col = 0; col < cells.length; col++) {
					int cellSize = cells[row][col].size();
					stats.totalEntries += cellSize;
					if (stats.minEntries > cellSize)
						stats.minEntries = cellSize;
					if (stats.maxEntries < cellSize)
						stats.maxEntries = cellSize;
				}
			}
		}
		
		return stats;
	}
	
	public static class SliceStats {
		private int minEntries = Integer.MAX_VALUE;
		private int maxEntries = 0;
		private int totalEntries = 0;
		
		public int getMinEntries() {
			return minEntries;
		}
		public int getMaxEntries() {
			return maxEntries;
		}
		public int getTotalEntries() {
			return totalEntries;
		}
		
		
	}
}
