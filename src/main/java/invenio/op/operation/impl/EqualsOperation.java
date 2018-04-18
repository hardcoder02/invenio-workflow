package invenio.op.operation.impl;

import invenio.operator.data.Feature;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.StringFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class EqualsOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "equals";

	public static final Class[] EXPECTED_TYPES = {
		Object.class,
		Object.class
	};
	
	public EqualsOperation() {
		super(null, OP_NAME);
	}
	
	public EqualsOperation(String id) {
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
		
		return parseArg( args[0] ).equals( parseArg( args[1] ) );
	}
	
	private Object parseArg(Object arg) {
		if (arg instanceof NumericFeature)
			return ((NumericFeature)arg).getValue();
		else if (arg instanceof StringFeature)
			return ((StringFeature)arg).getValue();
		else
			return arg;
	}
}
