package invenio.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MenuBar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import invenio.GraphSession;
import invenio.data.InvenioGraph;
import invenio.io.file.SparseGraphFileReader;
import invenio.ui.ButtonToolBar;
import invenio.ui.DisplayPanel;
import invenio.ui.MainMenuBar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;


public class TestInvenio {
	GraphSession graph;
	
	public static Rengine RE; //R-Engine execution engine used for invoking R commands
	
	public TestInvenio() throws Exception {

		InvenioGraph invGraph = null;

		try {
			//invGraph = readFromTextFile();
			invGraph = readFromSparseGraphFile();
			//invGraph = readFromPajekFile();
			//invGraph = readFromDB();
			//invGraph = readFromGIA();
			//invGraph = readFromTabDelimFile();

			if( invGraph != null){
				//testRead(invGraph);
				TestUserSession session = TestUserSession.getInstance("Session1");
				GraphSession graph = new GraphSession(invGraph);
				session.addAndActivateGraphSessionInNewDisplay(graph);
				TestMainFrameController.getInstance().updateGraphSetsTree();
			}
		}

		catch(Exception e) {
			System.err.println("Ahh crap: " + e.getMessage());
			e.printStackTrace();
		}


	}
	
	/**
	 * Creates an {@link InvenioGraph} by reading in data from a
	 * SparseGraphFile, using the {@link SparseGraphFileReader} class.
	 *
	 * @return the {@link InvenioGraph} that was created.
	 *
	 * @see SparseGraphFileReader
	 */
	public InvenioGraph readFromSparseGraphFile(){
		try{
			InvenioGraph invGraph = new InvenioGraph();
	
			//SparseGraphFileReader reader = new SparseGraphFileReader("wiki-Vote.sgf");//"synthethic-combine-cluster.sgf");//"dawsons_creek.sgf");//"matrilines3.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("colored.sgf");
			SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_1990.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("sample_results/researcher_trainer='OTHER'-s1.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("testing_bimodal_small.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("nns_data/location=HB_no_res.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("colored.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_bimodal.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_bimodal_full.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("dolphins_bimodal.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("dolphins.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("synthetic-long.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("sw.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("ca-GrQc.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("p2p-Gnutella08.sgf");
			//SparseGraphFileReader reader = new SparseGraphFileReader("wiki-Vote.sgf");
			
			invGraph = reader.readGraph();
			reader.close();
			return invGraph;
		}
		catch(Exception e){
			e.printStackTrace();	
			return null;
		}
	}
	
	public static void init() {
		boolean igraphLoad = true, RLoad = true;
		try {
		            if (!Rengine.versionCheck()) {
		                System.err.println("** Version mismatch - Java files don't match library version");
		            } else {
		                RE =new Rengine(null, false, new TextConsole());
		                System.out.println("Rengine created, waiting for R");

		                // the engine creates R is a new thread, so we should wait until it's ready
		                if (!RE.waitForR()) {
		                    System.out.println("Cannot load R");
		                    RLoad = false;
		                }

		                //Load the igraph library
		                igraphLoad = RE.eval("library(igraph, lib.loc='library')") != null;
		                
		            }

//		            if (!RLoad) JOptionPane.showMessageDialog(TestMainFrameController.getInstance().getGraphPanel(), "Oops! Cannot load R.\n Sampling tasks will not work properly.");
//		            if (!igraphLoad) JOptionPane.showMessageDialog(TestMainFrameController.getInstance().getGraphPanel(), "Oops! iGraph library could not be loaded.\n Sampling tasks will not work properly.");

		            
		        }catch(Exception e){
	                System.err.println("Error: ");
	                e.printStackTrace();
		        }
	}
	
	public static void main(String args[]) throws Exception {
		init();
        new TestInvenio();
        
        
        DisplayPanel displayPanel = TestMainFrameController.getInstance().getGraphPanel();
        
        JFrame frame = new JFrame("Invenio");

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			System.err.println("Error: Failed to set look & feel");
		}

   		frame.setSize(1200, 900);
//   		frame.addWindowListener(this);

//    		m_displayTable = null;
//
//    		visualRulesListModel = new DefaultListModel();
//    		visualRulesList = new JList(visualRulesListModel);
//    		visualRulesScrollPane = new JScrollPane(visualRulesList);
//    		visualRulesScrollPane.setBorder(null);

    	ButtonToolBar toolbar = new ButtonToolBar();
    		
    	int insetTop = 2;
    	int insetBottom = 20;
    	int insetLeft = 10;
    	int insetRight = 10;
    		
    	BorderLayout mainFrameLayout = new BorderLayout();
    	frame.getContentPane().setLayout(mainFrameLayout);
    	((JComponent)frame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(insetTop, insetLeft-2, insetBottom, insetRight));

    	frame.getContentPane().add(toolbar, BorderLayout.NORTH);
    		
    	ArrayList<DisplayPanel> displayPanels = new ArrayList<DisplayPanel>();
    	displayPanels.add(displayPanel);
    		
    	JSplitPane displaysSidebarSplit = new JSplitPane();
    	displaysSidebarSplit.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
    		displaysSidebarSplit.setLeftComponent(displayPanels.get(0));
    		
    	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
    	((JComponent)displaysSidebarSplit.getLeftComponent()).setBorder(lineBorder);
		displaysSidebarSplit.setRightComponent(displayPanels.get(0).getControlPanel());
    	displaysSidebarSplit.setDividerLocation(frame.getWidth() - 307 - insetLeft - insetRight);
		displaysSidebarSplit.setContinuousLayout(true);
		displaysSidebarSplit.setOneTouchExpandable(true);
		displaysSidebarSplit.setDividerSize(14);
		displaysSidebarSplit.setResizeWeight(1);
    		
    	frame.getContentPane().add(displaysSidebarSplit);
    		
    	frame.setVisible(true);
//    	MainMenuBar topBar = new MainMenuBar(frame);
//    	frame.setJMenuBar(topBar);
//    	topBar.setVisible(true);
    		
	}
	
}

class TextConsole implements  RMainLoopCallbacks
{
    public void rWriteConsole(Rengine re, String text, int oType) {
        System.out.print(text);
    }

    public void rBusy(Rengine re, int which) {
        System.out.println("rBusy("+which+")");
    }

    public String rReadConsole(Rengine re, String prompt, int addToHistory) {
        System.out.print(prompt);
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String s=br.readLine();
            return (s==null||s.length()==0)?s:s+"\n";
        } catch (Exception e) {
            System.out.println("jriReadConsole exception: "+e.getMessage());
        }
        return null;
    }

    public void rShowMessage(Rengine re, String message) {
        System.out.println("rShowMessage \""+message+"\"");
    }

    public String rChooseFile(Rengine re, int newFile) {
		return null;
    }

    public void   rFlushConsole (Rengine re) {
    }

	public void rLoadHistory  (Rengine re, String filename) {
    }

    public void   rSaveHistory  (Rengine re, String filename) {
    }
}
