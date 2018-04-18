package qng.core.datastructures.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import qng.core.datastructures.Path;

public class ArrayListPath<K> implements Path<K> {
	
	public final static int PATH_DEFAULT_CAPACITY = 10;
	private List<K> path = new ArrayList<K>(PATH_DEFAULT_CAPACITY);

	@Override
	public int size() {
		return path.size();
	}
	
	@Override
	public boolean isEmpty() {
		return path.isEmpty();
	}

	@Override
	public boolean containsPath(Path<K> otherPath) {
		if (otherPath.size() > path.size())
			return false;
		
		List<K> otherKeys = otherPath.keys();
		Iterator<K> thisKeys = path.iterator();
		for (K k : otherKeys) {
			if (!k.equals(thisKeys.next()))
				return false;
		}
		
		return true;
	}
	
	@Override
	public Path<K> appendPath(Path<K> otherPath) {
		for (K key : otherPath.keys()) {
			appendSegment(key);
		}
		return this;
	}
	
	@Override
	public Path<K> appendPath(K[] segmentKeys) {
		for (K k : segmentKeys) {
			appendSegment(k);
		}
		
		return this;
	}
	
	@Override
	public Path<K> appendPath(K[] keys, int from) throws ArrayIndexOutOfBoundsException {
		for (int i = from; i < keys.length; i++) {
			appendSegment(keys[i]);
		}
		
		return this;
	}

	@Override
	public Path<K> appendSegment(K segmentKey) {
		path.add(segmentKey);
		return this;
	}

	@Override
	public Path commonSubpath(Path otherPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> keys() {
		return Collections.unmodifiableList(path);
	}

}
