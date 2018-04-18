package invenio.workflow.swing;

import invenio.main.TestInvenio;
import invenio.main.TestMainFrameController;
import invenio.wf.BaseWorkflowItem;
import invenio.wf.ItemController;
import invenio.wf.Node;
import invenio.wf.WorkflowManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.DockingManager;
import com.jidesoft.icons.JideIconsFactory;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;


public class CustomActions {

	/**
	 * 
	 */
	//static final Action editAction = new EditAction("edit");

	
	
//	public static Action getEditAction() {
//		return editAction;
//	}


	/**
	 * 
	 * @param e
	 * @return Returns the graph for the given action event.
	 */
	public static final mxGraph getGraph(ActionEvent e)
	{
		Object source = e.getSource();

		if (source instanceof mxGraphComponent)
		{
			return ((mxGraphComponent) source).getGraph();
		}

		return null;
	}
	

	public static class ViewAction extends AbstractAction
	{
		private final DockingManager dock;

		/**
		 * 
		 */
		private static final long serialVersionUID = 4610112721356742702L;

		/**
		 * 
		 * @param name
		 */
		public ViewAction(String name, DockingManager dock)
		{
			super(name);
			this.dock = dock;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = getGraph(e);

			if (graph != null)
			{
				graph.getSelectionCell();
				try {
					TestInvenio t = new TestInvenio();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				DockableFrame f = createInvenioGraph();
				dock.addFrame(f);
				dock.showFrame(f.getKey());
				
				f = createInvenioPanel();
				dock.addFrame(f);
				dock.showFrame(f.getKey());
				
			}
		}
		
		protected static DockableFrame createDockableFrame(String key, Icon icon) {
	        DockableFrame frame = new DockableFrame(key, icon);
	        frame.setPreferredSize(new Dimension(400, 200));
	        return frame;
	    }
		
		protected static DockableFrame createInvenioGraph() {
	        DockableFrame frame = createDockableFrame("Graph", JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME8));
	        frame.getContext().setInitMode(DockContext.STATE_FLOATING);
	        frame.getContext().setInitSide(DockContext.DOCK_SIDE_SOUTH);
	        frame.getContext().setInitIndex(0);
	        frame.setLayout(new BorderLayout(2, 2));
	        
	        frame.add(TestMainFrameController.getInstance().getGraphPanel());
	        return frame;
	    }
		
		private DockableFrame createInvenioPanel() {
			DockableFrame frame = createDockableFrame("InvenioPanel", JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME8));
	        frame.getContext().setInitMode(DockContext.STATE_FRAMEDOCKED);
	        frame.getContext().setInitSide(DockContext.DOCK_SIDE_EAST);
	        frame.getContext().setInitIndex(0);
	        frame.setLayout(new BorderLayout(2, 2));
	        
	        frame.add(TestMainFrameController.getInstance().getGraphPanel().getControlPanel());
	        return frame;
		}

	}
	
	public static class EditAction extends AbstractAction
	{
		private final DockingManager dock;

		/**
		 * 
		 */
		private static final long serialVersionUID = 4610112721356742702L;
		
		private static final String[] types = {"Oracle", "MySQL", "PostgreSQL"};
		private static final String TEST_LOCATION = "jdbc:oracle:thin:@lilac.cs.georgetown.edu:1521:research";
		private static final String TEST_USER = "pir";
		private static final String TEST_PASS = "proteins";
		
		/**
		 * 
		 * @param name
		 */
		public EditAction(String name, DockingManager dock)
		{
			super(name);
			this.dock = dock;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = getGraph(e);

			if (graph != null)
			{
				mxCell cell = (mxCell) graph.getSelectionCell();
				String key = WorkflowUtils.getItem(cell).getKey();
				
				DockableFrame f = dock.getFrame( key );
				
				if ( f != null )
					dock.showFrame(key);
				else {
					f = createImportDialog( key );
					dock.addFrame(f);
					dock.showFrame( key );
				}
			}
		}
		
		protected static DockableFrame createDockableFrame(String key, Icon icon) {
	        DockableFrame frame = new DockableFrame(key, icon);
	        frame.setPreferredSize(new Dimension(400, 200));
	        return frame;
	    }
		
		protected static DockableFrame createImportDialog(String key) {
	        DockableFrame frame = createDockableFrame(key, JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME8));
	        frame.getContext().setInitMode(DockContext.STATE_FLOATING);
	        //frame.getContext().setInitSide(DockContext.DOCK_SIDE_SOUTH);
	        frame.getContext().setInitIndex(0);
	        frame.setLayout(new BorderLayout(2, 2));
	        
	        frame.add(createImportPanel());
	        return frame;
	    }

		private static Component createImportPanel() {
			JPanel importPanel = new JPanel();
			importPanel.setLayout(null);
			
			JLabel titleLabel = new JLabel("Database Connection");
			titleLabel.setBounds(20, 20, 200, 20);
			importPanel.add(titleLabel);
			
			JLabel typeLabel = new JLabel("Type of database:");
			typeLabel.setBounds(20, 60, 150, 20);
			importPanel.add(typeLabel);
			
			JComboBox typeComboBox = new JComboBox(types);
			typeComboBox.setBounds(130, 60, 130, 20);
			typeComboBox.setBackground(Color.WHITE);
			importPanel.add(typeComboBox);
			
			JLabel serverLabel = new JLabel("Server location: ");
			serverLabel.setBounds(20, 90, 150, 20);
			JTextField serverTextField = new JTextField(TEST_LOCATION);
			serverTextField.setBounds(130, 90, 130, 20);
			importPanel.add(serverLabel);
			importPanel.add(serverTextField);
			
			JLabel userLabel = new JLabel("User name:");
			userLabel.setBounds(20, 120, 100, 20);
			JTextField userTextField = new JTextField(TEST_USER);
			userTextField.setBounds(130, 120, 130, 20);
			importPanel.add(userLabel);
			importPanel.add(userTextField);
			
			JLabel passLabel = new JLabel("Password:");
			passLabel.setBounds(20, 150, 100, 20);
			JPasswordField passTextField = new JPasswordField(TEST_PASS);
			passTextField.setBounds(130, 150, 130, 20);
			//passTextField.addActionListener(this);
			importPanel.add(passLabel);
			importPanel.add(passTextField);
			
			JButton connectButton = new JButton("OK");
			connectButton.setBounds(25, 180, 100, 25);
			//connectButton.addActionListener(this);
			importPanel.add(connectButton);
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(145, 180, 100, 25);
			//cancelButton.addActionListener(this);
			importPanel.add(cancelButton);
			
			return importPanel;
		}
		
	}
	
	public static class EditorAction extends AbstractAction
	{
		private final DockingManager dock;

		
		/**
		 * 
		 * @param name
		 */
		public EditorAction(String name, DockingManager dock)
		{
			super(name);
			this.dock = dock;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = getGraph(e);

			if (graph != null)
			{
				mxCell cell = (mxCell) graph.getSelectionCell();
				if ( cell == null )
					return;
				BaseWorkflowItem item = WorkflowUtils.getItem(cell);
				String key = item.getConfigKey();
				
				DockableFrame f = dock.getFrame( key );
				
				if ( f != null )
					dock.showFrame(key);
				else {
					ItemController contr = item.getController();
					if ( contr.hasConfigView() ) {
						f = createImportDialog( key, contr.getConfigView() );
						dock.addFrame(f);
						dock.showFrame( key );
					}
				}
			}
		}
		
		protected static DockableFrame createDockableFrame(String key, Icon icon) {
	        DockableFrame frame = new DockableFrame(key, icon);
	        frame.setPreferredSize(new Dimension(400, 200));
	        return frame;
	    }
		
		protected static DockableFrame createImportDialog(String key, JComponent comp) {
	        DockableFrame frame = createDockableFrame(key, JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME8));
	        frame.getContext().setInitMode(DockContext.STATE_FLOATING);
	        //frame.getContext().setInitSide(DockContext.DOCK_SIDE_SOUTH);
	        frame.getContext().setInitIndex(0);
	        frame.setLayout(new BorderLayout(2, 2));
	        
	        frame.add( comp );
	        return frame;
	    }		
	}
	
	
	public static class ResultAction extends AbstractAction
	{
		private final DockingManager dock;

		
		/**
		 * 
		 * @param name
		 */
		public ResultAction(String name, DockingManager dock)
		{
			super(name);
			this.dock = dock;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = getGraph(e);

			if (graph != null)
			{
				mxCell cell = (mxCell) graph.getSelectionCell();
				if ( cell == null )
					return;
				BaseWorkflowItem item = WorkflowUtils.getItem(cell);
				String key = item.getResultKey();
				
				DockableFrame f = dock.getFrame( key );
				
				if ( f != null )
					dock.showFrame(key);
				else {
					ItemController contr = item.getController();
					if ( contr.hasResultView() ) {
						f = createResultDialog( key, contr.getResultView() );
						dock.addFrame(f);
						dock.showFrame( key );
					}
				}
			}
		}
		
		protected static DockableFrame createDockableFrame(String key, Icon icon) {
	        DockableFrame frame = new DockableFrame(key, icon);
	        frame.setPreferredSize(new Dimension(400, 200));
	        return frame;
	    }
		
		protected static DockableFrame createResultDialog(String key, JComponent comp) {
	        DockableFrame frame = createDockableFrame(key, JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME8));
	        frame.getContext().setInitMode(DockContext.STATE_FLOATING);
	        //frame.getContext().setInitSide(DockContext.DOCK_SIDE_SOUTH);
	        frame.getContext().setInitIndex(0);
	        frame.setLayout(new BorderLayout(2, 2));
	        
	        frame.add( comp );
	        return frame;
	    }		
	}
	
	
	public static class ExecToThisAction extends AbstractAction
	{
		private final DockingManager dock;

		
		/**
		 * 
		 * @param name
		 */
		public ExecToThisAction(String name, DockingManager dock)
		{
			super(name);
			this.dock = dock;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = getGraph(e);

			if (graph != null)
			{
				mxCell cell = (mxCell) graph.getSelectionCell();
				if ( cell == null )
					return;
				Node node = WorkflowUtils.getNode(cell);
				
				if ( node == null)
					return;
				
				WorkflowManager manager = WorkflowManager.getInstance();
				manager.executeWorkflow(node, false);
			}
		}
	}
}
