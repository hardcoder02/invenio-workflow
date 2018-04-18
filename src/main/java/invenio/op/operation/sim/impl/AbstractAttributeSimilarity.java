package invenio.op.operation.sim.impl;

import invenio.operator.data.CategoricalFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public abstract class AbstractAttributeSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	public static final Class[] EXPECTED_TYPES = {
		CategoricalFeature.class,
		CategoricalFeature.class
	};
	
	public AbstractAttributeSimilarity(String id, String name) {
		super(id, name);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	
	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args.length < 2 || args[0] == null && args[1] == null )
			return null;
		
		CategoricalFeature f1 = (CategoricalFeature) args[0];
		CategoricalFeature f2 = (CategoricalFeature) args[1];
		
		if ( !f1.getFeatureDescriptor().isEquivalentTo( f2.getFeatureDescriptor() ) )
		 throw new QueryException( "Exception in operator " + getName() + 
					": incomparable attributes: " + args[0] + ", " + args[1] );
		
		return calculateSimilarityScore( f1, f2 );
	}

	protected abstract double calculateSimilarityScore(
			CategoricalFeature f1,	CategoricalFeature f2) throws QueryException;
	
}
