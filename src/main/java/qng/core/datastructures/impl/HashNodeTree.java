package qng.core.datastructures.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import qng.core.datastructures.NamedNodeTree;
import qng.core.datastructures.NamedPosition;
import qng.core.datastructures.Position;

// TODO: consider extending AbstractTree
public class HashNodeTree<K, E> implements NamedNodeTree<K, E> {
	
	protected NamedTreeNode<K, E> root;
	protected int size;
	
	public HashNodeTree() {
		root = createNamedTreeNode();
		size = 1;
	}
	
	public Position<E> root() {
		return root;
	}
	
	public Iterator<? extends Position<E>> children(Position<E> node) {
		//return new PositionIterator<Position<E>, TreeNode<E>> (getTreeNode(node).getChilren());
		return (getNamedTreeNode(node).getChilren());
	}

	public boolean isInternal(Position<E> node) {
		return ! getNamedTreeNode(node).isChildrenEmpty();
	}

	public boolean isExternal(Position<E> node) {
		return getNamedTreeNode(node).isChildrenEmpty();
	}

	public boolean isRoot(Position<E> node) {
		return (root == node);
	}

	public int size() {
		return size;
	}
	
	public int numberOfChildren(Position<E> node) {
		return getNamedTreeNode(node).numberOfChildren();
	}

	public E replaceElement(Position<E> node, E newElement) {
		return getNamedTreeNode(node).replaceElement(newElement);
	}
	
	public boolean containsChild(NamedPosition<K, E> node, K childNodeName) {
		return getNamedTreeNode(node).containsChild(childNodeName);
	}
	
	public NamedTreeNode<K, E> addOrReplaceChild(NamedPosition<K, E> node, K childNodeName, E newElement) {
		NamedTreeNode<K, E> castNode = getNamedTreeNode(node);
		
		if (!castNode.containsChild(childNodeName))
			size++;
		
		return castNode.addChild(childNodeName, newElement);
	}
	
	public NamedTreeNode<K, E> getChild(NamedPosition<K, E> node, K childNodeName) {
		return getNamedTreeNode(node).getChild(childNodeName);
	}
	
	
	public Iterator<? extends NamedPosition<K, E>> getChildren(NamedPosition<K, E> node) {
			return getNamedTreeNode(node).getChilren();
	}
	
	protected NamedTreeNode<K, E> getNamedTreeNode(Position<E> node) {
		return ((NamedTreeNode<K, E>)node);
	}
	
	protected NamedTreeNode<K, E> createNamedTreeNode() {
		return new HashNode<K, E>(null, null);
	}
}
