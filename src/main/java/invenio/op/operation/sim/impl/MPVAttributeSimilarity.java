package invenio.op.operation.sim.impl;

import invenio.data.InvenioVertex;
import invenio.op.operation.impl.MPVOperation;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.op.operation.impl.OperatorUtils;
import invenio.op.operation.impl.Triple;
import invenio.op.operation.sim.ego.impl.AbstractEgoNetAligner;
import invenio.op.operation.sim.ego.impl.AlignmentCallbackAdapter;
import invenio.op.operation.sim.ego.impl.EgoNetAligner;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.Schema;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import qng.client.QueryException;
import qng.core.compiler.OperationRegistry;
import qng.core.executor.OperationManager;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.Context;

// TODO: rewrite
public class MPVAttributeSimilarity extends AbstractAttributeSimilarity implements
		CompiledOperation {
	
	public final static String OP_NAME = "MPVAttributeSimilarity";
	
	public final static boolean DEFAULT_SIM_OR = true;
	
	private boolean simOr = DEFAULT_SIM_OR;

	public MPVAttributeSimilarity() {
		super(null, OP_NAME);
	}
	
	public MPVAttributeSimilarity(String id) {
		super(id, OP_NAME);
	}
	

	@Override
	protected double calculateSimilarityScore(CategoricalFeature f1,
			CategoricalFeature f2) throws QueryException {
		
		List<String> mpv1 = invokeMPV(f1);
		List<String> mpv2 = invokeMPV(f2);
		
		for ( String s : mpv1 ) {
			if ( mpv2.contains(s))
				return 1;
		}
		for ( String s : mpv2 ) {
			if ( mpv1.contains(s))
				return 1;
		}
		return 0;
	}
		
	protected List<String> invokeMPV(CategoricalFeature f ) throws QueryException {
		OperationRegistry reg = OperationRegistryFactory.getInstance();
		CompiledOperation simOp = reg.createCompiledOperation( MPVOperation.OP_NAME );
		
		Object[] argsArray = { f };
		
		OperationManager<CompiledOperation> exec = (OperationManager<CompiledOperation>)
			getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
		
		Object res = exec.invokeOperation(simOp, argsArray, getContext());
		if ( !(res instanceof List) )
			throw new QueryException("Exception in operator " + getName() +
					": similarity measure operator must return list result");
		return ((List)res);
		
	}
}
