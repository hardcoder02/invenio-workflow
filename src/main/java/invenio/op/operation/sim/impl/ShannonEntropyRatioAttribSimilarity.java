package invenio.op.operation.sim.impl;

import qng.client.QueryException;
import invenio.operator.data.CategoricalFeature;

public class ShannonEntropyRatioAttribSimilarity extends AbstractAttributeSimilarity {
	
	public final static String OP_NAME = "shannonEntropyRatioAttribSimilarity";
	
	public ShannonEntropyRatioAttribSimilarity() {
		super(null, OP_NAME);
	}
	
	public ShannonEntropyRatioAttribSimilarity(String id) {
		super(id, OP_NAME);
	}

	@Override
	protected double calculateSimilarityScore(CategoricalFeature f1,
			CategoricalFeature f2) throws QueryException {

		return calculateShannonEntropy(f1) / calculateShannonEntropy(f2);
	}
	
	private double calculateShannonEntropy( CategoricalFeature f) {
		int n = f.getFeatureDescriptor().getNumCategories();
		
		double sum = 0;
		for ( int i = 0; i < n; i++ ) {
			double p = f.getProbability( i );
			sum += p * Math.log( p ) / Math.log( 2 );
		}
		
		return -sum;
	}
}
