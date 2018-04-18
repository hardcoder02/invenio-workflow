package invenio.op.operation.sim.impl;

import invenio.op.operation.impl.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimilarityConfig implements Iterable<Pair<String, Object>> {
	private final Map<String, Integer> attributeMap = new HashMap<String, Integer>();
	private final List<Pair<String, Object>> attributeList = new ArrayList<Pair<String, Object>>();
	
	public void clear() {
		attributeMap.clear();
		attributeList.clear();
	}

	public boolean containsKey(Object key) {
		return attributeMap.containsKey(key);
	}

	public Object get(Object key) {
		Integer ind = attributeMap.get(key);
		
		return (ind == null) ? null : attributeList.get( ind ).second;
	}
	
	public Object getPair(Object key) {
		Integer ind = attributeMap.get(key);
		
		return (ind == null) ? null : attributeList.get( ind );
	}

	public boolean isEmpty() {
		return attributeMap.isEmpty();
	}

	public int size() {
		return attributeMap.size();
	}

	public Object put(String name, Object value) {
		Pair<String, Object> p = null;
		if ( attributeMap.containsKey( name ) ) {
			p = attributeList.get( attributeMap.get( name ) );
			Object oldValue = p.second;
			p.second = value;
			return oldValue;
		}
		else {
			p = new Pair<String, Object>(name, value);
			attributeList.add( p );
			attributeMap.put( name, attributeList.size() - 1);
			return null;
		}
	}
	
	public Iterator<Pair<String, Object>> iterator() {
		return attributeList.iterator();
	}
}
