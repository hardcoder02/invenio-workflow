package qng.core.datastructures;


public interface PathTree<K, E> extends NamedNodeTree<K, E> {
	NamedPosition<K, E> addPath(NamedPosition<K, E> node, Path<K> path);
	//Iterator<? extends TreeEdge<K, Position<E>>> childEdges(Position<E> node);
	
	Pair<K, E> evaluatePath(NamedPosition<K, E> node, Path<K> path);
	
	interface Pair<K, E> {
		Path<K> getPath();
		NamedPosition<K, E> getNode();
	}
}
