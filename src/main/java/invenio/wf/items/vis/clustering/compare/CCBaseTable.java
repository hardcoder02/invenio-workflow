package invenio.wf.items.vis.clustering.compare;

import invenio.data.InvenioVertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CCBaseTable {
	private final Map<String, InvenioVertex> id2Vertex;
	
	public CCBaseTable(int expectedSize) {
		id2Vertex = new HashMap<String, InvenioVertex>(expectedSize);
	}
	
	public boolean containsKey(String key) {
		return id2Vertex.containsKey(key);
	}
	
	public InvenioVertex put(String key, InvenioVertex entry) {
		return id2Vertex.put(key, entry);
	}
	
	public InvenioVertex get(String key) {
		return id2Vertex.get(key);
	}
	
	public int size() {
		return id2Vertex.size();
	}
	
	public boolean isEmptry() {
		return id2Vertex.isEmpty();
	}
	
	public Collection<InvenioVertex> values() {
		return id2Vertex.values();
	}
}
