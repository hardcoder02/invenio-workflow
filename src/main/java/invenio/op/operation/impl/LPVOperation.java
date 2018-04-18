package invenio.op.operation.impl;

import java.util.ArrayList;
import java.util.List;

import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class LPVOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "lpv";

	public static final Class[] EXPECTED_TYPES = {
		CategoricalFeature.class
	};
	
	public LPVOperation() {
		super(null, OP_NAME);
	}
	
	public LPVOperation(String id) {
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
		
		double minProb = 2;
		List<String> minCat = new ArrayList<String>();
		for (int i = 0; i < numCategories; i++) {
			if ( f.getProbability(i) < minProb ) {
				minProb = f.getProbability( i );
				minCat.clear();
				minCat.add( f.getFeatureDescriptor().getCategory( i ) );
			}
			else if ( f.getProbability(i) == minProb ) {
				minCat.add( f.getFeatureDescriptor().getCategory( i ) );
			}
		}
		
		return minCat;
	}
	
}
