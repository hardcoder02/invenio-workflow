package qng.tabular;

import qng.core.query.DuplicateException;

interface ModifiableSchema extends Schema {
	void addColumn(String name, Class clazz) throws DuplicateException;
}
