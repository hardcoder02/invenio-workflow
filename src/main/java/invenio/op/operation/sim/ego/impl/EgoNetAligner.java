package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioVertex;
import qng.client.QueryException;
import qng.core.query.Context;

public interface EgoNetAligner {
	void align(InvenioVertex n1, InvenioVertex n2, Context ctx, AlignmentCallback ac) throws QueryException;
}
