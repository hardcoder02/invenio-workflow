package qng.tabular;

import qng.core.query.NotExistsException;

public interface Tuple {
	Schema getSchema();
	
	Object getValue(int columnIndex);
	Object getValue(String columnName) throws NotExistsException;
	
	void setValue(int columnIndex, Object newValue);
	void setValue(String columnName, Object newValue) throws NotExistsException;
}
