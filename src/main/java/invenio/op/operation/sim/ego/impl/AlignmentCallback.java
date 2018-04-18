package invenio.op.operation.sim.ego.impl;

import qng.client.QueryException;
import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;

public interface AlignmentCallback {
	void startAlignment(InvenioVertex v1, InvenioVertex v2) throws QueryException;
	void startAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException;
	void alignEdge(InvenioEdge e1, InvenioEdge e2) throws QueryException;
	void endAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException;
	void endAlignment() throws QueryException;
}
