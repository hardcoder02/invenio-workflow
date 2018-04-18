package invenio.op.operation.sim.impl;

import java.util.Arrays;

import qng.client.QueryException;
import invenio.operator.data.CategoricalFeature;

// TODO: absolute distance calculation modified to include difference of first (index 0) probability and 0. This way, AD is never 0.
public class ADRatioAttribSimilarity extends AbstractAttributeSimilarity {
	
	public final static String OP_NAME = "adRatioAttribSimilarity";
	
	public ADRatioAttribSimilarity() {
		super(null, OP_NAME);
	}
	
	public ADRatioAttribSimilarity(String id) {
		super(id, OP_NAME);
	}

	@Override
	protected double calculateSimilarityScore(CategoricalFeature f1,
			CategoricalFeature f2) throws QueryException {

		return calculateAbsDistance(f1) / calculateAbsDistance(f2);
	}
	
	private double calculateAbsDistance( CategoricalFeature f) {
		int n = f.getFeatureDescriptor().getNumCategories();
		
		double[] prob = new double[n+1];
		
		// TODO: add method to f for getting probabilities as array?
		prob[0] = 0;
		for ( int i = 0; i < n; i++ ) {
			double p = f.getProbability( i );
			prob[i+1] = p;
		}
		
		Arrays.sort( prob );
		
		double dist = 0;
		for ( int i = 1; i < n; i++ ) {
			dist += ( prob[i] - prob[i-1] );
		}
		
		return dist;
	}
}
