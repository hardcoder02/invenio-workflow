package invenio.op.operation.sim.ego.impl;

import qng.client.QueryException;
import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;

public class AlignmentCallbackAdapter implements AlignmentCallback {

	@Override
	public void alignEdge(InvenioEdge e1, InvenioEdge e2) throws QueryException {	}

	@Override
	public void endAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException { }

	@Override
	public void endAlignment() throws QueryException { }

	@Override
	public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException { }

	@Override
	public void startAlignment(InvenioVertex v1, InvenioVertex v2) throws QueryException { }

}
