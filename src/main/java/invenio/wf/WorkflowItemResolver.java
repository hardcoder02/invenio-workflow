package invenio.wf;

import invenio.wf.item.algorithm.cluster.EBClustererItem;
import invenio.wf.item.algorithm.cluster.KMeansClustererItem;
import invenio.wf.item.visual.GraphDisplayItem;
import invenio.wf.items.file.FileImportItem;
import invenio.wf.items.file.TabReaderItem;
import invenio.wf.items.nodelabel.NodeLabelItem;
import invenio.wf.items.query.QueryItem;
import invenio.wf.items.query.TableSelectorItem;
import invenio.wf.items.vis.clustering.ClusteringVisItem;
import invenio.wf.items.vis.nodelabel.LabelVisualizationItem;
import invenio.wf.items.vis.query.QueryVisItem;

import java.util.HashMap;
import java.util.Map;

public class WorkflowItemResolver {
	private static WorkflowItemResolver itemResolver;
	private final Map<String, Class> itemMap = new HashMap<String, Class>(); 
	
	private WorkflowItemResolver() {
		itemMap.put("File Import", FileImportItem.class);
		itemMap.put("Tab Reader", TabReaderItem.class);
		itemMap.put("Query", QueryItem.class);
		itemMap.put("Node Label", NodeLabelItem.class);
		itemMap.put("Graph Display", GraphDisplayItem.class);
		itemMap.put("Table Selector", TableSelectorItem.class);
		itemMap.put("Label Visualization", LabelVisualizationItem.class);
		itemMap.put("Edge Betweenness", EBClustererItem.class);
		itemMap.put("K-Means", KMeansClustererItem.class);
		itemMap.put("Cluster Visualization", ClusteringVisItem.class);
		itemMap.put("Query Result", QueryVisItem.class);
	}
	
	public static WorkflowItemResolver getInstance() {
		if (itemResolver == null) {
			itemResolver = new WorkflowItemResolver();
		}
		return itemResolver;
	}
	
	public BaseWorkflowItem getNodeItem(String name) {
		if ( itemMap.containsKey( name )) {
			Class clazz = itemMap.get( name );
			try {
				return (BaseWorkflowItem) clazz.newInstance();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
