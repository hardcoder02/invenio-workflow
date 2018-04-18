package invenio.wf.synthetic;

import invenio.data.InvenioGraph;
import invenio.operator.io.tab.TabDelimWriter;

public class GraphWriterGenerator extends GeneratorStep {
	private boolean overwrite = false;
	private String nodeFile;
	private String edgeFile;
	
	public GraphWriterGenerator() {}

	public GraphWriterGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting graph writing...");
		long startTime = System.currentTimeMillis();
		new TabDelimWriter().writeGraph(graph, nodeFile, edgeFile, overwrite);
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Graph writing completed in [" + duration + "] msec.");
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public String getNodeFile() {
		return nodeFile;
	}

	public void setNodeFile(String nodeFile) {
		this.nodeFile = nodeFile;
	}

	public String getEdgeFile() {
		return edgeFile;
	}

	public void setEdgeFile(String edgeFile) {
		this.edgeFile = edgeFile;
	}
}
