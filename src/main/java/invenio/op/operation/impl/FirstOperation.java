package invenio.op.operation.impl;

import java.util.Collection;
import java.util.Iterator;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class FirstOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "first";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class
	};
	
	public FirstOperation() {
		super(null, OP_NAME);
	}
	
	public FirstOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if (args[0] == null)
			return null;
		
		Iterator iter = ((Collection)args[0]).iterator();
		if (iter.hasNext())
			return iter.next();
		else
			return null;
	}
	
}
