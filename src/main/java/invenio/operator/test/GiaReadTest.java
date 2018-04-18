package invenio.operator.test;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.operator.io.GiaFileReader;
import invenio.operator.io.GiaGraphConverter;

import java.io.IOException;

import linqs.gia.graph.Graph;

public class GiaReadTest {
	public static final String DEFAULT_CONFIG_FILE = "resource/SampleFiles/GraphVisualizationExperimentSample/experiment.cfg";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String configFile =
			(args.length >= 1) ? args[0] : DEFAULT_CONFIG_FILE;
		new GiaReadTest().testReadFromGia(configFile);
	}
	
	public InvenioGraph testReadFromGia(String configFile) throws IOException, DataFormatException {
		Graph giaGraph = readFromGIA(configFile);
		InvenioGraph invGraph = convertFromGia(giaGraph);
		
		System.out.println("GIA");
		System.out.println("Nodes: " + giaGraph.numNodes());
		System.out.println("Edges: " + giaGraph.numEdges());
		
		System.out.println("INVENIO");
		System.out.println("Nodes: " + invGraph.numVertices());
		System.out.println("Edges: " + invGraph.numEdges());
		
		return invGraph;
	}

	public Graph readFromGIA(String configFile) throws IOException {
		Graph giaGraph = GiaFileReader.readGraph(configFile);
		return giaGraph;
	}
	
	public InvenioGraph convertFromGia(Graph giaGraph) throws DataFormatException {
		return new GiaGraphConverter().convertfromGia(giaGraph);
	}
}
