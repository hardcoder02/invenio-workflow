package qng.tabular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qng.core.query.DuplicateException;
import qng.core.query.NotExistsException;

// TODO: add indexing to columns, so that they can be shifted around without shifting actual values in Tuples
public class SchemaImpl implements ModifiableSchema {
	
//	private static class Column {
//		private String name;
//		private Class clazz;
//	}
	
	private final List<String> columnNames = new ArrayList<String>();
	private final List<Class> columnTypes = new ArrayList<Class>();
	private final Map<String, Integer> columnMap = new HashMap<String, Integer>();
	
	SchemaImpl() {
		
	}
	
	@Override
	public int getColumnIndex(String columnName) throws NotExistsException {
		if ( !columnMap.containsKey(columnName) )
			throw new NotExistsException("Column name does not exist: " + columnName);
		return columnMap.get(columnName);
	}

	@Override
	public String getColumnName(int columnIdx) {
		return columnNames.get(columnIdx);
	}

	@Override
	public String[] getColumnNames() {
		return columnNames.toArray( new String[ columnNames.size() ] );
	}

	@Override
	public Class getDeclaredType(int columnIdx) {
		return columnTypes.get(columnIdx);
	}

	@Override
	public Class getDeclaredType(String columnName) throws NotExistsException {
		if ( !columnMap.containsKey(columnName) )
			throw new NotExistsException("Column name does not exist: " + columnName);
		return getDeclaredType( columnMap.get( columnName ));
	}

	@Override
	public Class[] getDeclaredTypes() {
		return (Class[]) columnTypes.toArray();
	}

	@Override
	public void addColumn(String name, Class clazz) throws DuplicateException {
		if (columnMap.containsKey( name ))
			throw new DuplicateException("Column name alread exists: " + name);
		columnNames.add(name);
		columnTypes.add(clazz);
		columnMap.put(name, columnNames.size() - 1);
		
	}

	@Override
	public int getNumberOfColumns() {
		return columnNames.size();
	}

	@Override
	public boolean hasColumn(String columnName) {
		return columnMap.containsKey( columnName );
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columnNames.size(); i++) {
			if (i > 0)
				sb.append("; ");
			sb.append(columnNames.get(i)).append("[").append(columnTypes.get(i)).append("]");
		}
		return sb.toString();
	}

	
}
