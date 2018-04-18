package invenio.operator;

public interface Predicate<E> {
	boolean satisfies(E value);
}
