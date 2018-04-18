package qng.tabular;

import java.util.Comparator;

import qng.core.query.DuplicateException;

public interface ModifiableTable extends Table {
	void addColumn(String name, Class clazz) throws DuplicateException;
	boolean removeColumn(String name);
	Tuple addTuple();
	boolean removeTuple(Tuple t);
	ModifiableTuple removeTuple(int index);
	
	void sort(Comparator<? super Tuple> c);
}
