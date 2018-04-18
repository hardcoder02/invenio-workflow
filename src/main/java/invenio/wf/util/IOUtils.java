package invenio.wf.util;

import invenio.data.InvenioGraph;
import invenio.io.file.PajekFileReader;
import invenio.io.file.SparseGraphFileReader;
import invenio.operator.data.DataFormatException;
import invenio.wf.items.file.SimpleFileReader;

import java.io.IOException;

public class IOUtils {
	public static InvenioGraph readFromTabFile(String nodeFile, String edgeFile) throws IOException, DataFormatException {
		InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
		return g;
	}
	
	public static void readFromTabFile(InvenioGraph graph, String nodeFile, String edgeFile) throws IOException, DataFormatException {
		(new invenio.operator.readers.tabio.TabDelimIO()).readGraph(graph, null, nodeFile, edgeFile);
	}
	
	 
    public static InvenioGraph readFromSparseGraphFile( String fileName ) throws IOException {
    	SparseGraphFileReader reader = new SparseGraphFileReader(fileName);
		
		InvenioGraph invGraph = reader.readGraph();
		reader.close();
		return invGraph;
    }
    
	public static InvenioGraph readFromPajekFile( String fileName, String graphName ) throws IOException {
			InvenioGraph invGraph;
			PajekFileReader reader = new PajekFileReader();
	
			invGraph = reader.readGraph( fileName, graphName );
	
			return invGraph;

	}
	
	
	public static InvenioGraph readFromSimpleFile(String fileName, String graphName) throws IOException, DataFormatException {
		InvenioGraph invGraph;
		SimpleFileReader reader = new SimpleFileReader();

		invGraph = reader.readGraph( fileName, graphName );

		return invGraph;
	}
}
