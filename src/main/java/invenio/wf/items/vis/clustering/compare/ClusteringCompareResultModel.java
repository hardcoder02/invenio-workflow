package invenio.wf.items.vis.clustering.compare;

public class ClusteringCompareResultModel {
	private CCMatrixTableModel mtModel;
//	private DetailTableModel dtModel;
	private CCMatrix matrix;
	private CCBaseTable baseTable;
	private CCBaseTable baseTable1;
	private int numVertices;
	private int numEdges;
	private float accuracy1;
	private float accuracy2;

//	public DetailTableModel getDtModel() {
//		return dtModel;
//	}
//
//	public void setDtModel(DetailTableModel dtModel) {
//		this.dtModel = dtModel;
//	}

	public CCMatrixTableModel getMatrixTableModel() {
		return mtModel;
	}

	public void setMatrixTableModel(CCMatrixTableModel mtModel) {
		this.mtModel = mtModel;
	}

	public CCMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(CCMatrix matrix) {
		this.matrix = matrix;
	}

	public CCBaseTable getBaseTable() {
		return baseTable;
	}

	public void setBaseTable(CCBaseTable baseTable) {
		this.baseTable = baseTable;
	}
	
	public CCBaseTable getBaseTable1() {
		return baseTable1;
	}

	public void setBaseTable1(CCBaseTable baseTable1) {
		this.baseTable1 = baseTable1;
	}
	
	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

	public float getAccuracy1() {
		return accuracy1;
	}

	public void setAccuracy1(float accuracy1) {
		this.accuracy1 = accuracy1;
	}

	public float getAccuracy2() {
		return accuracy2;
	}

	public void setAccuracy2(float accuracy2) {
		this.accuracy2 = accuracy2;
	}
	
	
}
