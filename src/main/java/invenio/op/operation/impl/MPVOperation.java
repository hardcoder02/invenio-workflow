package invenio.op.operation.impl;

import java.util.ArrayList;
import java.util.List;

import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class MPVOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "mpv";

	public static final Class[] EXPECTED_TYPES = {
		CategoricalFeature.class
	};
	
	public MPVOperation() {
		super(null, OP_NAME);
	}
	
	public MPVOperation(String id) {
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
		
		double maxProb = -1;
		List<String> maxCat = new ArrayList<String>();
		for (int i = 0; i < numCategories; i++) {
			if ( f.getProbability(i) > maxProb ) {
				maxProb = f.getProbability( i );
				maxCat.clear();
				maxCat.add( f.getFeatureDescriptor().getCategory( i ) );
			}
			else if ( f.getProbability(i) == maxProb ) {
				maxCat.add( f.getFeatureDescriptor().getCategory( i ) );
			}
		}
		
		return maxCat;
	}
	
}
