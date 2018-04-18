package qng.core.datastructures;

import java.util.Iterator;

public interface NamedNodeTree<K, E> extends BasicTree<E> {
	boolean containsChild(NamedPosition<K, E> node, K childNodeName);
	NamedPosition<K, E> addOrReplaceChild(NamedPosition<K, E> node, K childNodeName, E newElement);
	NamedPosition<K, E> getChild(NamedPosition<K, E> node, K childNodeName);
	Iterator<? extends NamedPosition<K, E>> getChildren(NamedPosition<K, E> node);
}
