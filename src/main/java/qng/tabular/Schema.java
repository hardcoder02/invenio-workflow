package qng.tabular;

import qng.core.query.NotExistsException;

public interface Schema {
	int getNumberOfColumns();
	boolean hasColumn(String columnName);
	
	String[] getColumnNames();
	String getColumnName(int columnIdx);
	int getColumnIndex(String columnName) throws NotExistsException;
	
	Class[] getDeclaredTypes();
	Class getDeclaredType(int columnIdx);
	Class getDeclaredType(String columnName) throws NotExistsException;
}
