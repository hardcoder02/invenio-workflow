package qng.tabular;

import java.util.ArrayList;
import java.util.List;

import qng.core.query.NotExistsException;

// TODO: add schema check when setting values
public class TupleImpl implements ModifiableTuple {
	private final Table table;
	private final List<Object> values = new ArrayList<Object>();
	
	TupleImpl(Table table) {
		this.table = table;
	}

	@Override
	public Schema getSchema() {
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
	public void setValue(String columnName, Object newValue) throws NotExistsException {
		int index = getSchema().getColumnIndex(columnName);
		setValue(index, newValue);
	}

	@Override
	public void addField() {
		values.add(null);
	}

	@Override
	public String toString() {
		return values.toString();
	}
	
}
