package qng.core.datastructures.impl;

import qng.core.datastructures.Position;
import qng.core.datastructures.Tree;

public class LinkedNodeTree<E> extends AbstractTree<E> implements Tree<E> {

	@Override
	protected TreeNode<E> createTreeNode() {
		return new LinkedNode<E>();
	}
	
	@Override
	public Position<E> addChild(Position<E> node, E newElement) {
		TreeNode<E> newChild = createTreeNode();
		newChild.setElement(newElement);
		((LinkedNode<E>)getTreeNode(node)).addChild(newChild);
		size++;
		return newChild;
	}
}
