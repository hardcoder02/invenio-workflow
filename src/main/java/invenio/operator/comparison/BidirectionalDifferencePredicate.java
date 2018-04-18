package invenio.operator.comparison;


public class BidirectionalDifferencePredicate<E> implements AlignedPairPredicate<E> {

	public boolean satisfies(AlignedPair<E> value) {
		if (value.getElement1() != null && value.getElement2() == null)
			return true;
		else if (value.getElement1() == null && value.getElement2() != null)
			return true;
		else
			return false;
	}

}
