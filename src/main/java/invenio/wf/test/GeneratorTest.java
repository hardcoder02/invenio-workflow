package invenio.wf.test;

import invenio.data.InvenioGraph;
import invenio.wf.synthetic.GraphWriterGenerator;
import invenio.wf.synthetic.RandomAttributeValueGenerator;
import invenio.wf.synthetic.RandomDenseEdgeGenerator;
import invenio.wf.synthetic.RandomSchemaGenerator;
import invenio.wf.synthetic.SequentialIdGenerator;
import invenio.wf.synthetic.VertexGenerator;

public class GeneratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		testGenerator();
	}
	
	public static void testGenerator() throws Exception {
		InvenioGraph graph = new InvenioGraph();
		
		GraphWriterGenerator gwg = new GraphWriterGenerator();
		gwg.setNodeFile("C:/Workspace/Invenio/DolphinData/Workflow/synthetic/synthetic.NODE.tab");
		gwg.setEdgeFile("C:/Workspace/Invenio/DolphinData/Workflow/synthetic/synthetic.EDGE.tab");
		
//		RandomSparseEdgeGenerator eg = new RandomSparseEdgeGenerator( gwg );
//		eg.setDuplicateEdgesAllowed(false);
//		eg.setMaxFailedAttempts( 3 );
//		eg.setFailOnMaxAttempts(true);
//		eg.setEdgeCount( 20 );
		
		RandomAttributeValueGenerator avg = new RandomAttributeValueGenerator( gwg );
		avg.setProcessEdges(true);
		avg.setProcessVertices(true);
		
		SequentialIdGenerator sig = new SequentialIdGenerator( avg );
		sig.setProcessVertices(true);
		sig.setProcessEdges(true);
		
		RandomSchemaGenerator sgv = new RandomSchemaGenerator( sig );
		sgv.setNumCategorical( 2 );
		sgv.setNumNumeric( 3 );
		sgv.setNumString( 0 );
		sgv.setMaxCatSize( 5 );
		sgv.setProcessEdges(false);
		
		RandomSchemaGenerator sge = new RandomSchemaGenerator( sgv );
		sge.setProcessVertices(false);
		
		RandomDenseEdgeGenerator eg = new RandomDenseEdgeGenerator( sge );
		eg.setUndirected(true);
		eg.setEdgeCount( 6 );
		
		VertexGenerator vg = new VertexGenerator( eg );
		vg.setVertexCount(5);
		
		vg.processGraph( graph );
		
	}

}
