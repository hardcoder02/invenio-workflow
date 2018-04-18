package invenio.op.query.core;

public interface Relation {
	void addColumn(String name);
	void addRow();
	
	int getNumColumns();
	int getNumRows();
	
	void removeColumn(int index) throws IndexOutOfBoundsException;
	void removeRow(int index) throws IndexOutOfBoundsException;
}
