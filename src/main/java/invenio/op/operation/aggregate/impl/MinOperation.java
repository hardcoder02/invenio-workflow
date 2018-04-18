package invenio.op.operation.aggregate.impl;

import invenio.operator.data.NumericFeature;

import java.util.List;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class MinOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "min";

	public static final Class[] EXPECTED_TYPES = {
		List.class,
	};
	
	public MinOperation() {
		super(null, OP_NAME);
	}
	
	public MinOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public boolean isAggregate() {
		return true;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		List l = ((List)args[0]);
		
		double min = Double.MAX_VALUE;
		for ( Object elem : l ) {
			if (elem == null)
				return null;
			if ( (elem instanceof Number) ) {
				if ( min > ((Number)elem).doubleValue() )
					min = ((Number)elem).doubleValue();
			}
			else if ( elem instanceof NumericFeature )
				if ( min > ((NumericFeature)elem).getValue() )
					min = ((NumericFeature)elem).getValue();
			else
				throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Unexpected type " +  elem.getClass());
		}
		
		return min;
	}
	
}
