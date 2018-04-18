package qng.core.datastructures;

import java.util.List;

public interface Path<K> {
	int size();
	boolean isEmpty();
	boolean containsPath(Path<K> otherPath);
	Path<K> appendPath(Path<K> otherPath);
	Path<K> appendPath(K[] keys);
	
	/**
	 * 
	 * @param keys
	 * @param from
	 * @throws ArrayIndexOutOfBoundsException if from is negative - no effect if >= keys.length
	 */
	Path<K> appendPath(K[] keys, int from) throws ArrayIndexOutOfBoundsException;
	Path<K> appendSegment(K key);
	
	//Path<K>[] segments();
	List<K> keys();
	Path<K> commonSubpath(Path<K> otherPath);
}
