package invenio.wf.items.vis.nodelabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MatrixCell {
	private List<BaseEntry> entries = new ArrayList<BaseEntry>();
	private final MatrixSlice slice;
	
	public MatrixCell(MatrixSlice slice) {
		if (slice == null)
			throw new NullPointerException("Matrix Slice cannot be null");
		this.slice = slice;
	}
	
	public int size() {
		return entries.size();
	}
	
	public List<BaseEntry> getEntries() {
		return Collections.unmodifiableList(entries);
	}
	
	public Iterator<BaseEntry> iterator() {
		return entries.iterator();
	}
	
	public BaseEntry get(int index) {
		return entries.get(index);
	}
	
	public void add(BaseEntry e) {
		entries.add(e);
		slice.invalidateStats();
	}

	public MatrixSlice getSlice() {
		return slice;
	}

}
