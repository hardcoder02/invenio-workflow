package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class GreaterThanOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "greaterThan";

	public static final Class[] EXPECTED_TYPES = {
		Number.class,
		Number.class
	};
	
	public GreaterThanOperation() {
		super(null, OP_NAME);
	}
	
	public GreaterThanOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if (args[0] == null || args[1] == null)
			return null;
		
		try {
			if ( args[0] instanceof Comparable )
				return ((Comparable)args[0]).compareTo( args[1] ) > 0;
			else
				throw new QueryException( "Exception in operator " + getName() + 
						": incomparable arguments: " + args[0] + ", " + args[1] );
		}
		catch (ClassCastException ex) {
			throw new QueryException( "Exception in operator " + getName() + 
					": incomparable arguments: " + args[0] + ", " + args[1] );
		}
		
	}
}
