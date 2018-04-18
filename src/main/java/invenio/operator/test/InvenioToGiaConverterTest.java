package invenio.operator.test;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.operator.io.InvenioToGiaConverter;
import invenio.operator.io.tab.TabDelimWriter;

import java.io.IOException;

import linqs.gia.graph.Graph;


public class InvenioToGiaConverterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		String nodeFile =
	       		"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin.NODE.dolphin.tab";
		String edgeFile =
	    		"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin.UNDIRECTED.seenwith.tab";
		
		InvenioGraph g = readTabIO(nodeFile, edgeFile);
		g.setName("Test Graph");
		convertToGia(g);
	}
	
	public static InvenioGraph readTabIO(String nodeFile, String edgeFile) throws IOException, DataFormatException {
		
    	InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
    	
		System.out.println("Edges: " + g.numEdges());
		System.out.println("Vertices: " + g.numVertices());
		
		return g;

	}
	
	public static Graph convertToGia(InvenioGraph g) throws DataFormatException {
		InvenioToGiaConverter converter = new InvenioToGiaConverter();
		
		Graph giaGraph = converter.loadGraph(g, "graphSchemaId", "defNodeSchemaId", "defEdgeSchemaId");
		System.out.println("Edges: " + giaGraph.numEdges());
		System.out.println("Vertices: " + giaGraph.numNodes());
		
		return giaGraph;
	}
}
