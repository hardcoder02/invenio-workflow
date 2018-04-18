package invenio.op.operation.directed;

import invenio.data.InvenioVertex;
import java.util.Collection;
import java.util.Iterator;

import qng.client.QueryException;

public class GraphFeatureProcessor {
	public static int countFeature(Collection vertices, FeaturePredicate p) throws QueryException {
		int runningCount = 0;
		
		Iterator iter = vertices.iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			if ( (o instanceof InvenioVertex) && p.hasFeature( (InvenioVertex)o) )
				runningCount++;
		}
		return runningCount;
	}
	
	public static interface FeaturePredicate {
		boolean hasFeature(InvenioVertex v) throws QueryException;
	}
}
