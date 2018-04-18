package invenio.main;

import invenio.DisplaySession;
import invenio.GraphSession;
import invenio.VisualGraphSession;

public class SubDisplaySession extends DisplaySession {
	protected TestUserSession userSession;
	
	public void setUserSession(TestUserSession userSession) {
		this.userSession = userSession;
	}
	
	public VisualGraphSession getVisualGraphSession(GraphSession session){
		return userSession.getVisualGraphSession(session, this);
	}
}
