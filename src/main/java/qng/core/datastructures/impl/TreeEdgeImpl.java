package qng.core.datastructures.impl;

import qng.core.datastructures.Position;
import qng.core.datastructures.TreeEdge;

class TreeEdgeImpl<K, E> implements TreeEdge<K, E> {
	private K key;
	private Position<E> parent, child;
	
	protected TreeEdgeImpl(K key, Position<E> parent, Position<E> child) {
		this.key = key;
		this.parent = parent;
		this.child = child;
	}
	
	@Override
	public Position<E> getChild() {
		return child;
	}
	
	@Override
	public Position getParent() {
		return parent;
	}

	@Override
	public K getKey() {
		return key;
	}
}
