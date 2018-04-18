package invenio.visual;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import prefuse.Display;
import prefuse.Visualization;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;

public class DisplayShape extends JPanel {
	
	private Display m_display;
	
	private JPanel menuPanel;
	private JLabel layoutLabel;
	private JComboBox layoutComboBox;
	
//	protected GraphPainter m_graphPainter;
	
	protected VisualGraphSession controller;
	
	public DisplayShape() {
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());

		add( getMenuPanel(), BorderLayout.NORTH );
	}
	
	public Display getDisplay(){
		return m_display;
	}
	
	public void setController(VisualGraphSession visualGraphSession) {
		if (controller != null) {
			getLayoutComboBox().removeActionListener(controller);
		}
		this.controller = visualGraphSession;
		if (controller != null) {
			getLayoutComboBox().addActionListener(controller);
		}
		updateDisplay();
	}
	
	public void updateDisplay() {
		if (m_display != null) {
			remove( m_display);
		}
		
		if (controller != null && controller.getVisualization() != null) {
			Visualization vis = controller.getVisualization();

			m_display = new Display(vis);
			m_display.setSize(720, 500); // set display size
	        // drag individual items around
			m_display.addControlListener(new DragControl());
	        // pan with left-click drag on background
			m_display.addControlListener(new PanControl()); 
	        // zoom with right-click drag
			m_display.addControlListener(new ZoomControl());
	
			add(m_display, BorderLayout.CENTER);
		}
	}
	
	public JPanel getMenuPanel() {
		if (menuPanel == null) {
			menuPanel = new JPanel();
			menuPanel.add(getLayoutLabel());
			menuPanel.add(getLayoutComboBox());
		}
		return menuPanel;
	}
	
	public JComboBox getLayoutComboBox() {
		if ( layoutComboBox == null ) {
			layoutComboBox = new JComboBox(DefaultLayoutManager.LAYOUT_VALUES);
			layoutComboBox.setEditable(false);
			layoutComboBox.setActionCommand("UPDATE_LAYOUT");
		}
		return layoutComboBox;
	}

	public JLabel getLayoutLabel() {
		if (layoutLabel == null) {
			layoutLabel = new JLabel("Layout:");
		}
		return layoutLabel;
	}
	
//	/**
//	 * @return the {@link GraphPainter} used to control all visual features
//	 * and rules pertaining to the drawing of the graph.
//	 */
//	public GraphPainter getGraphPainter() {
//		return m_graphPainter;
//	}
//	
//	public void setGraphPainter(GraphPainter painter) {
//		this.m_graphPainter = painter;
//	}
	
	
	
	
//	public void addVisualGraphSession(VisualGraphSession visSession){
//		m_visualGraphSessions.add(visSession);
//		m_displayPanel.updateTitle();
//		if(getDisplay().getPredicate().equals(BooleanLiteral.TRUE))
//			getDisplay().setPredicate(visSession.getVisualGraphPredicate());
//		else
//			getDisplay().setPredicate(new OrPredicate(visSession.getVisualGraphPredicate(), getDisplay().getPredicate()));
//		if(visSession.getGraphPainter().isAnimated())
//			visSession.getGraphPainter().runAnimation();
//	}
//	
//	public void removeVisualGraphSession(VisualGraphSession visSession){
//		m_visualGraphSessions.remove(visSession);
//		m_displayPanel.updateTitle();
//		if(m_visualGraphSessions.size() == 0){
//			getDisplay().setPredicate(BooleanLiteral.FALSE);
//			return;
//		}
//		Predicate p = m_visualGraphSessions.get(0).getVisualGraphPredicate();
//		for(int i=1; i<m_visualGraphSessions.size(); i++){
//			p = new OrPredicate(p, m_visualGraphSessions.get(i).getVisualGraphPredicate());
//		}
//		getDisplay().setPredicate(p);
////		if(!(UserSession.getInstance().getActiveDisplaySession() == this))
//			visSession.getGraphPainter().pauseAnimation();
//	}
//	

	
}
