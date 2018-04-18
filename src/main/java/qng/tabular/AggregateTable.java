package qng.tabular;

import java.util.Iterator;
import java.util.List;

public interface AggregateTable extends Table {
	AggregateSchema getSchema();
	Iterator<? extends AggregateTuple> getIterator();
	List<? extends AggregateTuple> getTuples();
	
	Table getAggregatedTable();
}
