package invenio.op.operation.sim.impl;

import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperatorUtils;
import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;
import qng.core.query.Context;

public class MFDistanceAttribSimilarity extends AbstractAttributeSimilarity {
	
	public final static int DEFAULT_R = 1;
	
	public final static String OP_NAME = "MFDistanceAttribSimilarity";
	
	public MFDistanceAttribSimilarity() {
		super(null, OP_NAME);
	}
	
	public MFDistanceAttribSimilarity(String id) {
		super(id, OP_NAME);
	}

	@Override
	protected double calculateSimilarityScore(CategoricalFeature f1,
			CategoricalFeature f2) throws QueryException {
		
		int r = DEFAULT_R;
		
		try {
			r = OperatorUtils.retrieveContextParamAsInt(getContext(), OperationConstants.MFDISTANCE_R, DEFAULT_R);
		}
		catch (NumberFormatException ex) {
			throw new QueryException("Exception in operator " + getName() + 
					": parameter: " + OperationConstants.MFDISTANCE_R + " must be an integer");
		}
		
		if ( r == 0)
			throw new QueryException("Exception in operator " + getName() + 
					": parameter: " + OperationConstants.MFDISTANCE_R + " cannot be equal to 0");
		
		int n = f1.getFeatureDescriptor().getNumCategories();
		
		// should not happen - verified in AbstractAttributeSimilarity
		if ( n != f2.getFeatureDescriptor().getNumCategories() )
			throw new QueryException("Exception in operator " + getName() + 
					": mismatched number of categories in attribute: " + f1 + " and" + f2);
		
		double sum = 0;
		for ( int i = 0; i < n; i++ ) {
			double p1 = f1.getProbability( i );
			double p2 = f2.getProbability( i );
			sum += Math.pow( Math.abs( p1 - p2 ), r );
		}
		
		double revR = (double) 1 / (double) r;
		return Math.pow( sum, revR );
	}
}
