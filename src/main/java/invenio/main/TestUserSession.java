package invenio.main;

import invenio.DisplaySession;
import invenio.GraphSession;
import invenio.VisualGraphSession;
import invenio.data.InvenioGraph;
import invenio.event.UserSessionEvent;
import invenio.event.UserSessionListener;
import invenio.ui.VisualRuleListener;
import invenio.visual.GraphPainter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;

import prefuse.Display;
import prefuse.Visualization;
import edu.uci.ics.jung.utils.Pair;

/**
 * The UserSession is a singleton class that is created once for the Invenio
 * program in order to manage that particular session using Invenio.  The 
 * UserSession controls the Prefuse {@link Visualization} we are using, the
 * Prefuse {@link Display}s, and the {@link GraphSession}s, revealing high 
 * level functionality for each of these components.
 */
public class TestUserSession {
	private static final Map<String, TestUserSession> sessionMap = new HashMap<String, TestUserSession>();

	private Visualization m_vis;
	private ArrayList<DisplaySession> m_displaySessions;
	private ArrayList<GraphSession> m_graphSessions;
	private HashMap<Pair, VisualGraphSession> m_visGraphSessions;
	private int m_activeDisplaySession;
	private VisualRuleListener visualRuleListener;
	private boolean createDisplaysHQ;
	
	private TestUserSession() {
		m_vis = new Visualization();
		m_displaySessions = new ArrayList<DisplaySession>();
		m_graphSessions = new ArrayList<GraphSession>();
		m_visGraphSessions = new HashMap<Pair, VisualGraphSession>();
		visualRuleListener = new VisualRuleListener();
		createDisplaysHQ = false;
		m_activeDisplaySession = -1;
	}
	
	
	public VisualRuleListener getVisualRuleListener() {
		return visualRuleListener;
	}

/*
	public void displayGIAGraph(String str, prefuse.data.Graph graphdata, GraphSession graphsess){
		m_vis.add(str, graphdata);
		graphsess.getGraphPainter().runAnimation();
		
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.GRAPH_SESSION_ADDED, graphsess));
	}
*/	

	/**
	 * Register a GraphSession to the UserSession.  This will not create a Prefuse VisualGraph or
	 * a Display for the GraphSession.  In order to make a GraphSession able to be visualized, use
	 * the addAndActivateGraphSession(...) or addAndActivateGraphSessionInNewDisplay(...) methods,
	 * which are generally preferred.
	 */
	public void addGraphSession(GraphSession session) {
		m_graphSessions.add(session);
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.GRAPH_SESSION_ADDED, session));
	}
	
	private VisualGraphSession createVisualGraphSession(GraphSession graphSession, DisplaySession displaySession){
		int layout = graphSession.getGraph().numVertices()<1000 ? GraphPainter.FORCE_LAYOUT : GraphPainter.CIRCLE_LAYOUT;
		VisualGraphSession visSession = new VisualGraphSession(graphSession, displaySession, layout);
		m_visGraphSessions.put(new Pair(graphSession, displaySession), visSession);
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.VIS_GRAPH_SESSION_ADDED, visSession));
		return visSession;
	}
	
	public void setDisplayHighQuality(DisplaySession display, boolean highQuality){
		display.getDisplayPanel().setDisplayHighQuality(highQuality);
		for(Iterator<VisualGraphSession> iter = display.getVisualGraphSessions().iterator(); iter.hasNext(); )
			iter.next().getGraphPainter().quickRepaintGraph();
	}
	
	public void setActiveDisplayHighQuality(boolean highQuality){
		setDisplayHighQuality(getActiveDisplaySession(), highQuality);
	}
	
	public void setAllDisplaysHighQuality(boolean highQuality){
		createDisplaysHQ = highQuality;
		for(int i=0; i<m_displaySessions.size(); i++)
			setDisplayHighQuality(m_displaySessions.get(i), highQuality);
	}
	
	private DisplaySession createDisplaySession() {
		DisplaySession ds = new DisplaySession();
		m_displaySessions.add(ds);
		if(createDisplaysHQ)
			setDisplayHighQuality(ds, true);
//		fireUserSessionEvent(new UserSessionEvent(UserSessionEvent.ACTIVE_DISPLAY_SET));
		return ds;
	}

	/**
	 * @param session
	 * @return whether the GraphSession with the parameter name is being visualized in the
	 * active Display.
	 */
	public boolean isActiveGraphSession(String session) {
		GraphSession graphSession = getGraphSession(session);
		for(Iterator<VisualGraphSession> iter = getActiveDisplaySession().getVisualGraphSessions().iterator(); iter.hasNext(); )
			if(iter.next().getGraphSession() == graphSession)
				return true;
		return false;
	}
	
	/**
	 * Removes the GraphSession with the parameter name from this UserSession.  This method
	 * is currently not supported.
	 * @param sessionName
	 */
	public void removeGraphSession(String sessionName) {
		throw new UnsupportedOperationException("UserSession::removeGraphSession() not supported at this time!");
//		removeGraphSession(getGraphSession(sessionName));
	}
	
	/**
	 * Removes the given GraphSession from the UserSession.  This method is currently not supported.
	 * @param session
	 */
	public void removeGraphSession(GraphSession session){
		throw new UnsupportedOperationException("UserSession::removeGraphSession() not supported at this time!");
//		int index = m_graphSessions.indexOf(session);
//		for(int i = 0; i < m_displays.size(); i++)
//			if(m_displays.get(i).getGraphSession() == session)
//				m_displays.get(i).setGraphSession(null);
//		
//		session.remove();
//		m_graphSessions.remove(index);
//		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.GRAPH_SESSION_REMOVED));
	}

	/**
	 * Sets the Display visualizing the VisualGraph associated with the given VisualGraphSession
	 * to be the active Display.  If the the Display is already visible, it will be given active
	 * focus; if it is not visible, the previously active Display will be swapped out and be no
	 * longer visible, and the newly activated Display will be put in its place.
	 * 
	 * This method works given our assumption that each VisualGraph
	 * can only be visualized by a single Display.  The VisualGraph is only the visual representation
	 * of an InvenioGraph, any given data graph (InvenioGraph) can be visualized via multiple
	 * VisualGraphs each with their own Display.
	 * @param visSession
	 */
	public void setActiveVisualGraphSession(VisualGraphSession visSession){
		setActiveDisplaySession(visSession.getDisplaySession());
	}
	
	/**
	 * Sets the Display visualizing the VisualGraph associated with the given VisualGraphSession
	 * to be the active Display.  If the Display is already visible, it will merely be given 
	 * active focus and this method will terminate; if it is not visible, however, the Display 
	 * will be made visible while all other visible displays will also remain visible.
	 * 
	 * This method works given our assumption that each VisualGraph
	 * can only be visualized by a single Display.  The VisualGraph is only the visual representation
	 * of an InvenioGraph, any given data graph (InvenioGraph) can be visualized via multiple
	 * VisualGraphs each with their own Display.
	 * @param visSession
	 */
	public void setActiveVisualGraphSessionInNewDisplay(VisualGraphSession visSession){
		if(visSession.getDisplaySession().isVisible()){
			setActiveVisualGraphSession(visSession);
			return;
		}
		visSession.getDisplaySession().activateDisplay();
		setActiveDisplaySession(visSession.getDisplaySession());
		getActiveVisualGraphSession().getGraphPainter().runAnimation();
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.NEW_DISPLAY_ADDED, visSession.getDisplaySession()));
	}
	
	/**
	 * This method receives an existing GraphSession already registered in this UserSession as input, 
	 * and creates a new VisualGraph and Display for this GraphSession.  Essentially, this method 
	 * creates a new view of an existing InvenioGraph.  The new view will have coordinated selection
	 * with other views visualizing the same graph, but will be able to independently visualize the
	 * graph.  The new view will be displayed upon creation in place of the previously active Display.
	 * @param graphSession
	 */
	public void addAndActivateVisualGraphSession(GraphSession graphSession){
		if(!m_graphSessions.contains(graphSession))
			throw new RuntimeException("Error adding VisualGraphSession: GraphSession does not exist in UserSession!");
		DisplaySession oldDisplaySession = getActiveDisplaySession();
		DisplaySession newDisplaySession = createDisplaySession();
		createVisualGraphSession(graphSession, newDisplaySession);
		newDisplaySession.activateDisplay();
		setActiveDisplaySession(newDisplaySession);
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.DISPLAYS_SWAPPED, new Pair(oldDisplaySession, newDisplaySession)));
		getActiveVisualGraphSession().getGraphPainter().runAnimation();
	}
	
	/**
	 * This method receives an existing GraphSession already registered in this UserSession as input, 
	 * and creates a new VisualGraph and Display for this GraphSession.  Essentially, this method 
	 * creates a new view of an existing InvenioGraph.  The new view will have coordinated selection
	 * with other views visualizing the same graph, but will be able to independently visualize the
	 * graph.  The new view will be displayed upon creation in a new Display alongside other currently
	 * visible Displays.
	 */
	public void addAndActivateVisualGraphSessionInNewDisplay(GraphSession graphSession) {
		if(!m_graphSessions.contains(graphSession))
			throw new RuntimeException("Error adding VisualGraphSession: GraphSession does not exist in UserSession!");
		DisplaySession newDisplaySession = createDisplaySession();
		createVisualGraphSession(graphSession, newDisplaySession);
		newDisplaySession.activateDisplay();
		setActiveDisplaySession(newDisplaySession);
		getActiveVisualGraphSession().getGraphPainter().runAnimation();
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.NEW_DISPLAY_ADDED, newDisplaySession));
	}
	
	/**
	 * Adds a GraphSession to this UserSession, creates a VisualGraph and Display associated with it,
	 * and shows and makes active this Display in place of the previously active Display.
	 * 
	 * This method should only be used when adding a GraphSession to the UserSession that is
	 * not already registered with the UserSession.  To create a new view of an existing
	 * GraphSession, use the method addAndActivateVisualGraphSessionInNewDisplay(...)
	 * @param graphSession
	 */
	public void addAndActivateGraphSession(GraphSession graphSession) {
		GraphSession gs = getActiveGraphSession();
		if(m_graphSessions.contains(graphSession)) {
			
			DisplaySession oldDisplaySession = getActiveDisplaySession();
			VisualGraphSession visualDisplaySession = getActiveVisualGraphSession();
			
			//Remove this group
			String groupName = gs.getGraph().getName() + " View " + (gs.getVisualGraphSessions().size());
			m_vis.removeGroup(groupName);
			
			//Remove this triple
			m_visGraphSessions.remove(new Pair(gs, oldDisplaySession));
			
			createVisualGraphSession(graphSession, oldDisplaySession);
			oldDisplaySession.activateDisplay();
			setActiveDisplaySession(oldDisplaySession);
			
			fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.ACTIVE_DISPLAY_SET));
			getActiveVisualGraphSession().getGraphPainter().runAnimation();
			
		} 
		addGraphSession(graphSession);
			
		DisplaySession oldDisplaySession = getActiveDisplaySession();
		DisplaySession newDisplaySession = createDisplaySession();
		
		createVisualGraphSession(graphSession, newDisplaySession);
		newDisplaySession.activateDisplay();
		setActiveDisplaySession(newDisplaySession);
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.DISPLAYS_SWAPPED, new Pair(oldDisplaySession, newDisplaySession)));
		getActiveVisualGraphSession().getGraphPainter().runAnimation();
	}
	

	
	/**
	 * Adds a GraphSession to this UserSession, creates a VisualGraph and Display associated with it,
	 * and shows and makes active this Display alongside other Displays that are currently visible.
	 * 
	 * This method should only be used when adding a GraphSession to the UserSession that is
	 * not already registered with the UserSession.  To create a new view of an existing
	 * GraphSession, use the method addAndActivateVisualGraphSessionInNewDisplay(...)
	 * @param session
	 */
	public void addAndActivateGraphSessionInNewDisplay(GraphSession graphSession) {
		if(m_graphSessions.contains(graphSession))
			throw new RuntimeException("Error adding GraphSession: GraphSession already exists in UserSession!");
		DisplaySession newDisplaySession = createDisplaySession();
		addGraphSession(graphSession);
		createVisualGraphSession(graphSession, newDisplaySession);
		newDisplaySession.activateDisplay();
		setActiveDisplaySession(newDisplaySession);
		getActiveVisualGraphSession().getGraphPainter().runAnimation();
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.NEW_DISPLAY_ADDED, newDisplaySession));
	}
//	
//	public void addAndActivateGraphSession(GraphSession session, int display) {
//		addGraphSession(session);
//		setActiveGraphSession(session, display);
//		session.getGraphPainter().runAnimation();
//	}
//	
	/**
	 * @return the Prefuse Visualization that manages all the displays and VisualGraphs associated
	 * with this UserSession.
	 */
	public Visualization getVisualization() {
		return m_vis;
	}
	
	private DisplaySession getDisplaySession(int index) {
		if(index >= 0 && index < m_displaySessions.size())
			return m_displaySessions.get(index);
		else
			return null;
	}
	
	/**
	 * @return the number of Displays that are currently visible.
	 */
	public int getDisplayCount() {
		int count=0;
		for(int i=0; i<m_displaySessions.size(); i++){
			if(m_displaySessions.get(i).isVisible())
				count++;
		}
		return count;
	}
	
	private void removeDisplay(int index) {
		if(m_activeDisplaySession == index){
			// look for visible display prior to given display in list
			for(int i=index-1; i>=0; i--){
				if(m_displaySessions.get(i).isVisible()){
					setActiveDisplaySession(i);
					m_displaySessions.get(index).deactivateDisplay();
					return;
				}
			}
			// look for visible display after given display in list
			for(int i=index+1; i<m_displaySessions.size(); i++){
				if(m_displaySessions.get(i).isVisible()){
					setActiveDisplaySession(i);
					m_displaySessions.get(index).deactivateDisplay();
					return;
				}
			}
			// if there are no other visible displays, then choose a non-visible one and show it
			int i = (m_activeDisplaySession == 0 ? 1 : m_activeDisplaySession - 1);
			setActiveDisplaySession(i);
		}
		m_displaySessions.get(index).deactivateDisplay();
	}
	
	/**
	 * Makes the Display associated with the given DisplaySession no longer visible.  If that
	 * display was previously the active Display, then give the active focus to another visible
	 * Display.  If there are no other visible Displays, then choose one to be visible and give
	 * the focus to it.
	 * @param ds
	 */
	public void removeDisplay(DisplaySession ds){
		removeDisplay(m_displaySessions.indexOf(ds));
	}
	
	/**
	 * Makes the Display associated with the active DisplaySession no longer visible.  We give 
	 * the active focus to another visible Display.  If there are no other visible Displays, 
	 * then we choose one to be visible and give the focus to it.
	 * @param ds
	 */
	public void removeActiveDisplay() {
		removeDisplay(m_activeDisplaySession);
	}
	
	/**
	 * @return the DisplaySession associated with the currently active Display.
	 */
	public DisplaySession getActiveDisplaySession() {
		return getDisplaySession(m_activeDisplaySession);
	}
	
	/**
	 * Sets the given DisplaySession to be the active DisplaySession.  If the Display associated
	 * with the given DisplaySession is not currently visible, we make it visible and put it in
	 * the place of the previously active Display.
	 * @param displaySession
	 */
	public void setActiveDisplaySession(DisplaySession displaySession) {
		if(getActiveDisplaySession() != null)
			getActiveDisplaySession().setSelected(false);
		if(!displaySession.isVisible()){
			displaySession.activateDisplay();
			if(getActiveDisplaySession() != null)
				fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.DISPLAYS_SWAPPED, new Pair(getActiveDisplaySession(), displaySession)));
		}
		m_activeDisplaySession = m_displaySessions.indexOf(displaySession);
		displaySession.setSelected(true);
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.ACTIVE_DISPLAY_SET));
	}
	
	private void setActiveDisplaySession(int display) {
		setActiveDisplaySession(m_displaySessions.get(display));
	}
	
	/**
	 * @return the GraphSession that is being visualized in the currently active Display.
	 * We are assuming for the time being that a given display can only visualize a single
	 * GraphSession.
	 */
	public GraphSession getActiveGraphSession() {
		return getActiveDisplaySession().getVisualGraphSessions().get(0).getGraphSession();
	}
	
	public void replaceActiveVisualGraphSession(VisualGraphSession vgs) {
		getActiveDisplaySession().getVisualGraphSessions().add(0, vgs);
	}
	/*
	public GraphSession[] getVisibleGraphSessions() {
		GraphSession[] sessions = new GraphSession[m_displays.size()];
		for(int i=0; i<m_displays.size(); i++)
			sessions[i] = m_displays.get(i).getGraphSession();
		return sessions;
	}
	*/
	/**
	 * @return the InvenioGraph associated with the GraphSession that is being visualized in
	 * the currently active Display.
	 * We are assuming for the time being that a given display can only visualize a single 
	 * GraphSession.
	 */
	public InvenioGraph getActiveGraph() {
		return getActiveGraphSession().getGraph();
	}
	
	/**
	 * @return the name of the InvenioGraph/GraphSession that is being visualized in
	 * the currently active Display.
	 * We are assuming for the time being that a given display can only visualize a single 
	 * GraphSession.
	 */
	public String getActiveGraphName() {
		return getActiveGraph().getName();
	}
	
	/**
	 * @param name
	 * @return the InvenioGraph with the given name, retrieved from the GraphSessions stored
	 * by this UserSession.
	 */
	public InvenioGraph getGraph(String name) {
		return getGraphSession(name).getGraph();
	}

	/**
	 * @param name
	 * @return the GraphSession with the given name, retrieved from the GraphSessions stored
	 * by this UserSession.
	 */
	public GraphSession getGraphSession(String name) {
		for(int i = 0; i < m_graphSessions.size(); i++)
			if(m_graphSessions.get(i).getName().equals(name))
				return m_graphSessions.get(i);
		
		return null;
	}
	
	/**
	 * @return a list of all the GraphSessions stored by this UserSession.
	 */
	public List<GraphSession> getGraphSessions() {
		return Collections.unmodifiableList(m_graphSessions);
	}
	
	/**
	 * @return an array of all the names of the GraphSessions stored by this UserSession.
	 */
	public String[] getGraphSessionNames() {
		int count = m_graphSessions.size();
		String[] names = new String[count];
		
		for(int i = 0; i < count; i++)
			names[i] = m_graphSessions.get(i).getName();
		
		return names;
	}
	
	/**
	 * @param graphSession
	 * @param display
	 * @return the VisualGraphSession associated with the given GraphSession and DisplaySession.
	 * That is to say, return the VisualGraphSession that is the visual representation of the given
	 * GraphSession and is being displayed by the given DisplaySession.  These two parameters 
	 * uniquely identify the VisualGraphSession since we assume that a VisualGraph is only viewable
	 * in a single display, and only represents a single graph. 
	 */
	public VisualGraphSession getVisualGraphSession(GraphSession graphSession, DisplaySession display){
		return m_visGraphSessions.get(new Pair(graphSession, display));
	}
	
	/**
	 * @param visualGraphName the Prefuse group name of the VisualGraph whose associated 
	 * VisualGraphSession we wish to retrieve.
	 * @return the VisualGraphSession associated with the given VisualGraph name.
	 */
	public VisualGraphSession getVisualGraphSession(String visualGraphName) {
		int index = visualGraphName.lastIndexOf("V");
		
		GraphSession gs = getGraphSession(visualGraphName.substring(0, index-1));
		System.out.println ("Graph session = " + gs);
		System.out.println ("Graph # = " + (Integer.parseInt(visualGraphName.substring(index+5)) - 1));
		return gs.getVisualGraphSessions().get(Integer.parseInt(visualGraphName.substring(index+5)) - 1);
	}
	
	/**
	 * @return the VisualGraphSession whose associated VisualGraph is being displayed in the 
	 * currently active Display.
	 */
	public VisualGraphSession getActiveVisualGraphSession() {
		return getActiveDisplaySession().getVisualGraphSessions().get(0);
	}
	
	/**
	 * @param visSession
	 * @return whether the currently active Display is visualizing the VisualGraph associated
	 * with the given VisualGraphSession.
	 */
	public boolean isActiveVisualGraphSession(VisualGraphSession visSession){
		return getActiveVisualGraphSession() == visSession;
	}
	
	/**
	 * @return a collection of all the VisualGraphSessions registered with this UserSession.
	 * Essentially, this is a list of all the views of all the GraphSessions.
	 */
	public Collection<VisualGraphSession> getVisualGraphSessions() {
		return m_visGraphSessions.values();
	}
	
	public void saveSession(String filename) throws IOException {
/*		
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
		output.writeInt(m_activeGraphSessions.size());
		//output.write(m_activeGraphSessions.size());
		for(int i = 0; i < m_activeGraphSessions.size(); i++) {
			System.out.println(m_activeGraphSessions.get(i));
			output.writeInt(m_activeGraphSessions.get(i));
		}
//rite int somewhere for edges
	//	System.out.println(m_graphSessions.);
		output.writeObject(m_graphSessions);		
*/	}
	
	public void loadSession(String filename) throws IOException, ClassNotFoundException {
/*		try{
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
		int val=0;
		
		m_vis = new Visualization();
		m_displays = new ArrayList<DisplayPanel>();
		m_activeDisplay = 0;
		addDisplay(new Display(m_vis));
		
		val = input.readInt();
		
		m_activeGraphSessions = new ArrayList<Integer>(val);
		//for(int i = 0; i < m_activeGraphSessions.size(); i++)
		for(int i = 0; i < val; i++){
			
			int temp = input.readInt();
			m_activeGraphSessions.add(temp);
			System.out.println("temp "+temp);
			//m_activeGraphSessions.set(i, temp);
		}
		m_graphSessions = (ArrayList<GraphSession>) input.readObject();
		
		for(int i = 0; i < m_graphSessions.size(); i++)
			m_vis.add(m_graphSessions.get(i).getGraph().getName(), m_graphSessions.get(i).getPrefuseGraph());

		
		fireUserSessionEvent(new UserSessionEvent(this, UserSessionEvent.USER_SESSION_LOADED));
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
*/	}
	
//	private static UserSession m_instance;
	
	public static TestUserSession getInstance(String id) {
		TestUserSession userSession = sessionMap.get(id);
		if(userSession == null) {
			userSession = new TestUserSession();
			sessionMap.put(id, userSession);
		}
		
		return userSession;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	//------------------------ UserSessionListener methods -----------------------------
	
	private EventListenerList listenerList = new EventListenerList();
    
	public void addUserSessionListener(UserSessionListener listener) {
		listenerList.add(UserSessionListener.class, listener);
	}

	public void removeUserSessionListener(UserSessionListener listener) {
		listenerList.remove(UserSessionListener.class, listener);
	}
	
	private void fireUserSessionEvent(UserSessionEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i+=2) {
			if (listeners[i] == UserSessionListener.class) {
				((UserSessionListener)listeners[i+1]).userSessionChanged(evt);
			}
		}
	}


	
}
