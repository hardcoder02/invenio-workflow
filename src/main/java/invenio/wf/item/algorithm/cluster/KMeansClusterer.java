package invenio.wf.item.algorithm.cluster;

import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.util.AlgorithmParameterPanel;
import invenio.data.InvenioSchemaTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import edu.uci.ics.jung.algorithms.cluster.ClusterSet;

public class KMeansClusterer extends
		invenio.algorithms.cluster.EdgeBetweennessClusterer {
	
	protected void initializeParameterTypes() {
		_parameterTypes = new Class[2];
		_parameterTypes[0] = Integer.class;
		_parameterTypes[1] = String.class;
	}
	
	public AlgorithmParameterPanel createParameterPanel(int instanceCount) {
			
			AlgorithmParameterPanel dialog = new AlgorithmParameterPanel(this);
	
			dialog.addIntegerParameter("# Edges to Remove", 20);
			dialog.addStringParameter("Set Names", getName() + " " + instanceCount);
			
			dialog.lockPanel();
			
			return dialog;
		}

	
	
	public AlgorithmResult execute() {
		
		if(!validateParameters()) return null;
		
		int edgeRemovals = (Integer)_params[0];
		String clusterName = (String)_params[1];
		
		GroupingAlgorithmResult clusterResult = new GroupingAlgorithmResult(this, clusterName, getGraph());
		clusterResult.createRootSet(clusterName, InvenioSchemaTree.ABSTRACTION, getGraph().getSets());//getSetParent());
		
//		Vector removedVertices = new Vector(),
//			   removedEdges = new Vector();
//		
//		filterGraph(getGraph(), getPredicate(), removedVertices, removedEdges);
//		
//		if(getGraph().numEdges() < edgeRemovals || edgeRemovals < 0) {
//			restoreGraph(getGraph(), removedVertices, removedEdges);
//			return null;
//		}
		
		edu.uci.ics.jung.algorithms.cluster.EdgeBetweennessClusterer clusterer = new edu.uci.ics.jung.algorithms.cluster.EdgeBetweennessClusterer(edgeRemovals);
		System.out.println("Size: " + getGraph().getEdges().size() );
		ClusterSet clusters = clusterer.extract(getGraph());
		
		Iterator iter = clusters.iterator();
		Collection cluster;
		String setName;
		int count = 0;
		
		while(iter.hasNext()) {
			cluster = (Set)iter.next();
			setName = clusterName + " " + count;
			clusterResult.addSet(setName);
			clusterResult.addElements(setName, cluster);
			count++;
		}
		clusterResult.setElementResultGraph(getGraph());
//		restoreGraph(getGraph(), removedVertices, removedEdges);
		

//		if(addGraph.equals(NO_GRAPH))
			return clusterResult;
//		else {
//			InvenioGraph graph = null;
//			if(!addGraph.equals(NEW_GRAPH))
//				graph = UserSession.getInstance().getGraph(addGraph);
//			
//			MultiAlgorithmResult results = getMultiAlgorithmResult(graph, clusterResult);
//			results.addResult(clusterResult);
//			return results;
//		}
	}
	
}
