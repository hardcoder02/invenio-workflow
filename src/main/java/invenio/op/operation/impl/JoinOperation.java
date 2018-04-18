package invenio.op.operation.impl;

import invenio.operator.data.NumericFeature;
import invenio.operator.data.StringFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import qng.client.QueryException;
import qng.core.executor.QueryExecutor;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

// TODO: process attributes
public class JoinOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "join";
	
	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class,
		ModifiableTable.class
	};
	
	public JoinOperation() {
		super(null, OP_NAME);
	}
	
	public JoinOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public boolean hasExactNumberOfArguments() {
		return false;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		ModifiableTable table1 = (ModifiableTable) args[0];
		ModifiableTable table2 = (ModifiableTable) args[1];
		
		long startTime = System.currentTimeMillis();
		System.out.println("Starting execution of: " + getName());
		
		CompiledQuery query1 = null;
		CompiledQuery query2 = null;
		QueryExecutor exec = null;
		if ( args.length > 2 ) {
			if ( args.length < 4 )
				throw new QueryException("Exception in operator " + getName() + 
						": expected either 2 or 4 arguments, but actual number is: " + args.length );
			if ( args[2] != null && args[3] != null ) {
				if ( !(args[2] instanceof CompiledQuery) )
					throw new QueryException("Exception in operator " + getName() + 
							": expected boolean expression, but actual type is: " + args[2].getClass().getName() );
				query1 = (CompiledQuery) args[2];
				
				if ( !(args[3] instanceof CompiledQuery) )
					throw new QueryException("Exception in operator " + getName() + 
							": expected boolean expression, but actual type is: " + args[3].getClass().getName() );
				query2 = (CompiledQuery) args[3];
				
				exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
				if (exec == null)
					throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
			}
		}
		
		ModifiableTable result = null;
		if ( query1 == null)
			result = crossJoin(table1, table2);
		else
			result = innerJoin(table1, table2, query1, query2, exec);
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Finished execution of " + getName() + " in (msec): " + duration);
		
		return result;
	}
	
	private ModifiableTable crossJoin(ModifiableTable t1, ModifiableTable t2) throws QueryException {
		ModifiableTable t = prepareTable(t1, t2);
		
		Iterator<? extends Tuple> i1 = t1.getIterator();
		while ( i1.hasNext() ) {
			Tuple tp1 = i1.next();
			Iterator<? extends Tuple> i2 = t2.getIterator();
			while ( i2.hasNext() ) {
				Tuple tp2 = i2.next();
				Tuple tp = t.addTuple();
				setValues( tp1, tp2, tp);
			}
		}
		
		return t;
	}
	
	private ModifiableTable innerJoin(ModifiableTable t1, ModifiableTable t2,
			CompiledQuery con1, CompiledQuery con2, QueryExecutor exec) throws QueryException {
		
		ModifiableTable t = prepareTable(t1, t2);
		
		Map<Object, List<Tuple>> m = new HashMap<Object, List<Tuple>>();
		
		Iterator<? extends Tuple> i1 = t1.getIterator();
		while ( i1.hasNext() ) {
			Tuple tp1 = i1.next();
			
			getContext().setVariable( OperationConstants.VAR_TUPLE, tp1 );
			Object resRaw = exec.execute(con1, getContext()).getResultValue();
			
			Object res = parseArg(resRaw);
			if (res != null) {
				if ( !m.containsKey( res ))
					m.put( res, new ArrayList<Tuple>());
				m.get(res).add( tp1 );
			}
		}
		
		Iterator<? extends Tuple> i2 = t2.getIterator();
		while ( i2.hasNext() ) {
			Tuple tp2 = i2.next();
			
			getContext().setVariable( OperationConstants.VAR_TUPLE, tp2 );
			Object resRaw = exec.execute(con2, getContext()).getResultValue();
			Object res = parseArg(resRaw);
			
			if (res != null && m.containsKey( res ) ) {
				List<Tuple> lt1 = m.get(res);
				for ( Tuple tp1 : lt1 ) { 
					Tuple tp = t.addTuple();
					setValues( tp1, tp2, tp);
				}
			}
		}
		
		return t;
	}
	
	private ModifiableTable prepareTable(ModifiableTable t1, ModifiableTable t2) throws QueryException {
		final int col1 = t1.getSchema().getNumberOfColumns();
		final int size1 = t1.getSize();
		final int col2 = t2.getSchema().getNumberOfColumns();
		final int size2 = t2.getSize();
		final Schema schema1 = t1.getSchema();
		final Schema schema2 = t2.getSchema();
		
		ModifiableTable t = TableFactory.createTable();
		
		for (int i = 0; i < col1; i++) {
			t.addColumn( schema1.getColumnName( i ), schema1.getDeclaredType( i) );
		}
		
		for (int i = 0; i < col2; i++) {
			t.addColumn( schema2.getColumnName( i ), schema2.getDeclaredType( i) );
		}
		
		return t;
	}
	
	private void setValues(Tuple tp1, Tuple tp2, Tuple tp) {
		int size1 = tp1.getSchema().getNumberOfColumns();
		int size2 = tp2.getSchema().getNumberOfColumns();
		
		for (int i = 0; i < size1; i++)
			tp.setValue( i, tp1.getValue(i) );
		
		for (int i = 0; i < size2; i++)
			tp.setValue( i + size1, tp2.getValue(i) );
	}
	
	// TODO: fix
	private Object parseArg(Object arg) {
		if (arg instanceof NumericFeature)
			return ((NumericFeature)arg).getValue();
		else if (arg instanceof StringFeature)
			return ((StringFeature)arg).getValue();
		else
			return arg;
	}

}
