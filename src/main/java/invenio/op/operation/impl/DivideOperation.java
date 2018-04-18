package invenio.op.operation.impl;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class DivideOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "divide";

	public static final Class[] EXPECTED_TYPES = {
		Number.class,
		Number.class
	};
	
	public DivideOperation() {
		super(null, OP_NAME);
	}
	
	public DivideOperation(String id) {
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
			// TODO: check
//			if ( args[0] instanceof Integer && args[1] instanceof Integer)
//				return (Integer)args[0] / (Integer) args[1];
//			else
				return ((Number)args[0]).doubleValue() / ((Number) args[1]).doubleValue();
		}
		catch (ArithmeticException ex) {
			throw new QueryException( "Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Division by 0", ex);
		}
	}
}