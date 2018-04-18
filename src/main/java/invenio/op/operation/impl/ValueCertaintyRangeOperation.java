package invenio.op.operation.impl;

import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class ValueCertaintyRangeOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "valueCertaintyRange";

	public static final Class[] EXPECTED_TYPES = {
		CategoricalFeature.class
	};
	
	public ValueCertaintyRangeOperation() {
		super(null, OP_NAME);
	}
	
	public ValueCertaintyRangeOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null)
			return null;
		
		CategoricalFeature f = (CategoricalFeature) args[0];
		
		int numCategories = f.getFeatureDescriptor().getNumCategories();
		
		double minProb = 1, maxProb = 0;
		for (int i = 0; i < numCategories; i++) {
			double p = f.getProbability( i );
			if ( p < minProb)
				minProb = p;
			if ( p > maxProb )
				maxProb = p;
		}
		
		return maxProb - minProb;
	}
	
}
