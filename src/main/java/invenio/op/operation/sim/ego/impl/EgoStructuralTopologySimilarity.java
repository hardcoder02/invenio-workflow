package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioVertex;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperatorUtils;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class EgoStructuralTopologySimilarity extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "egoStructuralTopologySimilarity";
	
	public final static boolean DEFAULT_ALIGN = true;

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public EgoStructuralTopologySimilarity() {
		super(null, OP_NAME);
	}
	
	public EgoStructuralTopologySimilarity(String id) {
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
			EgoNetAligner al = AbstractEgoNetAligner.createInstance(null);
			EgoStructuralTopologyAC ac = new EgoStructuralTopologyAC();
			al.align(n1, n2, getContext(), ac );
			return ac.getResult();
		}
		else {
			EgoNetAligner al = AbstractEgoNetAligner.createInstance( AbstractEgoNetAligner.PSEUDO_ALIGNER_NAME );
			UnalignedEgoStructuralTopologyAC ac = new UnalignedEgoStructuralTopologyAC();
			al.align(n1, n2, getContext(), ac );
			return ac.getResult();
		}
	}

	private static class EgoStructuralTopologyAC extends AlignmentCallbackAdapter {
		private int numCommon = 0;
		private int numTotal = 0;
		
	
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) {
			numTotal++;
			if (v1 != null && v2 != null)
				numCommon++;
		}
		
		public double getResult() {
			if ( numTotal == 0)
				return 1;
			return (numTotal == 0) ? 1 : numCommon / (double) numTotal;
		}
	}
	
	private static class UnalignedEgoStructuralTopologyAC extends AlignmentCallbackAdapter {
		private int numG1 = 0;
		private int numG2 = 0;
		
	
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) {
			if (v1 != null)
				numG1++;
			if (v2 != null)
				numG2++;
		}
		
		public double getResult() {
			if ( numG1 < numG2)
				return numG1 / (double) numG2;	// numG2 at least 1, if it is greater than numG1
			else if (numG1 == 0)
				return 1;
			else
				return numG2 / (double) numG1;
		}
	}

}
