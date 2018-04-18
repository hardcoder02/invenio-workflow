package invenio.op.operation.sim.impl;

import invenio.data.InvenioElement;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public abstract class AbstractElementSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	protected SimilarityConfig simConfig = null;
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioElement.class,
		InvenioElement.class
	};
	
	public AbstractElementSimilarity(String id, String name) {
		super(id, name);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	
	
	
	@Override
	public boolean hasExactNumberOfArguments() {
		return false;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args.length < 2 || args[0] == null && args[1] == null )
			return null;
		
		if ( args.length > 2 && args[2] instanceof SimilarityConfig)
			simConfig = (SimilarityConfig) args[2];
		
		InvenioElement e1 = (InvenioElement) args[0];
		InvenioElement e2 = (InvenioElement) args[1];
		
		return calculateSimilarityScore( e1, e2 );
	}

	protected abstract double calculateSimilarityScore(
			InvenioElement e1, InvenioElement e2) throws QueryException;
	
	
	protected ComparableFeatureContainer getFeatureContainer(InvenioElement e) {
		return (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
	}
}
