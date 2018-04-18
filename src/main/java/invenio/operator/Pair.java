package invenio.operator;


public class Pair<E> {
	private final E element1, element2;
	
	public Pair(E element1, E element2) {
		if (element1 == null && element2 == null)
			throw new IllegalArgumentException("Both elements cannot be null");
		
		this.element1 = element1;
		this.element2 = element2;
	}

	public E getElement1() {
		return element1;
	}

	public E getElement2() {
		return element2;
	}
	
	public boolean equals(Object o){
		if (! (o instanceof Pair))
			return false;
		return this == o || (((Pair<E>)o).getElement1().equals(element1) && ((Pair<E>)o).getElement2().equals(element2));
	}
	
}
