package invenio.op.operation.directed;

import qng.client.QueryException;
import invenio.data.InvenioVertex;

public class SwitchNumberCalculator {
	
	public SwitchNumberCount evaluateCount(InvenioVertex v, IncrementCountPredicate p) throws QueryException {
		SwitchNumberCount runningCount = new SwitchNumberCount();
		
		evaluateCount(v, runningCount, p);
		
		return runningCount;
	}
	
	
	protected void evaluateCount(InvenioVertex v, SwitchNumberCount runningCount, IncrementCountPredicate p) throws QueryException {		
		for (InvenioVertex vChild : DirectedUtils.getChildren(v) ) {
			if ( p.isSwitch(v, vChild) )
				runningCount.switchCount++;
			else
				runningCount.stableCount++;
			evaluateCount(vChild, runningCount, p);
		}
	}
	
	public static class SwitchNumberCount {
		public int stableCount;
		public int switchCount;
		
		public int getTotalCount() {
			return stableCount + switchCount;
		}

		public double getSwitchRatio() {
			return ((double) switchCount) / ( (double)(stableCount + switchCount) );
		}
	}
	
	public static interface IncrementCountPredicate {
		boolean isSwitch(InvenioVertex v1, InvenioVertex v2) throws QueryException;
	}
}
