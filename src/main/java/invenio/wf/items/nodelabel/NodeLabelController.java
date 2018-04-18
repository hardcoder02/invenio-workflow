package invenio.wf.items.nodelabel;

import invenio.data.InvenioGraph;
import invenio.io.file.GraphReader;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.log.Logger;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.dd.invenio.gia.NodeLabelExperiment;

public class NodeLabelController extends AbstractController<InvenioGraph>
		implements ItemController<InvenioGraph>, ActionListener {
	
	private static Logger log = Logger.getInstance();
	
	private NodeLabelConfigView configView;
	
	private JFileChooser fileChooser;
	private File lastOpenFile =
			new File("C:/Workspace/Invenio/JIDE_graphx/resource/SampleFiles/NodeLabelExperiment-cora/experiment.cfg");
	
	public NodeLabelController() {
		initContoller();
	}
	
	protected void initContoller() {
		configView = new NodeLabelConfigView();
		configView.setController(this);
	}

	@Override
	public boolean hasConfigView() {
		return true;
	}

	@Override
	public boolean hasResultView() {
		return false;
	}

	@Override
	public JComponent getConfigView() {
		return configView;
	}

	@Override
	public JComponent getResultView() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if ( evt.getSource() == configView.getOpenButton() ) {
			processOpenButton();
		}
	}

	private void processOpenButton() {
		File f = displayFileChooser();
		if ( f != null)
			loadParams(f);
	}
	
	protected void loadParams(File f) {
		try {
			String params = readFile(f);
			configView.getParamTextArea().setText( params );
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog( configView,
					"Error reading param file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private File displayFileChooser() {
		//Create a file chooser
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastOpenFile);

		//In response to a button click:
	    int returnVal = fileChooser.showOpenDialog( configView );
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        lastOpenFile = fileChooser.getSelectedFile();

	        return lastOpenFile;
	    } else {
	        return null;
	    }
	}
	
	private String readFile( File f ) throws IOException {
		
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader( f ));
			StringBuffer sb = new StringBuffer();
			String s = new String();
			while ( (s = r.readLine()) != null )
				sb.append(s).append("\n");
			return sb.toString();
		}
		finally {
			try {
				if (r != null)
					r.close();
			}
			catch (IOException ex) {}
		}
	}
	

	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		// TODO: use actual contents of paramTextArea instead of file
		if (inputs.length < 1)
			return null;
		
		try{
			log.log(this, "Running node labeling algorithm...");
			NodeLabelExperiment e = new NodeLabelExperiment();
			e.setInputGraph((InvenioGraph)inputs[0]);
			e.setParametersFile( lastOpenFile.getAbsolutePath() );
			e.runExperiment();
			log.log(this, "Node labeling completed");
			// TODO: fix
			return e.getOutputGraph();
		}
		catch(Exception e){
			e.printStackTrace();
			log.log( this, e.getMessage() );
			return null;
		}
	}

	

}
