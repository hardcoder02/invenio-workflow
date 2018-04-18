package invenio.op.operation.impl;

import qng.core.query.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import qng.client.QueryException;
import qng.client.UncheckedQueryException;
import qng.core.executor.QueryExecutor;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.tabular.ModifiableTable;
import qng.tabular.Tuple;

public class OrderByOperation extends AbstractOperation implements
		CompiledOperation {
	
	private ModifiableTable table;

	private List<Object> selections = new ArrayList<Object>();
	
	public final static String OP_NAME = "orderBy";

	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class
	};
	
	public OrderByOperation() {
		super(null, OP_NAME);
	}
	
	public OrderByOperation(String id) {
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
		
		cleanup();
		parseArguments(args);
		
		QueryExecutor exec = (QueryExecutor) getContext().getVariable(OperationConstants.VAR_EXECUTOR);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find QueryExecutor in Context");
		
		try {
			table.sort( new TupleComparator(exec, selections, getContext() ) );
		}
		catch (UncheckedQueryException ex) {
			Throwable e = ex.getCause();
			throw new QueryException("Exception in operator " + getName() + ": " + e.getMessage(), e);
		}
		
		return table;
	}


	private void parseArguments(Object[] args) throws QueryException {
		table = (ModifiableTable) args[0];
		
		final Set<String> originalColumns = new HashSet<String>();
		for ( String c : table.getSchema().getColumnNames() )
			originalColumns.add( c );
		
		if ( args.length <= 1 ) {
			throw new QueryException("Exception in operator " + getName() + 
					": at least one column or expression is required" );
		}
		
		for (int i = 1; i < args.length; i++) {
			if ( (args[i] instanceof String) )  {
				String colName = (String) args[i];
				if ( !originalColumns.contains( colName) )
					throw new QueryException("Exception in operator " + getName() + 
							": unrecognized column: " + colName);
				
				selections.add( colName );
			}
			else if ( (args[i] instanceof CompiledQuery) ) {
				selections.add( args[i] );
			}
			else {
				throw new QueryException("Exception in operator " + getName() + 
						": unexpected type: " + args[i].getClass().getName() + ", column or expression required instead");
			}
		}
	}
	

	private void cleanup() {
		table = null;
		selections.clear();
	}

	private static class TupleComparator implements Comparator<Tuple> {
		private final QueryExecutor exec;
		private final List<Object> selections;
		private final Context ctx;
		private final TupleComparatorCache cache = new TupleComparatorCache();
		
		public TupleComparator(QueryExecutor e, List<Object> selections, Context ctx) {
			this.exec = e;
			this.selections = new ArrayList<Object>(selections);
			this.ctx = ctx;
		}
		
		@Override
		public int compare(Tuple o1, Tuple o2) throws UncheckedQueryException {
			if (o1 == null) {
				if (o2 == null)
					return 0;
				else
					return 1;
			}
			else {
				if (o2 == null)
					return -1;
			}
			
			// both o1 and o2 are not null
			return compareTuples(o1, o2);
		}
		
		private int compareTuples(Tuple t1, Tuple t2) throws UncheckedQueryException {
			try {
				for (int i = 0; i < selections.size(); i++) {
					int comp = 0;
					Object sel = selections.get(i);
					if ( sel instanceof String ) {
						comp = compare(t1, t2, (String) sel);
					}
					else if ( sel instanceof CompiledQuery ) {
						comp = compare(t1, t2, (CompiledQuery) sel);
					}
					else {	// should not happen
						
					}
					if (comp != 0)
						return comp;
				}
			}
			catch (QueryException ex) {
				throw new UncheckedQueryException(ex);
			}
			
			// tuples are the same
			return 0;
		}

		private int compare(Tuple t1, Tuple t2, String column) throws QueryException {
			return compareValues( t1.getValue( column ), t2.getValue( column ) );
		}

		private int compare(Tuple t1, Tuple t2, CompiledQuery q) throws QueryException {
			Object val1 = getValue(t1, q);
			Object val2 = getValue(t2, q);
			
			return compareValues(val1, val2);
		}
		
		private Object getValue(Tuple t, CompiledQuery q) throws QueryException {
			if (cache.hasCacheEntry(t, q))
				return cache.getCacheEntry(t, q);
			
			ctx.setVariable( OperationConstants.VAR_TUPLE, t);
			Object res = exec.execute(q, ctx).getResultValue();
			
			cache.setCacheEntry(t, q, res);
			
			return res;
		}
		
		private int compareValues(Object val1, Object val2) throws QueryException {
			if (val1 == null) {
				if (val2 == null)
					return 0;
				else
					return 1;
			}
			else {
				if (val2 == null)
					return -1;
			}
			
			// both val1 and val2 are not null
			// TODO: use compare operator?
			try {
				if ( val1 instanceof Comparable )
					return ((Comparable)val1).compareTo( val2 );
				else
					throw new QueryException( "Incomparable values: " + val1 + ", " + val2 );
			}
			catch (ClassCastException ex) {
				throw new QueryException( "Incomparable values: " + val1 + ", " + val2 );
			}
		}
		
		
	}
	
	// TODO: or just make a single composite key of type Tuple + CompiledQuery?
	private static class TupleComparatorCache {
		private final Map<Tuple, Map<CompiledQuery, Object>> cache = new HashMap<Tuple, Map<CompiledQuery, Object>>();
		
		public Object setCacheEntry(Tuple t, CompiledQuery q, Object value) {
			final Map<CompiledQuery, Object> m = getOrCreateOuter(t);
			
			return m.put(q, value);
		}
		
		public boolean hasCacheEntry(Tuple t, CompiledQuery q) {
			final Map<CompiledQuery, Object> m = cache.get(t);
			if (m == null)
				return false;
			
			return m.containsKey(q);
		}
		
		public Object getCacheEntry(Tuple t, CompiledQuery q) {
			final Map<CompiledQuery, Object> m = cache.get(t);
			if (m == null)
				return false;
			
			return m.get(q);
		}
		
		private Map<CompiledQuery, Object> getOrCreateOuter(Tuple t) {
			Map<CompiledQuery, Object> m = cache.get(t);
			if (m == null) {
				m = new HashMap<CompiledQuery, Object>();
				cache.put(t, m);
			}
			return m;
		}
		
	}
	
}
