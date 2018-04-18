package invenio.operator.comparison;


public class IntersectionPredicate<E> implements AlignedPairPredicate<E> {

	public boolean satisfies(AlignedPair<E> value) {
		if (value.getElement1() != null && value.getElement2() != null)
			return true;
		else
			return false;
	}

}
