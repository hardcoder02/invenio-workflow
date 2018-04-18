package invenio.wf.item.algorithm.cluster;


import java.util.Iterator;

import invenio.algorithms.AbstractVisualAlgorithm;
import invenio.algorithms.Algorithm;
import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.util.AlgorithmParameterPanel;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.wf.AbstractController;
import invenio.wf.ItemController;
import invenio.wf.item.algorithm.AlgorithmManager;
import invenio.wf.log.Logger;

import javax.swing.JComponent;

public class EBClustererController extends AbstractController<AlgorithmResult>
		implements ItemController<AlgorithmResult> {
	
	private static Logger log = Logger.getInstance();
	
	private final AlgorithmManager algorithmManager = new AlgorithmManager();
	private final Class algorithmClass;
	
	private AlgorithmParameterPanel configView;
//	private LabelVisualizationResultModel resultModel; 
	
	public EBClustererController(Class algorithm) {
		this.algorithmClass = algorithm;
		initContoller();
	}
	
	protected void initContoller() {
//		resultView = new LabelVisualizationResultView();
//		resultView.setController(this);
//		
//		resultModel = new LabelVisualizationResultModel();
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
		if (configView == null) {
			configView = algorithmManager.runAlgorithm(algorithmClass, null);
		}
		return configView;
	}

	@Override
	public JComponent getResultView() {
		return null;
	}

	
	@Override
	public AlgorithmResult updateResult(Object[] inputs) {
		if (inputs == null || inputs.length < 1)
			return null;
		
		InvenioGraph graph = (InvenioGraph)inputs[0];
		Object[] params = configView.getParameters();
		
		Algorithm alg = null;
		alg = algorithmManager.getAlgorithm(algorithmClass, graph);
		
		if(alg == null)
			return null;
		
		if (alg instanceof AbstractVisualAlgorithm)
			((AbstractVisualAlgorithm)alg).setGraph(graph);
		
		if(!alg.setParameters(params))
			return null;
				
		AlgorithmResult results = alg.execute();
		
//		GroupingAlgorithmResult gar = (GroupingAlgorithmResult) results;
//		Iterator<InvenioElementSet> sets = gar.getSets();
//		while (sets.hasNext()) {
//			InvenioElementSet es = sets.next();
//			System.out.println( "Count: " + es.getNumElements() );
//		}
		
		return results;
	}
	
}
