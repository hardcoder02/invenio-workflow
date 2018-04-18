package invenio.main;


import invenio.DisplaySession;
import invenio.GraphSession;
import invenio.UserSession;
import invenio.VisualGraphSession;
import invenio.algorithms.AlgorithmResult;
import invenio.control.SuperNodeSelectControl;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.data.InvenioSchemaTree;
import invenio.event.GraphPainterEvent;
import invenio.event.GraphPainterListener;
import invenio.event.GraphSessionEvent;
import invenio.event.GraphSessionListener;
import invenio.event.UserSessionEvent;
import invenio.event.UserSessionListener;
import invenio.ui.ControlPanel;
import invenio.ui.DefaultControlPanel;
import invenio.ui.DisplayPanel;
import invenio.ui.GraphInfoPane;
import invenio.ui.MainFrame;
import invenio.ui.NavigationPane;
import invenio.visual.GraphPainter;
import invenio.visual.VisualRuleSet;

import prefuse.visual.VisualItem;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import javax.swing.text.Position;

import edu.uci.ics.jung.utils.Pair;

/**
 * The MainFrameController class is a primarily a listener class for the 
 * majority of the important classes in Invenio that need listeners.  It 
 * implements the {@link GraphSessionListener} for {@link GraphSession}s, the
 * {@link UserSessionListener} for the {@link UserSession}, the {@link 
 * GraphPainterListener} for the {@link GraphPainter}.
 * 
 */
public class TestMainFrameController implements GraphSessionListener, UserSessionListener, GraphPainterListener {

	/**
	 * Handles {@link GraphSessionEvent}s that indicate that the {@link 
	 * GraphSession} has changed.  
	 */
	public void graphSessionChanged(GraphSessionEvent e) {
		
		switch(e.getEventType()) {
			case GraphSessionEvent.GRAPH_SETS_CHANGED:
				System.out.println("*** Graph Sets Changed ***");
				updateGraphSetsTree();
				break;
			case GraphSessionEvent.GRAPH_TRANSLATED:
				System.out.println("*** Graph Translated ***");
				updateDisplayTable();
				updateGraphSetsTree();
				break;
			case GraphSessionEvent.GRAPH_SELECTION_CHANGED:
				System.out.println("*** Graph Selection Changed ***");
				updateReferenceTextField();
				break;
			case GraphSessionEvent.PAINTER_LAYOUT_CHANGED:
				System.out.println("*** Graph Layout Changed ***");
				m_frame.getToolbar().getAnimationButton().setAnimated(((VisualGraphSession)e.getSource()).getGraphPainter().isAnimated());
				break;
			case GraphSessionEvent.SESSION_NAME_CHANGED:
				System.out.println("*** Graph Name Changed ***");
				updateGraphSetsTree();
				break;
			case GraphSessionEvent.ALGORITHM_HIGHLIGHTED:
				System.out.println("*** Algorithm Highlighted ***");
				
				break;
			case GraphSessionEvent.VISUAL_HISTORY_CHANGED:
				System.out.println("*** Visual History Changed ***");
				updateVisualHistory();
				break;
			case GraphSessionEvent.ALGORITHM_HISTORY_CHANGED:
				System.out.println("*** Algorithm History Changed ***");
				updateAlgorithmHistory();
				break;
		}
	}
	
	/**
	 * Handles {@link UserSessionEvent}s that indicate that the {@link 
	 * UserSession} has changed.
	 */
	public void userSessionChanged(UserSessionEvent e) {
		
		switch(e.getEventType()) {
			case UserSessionEvent.GRAPH_SESSION_ADDED:
				System.out.println("*** Graph Session Added ***");
				updateGraphSetsTree();
				((GraphSession)e.getRelevantObject()).addGraphSessionListener(this);
				((GraphSession)e.getRelevantObject()).getGraphSelection().addGraphSessionListener(this);
				break;
			case UserSessionEvent.VIS_GRAPH_SESSION_ADDED:
				System.out.println("*** Visual Graph Session Added ***");
				((VisualGraphSession)e.getRelevantObject()).addGraphSessionListener(this);
				((VisualGraphSession)e.getRelevantObject()).getGraphPainter().addGraphPainterListener(this);
				break;
			case UserSessionEvent.GRAPH_SESSION_REMOVED:
				System.out.println("*** Graph Session Removed ***");
				updateGraphSetsTree();
				updateActiveGraphSession();
				break;
			case UserSessionEvent.ACTIVE_DISPLAY_SET:
				System.out.println("*** Active Display Set ***");
				updateActiveDisplay();
				updateActiveGraphSession();
				break;
			case UserSessionEvent.DISPLAYS_SWAPPED:
				System.out.println("*** Displays Swapped ***");
				Pair pair = (Pair)e.getRelevantObject();
				m_frame.swapDisplays(((DisplaySession)pair.getFirst()).getDisplayPanel(), ((DisplaySession)pair.getSecond()).getDisplayPanel());
				((DisplaySession)pair.getFirst()).deactivateDisplay();
				break;
			case UserSessionEvent.NEW_DISPLAY_ADDED:
				System.out.println("*** New Display Added ***");
				m_frame.addDisplay(((DisplaySession)e.getRelevantObject()).getDisplayPanel());
				break;
			case UserSessionEvent.USER_SESSION_LOADED:
				System.out.println("*** User Session Loaded ***");
				initialize();
				m_session.addUserSessionListener(this);
				updateGraphSetsTree();
				updateActiveGraphSession();
				break;
		}
	}
	
	/**
	 * Performs operations required before closing Invenio, such as autosaving,
	 * though this is not currently implemented (commented out).
	 */
	public void shutdownInvenio() {
		/*
		if(Prefs.getInstance().getAutoSavePref()) {
			try {
				UserSession.getInstance().saveSession(Constants.AUTOSAVE_FILE);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}*/
	}

	/**
	 * Handles {@link GraphPainterEvent}s that indicate that the {@link 
	 * GraphPainter} has changed, either by a layout or an animation change, by
	 * updating the animation button to match the animated state of the graph.
	 */
	public void graphPainterChanged(GraphPainterEvent e) {
		
		switch(e.getEventType()) {
			case GraphPainterEvent.LAYOUT_CHANGED:
			case GraphPainterEvent.ANIMATION_CHANGED:
				m_frame.getToolbar().getAnimationButton().setAnimated(((GraphPainter)e.getSource()).isAnimated());
//				m_frame.getToolbar().getAnimationButton().setAnimated(m_session.getActiveGraphSession().getGraphPainter().isAnimated());
				break;
		}
	}
	
	/**
	 * Updates the contents of the info tabs for graph information, node 
	 * information, and edge information, that is shown currently at the 
	 * bottom right hand side of the user interface.  This information is
	 * specific to the current {@link InvenioGraph} that is being viewed, and
	 * is computed from the {@link InvenioGraph}'s implementation of the 
	 * {@link InvenioGraph#computeStatistics()} method, and gotten by this 
	 * method using the {@link InvenioGraph#getStatistics()} method.
	 * 
	 * @see InvenioGraph#computeStatistics()
	 * @see InvenioGraph#getStatistics()
	 */
	public void updateGraphInfoPanel(){
		GraphInfoPane graphInfoPane = GraphInfoPane.getInstance();
		try{
//			System.out.println(m_session);
//			System.out.println(m_session.getActiveGraph()); //
//			System.out.println(m_session.getActiveGraph().getName());
//			System.out.println(UserSession.getInstance().getGraph(m_session.getActiveGraph().getName()));
//			System.out.println(UserSession.getInstance().getGraph(m_session.getActiveGraph().getName()).getStatistics());
			String stat = UserSession.getInstance().getActiveGraph().getStatistics();
			String[] stats = stat.split("start");
			graphInfoPane.setGraphInfo(stats[1]);
			graphInfoPane.setNodeInfo(stats[2]);
			if(stats.length > 3)
				graphInfoPane.setEdgeInfo(stats[3]);	
			else
				graphInfoPane.setEdgeInfo("");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates user interface components related to the active {@link 
	 * GraphSession}.  Called from the {@link 
	 * #userSessionChanged(UserSessionEvent)} method fired by the {@link 
	 * UserSession}.
	 */
	public void updateActiveGraphSession() {
		
		updateGraphInfoPanel();
		
		updateReferenceTextField();

		updateVisualHistory();
		updateAlgorithmHistory();

		updateDisplayTable();
		
		ControlPanel controlPanel = m_session.getActiveDisplaySession().getDisplayPanel().getControlPanel();
		DefaultControlPanel defaultControlPanel;
		
		if(m_session.getActiveGraphSession() == null) {
			System.err.println("WARNING: no GraphSessions are currently active");
			m_frame.getToolbar().getAnimationButton().setAnimated(true);
			GraphInfoPane.getInstance().setGraphInfo("");
			m_frame.getToolbar().getLayoutComboBox().setSelectedIndex(0);

			if(controlPanel instanceof DefaultControlPanel){
				defaultControlPanel = (DefaultControlPanel)controlPanel;
				defaultControlPanel.getUtilSlider().getModel().setValue(0);
			}
		}
		else {
			m_frame.getToolbar().getAnimationButton().setAnimated(m_session.getActiveVisualGraphSession().getGraphPainter().isAnimated());
			m_frame.getToolbar().getLayoutComboBox().setSelectedIndex(m_session.getActiveVisualGraphSession().getGraphPainter().getLayout());

//			if(controlPanel instanceof DefaultControlPanel){
//				defaultControlPanel = (DefaultControlPanel)controlPanel;
//				int val = m_session.getActiveGraphSession().getGraphPainter().getFilterSet().get(defaultControlPanel.getUtilSliderComboBox().getSelectedIndex());
//				if(val >= 0) defaultControlPanel.getUtilSlider().getModel().setValue(val);
//			}
			m_frame.m_displayTable = m_session.getActiveGraphSession().getPrefuseTableComponent();
			m_frame.m_displayTableScrollPane = new JScrollPane(m_frame.m_displayTable);
//			m_frame.resizeComponents();
		}
		updateGraphSetsTree();
	}
	
	public void updateActiveDisplay() {
		m_frame.updateActiveDisplay();
	}
	
	/**
	 * Updates the graph sets navigation tree that is currently on the right
	 * side of the Invenio UI.  In order not to change the expanded states of
	 * the tree, we create an updated tree first, but don't set this as a 
	 * replacement, but rather find all new updates in the updated tree and
	 * add them to the original tree.  This way, the tree does not collapse
	 * every time it is updated.  This is rather inefficient, but given that
	 * this tree should be relatively small, it should not have any performance
	 * impact.
	 */
	public void updateGraphSetsTree() {
		
		NavigationPane navPane = NavigationPane.getInstance();
		
		DefaultMutableTreeNode updateTree = new DefaultMutableTreeNode("root");
		
		List<GraphSession> graphSessions = m_session.getGraphSessions();
		
		InvenioSchemaTree sets;
		for(int i=0; i<graphSessions.size(); i++){
			sets = graphSessions.get(i).getGraph().getSets();
			if(sets != null && sets.getSetType() == InvenioSchemaTree.ROOT)
				updateTree.add(sets.getUISubtree(InvenioElementSet.ALL_SETS));
		}
		
		compareAndUpdateTree((DefaultMutableTreeNode) navPane.getGraphTree().getModel().getRoot(), updateTree);

		if(!(navPane.getGraphTree() == null)){
			navPane.getGraphTree().updateUI();
		}else{
			System.err.println("ERROR in MainFrameController: graphTree is null");
		}
	}
	private void compareAndUpdateTree(DefaultMutableTreeNode graphTree, DefaultMutableTreeNode updateTree){
		graphTree.removeAllChildren();
		//for(int i=0; i<updateTree.getChildCount(); i++){
		while ( updateTree.getChildCount() > 0){
	//		if(i==graphTree.getChildCount()){
				graphTree.add((MutableTreeNode)updateTree.getChildAt(0));
	//			continue;
	//		}
	//		compareAndUpdateTree((DefaultMutableTreeNode)graphTree.getChildAt(i), 
	//				(DefaultMutableTreeNode)updateTree.getChildAt(i));
		}
	}

	/**
	 * Updates the reference text field by filling it with the name of the 
	 * current reference selection name.  This selection would exist if a 
	 * reference selection was made using the {@link
	 * SuperNodeSelectControl#itemClicked(VisualItem, MouseEvent)} method.
	 */
	public void updateReferenceTextField() {
		if(!(m_session.getActiveDisplaySession().getDisplayPanel().getControlPanel() instanceof DefaultControlPanel))
			return;
		
		DefaultControlPanel defaultControlPanel = (DefaultControlPanel) m_session.getActiveDisplaySession().getDisplayPanel().getControlPanel();
		
		JTextField referenceTextField = defaultControlPanel.getReferenceTextField();
		
		if(m_session.getActiveGraphSession() == null) {
			referenceTextField.setText("");
			return;
		}
		
		InvenioElementSet set = m_session.getActiveGraphSession().getGraphSelection().getReferenceSelection();
		if(set == null)
			referenceTextField.setText("");
		else
			referenceTextField.setText(set.getName());				
	}
	
	public void updateDisplayTable() {
//		
//		System.out.println("Updating Display Table...");
//		
//		if(m_session.getActiveGraphSession() == null) {
//			m_frame.m_displayTable = null;
//			System.out.println("Nevermind... there's no active graph session...");
//			return;
//		}
//		
//		if(m_frame.m_displayTable != null) m_frame.remove(m_frame.m_displayTable);
//		m_frame.m_displayTable = m_session.getActiveGraphSession().getPrefuseTableComponent();
//		m_frame.m_displayTableScrollPane = new JScrollPane(m_frame.m_displayTable);
//		
//		if(m_session.getActiveGraphSession() == null) System.out.println("W");
//		if(m_frame.m_displayTable == null) System.out.println("S");
//		m_frame.m_displayTable.setBorder(BorderFactory.createEtchedBorder());
//		
//		System.out.println("WTF... adding prefuse table?!?!?!");
//		m_frame.add(m_frame.m_displayTableScrollPane);
	}

	
	public void updateVisualHistory() {
		
		m_frame.visualRulesListModel.clear();
		if(m_session.getActiveGraphSession() == null) return;

		ArrayList<VisualRuleSet> history = m_session.getActiveVisualGraphSession().getVisualHistory();
		for(int i = 0; i < history.size(); i++)
			m_frame.visualRulesListModel.addElement(history.get(i).getDescription());
	}
	
	public void updateAlgorithmHistory() {
		NavigationPane navPane = NavigationPane.getInstance();
		
		if(m_session.getActiveGraphSession() == null) return;
		ArrayList<AlgorithmResult> history = m_session.getActiveVisualGraphSession().getAlgorithmHistory();
		try{
			navPane.getAlgorithmList().setSelectionRow(0);
			navPane.getAlgorithmList().expandRow(0);
			DefaultMutableTreeNode n = (DefaultMutableTreeNode)navPane.getAlgorithmList().getNextMatch(m_session.getActiveGraphSession().getName().substring(0, 2), 0, Position.Bias.Forward).getLastPathComponent();//new DefaultMutableTreeNode();
			for(int i = 0; i < history.size(); i++)
				if( navPane.getAlgorithmListModel().getChildCount(n) < history.size())
					navPane.getAlgorithmListModel().insertNodeInto(new DefaultMutableTreeNode(history.get(i).getAlgorithm().getName()), n, n.getChildCount());
		}
		catch(Exception e){			
		}
		
	}
	
	/**
	 * @return the {@link MainFrame} that this class is controlling.
	 */
	public TestMainFrame getFrame() {
		return m_frame;
	}
	
	TestMainFrame m_frame;
	UserSession m_session;
	
	final String FILTER_FIELD_DEFAULT = "Enter a set constraint";
	final String ALL_SUPERNODES = "All Super Nodes";
	
	public void initialize() {
		m_session = UserSession.getInstance();
		m_session.addUserSessionListener(this);
		
		VisualGraphSession visGraphSession;
		Iterator iter = m_session.getVisualGraphSessions().iterator();
		while(iter.hasNext()) {
			visGraphSession = (VisualGraphSession)iter.next();
			visGraphSession.getGraphSession().addGraphSessionListener(this);
			visGraphSession.getGraphPainter().addGraphPainterListener(this);
		}
		
		m_frame.initialize();
	}
	
	//------------------- Singleton Methods -------------------
	
	private TestMainFrameController() {
		m_frame = new TestMainFrame();
	}
	
	public static TestMainFrameController getInstance() {
		if(m_instance == null) {
			m_instance = new TestMainFrameController();
			m_instance.initialize();
		}
		return m_instance;
	}
	
	private static TestMainFrameController m_instance;

	public DisplayPanel getGraphPanel() {
		return m_frame.getGraphPanel();
	}
	
}
