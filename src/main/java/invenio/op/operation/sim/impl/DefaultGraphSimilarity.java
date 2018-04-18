package invenio.op.operation.sim.impl;

import invenio.data.InvenioElement;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.Schema;
import qng.client.QueryException;
import qng.core.compiler.OperationRegistry;
import qng.core.executor.OperationManager;
import qng.core.query.CompiledOperation;

public class DefaultGraphSimilarity extends AbstractElementSimilarity {
	
	public final static String OP_NAME = "defaultElementSimilarity";
	
	public DefaultGraphSimilarity() {
		super(null, OP_NAME);
	}
	
	public DefaultGraphSimilarity(String id) {
		super(id, OP_NAME);
	}

	@Override
	protected double calculateSimilarityScore(InvenioElement e1,
			InvenioElement e2) throws QueryException {
		
		if (e1 == null || e2 == null)
			return 0;
		
		ComparableFeatureContainer c1 = getFeatureContainer(e1);
		ComparableFeatureContainer c2 = getFeatureContainer(e2);
		
		if (c1 == null || e2 == null)
			return 0;
		
		Schema s1 = c1.getSchema();
		Schema s2 = c2.getSchema();
		
		if ( !s1.isEquivalentTo( s2 ) )
			throw new QueryException("Exception in operator " + getName() + 
					": mismatched schemas ");
		
		double curScore = 0;
		int numCategorical = 0;
		for ( int i = 0; i < s1.size(); i++ ) {
			if ( s1.canGetAsCategorical( i ) ) {
				try {
					CategoricalFeature f1 = (CategoricalFeature) c1.getFeature( i );
					CategoricalFeature f2 = (CategoricalFeature) c2.getFeature( i );
					
					OperationRegistry reg = OperationRegistryFactory.getInstance();
					
					CompiledOperation delegateSim = reg.createCompiledOperation( SimilarityConstants.DEFAULT_ATTR_SIM );
					
					OperationManager<CompiledOperation> exec = (OperationManager<CompiledOperation>)
							getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
					
					if (exec == null)
						throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
					
					Object[] argsArray = null;
					if ( simConfig == null )
						argsArray = new Object[ 2 ];
					else {
						argsArray = new Object[ 3 ];
						argsArray[2] = simConfig;
					}
					argsArray[0] = f1;
					argsArray[1] = f2;
					
					
					Object res = exec.invokeOperation(delegateSim, argsArray, getContext());
					if ( !(res instanceof Double) )
						throw new QueryException("Exception in operator " + getName() +
								": similarity measure must return double result");
					curScore += (Double) res;
					numCategorical++;
				}
				catch (MissingElementException ex) {
					// should not happen
					throw new QueryException( "Exception in operator " + getName() + 
					": " + ex.getMessage(),  ex);
				}
			}
		}
		return (numCategorical == 0) ? 1 : curScore / numCategorical;
	}
	
	
	
}
