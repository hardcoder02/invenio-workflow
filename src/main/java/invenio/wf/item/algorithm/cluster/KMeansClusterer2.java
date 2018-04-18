package invenio.wf.item.algorithm.cluster;

import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.cluster.AbstractClusterer;
import invenio.algorithms.util.AlgorithmParameterPanel;
import invenio.data.InvenioEdge;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioSchemaTree;
import invenio.data.InvenioVertex;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class KMeansClusterer2 extends AbstractClusterer {

	protected void initializeParameterTypes() {
		_parameterTypes = new Class[4];
		_parameterTypes[0] = Integer.class;
		_parameterTypes[1] = Double.class;
		_parameterTypes[2] = Integer.class;
		_parameterTypes[3] = String.class;
//		_parameterTypes[4] = String.class;
	}
	
	public String getName() {
		return "K-Means Clustering";
	}

	
	public AlgorithmParameterPanel createParameterPanel(int instanceCount) {
		
		AlgorithmParameterPanel dialog = new AlgorithmParameterPanel(this);

		dialog.addIntegerParameter("Max Iterations", 100);
		dialog.addDoubleParameter("Convergence Threshold", 0.1);
		dialog.addIntegerParameter("Number of Clusters", 3);
		dialog.addStringParameter("Result Set Names", getName() + " " + instanceCount);
//		String[] graphNames = UserSession.getInstance().getGraphSessionNames();
//		String[] graphs = new String[graphNames.length + 2];
//		for(int i = 0; i < graphNames.length; i++)
//			graphs[i + 2] = graphNames[i];
//		graphs[0] = NO_GRAPH;
//		graphs[1] = NEW_GRAPH;
//		
//		dialog.addSingleSelectionParameter("Add to Graph", graphs, "Choose the graph to add reference vertices to");
		
		dialog.lockPanel();
		
		return dialog;
	}
	
	
	public AlgorithmResult execute() {
		
		if(!validateParameters()) return null;

		int maxIter = (Integer)_params[0];
		double convThreshold = (Double)_params[1];
		int means = (Integer)_params[2];
		String setNames = (String)_params[3];
//		String addGraph = (String)_params[4];
		
		GroupingAlgorithmResult clusterResults = new GroupingAlgorithmResult(this, setNames, getGraph());
		clusterResults.createRootSet(setNames, InvenioSchemaTree.ABSTRACTION, getGraph().getSets());//getSetParent());
		
		edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath shortPath = new edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath(getGraph());
		String baseName = "cluster";
		int startIndex = 1;
		Object[] vertexSet = getGraph().getVertices().toArray();
		Vector<InvenioVertex> meansVector = new Vector<InvenioVertex>();

		//Chooses means randomly
		while(meansVector.size() < means) {
			Random generator = new Random();
			int potentialIndex = (int)(generator.nextInt(vertexSet.length));
			if(!meansVector.contains(vertexSet[potentialIndex]))
				meansVector.add((InvenioVertex) vertexSet[potentialIndex]);
		}

		//Get distance maps for each mean in order
		Vector<Map<InvenioVertex, Integer>> distanceMapVector = new Vector<Map<InvenioVertex, Integer>>();
		if(shortPath != null){
			for(int i = 0; i < meansVector.size(); i++) {
				distanceMapVector.add(shortPath.getDistanceMap(meansVector.elementAt(i)));
			}
		}

		//Each vertexSet corresponds to a cluster
		Vector<InvenioElementSet> clusterVector = new Vector<InvenioElementSet>();
		for(int i=0; i < meansVector.size(); i++) {
			//Add the means as the first element of a cluster (because they will have distances of zero)
			//Note that now all vxs operated on are copies. Need to remap the clusters to their actual vxs at return
			InvenioElementSet newSet = new InvenioElementSet();
			newSet.addElement(meansVector.elementAt(i));
			clusterVector.add(newSet);
		}
		Iterator<InvenioVertex> vertexIterator = null;
		for(vertexIterator = getGraph().getVertices().iterator(); vertexIterator.hasNext(); ) {
			InvenioVertex next = vertexIterator.next();
			int closest = Integer.MAX_VALUE;
			int clusterIndex = 0;
			for(int i = 0; i < means; i++)	{
				if(distanceMapVector.elementAt(i).get(next) != null) {	
					if(closest > distanceMapVector.elementAt(i).get(next)) {
						closest = distanceMapVector.elementAt(i).get(next);
						clusterIndex = i;
					}
				}
			}
			clusterVector.elementAt(clusterIndex).addElement(next);
		}
		
		//For calculating convergence
		Vector<InvenioElementSet> previousClusterVector = (Vector<InvenioElementSet>) clusterVector.clone();
		Vector<InvenioElementSet> resultSet = null;
		for(int iterCount = 0; iterCount < maxIter; iterCount++) {
			Vector<InvenioVertex> newCentroidVector = new Vector();
			for(int i = 0; i < previousClusterVector.size(); i++) {
				double lowestAvgDist = Double.MAX_VALUE;
				int newCentroidIndex = 0;
				for(int q = 0; q < previousClusterVector.elementAt(i).size(); q++) {
					double total = 0;
					Map<InvenioVertex, Integer> distanceMap = shortPath.getDistanceMap((InvenioVertex)previousClusterVector.elementAt(i).getElementAt(q));
					Collection<Integer> distances = distanceMap.values();
					Iterator<Integer> distIterator = distances.iterator();
					while(distIterator.hasNext()) {
						total += distIterator.next();
					}
					if (total/distances.size() < lowestAvgDist)	{
						lowestAvgDist = total/distances.size();
						newCentroidIndex = q;
					}
				}
				newCentroidVector.add((InvenioVertex)previousClusterVector.elementAt(i).getElementAt(newCentroidIndex));
			}
			//Get distance maps for each mean in order
			Vector<Map<InvenioVertex, Integer>> iteratedDistanceMapVector = new Vector();

			for(int i=0; i < newCentroidVector.size(); i++)	{
				iteratedDistanceMapVector.add(shortPath.getDistanceMap(newCentroidVector.elementAt(i)));
			}

			//Each vertexSet corresponds to a cluster
			Vector<InvenioElementSet> iteratedClusterVector = new Vector();

			for(int i=0; i < newCentroidVector.size(); i++)	{
				//Add the means as the first element of a cluster (because they will have distances of zero)
				InvenioElementSet newSet = new InvenioElementSet();
				newSet.addElement(newCentroidVector.elementAt(i));
				newSet.setName("Cluster "+i);
				iteratedClusterVector.add(newSet);
			}

			for(vertexIterator = getGraph().getVertices().iterator(); vertexIterator.hasNext(); ) {
				InvenioVertex next = vertexIterator.next();
				int closest = Integer.MAX_VALUE;
				int clusterIndex = 0;
				for(int i = 0; i < means; i++) {
					if(iteratedDistanceMapVector.elementAt(i).get(next)!=null) {
						if(closest > iteratedDistanceMapVector.elementAt(i).get(next)) {
							closest = iteratedDistanceMapVector.elementAt(i).get(next);
							clusterIndex = i;
						}
					}
				}
				iteratedClusterVector.elementAt(clusterIndex).addElement(next);
			}

			double temp = 0;
			for(int i = 0; i < iteratedClusterVector.size(); i++) {
				temp += getPercentChange(previousClusterVector.elementAt(i), iteratedClusterVector.elementAt(i));
			}
			temp = temp/iteratedClusterVector.size();

			if(temp > convThreshold) {
				resultSet = (Vector<InvenioElementSet>) iteratedClusterVector.clone();
				break;
			}

			resultSet =(Vector<InvenioElementSet>) iteratedClusterVector.clone();
			previousClusterVector = iteratedClusterVector;
		}

		for(int i = 0; i < resultSet.size(); i++) {
			InvenioElementSet tempSet = new InvenioElementSet();
			tempSet.setName(baseName + startIndex);
			startIndex++;
			for(int q = 0; q < resultSet.elementAt(i).size(); q++) {
				tempSet.addElement(resultSet.elementAt(i).getElementAt(q));
			}
			resultSet.setElementAt(tempSet, i);
		}
		InvenioElementSet edges = new InvenioElementSet("edges");
		edges.addAll(getGraph().getEdges());
		foldEdges(resultSet, edges);
		
		for(int i = 0; i < resultSet.size(); i++){
			InvenioElementSet newCluster = resultSet.get(i);
			clusterResults.addGroupElementAndSet(new InvenioVertex(), newCluster);
		}
		
		

//		if(addGraph.equals(NO_GRAPH))
			clusterResults.setElementResultGraph(getGraph());
			return clusterResults;
//		else {
//			InvenioGraph graph = null;
//			if(!addGraph.equals(NEW_GRAPH))
//				graph = UserSession.getInstance().getGraph(addGraph);
//			
//			MultiAlgorithmResult results = getMultiAlgorithmResult(graph, clusterResults);
//			results.addResult(clusterResults);
//			return results;
//		}
	}
	
	private void foldEdges(Vector<InvenioElementSet> resultSet, InvenioElementSet currentTimeSet) {
		HashSet<InvenioEdge> inClusterEdge = new HashSet<InvenioEdge>();
		for(Iterator<InvenioEdge> iterator = currentTimeSet.getEdgeIterator(); iterator.hasNext(); ) {
			InvenioEdge edge = iterator.next();
			for(int i = 0; i < resultSet.size(); i++) {
				InvenioElementSet cluster = resultSet.get(i);
				InvenioVertex sourceVertex = edge.getSourceVertex();
				InvenioVertex targetVertex = edge.getTargetVertex();
				if((cluster.containsElement(sourceVertex)) && (cluster.containsElement(targetVertex))) {
					cluster.addElement(edge);
					inClusterEdge.add(edge);
				}
			}
		}			
	}		
	
	private double getPercentChange(InvenioElementSet prev, InvenioElementSet iterated) {

		Iterator<InvenioVertex> prevIter = prev.getVertexIterator();
		double contained = 0; 
		while(prevIter.hasNext()) {
			if(iterated.containsElement(prevIter.next())) {
				contained++;
			}
		}

		double percentRemain = 0;
		if(prev.getNumVertices() > iterated.getNumVertices())
			percentRemain = contained/prev.getNumVertices();
		else
			percentRemain = contained/iterated.getNumVertices();
		return percentRemain;
	}
	

}
