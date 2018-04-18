package qng.core.datastructures;

import java.util.Iterator;

public interface BasicTree<E> {
	Position<E> root();
	//Iterator<Position<E>> children(Position<E> node);
	Iterator<? extends Position<E>> children(Position<E> node);
	
	boolean isInternal(Position<E> node);
	boolean isExternal(Position<E> node);
	boolean isRoot(Position<E> node);
	
	int size();
	int numberOfChildren(Position<E> node);
	E replaceElement(Position<E> node, E newElement);
}
