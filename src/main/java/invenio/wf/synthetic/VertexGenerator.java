package invenio.wf.synthetic;

import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;

public class VertexGenerator extends GeneratorStep {
	public final static int DEFAULT_VERTEX_COUNT = 50;
	private int vertexCount = DEFAULT_VERTEX_COUNT;
	
	public VertexGenerator() {}
	public VertexGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting vertex generation...");
		long startTime = System.currentTimeMillis();
		long cycleStartTime = startTime;
		for (int i = 0; i < vertexCount; i++) {
			InvenioVertex v = createVertex( );
			graph.addVertex(v);
			if ( i % 10000 == 0) {
				long cycleDuration = System.currentTimeMillis() - cycleStartTime;
				System.out.println("Generated " + i + " vertices in [" + cycleDuration + "] msec");
				cycleStartTime = System.currentTimeMillis();
			}
		}
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Vertex generation completed in [" + duration + "] msec.");
	}

	/*
	 * In a separate method in case additional processing on creation is required in the future.
	 */
	protected InvenioVertex createVertex( ) {
		InvenioVertex v = new InvenioVertex();
		
		return v;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		if (vertexCount < 0)
			throw new IllegalArgumentException("Vertex count must be non-negative: " + vertexCount);
		this.vertexCount = vertexCount;
	}
	
	
}
