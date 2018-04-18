package invenio.wf.items.vis.nodelabel;

import java.util.Arrays;

public class Matrix {
	private final MatrixSlice[] slices;
	private final String[] header;
	
	public Matrix(int numSlices, String[] header) {
		this.header = Arrays.copyOf(header, header.length);
		this.slices = new MatrixSlice[numSlices];
		
		int sliceSize = header.length;
		for (int i = 0; i < slices.length; i++) {
			slices[i] = new MatrixSlice(sliceSize);
		}
	}
	
	public MatrixSlice getSlice(int index) {
		return slices[index];
	}
	
	public int size() {
		return slices.length;
	}
	
	public String[] getHeader() {
		return header;
	}
}
