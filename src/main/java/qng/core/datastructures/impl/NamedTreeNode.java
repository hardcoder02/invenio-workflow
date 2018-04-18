package qng.core.datastructures.impl;

import java.util.Iterator;

import qng.core.datastructures.NamedPosition;

interface NamedTreeNode<K, E> extends NamedPosition<K, E> {

	Iterator<NamedTreeNode<K, E>> getChilren();

	boolean isChildrenEmpty();

	int numberOfChildren();

	E replaceElement(E newElement);

	NamedTreeNode<K, E> addChild(K childName, E childElement);
	
	boolean containsChild(K childName);
	NamedTreeNode<K, E> getChild(K childNodeName);

}