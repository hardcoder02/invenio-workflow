package invenio.wf.items.file;

import invenio.data.InvenioGraph;
import invenio.io.file.GraphReader;
import invenio.io.file.PajekFileReader;
import invenio.io.file.SparseGraphFileReader;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.items.file.FileImportConfigView.FileType;
import invenio.wf.log.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class FileImportController extends AbstractController<InvenioGraph>
		implements ItemController<InvenioGraph>, ActionListener {
	
	private static Logger log = Logger.getInstance();
	
	private FileImportConfigView configView;
	
	private JFileChooser fileChooser;
	private String lastFile = "C:/Workspace/Invenio/invenio.main/dolphins_3_years.sgf";
	
	public FileImportController() {
		initContoller();
	}
	
	protected void initContoller() {
		configView = new FileImportConfigView();
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
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastFile);
		
		int returnVal = fileChooser.showOpenDialog( configView );
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			lastFile = fileChooser.getSelectedFile().getAbsolutePath();
			configView.getFileNameTextField().setText(lastFile);
		}
	}

	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		String file = configView.getFileNameTextField().getText();
		FileImportConfigView.FileType fileType = 
				(FileImportConfigView.FileType) configView.getFileTypeComboBox().getSelectedItem();
		String graphName = configView.getGraphNameTextField().getText();
		
		if ( fileType == FileType.TEXT)
			return readTextFile( file, graphName );
		else if ( fileType == FileType.SPARSE )
			return readSparseFile( file, graphName );
		else if ( fileType == FileType.PAJEK )
			return readPajekFile( file, graphName );
		else if ( fileType == FileType.SIMPLE )
			return readSimpleFile( file, graphName );
		else
			return null;
	}
	
	protected InvenioGraph readTextFile(String file, String graphName) {
		try{
			log.log(this, "Loading InvenioGraph...");
			
			InvenioGraph invGraph = new InvenioGraph();
			GraphReader reader = new GraphReader(file);
			invGraph = (InvenioGraph)reader.readGraph();
			invGraph.setName(graphName);
			reader.close();
			
			log.log(this, "InvenioGraph loaded, nodes: " + invGraph.numVertices() + ", edges: " + invGraph.numEdges());
			
			return invGraph;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public InvenioGraph readSparseFile(String file, String graphName) {
		try{
			log.log(this, "Loading InvenioGraph...");
			
			InvenioGraph invGraph = new InvenioGraph();
	
			SparseGraphFileReader reader = new SparseGraphFileReader(file);
			
			invGraph = reader.readGraph();
			invGraph.setName( graphName );
			reader.close();
			
			log.log(this, "InvenioGraph loaded, nodes: " + invGraph.numVertices() + ", edges: " + invGraph.numEdges());
			
			return invGraph;
		}
		catch(Exception e){
			e.printStackTrace();	
			return null;
		}
	}

	public InvenioGraph readPajekFile( String file, String graphName ) {
		try{
			log.log(this, "Loading InvenioGraph...");
			
			InvenioGraph invGraph = new InvenioGraph();
			PajekFileReader reader = new PajekFileReader();
			invGraph = reader.readGraph( file, graphName );
			
			log.log(this, "InvenioGraph loaded, nodes: " + invGraph.numVertices() + ", edges: " + invGraph.numEdges());
			
			return invGraph;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public InvenioGraph readSimpleFile( String file, String graphName ) {
		try{
			log.log(this, "Loading InvenioGraph...");
			
			InvenioGraph invGraph = null;
			SimpleFileReader reader = new SimpleFileReader();
			invGraph = reader.readGraph( file, graphName );
			
			log.log(this, "InvenioGraph loaded, nodes: " + invGraph.numVertices() + ", edges: " + invGraph.numEdges());
			
			return invGraph;
		}
		catch(Exception e){
			return null;
		}
	}

}
