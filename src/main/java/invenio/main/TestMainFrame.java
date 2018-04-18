package invenio.main;


import invenio.Constants;
import invenio.UserSession;
import invenio.control.ControlManager;
import invenio.data.bridge.BridgeTuple;
import invenio.data.bridge.BridgeVisualItem;
import invenio.ui.ButtonToolBar;
import invenio.ui.DisplayPanel;
import invenio.ui.MainMenuBar;
import invenio.ui.OptionsPanel;
import invenio.ui.controller.MainFrameController;
import invenio.visual.DefaultVisualRuleSetFactory;
import invenio.visual.VisualRuleSet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.util.ui.JPrefuseTable;
import prefuse.visual.NodeItem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Image;

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.text.*;

/**
 * The MainFrame class contains and controls listeners for the majority of
 * the functionality that is present in the user interface for Invenio.
 * It creates the window frame for the program and creates a top row of
 * buttons, and a sidebar of navigational, informational, etc functionality.
 * It adds a {@link MainMenuBar} and most importantly the {@link Display} 
 * in which all the visualization is done.
 * 
 */
public class TestMainFrame implements MouseListener, ActionListener, WindowListener, ComponentListener {

	final String FILTER_FIELD_DEFAULT = "Enter a set constraint";
	//private final String ALL_SETS = "All Sets";
	
	private ButtonToolBar toolbar;
	private BorderLayout mainFrameLayout;
	private JSplitPane displaysSidebarSplit;
	private ArrayList<DisplayPanel> displayPanels;

	private JSplitPane twoPaneSplit;
	private JSplitPane leftPaneSplit;
	private JSplitPane rightPaneSplit;
	private DisplayPanel twoPaneSplit_left;
	private DisplayPanel twoPaneSplit_right;
	private DisplayPanel leftPaneSplit_top;
	private DisplayPanel leftPaneSplit_bottom;
	private DisplayPanel rightPaneSplit_top;
	private DisplayPanel rightPaneSplit_bottom;

	private final Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
	private final Border emptyBorder = BorderFactory.createEmptyBorder();
	
	private int insetTop, insetBottom, insetRight, insetLeft;

	public TestMainFrame() {
//		super("Invenio");
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		}
//		catch(Exception e) {
//			System.err.println("Error: Failed to set look & feel");
//		}
//
////		setExtendedState(MAXIMIZED_BOTH);
//		setSize(1024, 700);
//		
//
//		this.addWindowListener(this);

		m_displayTable = null;

		visualRulesListModel = new DefaultListModel();
		visualRulesList = new JList(visualRulesListModel);
		visualRulesScrollPane = new JScrollPane(visualRulesList);
		visualRulesScrollPane.setBorder(null);

		toolbar = new ButtonToolBar();
		
		insetTop = 2;
		insetBottom = 20;
		insetLeft = 10;
		insetRight = 10;
		
//		mainFrameLayout = new BorderLayout();
//		getContentPane().setLayout(mainFrameLayout);
//		((JComponent)getContentPane()).setBorder(BorderFactory.createEmptyBorder(insetTop, insetLeft-2, insetBottom, insetRight));
//
//		getContentPane().add(toolbar, BorderLayout.NORTH);
//		
		displayPanels = new ArrayList<DisplayPanel>();
		displayPanels.add(UserSession.getInstance().getActiveDisplaySession().getDisplayPanel());
		
//		displaysSidebarSplit = new JSplitPane();
//		displaysSidebarSplit.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
//		displaysSidebarSplit.setLeftComponent(displayPanels.get(0));
//		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
//		displaysSidebarSplit.setRightComponent(displayPanels.get(0).getControlPanel());
//		displaysSidebarSplit.setDividerLocation(this.getWidth() - 307 - insetLeft - insetRight);
//		displaysSidebarSplit.setContinuousLayout(true);
//		displaysSidebarSplit.setOneTouchExpandable(true);
//		displaysSidebarSplit.setDividerSize(14);
//		displaysSidebarSplit.setResizeWeight(1);
//		
//		getContentPane().add(displaysSidebarSplit);
//		
//		topBar = new MainMenuBar(this);
//		setJMenuBar(topBar);
//		topBar.setVisible(true);
		
	}
	
	
	
	public void updateActiveDisplay(){
		if(displaysSidebarSplit.getRightComponent() != UserSession.getInstance().getActiveDisplaySession().getControlPanel()){
			int dividerLocation = displaysSidebarSplit.getDividerLocation();
			displaysSidebarSplit.setRightComponent(UserSession.getInstance().getActiveDisplaySession().getControlPanel());
			displaysSidebarSplit.setDividerLocation(dividerLocation);
			UserSession.getInstance().getActiveDisplaySession().getControlPanel().validate();
		}
		
		ControlManager controls = UserSession.getInstance().getActiveDisplaySession().getControlManager();
		if(controls.getPanControl().isEnabled()){
			toolbar.getCursorButton().setSelected(true);
		}else if(controls.getUserSelectControl().isEnabled()){
			toolbar.getDragCursorButton().setSelected(true);
		}
	}
	
	public void addDisplay(DisplayPanel ds){
		if(ds == null){
			System.err.println("Cannot add null display");
			return;
		}
		twoPaneSplit = new JSplitPane();
		displayPanels.add(ds);
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(emptyBorder);
		switch(displayPanels.size()){
			case 2:
				twoPaneSplit.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
				twoPaneSplit.setContinuousLayout(true);
				twoPaneSplit.setResizeWeight(0.5);
				twoPaneSplit_left = (DisplayPanel) displaysSidebarSplit.getLeftComponent();
				twoPaneSplit.setLeftComponent(twoPaneSplit_left);
				twoPaneSplit_right = ds;
				twoPaneSplit.setRightComponent(twoPaneSplit_right);
				displaysSidebarSplit.setLeftComponent(twoPaneSplit);
//				displaysSidebarSplit.setRightComponent(twoPaneSplit_right.getControlPanel());
				break;
			case 3:
				leftPaneSplit = new JSplitPane();
				leftPaneSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
				leftPaneSplit.setContinuousLayout(true);
				leftPaneSplit.setResizeWeight(0.5);
				leftPaneSplit_top = twoPaneSplit_left;
				leftPaneSplit_bottom = twoPaneSplit_right;
				leftPaneSplit.setTopComponent(leftPaneSplit_top);
				leftPaneSplit.setBottomComponent(leftPaneSplit_bottom);
				twoPaneSplit_left = null;
				twoPaneSplit_right = ds;
				twoPaneSplit.setLeftComponent(leftPaneSplit);
				twoPaneSplit.setRightComponent(twoPaneSplit_right);
				displaysSidebarSplit.setLeftComponent(twoPaneSplit);
//				displaysSidebarSplit.setRightComponent(twoPaneSplit_right.getControlPanel());
				break;
			case 4:
				rightPaneSplit = new JSplitPane();
				rightPaneSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
				rightPaneSplit.setContinuousLayout(true);
				rightPaneSplit.setResizeWeight(0.5);
				if(twoPaneSplit_left != null){
					System.out.println("Adding on the left...");
					leftPaneSplit_top = twoPaneSplit_left;
					leftPaneSplit_bottom = rightPaneSplit_bottom;
					rightPaneSplit_bottom = rightPaneSplit_top;
					leftPaneSplit.setTopComponent(leftPaneSplit_top);
					leftPaneSplit.setBottomComponent(leftPaneSplit_bottom);
				}else{
					System.out.println("Adding on the right");
					rightPaneSplit_bottom = twoPaneSplit_right;
				}
				rightPaneSplit_top = ds;
				twoPaneSplit_left = null;
				twoPaneSplit_right = null;
				rightPaneSplit.setBottomComponent(rightPaneSplit_bottom);
				rightPaneSplit.setTopComponent(rightPaneSplit_top);
				twoPaneSplit.setLeftComponent(leftPaneSplit);
				twoPaneSplit.setRightComponent(rightPaneSplit);
				displaysSidebarSplit.setLeftComponent(twoPaneSplit);
//				displaysSidebarSplit.setRightComponent(rightPaneSplit_top.getControlPanel());
				break;
			default:
				twoPaneSplit_left = null;
				twoPaneSplit_right = null;
				leftPaneSplit_top = null;
				leftPaneSplit_bottom = null;
				rightPaneSplit_top = null;
				rightPaneSplit_bottom = null;
				displaysSidebarSplit.setLeftComponent(ds);
				for(int i=0; i<displayPanels.size(); i++)
					UserSession.getInstance().removeDisplay(displayPanels.get(i).getDisplaySession());
				displayPanels.clear();
				displayPanels.add(ds);
//				displaysSidebarSplit.setRightComponent(dp.getControlPanel());
				break;
		}
		if(twoPaneSplit != null)	twoPaneSplit.setBorder(emptyBorder);
		if(leftPaneSplit != null)	leftPaneSplit.setBorder(emptyBorder);
		if(rightPaneSplit != null)	rightPaneSplit.setBorder(emptyBorder);
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
	}
	
	public void removeDisplay(DisplayPanel dp){
		int dividerLocation = displaysSidebarSplit.getDividerLocation();
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(emptyBorder);
		switch(displayPanels.size()){
			case 1:
				((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
				return;
			case 2:
				if(dp == twoPaneSplit_left){
					displaysSidebarSplit.setLeftComponent(twoPaneSplit_right);
//					displaysSidebarSplit.setRightComponent(twoPaneSplit_right.getControlPanel());
					twoPaneSplit_left = null;
					twoPaneSplit_right = null;
				}else if(dp == twoPaneSplit_right){
					displaysSidebarSplit.setLeftComponent(twoPaneSplit_left);
//					displaysSidebarSplit.setRightComponent(twoPaneSplit_left.getControlPanel());
					twoPaneSplit_left = null;
					twoPaneSplit_right = null;
				}
				break;
			case 3:
				if(dp == twoPaneSplit_right){
					twoPaneSplit_right = leftPaneSplit_bottom;
					twoPaneSplit_left = leftPaneSplit_top;
					leftPaneSplit_top = null;
					leftPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
					displaysSidebarSplit.setLeftComponent(twoPaneSplit);
				}
				else if(dp == twoPaneSplit_left){
					twoPaneSplit_left = rightPaneSplit_bottom;
					twoPaneSplit_right = rightPaneSplit_top;
					rightPaneSplit_top = null;
					rightPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
					displaysSidebarSplit.setLeftComponent(twoPaneSplit);
				}
				else if(dp == leftPaneSplit_top){
					twoPaneSplit_left = leftPaneSplit_bottom;
					leftPaneSplit_top = null;
					leftPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
				}
				else if(dp == leftPaneSplit_bottom){
					twoPaneSplit_left = leftPaneSplit_top;
					leftPaneSplit_top = null;
					leftPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
				}
				else if(dp == rightPaneSplit_top){
					twoPaneSplit_right = rightPaneSplit_bottom;
					rightPaneSplit_top = null;
					rightPaneSplit_bottom = null;
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
				}
				else if(dp == rightPaneSplit_bottom){
					twoPaneSplit_right = rightPaneSplit_top;
					rightPaneSplit_top = null;
					rightPaneSplit_bottom = null;
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
				}
				break;
			case 4:
				if(dp == leftPaneSplit_top){
					twoPaneSplit_left = leftPaneSplit_bottom;
					leftPaneSplit_top = null;
					leftPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
				}
				else if(dp == leftPaneSplit_bottom){
					twoPaneSplit_left = leftPaneSplit_top;
					leftPaneSplit_top = null;
					leftPaneSplit_bottom = null;
					twoPaneSplit.setLeftComponent(twoPaneSplit_left);
				}
				else if(dp == rightPaneSplit_top){
					twoPaneSplit_right = rightPaneSplit_bottom;
					rightPaneSplit_top = null;
					rightPaneSplit_bottom = null;
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
				}
				else if(dp == rightPaneSplit_bottom){
					twoPaneSplit_right = rightPaneSplit_top;
					rightPaneSplit_top = null;
					rightPaneSplit_bottom = null;
					twoPaneSplit.setRightComponent(twoPaneSplit_right);
				}
				break;
			default:
				break;
		}
		displayPanels.remove(dp);
		displaysSidebarSplit.setDividerLocation(dividerLocation);
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
		UserSession.getInstance().removeDisplay(dp.getDisplaySession());
	}
	
	public void swapDisplays(DisplayPanel oldDisplay, DisplayPanel newDisplay){
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(emptyBorder);
		
		if(displaysSidebarSplit.getLeftComponent() == oldDisplay){
			displaysSidebarSplit.setLeftComponent(newDisplay);
		}else if(twoPaneSplit_left == oldDisplay){
			twoPaneSplit_left = newDisplay;
			twoPaneSplit.setLeftComponent(newDisplay);
		}else if(twoPaneSplit_right == oldDisplay){
			twoPaneSplit_right = newDisplay;
			twoPaneSplit.setRightComponent(newDisplay);
		}else if(leftPaneSplit_top == oldDisplay){
			leftPaneSplit_top = newDisplay;
			leftPaneSplit.setTopComponent(newDisplay);
		}else if(leftPaneSplit_bottom == oldDisplay){
			leftPaneSplit_bottom = newDisplay;
			leftPaneSplit.setBottomComponent(newDisplay);
		}else if(rightPaneSplit_top == oldDisplay){
			rightPaneSplit_top = newDisplay;
			rightPaneSplit.setTopComponent(newDisplay);
		}else if(rightPaneSplit_bottom == oldDisplay){
			rightPaneSplit_bottom = newDisplay;
			rightPaneSplit.setBottomComponent(newDisplay);
		}
		displayPanels.remove(oldDisplay);
		displayPanels.add(newDisplay);
		((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
	}

	public ButtonToolBar getToolbar() {
		return toolbar;
	}

	public void mouseClicked(MouseEvent e){

		Point p = new Point(e.getX(), e.getY());
		
		if(e.getSource() == dataTable){
			int col = dataTable.columnAtPoint(p);
			int row = dataTable.rowAtPoint(p);

			String selectedNodeKey = dataTable.getValueAt(row, col).toString();
			String colName = dataTable.getColumnName(col);

			Visualization vis = UserSession.getInstance().getVisualization();
			Iterator iter = vis.visibleItems();
			Object o;
			int flags = 0;

			while((iter.hasNext())&&(flags <= 2)) {
				o = iter.next();
				if(o instanceof NodeItem) {
					if(((BridgeTuple)vis.getSourceTuple((NodeItem)o)).get(colName).toString() == lastSelectedNodeKey){
						((BridgeVisualItem)o).setSize(((BridgeVisualItem)o).getSize() - 2);
						flags++;
					}

					if(((BridgeTuple)vis.getSourceTuple((NodeItem)o)).get(colName).toString() == selectedNodeKey){
						((BridgeVisualItem)o).setSize(((BridgeVisualItem)o).getSize() + 2);
						flags++;
					}
				}
			}
			lastSelectedNodeKey = selectedNodeKey;
			UserSession.getInstance().getActiveDisplaySession().getDisplay().repaint();
		}
	}


	public void initialize() {

//		addComponentListener(this);
//		setVisible(true);
	}
	
	private int controlPanelWidth;
	
	public void componentResized(ComponentEvent e) {
////		resizeComponents();
////		mainFrameLayout.invalidateLayout(sidebarSplit);
//		controlPanelWidth = displaysSidebarSplit.getWidth() - displaysSidebarSplit.getDividerLocation();
//	//	getContentPane().setSize(getRootPane().getSize());
//	//	displaysSidebarSplit.setSize(getRootPane().getSize());
//		Insets insets = getInsets();
//		int height = getHeight() - insets.bottom - insets.top - 100 - insetTop - insetBottom;
//		int width = getWidth() - insets.left - insets.right - insetLeft - insetRight + 6;
//		displaysSidebarSplit.setSize(new Dimension(width, height));
//		displaysSidebarSplit.setDividerLocation(displaysSidebarSplit.getWidth() - controlPanelWidth);
////		sidebarSplit.setSize(new Dimension(getWidth() - 4, 
////										   getHeight() - 34 - toolbar.getHeight()));
	}

	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		MainFrameController.getInstance().shutdownInvenio();
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		UserSession session = UserSession.getInstance();
		if(source == btnMoreButtons){
			if(toolBoxPane.isVisible() == false){
				toolBoxPane.setVisible(true);
				toolBoxPane.setExpanded(true);
			}
			else{
				toolBoxPane.setVisible(false);
				toolBoxPane.setExpanded(false);
			}
		}
		else if(source == defaultVisualRulesComboBox) {
			VisualRuleSet defaults;
			if(defaultVisualRulesComboBox.getSelectedItem().equals(DefaultVisualRuleSetFactory.getRuleSetDescription(DefaultVisualRuleSetFactory.HOP_TRIE)))
				defaults = DefaultVisualRuleSetFactory.createDefaultRuleSet(DefaultVisualRuleSetFactory.HOP_TRIE, session.getActiveGraph());
			else if(defaultVisualRulesComboBox.getSelectedItem().equals(DefaultVisualRuleSetFactory.getRuleSetDescription(DefaultVisualRuleSetFactory.RELATION_COLORING)))
				defaults = DefaultVisualRuleSetFactory.createDefaultRuleSet(DefaultVisualRuleSetFactory.RELATION_COLORING, session.getActiveGraph());
			else
				defaults = DefaultVisualRuleSetFactory.createDefaultRuleSet(DefaultVisualRuleSetFactory.DEFAULT, session.getActiveGraph());
			session.getActiveVisualGraphSession().getGraphPainter().setDefaultRules(defaults.getRules());
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public JPrefuseTable m_displayTable;
	public JScrollPane m_displayTableScrollPane;
	JComponent displaysPanel;
	JInternalFrame dispPanelFrame;

	MainMenuBar topBar;

	String lastSelectedNodeKey;//string for resizing node back to original size when a new node is selected from table

	//Standard Buttons
//	JButton btnNewDataSource, btnSave, btnNewAlgorithm, btnMoreButtons, btnAddToPersonalToolBar;
	JButton btnMoreButtons, btnAddToPersonalToolBar;
	
	//Non-standard Buttons
	JButton newGraphButton, newSetButton, newSuperNodeButton;

	boolean newGraphButtonDisplay, newSetButtonDisplay, newSuperNodeButtonDisplay;
	boolean colorButtonDisplay, sizeButtonDisplay, shapeButtonDisplay, labelButtonDisplay;
	boolean visibilityButtonDisplay, cursorButtonDisplay, dragCursorButtonDisplay;
	boolean animationButtonDisplay, animationStepButtonDisplay;
	boolean notSetsToggleDisplay, unionSetsToggleDisplay, intersectSetsToggleDisplay, collapseSuperNodeToggleDisplay;
	boolean clearReferenceButtonDisplay, clearFilterButtonDisplay;
	boolean layoutComboBoxDisplay;

	JButton clearReferenceButton;//, clearFilterButton;

	JPanel additionalButtonsPanel;

	JList listToolBarButtons;
	JButton btnSaveToolBar;
	JDialog toolBarListDlg;

	JDialog popupDialogForGraph;

	JPanel secondaryDisplayPanel;//Pane for displaying secondary graph in popup window


	JList visualRulesList;
	public DefaultListModel visualRulesListModel;

	JScrollPane visualRulesScrollPane;
	JTabbedPane historyTabbedPane;
	JButton historyDeleteButton;

	JComboBox defaultVisualRulesComboBox;

	OptionsPanel toolBoxPane;
	JSplitPane infoSplitPane;

	String[] defaultVisualRules = {"Model T", "Set Coloring", "Hop Expansion Trie Coloring"};

	JTable dataTable;
	

	// TabbedPaneUI with custom border
	public class BTabbedPaneUI extends javax.swing.plaf.basic.BasicTabbedPaneUI {
		protected Insets getContentBorderInsets(int tabPlacement) {
			return new Insets(0,0,0,0);
		}

		protected void paintContentBorder(Graphics g,
											int tabPlacement,
											int selectedIndex) {

			Image img = Toolkit.getDefaultToolkit().getImage(Constants.IMAGE_PREFIX + "border1.jpg");
			g.drawImage(img, 0, 0, 260, 150, 0, 0, 261, 151, null);
		}
	}

	public class BSplitPaneUI extends javax.swing.plaf.basic.BasicSplitPaneUI {
		protected Insets getContentBorderInsets(int tabPlacement) {
			return new Insets(0,0,0,0);
		}

		protected void paintContentBorder(Graphics g,
											int tabPlacement,
											int selectedIndex) {
		}
	}


	public static class PlotFormat
	{
	  final static DecimalFormat DF1 = new DecimalFormat ("0.0");
	  final static DecimalFormat DF2 = new DecimalFormat ("0.00");
	  final static DecimalFormat DF3 = new DecimalFormat ("0.000");
	  final static DecimalFormat DF4 = new DecimalFormat ("0.0000");

	  final static DecimalFormat SF1 = new DecimalFormat ("0.0E0");
	  final static DecimalFormat SF2 = new DecimalFormat ("0.00E0");
	  final static DecimalFormat SF3 = new DecimalFormat ("0.000E0");
	  final static DecimalFormat SF4 = new DecimalFormat ("0.0000E0");

	 /**
	  * The options include 1 to 3 decimal places. Values below
	  * decimalLimit use decimal notation; above this use scientific
	  * notation.
	  * @param input value
	  * @param upper limit before changing to scientific notation
	  * @param lower limit before changing to scientific notation
	  * @param number of decimal places in the output.
	  */
	  public static String getFormatted (double val,
	                                     double decimal_hi_limit,
	                                     double decimal_lo_limit,
	                                     int decimal_places) {
	  // If less than decimalLimit, or equal to zero, use decimal style
	    if (val == 0.0 ||  (Math.abs (val) <= decimal_hi_limit &&
	        Math.abs (val) > decimal_lo_limit)) {
	        switch  (decimal_places) {
	          case 1 : return DF1.format (val);
	          case 2 : return DF2.format (val);
	          case 3 : return DF3.format (val);
	          case 4 : return DF4.format (val);
	          default: return DF1.format (val);
	        }
	    } else {
	      // Create the format for Scientific Notation with E
	        switch  (decimal_places) {
	          case 1 : return SF1.format (val);
	          case 2 : return SF2.format (val);
	          case 3 : return SF3.format (val);
	          case 4 : return SF4.format (val);
	          default: return SF1.format (val);
	        }
	    }
	  } // getFormatted

	} // class PlotFormat


	public DisplayPanel getGraphPanel() {
		return displayPanels.get(0);
	}

}
