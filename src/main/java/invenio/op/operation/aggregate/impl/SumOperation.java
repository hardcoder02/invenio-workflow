package invenio.op.operation.aggregate.impl;

import invenio.operator.data.NumericFeature;

import java.util.Collection;
import java.util.List;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class SumOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "sum";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class,
	};
	
	public SumOperation() {
		super(null, OP_NAME);
	}
	
	public SumOperation(String id) {
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
		Collection l = ((Collection)args[0]);
		
		double sum = 0;
		for ( Object elem : l ) {
			if (elem == null)
				return null;
			if ( (elem instanceof Number) )
				sum += ((Number)elem).doubleValue();
			else if ( elem instanceof NumericFeature )
				sum += ((NumericFeature)elem).getValue();
			else
				throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Unexpected type " +  elem.getClass());
		}
		
		return sum;
	}
}
