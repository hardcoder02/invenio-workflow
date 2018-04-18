package qng.tabular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import qng.core.query.NotExistsException;

public class AggregateTupleImpl implements AggregateModifiableTuple {
	private final AggregateModifiableTable table;
	private final List<Object> values = new ArrayList<Object>();
	private final List<Tuple> aggregatedTuples = new ArrayList<Tuple>();
	
	AggregateTupleImpl(AggregateModifiableTable table) {
		this.table = table;
	}
	
	@Override
	public void addAggregatedTuple(Tuple t) {
		aggregatedTuples.add(t);
	}

	@Override
	public List<Tuple> getAggregatedTuples() {
		return Collections.unmodifiableList(aggregatedTuples);
	}

	@Override
	public AggregateSchema getSchema() {
		return table.getSchema();
	}

	@Override
	public Object getValue(int columnIndex) {
		return values.get(columnIndex);
	}

	@Override
	public Object getValue(String columnName) throws NotExistsException {
		int index = getSchema().getColumnIndex(columnName);
		return getValue(index);
	}

	@Override
	public void setValue(int columnIndex, Object newValue) {
		values.set(columnIndex, newValue);

	}

	@Override
	public void setValue(String columnName, Object newValue)
			throws NotExistsException {
		int index = getSchema().getColumnIndex(columnName);
		setValue(index, newValue);

	}

	@Override
	public void addField() {
		values.add(null);

	}
	
	@Override
	public String toString() {
		return values.toString() + "\n\tAggregatedTuples:" + aggregatedTuples.toString();
	}
}
