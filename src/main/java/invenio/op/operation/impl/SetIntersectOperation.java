package invenio.op.operation.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class SetIntersectOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "setIntersect";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class,
		Collection.class
	};
	
	public SetIntersectOperation() {
		super(null, OP_NAME);
	}
	
	public SetIntersectOperation(String id) {
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
		
		Collection c1 = (Collection)args[0];
		Collection c2 = (Collection)args[1];
		
		try {
			c1.retainAll( c2 );
			return c1;
		}
		catch (UnsupportedOperationException ex) {
			return retainAll(c1, c2);
		}
	}

	private Set retainAll(Collection c1, Collection c2) {
		Set s = new HashSet();
		for (Object o : c1) {
			if ( c2.contains(o))
				s.add(o);
		}
		return s;
	}
	
}
