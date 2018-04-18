package invenio.op.operation.sim.ego.impl;

import invenio.op.operation.impl.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import qng.core.query.DuplicateException;

public class EgoNetAlignment<U, V, I> implements Iterable<Triple<U, V, I>> {
	private final List<Triple<U, V, I>> elements = new ArrayList<Triple<U, V, I>>();
	private final Map<U, Integer> indexUtoV = new HashMap<U, Integer>();
	
	public void addAlignment(U u, V v, I i) throws DuplicateException {
		if (u != null && indexUtoV.containsKey( u ))
			throw new DuplicateException("Duplicate non-null element:" + u);
		elements.add(new Triple<U, V, I>(u, v, i) );
		indexUtoV.put(u, elements.size() - 1);
	}

	@Override
	public Iterator<Triple<U, V, I>> iterator() {
		return elements.iterator();
	}
	
	public int size() {
		return elements.size();
	}
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public Triple<U, V, I> getAlignment(U u) throws NullPointerException {
		if ( u == null)
			throw new NullPointerException("Only non-null elements can be quieried");
		
		return elements.get( indexUtoV.get( u ) );
	}
	
	public boolean containsAlignment(U u) throws NullPointerException {
		if ( u == null)
			throw new NullPointerException("Only non-null elements can be quieried");
		
		return indexUtoV.containsKey( u );
	}
}
