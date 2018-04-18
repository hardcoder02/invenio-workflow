package invenio.wf.item.algorithm.cluster;

import invenio.data.InvenioGraph;
import invenio.wf.ItemController;
import invenio.wf.item.algorithm.AlgorithmExecutorItem;

public class EBClustererItem extends AlgorithmExecutorItem {
	private final static String NAME = "Edge Betweenness";
	
	private final static Class ALGORITHM = EdgeBetweennessClusterer.class;
	
	private final static Class[] DESCRIPTOR = {InvenioGraph.class};
	
	private final EBClustererController controller = new EBClustererController(ALGORITHM);
	
	public EBClustererItem() {
		super(NAME, ALGORITHM, DESCRIPTOR);
	}
	
	@Override
	public ItemController getController() {
		return controller;
	}
}
