package qng.tabular;

import qng.core.query.DuplicateException;

public interface AggregateModifiableTable extends AggregateTable, ModifiableTable {
	AggregateTuple addTuple();
	
	void addAggregatedColumn(String name, Class clazz) throws DuplicateException;
	boolean removeAggregatedColumn(String name);
	Tuple addAggregatedTuple(AggregateTuple t);
	boolean removeAggregatedTuple(Tuple t);
}
