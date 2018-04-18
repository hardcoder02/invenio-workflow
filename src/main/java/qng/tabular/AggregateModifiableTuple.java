package qng.tabular;

public interface AggregateModifiableTuple extends AggregateTuple,
		ModifiableTuple {

	void addAggregatedTuple(Tuple t);
}
