package qng.core.datastructures.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import qng.core.datastructures.Position;

class LinkedNode<E> implements TreeNode<E> {
	private E element;
	private List<TreeNode<E>> children = new LinkedList<TreeNode<E>>();
	
	public LinkedNode() {
	}
	
	public LinkedNode(E element) {
		this.element = element;
	}

	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#element()
	 */
	@Override
	public E element() {
		return element;
	}

	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#setElement(E)
	 */
	@Override
	public void setElement(E element) {
		this.element = element;
	}

	/* (non-Javadoc)
	 * @see edu.georgetown.cosc.t3.structures.impl.TreeNode#getChilren()
	 */
	public Iterator<TreeNode<E>> getChilren() {
		return children.iterator();
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
	public boolean addChild(TreeNode<E> newChild) {
		return children.add(newChild);
	}
}
