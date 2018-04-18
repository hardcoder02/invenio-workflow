package invenio.operator.comparison;


public class UnionPredicate<E> implements AlignedPairPredicate<E> {

	public boolean satisfies(AlignedPair<E> value) {
		return true;
	}

}
