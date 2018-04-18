package qng.core.datastructures.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import qng.core.datastructures.Position;

class HashNode<K, E> implements NamedTreeNode<K, E> {

	public boolean addChild(NamedTreeNode<K, E> newChild) {
		// TODO Auto-generated method stub
		return false;
	}

	private E element;
	private K name;
	private Map<K, NamedTreeNode<K, E>> children = new HashMap<K, NamedTreeNode<K, E>>();
	
	public HashNode(K name) {
		this.name = name;
	}
	
	public HashNode(K name, E element) {
		this.name = name;
		this.element = element;
	}

	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#element()
	 */
	public E element() {
		return element;
	}
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#setElement(E)
	 */
	public void setElement(E element) {
		this.element = element;
	}

	public K getName() {
		return name;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#getChilren()
	 */
	public Iterator<NamedTreeNode<K, E>> getChilren() {
		return children.values().iterator();
	}
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#isChildrenEmpty()
	 */
	public boolean isChildrenEmpty() {
		return children.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#numberOfChildren()
	 */
	public int numberOfChildren() {
		return children.size();
	}
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#replaceElement(E)
	 */
	public E replaceElement(E newElement) {
		E oldElement = this.element;
		this.element = newElement;
		return oldElement;
	}
	
	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#addChild(edu.georgetown.cosc.t3.structures.Position)
	 */
	public NamedTreeNode<K, E> addChild(K childNodeName, E childElement) {
		NamedTreeNode<K, E> result = new HashNode<K, E>(childNodeName, childElement);
		children.put(childNodeName, result);
		return result;
	}
	
	public boolean containsChild(K childNodeName) {
		return children.containsKey(childNodeName);
	}
	
	public NamedTreeNode<K, E> getChild(K childNodeName) {
		return children.get(childNodeName);
	}

}
