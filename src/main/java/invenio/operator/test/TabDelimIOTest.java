package invenio.operator.test;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;
import invenio.operator.io.tab.TabDelimWriter;

import java.io.IOException;


public class TabDelimIOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		InvenioGraph g = readTabIO();
		writeTabIO(g);
	}
	
	public static InvenioGraph readTabIO() throws IOException, DataFormatException {
		
       	String nodeFile =
       		"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin.NODE.dolphin.tab";
    	String edgeFile =
    		"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin.UNDIRECTED.seenwith.tab";
    	
    	InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
    	
		System.out.println("Edges: " + g.numEdges());
		System.out.println("Vertices: " + g.numVertices());
		
		return g;

	}
	
	public static void writeTabIO(InvenioGraph g) throws IOException, DataFormatException {
		String nodeFile =
			"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin-out.NODE.dolphin.tab";
		String edgeFile =
			"C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-genderwithloc/dolphin-out.UNDIRECTED.seenwith.tab";
		(new TabDelimWriter()).writeGraph(g, nodeFile, edgeFile, true);
		
	}
}
