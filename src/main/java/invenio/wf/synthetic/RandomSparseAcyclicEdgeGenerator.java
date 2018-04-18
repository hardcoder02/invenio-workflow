package invenio.wf.synthetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;

/**
 * Generates directed edges between a random pair of vertices, avoiding cycles. Should be used for sparse graphs.
 * 
 * Continues generating edges until edgeCount edges are generated. If duplicateEdgesAllowed
 * is set to false, a randomly generated duplicate edge is not inserted and fail count is
 * incremented. If maxFailedAttempts is set to a non-negative number and is exceeded,
 * processing exits. It exits with an exception if failOnMaxAttempts flag is set, else simply
 * continues, resulting in a graph with less than edgeCount edges.
 * @author ddimitrov
 *
 */
public class RandomSparseAcyclicEdgeGenerator extends GeneratorStep {
	public final static int DEFAULT_EDGE_COUNT = 300;
	private int edgeCount = DEFAULT_EDGE_COUNT;
	
	private int maxFailedAttempts = -1;
	private boolean failOnMaxAttempts = false;
	private boolean duplicateEdgesAllowed = false;
	
	private Random rand = new Random();
	
	public RandomSparseAcyclicEdgeGenerator() {}
	public RandomSparseAcyclicEdgeGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting edge generation...");
		long startTime = System.currentTimeMillis();

		List<InvenioVertex> vertexList = new ArrayList<InvenioVertex>( graph.getVertices() );
		
		int numCreated = 0;
		int numFailed = 0;
		while (numCreated < edgeCount) {
			InvenioDirectedEdge e = createEdge( vertexList );
			
			// e == null if duplicate edge and duplicates not allowed
			if ( e == null) {
				numFailed++;
				if ( maxFailedAttempts >= 0 && numFailed > maxFailedAttempts ) {
					if ( failOnMaxAttempts )
						throw new GeneratorException("Quitting edge generation after: " + numFailed + " attempts.");
					else
						return;
				}
			}
			else {
				graph.addEdge(e);
				numCreated++;
			}
		}
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Edge generation completed in [" + duration + "] msec.");
	}
	
	protected InvenioDirectedEdge createEdge( List<InvenioVertex> vertexList ) {
		int vToIndex = rand.nextInt(vertexList.size());
		if (vToIndex == 0)
			return null;	// must be at least 1, see next line
		int vFromIndex = rand.nextInt( vToIndex );	// vFromIndex < vToIndex to keep graph acyclic
		InvenioVertex vFrom = vertexList.get( vFromIndex );
		InvenioVertex vTo = vertexList.get( vToIndex );
		
		if ( !duplicateEdgesAllowed && vFrom.isPredecessorOf(vTo) )
			return null;
		
		InvenioDirectedEdge e = new InvenioDirectedEdge( vFrom, vTo);
		
		return e;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(int edgeCount) {
		if (edgeCount < 0)
			throw new IllegalArgumentException("Edge count must be non-negative: " + edgeCount);
		this.edgeCount = edgeCount;
	}

	public boolean isDuplicateEdgesAllowed() {
		return duplicateEdgesAllowed;
	}

	public void setDuplicateEdgesAllowed(boolean duplicateEdgesAllowed) {
		this.duplicateEdgesAllowed = duplicateEdgesAllowed;
	}

	public int getMaxFailedAttempts() {
		return maxFailedAttempts;
	}

	public void setMaxFailedAttempts(int maxFailedAttempts) {
		this.maxFailedAttempts = maxFailedAttempts;
	}

	public boolean isFailOnMaxAttempts() {
		return failOnMaxAttempts;
	}

	public void setFailOnMaxAttempts(boolean failOnMaxAttempts) {
		this.failOnMaxAttempts = failOnMaxAttempts;
	}
}
