package invenio.wf.items.file;

import invenio.data.InvenioGraph;
import invenio.io.file.GraphReader;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.log.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class TabReaderController extends AbstractController<InvenioGraph>
		implements ItemController<InvenioGraph>, ActionListener {
	
	private static Logger log = Logger.getInstance();
	
	private TabReaderConfigView configView;
	
	private JFileChooser fileChooser;
//	private String lastNodeFile = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.NODE.dolphin.gt.tab";
//	private String lastEdgeFile = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.UNDIRECTED.seenwith.tab";
	private String lastNodeFile = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/";
	private String lastEdgeFile = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/";

	
	public TabReaderController() {
		initContoller();
	}
	
	protected void initContoller() {
		configView = new TabReaderConfigView();
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
		if ( evt.getSource() == configView.getNodeOpenButton() ) {
			processNodeOpenButton();
		}
		else if ( evt.getSource() == configView.getEdgeOpenButton() ) {
			processEdgeOpenButton();
		}
		
	}

	private void processNodeOpenButton() {
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastNodeFile);
		
		int returnVal = fileChooser.showOpenDialog( configView );
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			lastNodeFile = fileChooser.getSelectedFile().getAbsolutePath();
			configView.getNodeFileNameTextField().setText(lastNodeFile);
		}
	}
	
	private void processEdgeOpenButton() {
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastNodeFile);
		
		int returnVal = fileChooser.showOpenDialog( configView );
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			lastEdgeFile = fileChooser.getSelectedFile().getAbsolutePath();
			configView.getEdgeFileNameTextField().setText(lastEdgeFile);
		}
	}

	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		String nodeFile = configView.getNodeFileNameTextField().getText();
		String edgeFile = configView.getEdgeFileNameTextField().getText();
		String graphName = configView.getGraphNameTextField().getText();
		try{
			log.log(this, "Loading InvenioGraph...");
			InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
			g.setName( graphName );
			log.log(this, "InvenioGraph loaded, nodes: " + g.numVertices() + ", edges: " + g.numEdges());
			return g;
		}
		catch(Exception e){
			e.printStackTrace();
			log.log( this, e.getMessage() );
			return null;
		}
	}

	

}
