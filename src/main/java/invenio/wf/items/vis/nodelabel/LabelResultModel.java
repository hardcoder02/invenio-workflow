package invenio.wf.items.vis.nodelabel;

public class LabelResultModel {
	private MatrixTableModel mtModel;
	private DetailTableModel dtModel;
	private Matrix matrix;
	private BaseTable baseTable;
	private int numVertices;
	private int numEdges;
	private float accuracy1;
	private float accuracy2;

	public DetailTableModel getDtModel() {
		return dtModel;
	}

	public void setDtModel(DetailTableModel dtModel) {
		this.dtModel = dtModel;
	}

	public MatrixTableModel getMatrixTableModel() {
		return mtModel;
	}

	public void setMatrixTableModel(MatrixTableModel mtModel) {
		this.mtModel = mtModel;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public BaseTable getBaseTable() {
		return baseTable;
	}

	public void setBaseTable(BaseTable baseTable) {
		this.baseTable = baseTable;
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
