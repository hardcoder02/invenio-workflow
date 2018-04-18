package invenio.wf.items.vis.nodelabel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BaseTable {
	private final Map<String, BaseEntry> entries;
	
	public BaseTable(int expectedSize) {
		entries = new HashMap<String, BaseEntry>(expectedSize);
	}
	
	public boolean containsKey(String key) {
		return entries.containsKey(key);
	}
	
	public BaseEntry put(String key, BaseEntry entry) {
		return entries.put(key, entry);
	}
	
	public BaseEntry get(String key) {
		return entries.get(key);
	}
	
	public int size() {
		return entries.size();
	}
	
	public boolean isEmptry() {
		return entries.isEmpty();
	}
	
	public Collection<BaseEntry> values() {
		return entries.values();
	}
}
