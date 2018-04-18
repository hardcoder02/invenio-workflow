package invenio.wf.items.file;

import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DataMapper;
import invenio.operator.data.InvenioDataMapper;
import invenio.operator.io.GiaGraphConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import edu.uci.ics.jung.io.PajekNetReader;
import edu.uci.ics.jung.utils.UserData;
import edu.uci.ics.jung.utils.VertexGenerator;

public class SimpleFileReader {
	private HashMap<String, InvenioVertex> nodeIDHash =
			new HashMap<String, InvenioVertex>(); //keeps track of nodes added to the graph
		                                 //for easier access when adding edges
	
	public static final String TAG_OPEN = "[";
	public static final String TAG_CLOSE = "]";
	public static final String TAG_NODE = "node";
	public static final String TAG_EDGE = "edge";
	public static final String TAG_ID = "id";
	public static final String TAG_SOURCE = "source";
	public static final String TAG_TARGET = "target";
	
	public InvenioGraph readGraph(String file, String graphName) throws IOException, DataFormatException {
		InvenioGraph graph = new InvenioGraph();
		graph.setName(graphName);
		
		BufferedReader fileReader = null;
		
		try {
			fileReader = getReader(file);
			
			loadGraph(fileReader, graph);
			
			return graph;
		}
		finally {
			closeReader(fileReader);
		}
	}
	
	private void loadGraph(BufferedReader fr, InvenioGraph graph) throws DataFormatException, IOException {
		String line = fr.readLine(); //read in first line
		assert ( "graph".equalsIgnoreCase(line) );
		
		line = fr.readLine(); //read in schema line
		assert (line.startsWith( TAG_OPEN) );
		
		//add nodes to graph
		while ((line = fr.readLine()) != null) {
			if ( line.contains( TAG_NODE) )
				processNode(fr, graph);
			else if ( line.contains( TAG_EDGE) )
				processEdge(fr, graph);
			else if ( line.startsWith( TAG_CLOSE) )
				return;
		}
		
		throw new DataFormatException("Missing closing tag");
	}
	
	private void processEdge(BufferedReader fr, InvenioGraph graph) throws IOException {
		String line = fr.readLine(); //read in first line
		assert ( line.startsWith( TAG_OPEN) );
		
		line = fr.readLine(); //read in source data line
		int srcIndex = line.indexOf(TAG_SOURCE);
		assert (srcIndex >=0 );
		String srcID = line.substring(srcIndex + TAG_SOURCE.length() + 1);
		
		line = fr.readLine(); //read in target data line
		int tarIndex = line.indexOf(TAG_TARGET);
		assert (tarIndex >=0 );
		String tarID = line.substring(tarIndex + TAG_TARGET.length() + 1);
		
		line = fr.readLine(); //read in close tag
		assert (line.startsWith( TAG_CLOSE) );
		
//		String edgeID = tokens[0];
		
		//skip incorrect edges
		if (srcID == null || tarID == null || srcID.isEmpty() || tarID.isEmpty())
			return;
		
		InvenioVertex n1 = nodeIDHash.get(srcID);
		InvenioVertex n2 = nodeIDHash.get(tarID);
		InvenioEdge edge = new InvenioEdge(n1, n2);
		graph.addEdge( edge );
	}

	private void processNode(BufferedReader fr, InvenioGraph graph) throws IOException {
		String line = fr.readLine(); //read in first line
		assert ( line.startsWith( TAG_OPEN) );
		
		line = fr.readLine(); //read in data line
		int idIndex = line.indexOf(TAG_ID);
		assert (idIndex >=0 );
		String nodeID = line.substring(idIndex + TAG_ID.length() + 1);
		
		line = fr.readLine(); //read in close tag
		assert (line.startsWith( TAG_CLOSE) );
		
		InvenioVertex node = new InvenioVertex();
		
		node.setKeyAttribute(Constants.DEFAULT_KEY_ATTRIBUTE);
		node.setKey(nodeID);
		node.setName(nodeID);
		node.setInfoLabel(nodeID);
		
		nodeIDHash.put(nodeID, node);
		
		graph.addVertex(node);
		
	}

	private BufferedReader getReader(String fileName) throws IOException {
		return new BufferedReader( new FileReader(fileName) );
	}
	
	private void closeReader(Reader r) {
		try {
			if (r != null)
				r.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
