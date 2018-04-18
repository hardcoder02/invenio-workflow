package qng.tabular;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import qng.core.query.DuplicateException;

public class TableImpl implements ModifiableTable {
	private ModifiableSchema schema = TableFactory.createSchema();
	private List<ModifiableTuple> tuples = new LinkedList<ModifiableTuple>();
	
	TableImpl() {
		
	}
	
	@Override
	public Iterator<? extends Tuple> getIterator() {
		return tuples.iterator();
	}

	@Override
	public List<? extends Tuple> getTuples() {
		return Collections.unmodifiableList( tuples );
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public int getSize() {
		return tuples.size();
	}

	@Override
	public void addColumn(String name, Class clazz) throws DuplicateException {
		schema.addColumn(name, clazz);
		for (ModifiableTuple t : tuples) {
			t.addField();
		}
	}
	
	@Override
	public boolean removeColumn(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public Tuple addTuple() {
		ModifiableTuple t = TableFactory.createTuple(this);
		for (int i = 0; i < schema.getNumberOfColumns(); i++)
			t.addField();
		
		tuples.add(t);
		return t;
	}

	@Override
	public boolean removeTuple(Tuple t) {
		return tuples.remove(t);
	}
	
	@Override
	public ModifiableTuple removeTuple(int index) {
		return tuples.remove(index);
	}

	@Override
	public void sort(Comparator<? super Tuple> c) {
		Collections.sort( tuples, c);
		
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append( schema );
		for (Tuple t : tuples)
			sb.append("\n").append( t );
		return sb.toString();
	}
	
}
