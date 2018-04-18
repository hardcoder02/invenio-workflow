package qng.tabular;

import java.util.List;

public interface AggregateTuple extends Tuple {
	AggregateSchema getSchema();
	List<Tuple> getAggregatedTuples();
}
