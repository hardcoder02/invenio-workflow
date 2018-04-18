package invenio.wf.test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Schema;
import invenio.wf.util.IOUtils;

public class ParallelTabReader {

	public ParallelTabReader() {
		// TODO Auto-generated constructor stub
	}
	
	public static void loadGraph(QueryVisTestTemp q, InvenioGraph graph, String edgeFile,
		String... nodeFile) throws DataFormatException, IOException {
		
		long startTime = System.currentTimeMillis();
		System.out.println("Loading graph...");
		
		TabDelimIOTemp t = new TabDelimIOTemp();
		t.readNodes(graph, nodeFile[0], true);
		
		EdgeLoader el = new EdgeLoader(graph, edgeFile, q, startTime);
		CyclicBarrier barrier = new CyclicBarrier(nodeFile.length - 1, el);
		for ( int i = 1; i < nodeFile.length; i++) {
			new VertexLoader(barrier, nodeFile[i], graph, t.nSchema);
		}
	}
	
	// The run() method in this thread should be called only after nodes are
	// loaded
	private static class EdgeLoader extends Thread {
		private InvenioGraph graph;
		private String fileName;
		private QueryVisTestTemp q;
		private long startTime;

		public EdgeLoader() {
		}

		public EdgeLoader(InvenioGraph graph, String fileName, QueryVisTestTemp q, long startTime) {
			this.fileName = fileName;
			this.graph = graph;
			this.q = q;
			this.startTime = startTime;
		}

		public void run() {
			long duration = System.currentTimeMillis() - startTime;
			System.out.println("Vertices loaded in (msec): " + duration);

			System.out.println("Loading edges");
			try {
				(new TabDelimIOTemp()).readEdges(graph, fileName);
				duration = System.currentTimeMillis() - startTime;
				System.out.println("Graph loaded in (msec): " + duration);

				q.cont();
			}
			catch (Exception ex) {
				System.out.println("An exception occurred while waiting... "
						+ ex);
			}
		}
	}

	// This thread loads vertices
	// Once vertices are loaded, the tread should wait for other vertex loading
	// threads to arrive
	private static class VertexLoader extends Thread {
		CyclicBarrier waitPoint;
		String nodeFile;
		InvenioGraph graph;
		Schema s;

		public VertexLoader(CyclicBarrier barrier, String fileName,
				InvenioGraph graph, Schema schema) {
			waitPoint = barrier;
			this.nodeFile = fileName;
			this.graph = graph;
			this.s = schema;
			this.start();
		}

		public void run() {
			try {
				TabDelimIOTemp t = new TabDelimIOTemp();
				t.nSchema = s;
				t.readNodes(graph, nodeFile, false);
				waitPoint.await(); // await for all four players to arrive
			} catch (Exception ex) {
				System.out.println("An exception occurred while waiting... "
						+ ex);
			}
		}

	}
}