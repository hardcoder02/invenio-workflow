package invenio.wf.synthetic;

import invenio.data.InvenioGraph;

public class GeneratorInvokerRandom {
	public static final int NODE_COUNT = 1000;
	public static final int EDGE_COUNT = 10000;
	public static final boolean DIRECTED = false;
	
//	public static final String nodeFile = "C:/Workspace/Invenio/DolphinData/Workflow/synthetic/synthetic.NODE.tab";
//	public static final String edgeFile = "C:/Workspace/Invenio/DolphinData/Workflow/synthetic/synthetic.EDGE.tab";
	public static final String FILE_PATH = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/DEXA2013-JOURNAL/";
	public static final String NODE_FILE = FILE_PATH + "synthetic.NODE." + NODE_COUNT + ".tab";
	public static final String EDGE_FILE = FILE_PATH + "synthetic.EDGE." + NODE_COUNT + "." + EDGE_COUNT + (DIRECTED ? ".directed" : ".undirected") + ".tab";

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		writeSynthetic(NODE_FILE, EDGE_FILE);
		
		System.out.println("Generation completed successfully.");
	}
	
	public static void writeSynthetic(String nodeFile, String edgeFile) throws Exception {
		InvenioGraph graph = new InvenioGraph();
		
		GraphWriterGenerator gwg = new GraphWriterGenerator();
		gwg.setNodeFile(nodeFile);
		gwg.setEdgeFile(edgeFile);
		gwg.setOverwrite(true);
		
		RandomAttributeValueGenerator avg = new RandomAttributeValueGenerator( gwg );
		avg.setProcessEdges(true);
		avg.setProcessVertices(true);
		
		SequentialIdGenerator sig = new SequentialIdGenerator( avg );
		sig.setProcessVertices(true);
		sig.setProcessEdges(true);
		
		RandomSchemaGenerator sgv = new RandomSchemaGenerator( sig );
		sgv.setNumCategorical( 2 );
		sgv.setNumNumeric( 2 );
		sgv.setNumString( 2 );
		sgv.setMaxCatSize( 7 );
		sgv.setProcessEdges(false);
		
		RandomSchemaGenerator sge = new RandomSchemaGenerator( sgv );
		sge.setProcessVertices(false);
		
		RandomSparseAcyclicEdgeGenerator eg = new RandomSparseAcyclicEdgeGenerator( sge );
		eg.setDuplicateEdgesAllowed(false);
		eg.setMaxFailedAttempts( NODE_COUNT * 5 );
		eg.setFailOnMaxAttempts(true);
		eg.setEdgeCount( EDGE_COUNT );
		
//		RandomSparseEdgeGenerator eg = new RandomSparseEdgeGenerator( sge );
//		eg.setDuplicateEdgesAllowed(false);
//		eg.setMaxFailedAttempts( NODE_COUNT * 5 );
//		eg.setFailOnMaxAttempts(true);
//		eg.setEdgeCount( EDGE_COUNT );
		
//		RandomDenseEdgeGenerator eg = new RandomDenseEdgeGenerator( sge );
//		eg.setUndirected( !DIRECTED );
//		eg.setEdgeCount( EDGE_COUNT );
		
		VertexGenerator vg = new VertexGenerator( eg );
		vg.setVertexCount( NODE_COUNT );
		
		vg.processGraph( graph );
		
	}
}
