package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.data.InvenioVertex;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.StringFeature;

import java.util.ArrayList;
import java.util.Collection;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class ExtractIdOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "extractId";
	
	public static final Class[] EXPECTED_TYPES = {
		Collection.class
	};
	
	public ExtractIdOperation() {
		super(null, OP_NAME);
	}
	
	public ExtractIdOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	
	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null )
			return null;
		
		Collection el = (Collection) args[0];
		
		Collection<Object> ids = new ArrayList<Object>( el.size() );
		for (Object o : el)
			ids.add( getId(o));
		
		return ids;
	}

	private Object getId( Object o) throws QueryException {
		if (o instanceof InvenioElement)
			return ((InvenioElement)o).getKey();
		else
			throw new QueryException("Exception in operator " + getName() + 
					": expected InvenioElement, but actual type is: " + o.getClass().getName() );
	}
}
