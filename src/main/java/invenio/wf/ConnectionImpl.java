package invenio.wf;

import invenio.data.InvenioDirectedEdge;

public class ConnectionImpl implements Connection {
	private final InvenioDirectedEdge edge;
	private final ConnectionItem item;
	
	ConnectionImpl(ConnectionItem item, InvenioDirectedEdge e) {
		this.edge = e;
		this.item = item;
	}
	
	InvenioDirectedEdge getInvenioDirectedEdge() {
		return edge;
	}
	
	public String toString() {
		return (item == null) ? "" : item.toString();
	}
}
