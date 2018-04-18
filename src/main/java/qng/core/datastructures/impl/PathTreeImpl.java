package qng.core.datastructures.impl;

import java.util.Iterator;
import java.util.List;

import qng.core.datastructures.NamedPosition;
import qng.core.datastructures.Path;
import qng.core.datastructures.PathTree;

public class PathTreeImpl<K, E> extends HashNodeTree<K, E> implements PathTree<K, E> {
	
	public NamedPosition<K, E> addPath(NamedPosition<K, E> node, Path<K> path) {
		NamedTreeNode<K, E> currentNode = getNamedTreeNode(node);
				
		List<K> pathKeys = path.keys();
		for (K k : pathKeys) {
			if (containsChild(currentNode, k)) {
				currentNode = getChild(currentNode, k);
			}
			else {
				currentNode = addOrReplaceChild(currentNode, k, null);
			}
		}
		
		return currentNode;
	}
	
	@Override
	public Pair<K, E> evaluatePath(NamedPosition<K, E> node, Path<K> path) {
		NamedTreeNode<K, E> currentNode = getNamedTreeNode(node);
		Path<K> remainingPath = new ArrayListPath<K>();
		Iterator<K> pathKeys = path.keys().iterator();
		
		while (pathKeys.hasNext()) {
			K key = pathKeys.next();
			if (containsChild(currentNode, key)) {
				currentNode = getChild(currentNode, key);
			}
			else {
				remainingPath.appendSegment(key);
				break;
			}
		}
		
		while (pathKeys.hasNext()) {
			remainingPath.appendSegment(pathKeys.next());
		}
		
		return new Pair<K, E>(currentNode, remainingPath);
	}
	
	static class Pair<K, E> implements PathTree.Pair<K, E> {
		private final NamedPosition<K, E> node;
		private final Path<K> path;
		
		Pair(NamedPosition<K, E> node, Path<K> path) {
			this.node = node;
			this.path = path;
		}
		
		@Override
		public NamedPosition<K, E> getNode() {
			return node;
		}

		@Override
		public Path<K> getPath() {
			return path;
		}
		
	}
}
