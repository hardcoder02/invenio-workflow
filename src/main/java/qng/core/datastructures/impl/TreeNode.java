package qng.core.datastructures.impl;

import java.util.Iterator;

import qng.core.datastructures.Position;

interface TreeNode<E> extends Position<E> {

	public abstract Iterator<TreeNode<E>> getChilren();

	public abstract boolean isChildrenEmpty();

	public abstract int numberOfChildren();

	public abstract E replaceElement(E newElement);

	//public abstract boolean addChild(TreeNode<E> newChild);

}