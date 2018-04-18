package invenio.op.operation.sim.impl;

import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;

public class HistogramIntersectionAttribSimilarity extends AbstractAttributeSimilarity {
	
	public final static String OP_NAME = "histogramIntersectionAttribSimilarity";
	
	public HistogramIntersectionAttribSimilarity() {
		super(null, OP_NAME);
	}
	
	public HistogramIntersectionAttribSimilarity(String id) {
		super(id, OP_NAME);
	}

	@Override
	protected double calculateSimilarityScore(CategoricalFeature f1,
			CategoricalFeature f2) throws QueryException {
		
		int n = f1.getFeatureDescriptor().getNumCategories();
		
		// should not happen - verified in AbstractAttributeSimilarity
		if ( n != f2.getFeatureDescriptor().getNumCategories() )
			throw new QueryException("Exception in operator " + getName() + 
					": mismatched number of categories in attribute: " + f1 + " and" + f2);
		
		double sumMin = 0;
		double sumP2 = 0;
		for ( int i = 0; i < n; i++ ) {
			double p1 = f1.getProbability( i );
			double p2 = f2.getProbability( i );
			sumMin += Math.min( p1, p2 );
			sumP2 += p2;
		}
		
		return 1 - sumMin / sumP2;
	}
}
