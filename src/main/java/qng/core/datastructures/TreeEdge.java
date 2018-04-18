package qng.core.datastructures;

public interface TreeEdge<K, E> {
	Position<E> getParent();
	Position<E> getChild();
	K getKey();
}
