package invenio.wf.item.visual;

import invenio.data.InvenioGraph;
import invenio.io.file.GraphReader;
import invenio.visual.DisplayShape;
import invenio.visual.VisualGraphSession;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.items.file.TabReaderConfigView;
import invenio.wf.items.vis.query.QueryResultView;
import invenio.wf.log.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import qng.tabular.Table;

public class GraphDisplayController extends AbstractController<Object>
		implements ItemController<Object>, ActionListener {
	
	private static Logger log = Logger.getInstance();
	
	private VisualGraphSession resultController;
	
	public GraphDisplayController() {
		initContoller();
	}
	
	protected void initContoller() {
		 DisplayShape shape = new DisplayShape();
		 resultController = new VisualGraphSession();
		 resultController.setShape(shape);
		 shape.setController(resultController);
	}

	@Override
	public boolean hasConfigView() {
		return false;
	}

	@Override
	public boolean hasResultView() {
		return true;
	}

	@Override
	public JComponent getConfigView() {
		return null;
	}

	@Override
	public JComponent getResultView() {
		if (resultController != null)
			return resultController.getDisplaySession();
		else
			return new JPanel();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
//		if ( evt.getSource() == configView.getNodeOpenButton() ) {
//			processNodeOpenButton();
//		}
//		else if ( evt.getSource() == configView.getEdgeOpenButton() ) {
//			processEdgeOpenButton();
//		}
		
	}

//	private void processNodeOpenButton() {
//		if (fileChooser == null)
//			fileChooser = new JFileChooser(lastNodeFile);
//		
//		int returnVal = fileChooser.showOpenDialog( configView );
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			lastNodeFile = fileChooser.getSelectedFile().getAbsolutePath();
//			configView.getNodeFileNameTextField().setText(lastNodeFile);
//		}
//	}
//	
//	private void processEdgeOpenButton() {
//		if (fileChooser == null)
//			fileChooser = new JFileChooser(lastNodeFile);
//		
//		int returnVal = fileChooser.showOpenDialog( configView );
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			lastEdgeFile = fileChooser.getSelectedFile().getAbsolutePath();
//			configView.getEdgeFileNameTextField().setText(lastEdgeFile);
//		}
//	}

	@Override
	public InvenioGraph updateResult(Object[] inputs) {
		if (inputs == null || inputs.length < 1)
			return null;
		
		InvenioGraph g = (InvenioGraph) inputs[0];
		resultController.updateModel(g);
		
		return null;
	}
}
