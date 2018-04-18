package qng.tabular;

import qng.core.query.DuplicateException;
import qng.core.query.NotExistsException;

// TODO: refactor
public class AggregateSchemaImpl implements AggregateModifiableSchema {
	private final ModifiableSchema delegateSchema = TableFactory.createSchema();
	private final Schema aggregatedSchema;

	AggregateSchemaImpl( Schema aggregatedSchema ) {
		this.aggregatedSchema = aggregatedSchema;
	}
	
	@Override
	public int getColumnIndex(String columnName) throws NotExistsException {
		return delegateSchema.getColumnIndex(columnName);
	}

	@Override
	public String getColumnName(int columnIdx) {
		return delegateSchema.getColumnName(columnIdx);
	}

	@Override
	public String[] getColumnNames() {
		return delegateSchema.getColumnNames();
	}

	@Override
	public Class getDeclaredType(int columnIdx) {
		return delegateSchema.getDeclaredType(columnIdx);
	}

	@Override
	public Class getDeclaredType(String columnName) throws NotExistsException {
		return delegateSchema.getDeclaredType(columnName);
	}

	@Override
	public Class[] getDeclaredTypes() {
		return delegateSchema.getDeclaredTypes();
	}

	@Override
	public int getNumberOfColumns() {
		return delegateSchema.getNumberOfColumns();
	}
	
	@Override
	public boolean hasColumn(String columnName) {
		return delegateSchema.hasColumn(columnName);
	}

	@Override
	public void addColumn(String name, Class clazz) throws DuplicateException {
		if (aggregatedSchema.hasColumn( name ))
			throw new DuplicateException("Column name alread exists: " + name);
		delegateSchema.addColumn(name, clazz);
	}
	
	@Override
	public Schema getAggregatedSchema() {
		return aggregatedSchema;
	}

	@Override
	public String toString() {
		return delegateSchema.toString() + "\n\t{AggregatedSchema: " + aggregatedSchema + "}";
	}

}
