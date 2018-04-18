package invenio.wf.synthetic;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.wf.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates edges between a random pair of vertices. Should be used for dense graphs, while for sparse
 * graphs the sparse edge generator is more efficient. Does not allow duplicate edges - sparse edge
 * generator works fine in with duplicates. Also, loops are not allowed.
 * 
 * Generates edgeCount edges by enumerating all possible pairs of vertices, shuffling them, and
 * taking the first edgeCount pairs. Depending on whether edges should be undirected or directed, each
 * pair of vertices is enumerated only once or twice (once in each direction).
 * 
 * @author ddimitrov
 *
 */
public class RandomDenseEdgeGenerator extends GeneratorStep {
	public final static int DEFAULT_EDGE_COUNT = 300;
	private int edgeCount = DEFAULT_EDGE_COUNT;
	
	private boolean undirected = true;
	
	public RandomDenseEdgeGenerator() {}
	public RandomDenseEdgeGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		int numVertices = graph.numVertices();
		int totalEdges = numVertices * (numVertices-1);
		if ( undirected && edgeCount > totalEdges / 2)
			throw new GeneratorException(
					"Cannot generate [" + edgeCount + "] undirected edges for [" + numVertices + "] vertices without duplicates.");
		
		if ( !undirected && edgeCount > totalEdges )
			throw new GeneratorException(
					"Cannot generate [" + edgeCount + "] directed edges for [" + numVertices + "] vertices without duplicates.");
		
		List<Pair<InvenioVertex, InvenioVertex>> pairList = generateAdjacencyList(graph);
		Collections.shuffle(pairList);
		
		assert ( edgeCount <= pairList.size() ) : "Cannot generate [" + edgeCount + "] edges without duplicates.";
		
		for (int i = 0; i < edgeCount; i++) {
			
			InvenioVertex vFrom = pairList.get(i).first;
			InvenioVertex vTo = pairList.get(i).second;
			
			InvenioEdge e = createEdge( vFrom, vTo );
			graph.addEdge(e);
		}
	}
	
	protected InvenioEdge createEdge( InvenioVertex vFrom, InvenioVertex vTo ) {
		InvenioEdge e = undirected ? new InvenioEdge( vFrom, vTo) : new InvenioDirectedEdge(vFrom, vTo);
		
		return e;
	}
	
	/**
	 * Generates a list of all pairs between the graph's vertices. If undirected, a pair appears only once,
	 * else the same pair of vertices is represented twice - once in each direction.
	 * 
	 * @param graph
	 * @return
	 */
	private List<Pair<InvenioVertex, InvenioVertex>> generateAdjacencyList( InvenioGraph graph ) {
		List<Pair<InvenioVertex, InvenioVertex>> pairList = null;
		
		if ( undirected )
			pairList = new ArrayList<Pair<InvenioVertex, InvenioVertex>>( (graph.numVertices() ^ 2) / 2 );
		else
			pairList = new ArrayList<Pair<InvenioVertex, InvenioVertex>>( (graph.numVertices() ^ 2) );
		
		List<InvenioVertex> vertexList = new ArrayList<InvenioVertex>( graph.getVertices() );
		
		if ( undirected ) {
			for ( int i = 0; i < vertexList.size(); i++ ) {
				for ( int j = i + 1; j < vertexList.size(); j++ ) {
					pairList.add(
							new Pair<InvenioVertex, InvenioVertex>(
									vertexList.get(i), vertexList.get(j) ) );
				}
			}
		}
		else {	// same except that inner loop starts from 0 instead of from i
			for ( int i = 0; i < vertexList.size(); i++ ) {
				for ( int j = 0; j < vertexList.size(); j++ ) {
					if ( j != i)
						pairList.add(
								new Pair<InvenioVertex, InvenioVertex>(
										vertexList.get(i), vertexList.get(j) ) );
				}
			}
		}
		
		return pairList;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(int edgeCount) {
		if (edgeCount < 0)
			throw new IllegalArgumentException("Edge count must be non-negative: " + edgeCount);
		this.edgeCount = edgeCount;
	}
	public boolean isUndirected() {
		return undirected;
	}
	public void setUndirected(boolean undirected) {
		this.undirected = undirected;
	}
	
	
}
