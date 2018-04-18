package qng.core.datastructures.impl;

import java.util.Iterator;

import qng.core.datastructures.BasicTree;
import qng.core.datastructures.Position;
import qng.core.datastructures.Tree;

abstract class AbstractTree<E> implements BasicTree<E> {

	protected TreeNode<E> root;
	protected int size;
	
	public AbstractTree() {
		root = createTreeNode();
		size = 1;
	}
	
	public Position<E> root() {
		return root;
	}
	
	public Iterator<? extends Position<E>> children(Position<E> node) {
		//return new PositionIterator<Position<E>, TreeNode<E>> (getTreeNode(node).getChilren());
		return (getTreeNode(node).getChilren());
	}

	public boolean isInternal(Position<E> node) {
		return ! getTreeNode(node).isChildrenEmpty();
	}

	public boolean isExternal(Position<E> node) {
		return getTreeNode(node).isChildrenEmpty();
	}

	public boolean isRoot(Position<E> node) {
		return (root == node);
	}

	public int size() {
		return size;
	}

	public int numberOfChildren(Position<E> node) {
		return getTreeNode(node).numberOfChildren();
	}

	public E replaceElement(Position<E> node, E newElement) {
		return getTreeNode(node).replaceElement(newElement);
	}
	
	
	protected TreeNode<E> getTreeNode(Position<E> node) {
		return ((TreeNode<E>)node);
	}
	
	protected abstract TreeNode<E> createTreeNode();
}

