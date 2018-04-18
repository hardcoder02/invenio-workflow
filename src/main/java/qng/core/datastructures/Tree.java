package qng.core.datastructures;

public interface Tree<E> extends BasicTree<E> {
	Position<E> addChild(Position<E> node, E newElement);
}
