package invenio.main;

import invenio.DisplaySession;
import invenio.GraphSession;
import invenio.UserSession;
import invenio.VisualGraphSession;
import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.util.AlgorithmManager;
import invenio.visual.AlgorithmHighlighter;
import invenio.visual.GraphPainter;
import invenio.visual.VisualRuleSet;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;

import prefuse.visual.VisualGraph;
import prefuse.visual.expression.InGroupPredicate;

public class SubVisualGraphSession extends VisualGraphSession {
	protected TestUserSession userSession;
	
	public void setUserSession(TestUserSession userSession) {
		this.userSession = userSession;
	}
	
	public SubVisualGraphSession(GraphSession graphSession, DisplaySession displaySession, int layout) {
		m_graphSession = graphSession;
		m_displaySession = displaySession;
		
		//TODO: Fix visualGraphName such that deleting a VisualGraphSession won't mess up the indices
		m_visualGraphName = graphSession.getGraph().getName() + " View " + (graphSession.getVisualGraphSessions().size()+1);

		userSession.getVisualization().add(getVisualGraphName(), m_graphSession.getPrefuseGraph());
		
		m_graphPainter = new GraphPainter(this, layout);
		m_visualHistory = new ArrayList<VisualRuleSet>();
		m_visualGraphPredicate = new InGroupPredicate(getVisualGraphName());
		listenerList = new EventListenerList();

		m_algorithmHighlighter = new AlgorithmHighlighter(this);
		m_algorithmHistory = new ArrayList<AlgorithmResult>();
		m_algorithmManager = new AlgorithmManager(this);
		
		graphSession.addVisualGraphSession(this);
		displaySession.addVisualGraphSession(this);
	}
	
	public VisualGraph getVisualGraph() {
		return (VisualGraph) userSession.getVisualization().getVisualGroup(m_visualGraphName);
	}

}
