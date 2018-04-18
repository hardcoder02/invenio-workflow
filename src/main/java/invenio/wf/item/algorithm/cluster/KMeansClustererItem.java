package invenio.wf.item.algorithm.cluster;

import invenio.data.InvenioGraph;
import invenio.wf.ItemController;
import invenio.wf.item.algorithm.AlgorithmExecutorItem;

public class KMeansClustererItem extends AlgorithmExecutorItem {
	private final static String NAME = "K-Means";
	
	private final static Class ALGORITHM = KMeansClusterer2.class;
	
	private final static Class[] DESCRIPTOR = {InvenioGraph.class};
	
	private final KMeansClustererController controller = new KMeansClustererController(ALGORITHM);
	
	public KMeansClustererItem() {
		super(NAME, ALGORITHM, DESCRIPTOR);
	}
	
	@Override
	public ItemController getController() {
		return controller;
	}
}
