package qng.tabular;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import qng.core.query.DuplicateException;

public class AggregateTableImpl implements AggregateModifiableTable {
	private List<AggregateModifiableTuple> tuples = new LinkedList<AggregateModifiableTuple>();
	private final ModifiableTable aggregatedTable = TableFactory.createTable();
	private AggregateModifiableSchema schema = TableFactory.createAggregateSchema( aggregatedTable.getSchema() );
	
	AggregateTableImpl() {}

	@Override
	public AggregateSchema getSchema() {
		return schema;
	}

	@Override
	public int getSize() {
		return tuples.size();
	}
	
	@Override
	public Iterator<? extends AggregateTuple> getIterator() {
		return tuples.iterator();
	}

	@Override
	public List<? extends AggregateTuple> getTuples() {
		return Collections.unmodifiableList( tuples );
	}
	
	@Override
	public void addColumn(String name, Class clazz) throws DuplicateException {
		schema.addColumn(name, clazz);
		for (AggregateModifiableTuple t : tuples) {
			t.addField();
		}
	}

	@Override
	public boolean removeColumn(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public AggregateTuple addTuple() {
		AggregateModifiableTuple t = TableFactory.createAggregateTuple(this);
		for (int i = 0; i < schema.getNumberOfColumns(); i++)
			t.addField();
		
		tuples.add(t);
		return t;
	}
	
	@Override
	public boolean removeTuple(Tuple t) {
		// TODO: remove aggregated rows
		return tuples.remove(t);
	}

	@Override
	public Table getAggregatedTable() {
		return aggregatedTable;
	}
	
	@Override
	public void addAggregatedColumn(String name, Class clazz) throws DuplicateException {
		if (schema.hasColumn( name ))
			throw new DuplicateException("Column name alread exists: " + name);
		aggregatedTable.addColumn(name, clazz);
	}

	@Override
	public boolean removeAggregatedColumn(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tuple addAggregatedTuple(AggregateTuple t) {
		Tuple aggT = aggregatedTable.addTuple();
		((AggregateModifiableTuple)t).addAggregatedTuple(aggT);
		return aggT;
	}

	@Override
	public boolean removeAggregatedTuple(Tuple t) {
		// TODO Auto-generated method stub
		return false;
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
