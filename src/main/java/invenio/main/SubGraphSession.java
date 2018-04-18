package invenio.main;

import invenio.DisplaySession;
import invenio.GraphSession;
import invenio.VisualGraphSession;
import invenio.data.InvenioGraph;

public class SubGraphSession extends GraphSession {
	protected TestUserSession userSession;
	
	public SubGraphSession(InvenioGraph graph) {
		super(graph);
	}
	
	public SubGraphSession(InvenioGraph graph, int translationType) {
		super(graph, translationType);
	}
	
	public VisualGraphSession getVisualGraphSession(DisplaySession display){
		return userSession.getVisualGraphSession(this, display);
	}
	
	
	public void setUserSession(TestUserSession userSession) {
		this.userSession = userSession;
	}

}
