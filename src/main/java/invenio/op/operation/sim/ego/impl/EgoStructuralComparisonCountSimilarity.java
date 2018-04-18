package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioVertex;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperatorUtils;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

public class EgoStructuralComparisonCountSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "egoStructuralComparisonCountSimilarity";
	
	public final static boolean DEFAULT_ALIGN = true;

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public EgoStructuralComparisonCountSimilarity() {
		super(null, OP_NAME);
	}
	
	public EgoStructuralComparisonCountSimilarity(String id) {
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
			EgoStructuralComparisonCountAC ac = new EgoStructuralComparisonCountAC();
			al.align(n1, n2, getContext(), ac );
			return ac.getResult();
		}
		else {
			EgoNetAligner al = AbstractEgoNetAligner.createInstance( AbstractEgoNetAligner.PSEUDO_ALIGNER_NAME );
			UnalignedEgoStructuralComparisonCountAC ac = new UnalignedEgoStructuralComparisonCountAC();
			al.align(n1, n2, getContext(), ac );
			return ac.getResult();
		}
	}

	private static class EgoStructuralComparisonCountAC extends AlignmentCallbackAdapter {
		private int numCommon = 0;
	
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) {
			if (v1 != null && v2 != null)
				numCommon++;
		}
		
		public double getResult() {
			return numCommon;
		}
	}
	
	private static class UnalignedEgoStructuralComparisonCountAC extends AlignmentCallbackAdapter {
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
			return Math.min(numG1, numG2);
		}
	}

}
