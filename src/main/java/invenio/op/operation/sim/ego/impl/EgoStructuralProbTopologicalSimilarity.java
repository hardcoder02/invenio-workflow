package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioVertex;
import invenio.op.operation.impl.ConfOperation;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.op.operation.impl.OperatorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import qng.client.QueryException;
import qng.core.compiler.OperationRegistry;
import qng.core.executor.OperationManager;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.Context;

public class EgoStructuralProbTopologicalSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "egoStructuralProbTopologicalSimilarity";
	
	public final static boolean DEFAULT_ALIGN = true;

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public EgoStructuralProbTopologicalSimilarity() {
		super(null, OP_NAME);
	}
	
	public EgoStructuralProbTopologicalSimilarity(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		InvenioVertex n1 = (InvenioVertex) args[0];
		InvenioVertex n2 = (InvenioVertex) args[1];
		
		if (n1 == null || n2 == null)
			return null;
		
		
		// TODO: refactor
		boolean align = OperatorUtils.retrieveContextParamAsBoolean(
				getContext(), OperationConstants.EGOSIM_ALIGN, DEFAULT_ALIGN );
		
		
		if (align) {
			return processAligned(n1, n2);
		}
		else {
			return processUnaligned(n1, n2);
		}
	}
	
	private Object processAligned( InvenioVertex n1, InvenioVertex n2 ) throws QueryException {
		EgoNetAligner al = AbstractEgoNetAligner.createInstance(null);
		EgoStructuralProbTopologicalAC ac = new EgoStructuralProbTopologicalAC( );
		al.align(n1, n2, getContext(), ac );
		return ac.getResult();
	}
	
	private Object processUnaligned( InvenioVertex n1, InvenioVertex n2 ) throws QueryException {
		EgoNetAligner al = AbstractEgoNetAligner.createInstance( AbstractEgoNetAligner.PSEUDO_ALIGNER_NAME );
		UnalignedEgoStructuralProbTopologicalAC ac = new UnalignedEgoStructuralProbTopologicalAC();
		al.align(n1, n2, getContext(), ac );
		return ac.getResult();
	}

	private class EgoStructuralProbTopologicalAC extends AlignmentCallbackAdapter {
		private final OperationManager<CompiledOperation> exec;
		private final Context ctx;
		
		private InvenioVertex lastV1, lastV2;
		private int num = 0;
		private double sum = 0;
		
		private EgoStructuralProbTopologicalAC() throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
		}
		
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) {
			lastV1 = v1;
			lastV2 = v2;
		}
		
		@Override
		public void alignEdge(InvenioEdge e1, InvenioEdge e2) throws QueryException {
			sum+= Math.abs( getConf(lastV1, e1) - getConf(lastV2, e2) );
			num++;
		}
		
		private double getConf(InvenioVertex v, InvenioEdge e) throws QueryException {
			if (v == null || e == null)
				return 0;
			
			return getConf( v ) * getConf( e );
		}
		
		private double getConf(InvenioElement el) throws QueryException {
			OperationRegistry reg = OperationRegistryFactory.getInstance();
			CompiledOperation confOp = reg.createCompiledOperation( ConfOperation.OP_NAME );
			
			Object[] argsArray = { el };
			
			Object res = exec.invokeOperation(confOp, argsArray, ctx);
			if ( !(res instanceof Double) )
				throw new QueryException("Exception in operator " + getName() +
						": confidence operator must return double result");
			return ((Double)res);
		}

		public double getResult() {
			return (num == 0) ? 1 : (1 - sum / num);
		}
	}
	
	private class UnalignedEgoStructuralProbTopologicalAC extends AlignmentCallbackAdapter {
		private final OperationManager<CompiledOperation> exec;
		private final Context ctx;
		
		private InvenioVertex lastV1, lastV2;
		private List<Double> conf1 = new ArrayList<Double>();
		private List<Double> conf2 = new ArrayList<Double>();
		
		private UnalignedEgoStructuralProbTopologicalAC() throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
		}
		
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) {
			lastV1 = v1;
			lastV2 = v2;
		}
		
		@Override
		public void alignEdge(InvenioEdge e1, InvenioEdge e2) throws QueryException {
			if ( lastV1 != null )
				conf1.add( getConf(lastV1, e1) );
			if ( lastV2!= null )
				conf2.add( getConf(lastV2, e2) );
		}
		
		private double getConf(InvenioVertex v, InvenioEdge e) throws QueryException {
			if (v == null || e == null)
				return 0;
			
			return getConf( v ) * getConf( e );
		}
		
		private double getConf(InvenioElement el) throws QueryException {
			OperationRegistry reg = OperationRegistryFactory.getInstance();
			CompiledOperation confOp = reg.createCompiledOperation( ConfOperation.OP_NAME );
			
			Object[] argsArray = { el };
			
			Object res = exec.invokeOperation(confOp, argsArray, ctx);
			if ( !(res instanceof Double) )
				throw new QueryException("Exception in operator " + getName() +
						": confidence operator must return double result");
			return ((Double)res);
		}

		public double getResult() {
			double sum = 0;
			int total = Math.max( conf1.size(), conf2.size() );
			if (total == 0)
				return 1;
			
			Double[] aConf1 = new Double[conf1.size()];
			conf1.toArray(aConf1);
			Double[] aConf2 = new Double[conf2.size()];
			conf2.toArray(aConf2);
			
			Arrays.sort(aConf1, 0, aConf1.length - 1);
			Arrays.sort(aConf2, 0, aConf2.length - 1);
			
			int i = aConf1.length - 1;
			int j = aConf2.length - 1;
			for (; i >= 0 || j >=0; i--, j--) {
				 double c1 = ( i < 0 ) ? 0 : aConf1[i];
				 double c2 = ( j < 0 ) ? 0 : aConf2[j];
				 sum += Math.abs( c1 - c2 );
			}
			
			return 1 - sum / total;
		}
	}

}
