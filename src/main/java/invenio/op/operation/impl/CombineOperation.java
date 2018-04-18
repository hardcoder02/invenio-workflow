package invenio.op.operation.impl;

import java.util.Iterator;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.Tuple;

// TODO: re-write
public class CombineOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "combine";
	
	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class,
		ModifiableTable.class
	};
	
	public CombineOperation() {
		super(null, OP_NAME);
	}
	
	public CombineOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		ModifiableTable t1 = (ModifiableTable) args[0];
		ModifiableTable t2 = (ModifiableTable) args[1];
		
		final int col1 = t1.getSchema().getNumberOfColumns();
		final int size1 = t1.getSize();
		final int col2 = t2.getSchema().getNumberOfColumns();
		final int size2 = t2.getSize();
		final Schema schema2 = t2.getSchema();
		
		for (int i = 0; i < col2; i++) {
			t1.addColumn( schema2.getColumnName( i ), schema2.getDeclaredType( i) );
		}
		
		// grow first table if smaller than second
		for ( int i = 0; i < (size2 - size1); i++ ) { 
			t1.addTuple();
		}
		
		Iterator<? extends Tuple> i1 = t1.getIterator();
		Iterator<? extends Tuple> i2 = t2.getIterator();
		while ( i2.hasNext() ) {
			Tuple tp1 = i1.next();
			Tuple tp2 = i2.next();
			for ( int i = 0; i < col2; i++ ) {
				tp1.setValue( col1 + i, tp2.getValue( i ) );
			}
		}
		
		return t1;
	}
}
