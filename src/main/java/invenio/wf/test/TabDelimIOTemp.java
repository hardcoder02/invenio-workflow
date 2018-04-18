package invenio.wf.test;

import invenio.data.InvenioDirectedEdge;
import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DataMapper;
import invenio.operator.data.DuplicateElementException;
import invenio.operator.data.InvenioDataMapper;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeatureDescriptor;
import invenio.operator.data.UnsupportedFormatException;
import invenio.operator.io.GiaGraphConverter;
import invenio.operator.readers.tabio.CatFeature;
import invenio.operator.readers.tabio.Feature;
import invenio.operator.readers.tabio.FeatureSchema;
import invenio.operator.readers.tabio.NumericFeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import edu.uci.ics.jung.utils.UserData;

// TODO: refactor, move to right package

public class TabDelimIOTemp {	
	
	public static String NUMERIC_TYPE = "numeric";
	public static String STRING_TYPE = "string";
	public static String CAT_TYPE = "cat";
	public static String MULTICAT_TYPE = "multicat";
	
	private InvenioGraph graph = null;
	private FeatureSchema nodeSchema = null;
	private FeatureSchema edgeSchema = null;
	
	public Schema nSchema = null;
	private Schema eSchema = null;
	
	public static final String NO_FEATURES = "NO_FEATURES";
	
	private HashMap<String, InvenioVertex> nodeIDHash =
		new HashMap<String, InvenioVertex>(); //keeps track of nodes added to the graph
	                                 //for easier access when adding edges
	
	
	/**
	 * Reads data from a tab delimited format and puts it into the given graph
	 * object.
	 * 
	 * @param InvenioGraph the graph to add nodes and edges to
	 * @param graphFile GRAPH file (currently not used)
	 * @param nodesFile NODE file
	 * @param edgesFile UNDIRECTED or DIRECTED file
	 * @return Prefuse Graph object
	 */
	public void readNodes(InvenioGraph graph, String nodeFile, boolean initial) throws IOException, DataFormatException {
		
		this.graph = graph;
		BufferedReader nodeReader = null;
		
		try {
			nodeReader = getReader(nodeFile);
			readNodes(nodeReader, initial);
		}
		finally {
			closeReader(nodeReader);
		}
	}
	
	public void readEdges(InvenioGraph graph, String edgesFile) throws IOException, DataFormatException {
		
		this.graph = graph;
		BufferedReader edgeReader = null;
		
		try {
			edgeReader = getReader(edgesFile);
			
			for (InvenioVertex v : graph.getVertices()) {
				nodeIDHash.put("" + v.getKey(), v);
			}
			
			readEdges(edgeReader);
		}
		finally {
			closeReader(edgeReader);
		}	
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
	
	/**
	 * Parses a NODE file.
	 * 
	 * @param fr
	 */
	private void readNodes(BufferedReader fr, boolean initial) throws DataFormatException, IOException {
		String line = null;
		if (initial) {
			line = fr.readLine(); //read in first line
			parseNodeHeader(line);
			
			line = fr.readLine(); //read in schema line
			
			nSchema = getInvenioSchema(line);
			assert(nSchema != null);
		}
		
		int lineNum = 0;
		long startTime = System.currentTimeMillis();
		
		//add nodes to graph
		while ((line = fr.readLine()) != null) {
			parseNode(line);
			if ( ++lineNum % 10000 == 0) {
				long duration = System.currentTimeMillis() - startTime;
				System.out.println("Read lines: " + lineNum + " in (msec: " + duration);
				startTime = System.currentTimeMillis();
			}
		}
	}
	
	private void parseNodeHeader(String line) throws DataFormatException {
		String[] tokens = line.split("\t");
		if (tokens.length >= 2)
			setNodeSchemaId(tokens[1]);
	}
	
	private void setNodeSchemaId(String schemaId) {
		graph.addUserDatum(Constants.NODE_SCHEMAID_ATTRIBUTE, schemaId, UserData.SHARED);
	}
	
	private void parseEdgeHeader(String line) throws DataFormatException {
		String[] tokens = line.split("\t");
		if (tokens.length >= 2)
			setEdgeSchemaId(tokens[1]);
	}
	
	private void setEdgeSchemaId(String schemaId) {
		graph.addUserDatum(Constants.EDGE_SCHEMAID_ATTRIBUTE, schemaId, UserData.SHARED);
	}
	
	/**
	 * Given an instanceline in a NODE file, create a new Node, add the
	 * appropriate data to it, and add the node to the graph
	 * 
	 * @param line an instanceline in a NODE file.
	 */
	private void parseNode(String line) throws UnsupportedFormatException, MissingElementException, DuplicateElementException {
		
//		String[] tokens = line.split("\t");
//		HashMap<String, Feature> features = nodeSchema.getSchema();
//		String nodeID = tokens[0];
//		InvenioVertex node = new InvenioVertex();
//		
//		node.setKeyAttribute(DEFAULT_KEY_ATTRIBUTE);
//		node.setKey(nodeID);
//		node.setName(nodeID);
//		node.setInfoLabel(nodeID);
//		
//		nodeIDHash.put(nodeID, node);
//		
//		for (int i = 1; i < tokens.length; i++) {
//			int eqI = tokens[i].indexOf('=');
//			String id = tokens[i].substring(0, eqI);
//			String data = tokens[i].substring(eqI + 1, tokens[i].length());
//			
//			features.get(id).addToGraph(node, data);
//		}
//		
//		graph.addVertex(node);
		
		String[] tokens = line.split("\t");
		String nodeID = tokens[0];
		InvenioVertex node = new InvenioVertex();
		
		node.setKeyAttribute(Constants.DEFAULT_KEY_ATTRIBUTE);
		node.setKey(nodeID);
		node.setName(nodeID);
		node.setInfoLabel(nodeID);
		
//		nodeIDHash.put(nodeID, node);
		
		DataMapper dm = new InvenioDataMapper(node);
		ComparableFeatureContainer c = new ComparableFeatureContainer(nSchema, dm);
		node.addInvenioDatum(Constants.CONTAINER_ATTRIBUTE, c, UserData.SHARED);

		
		invenio.operator.data.Feature feature = c.instantiateFeature("id");
		setupUserData(feature, nodeID);
		
		for (int i = 1; i < tokens.length; i++) {
			int eqI = tokens[i].indexOf('=');
			String id = tokens[i].substring(0, eqI);
			//String data = tokens[i].substring(eqI + 1, tokens[i].length());
			int eq1 = eqI+1;
			int len = tokens[i].length();
			String data = tokens[i].substring(eq1, len);
			
			feature = c.instantiateFeature(id);
			setupUserData(feature, data);
		}
		
		graph.addVertex(node);
	}

	/**
	 * Given a schema line returns a FeatureSchema object representation of
	 * the schema.
	 * 
	 * @param line a schemaline
	 * @return
	 */
	private FeatureSchema getSchema(String line) {
		String[] tokens = line.split("\t");
		FeatureSchema schema = new FeatureSchema();
		
		for (String s : tokens) {
			String[] fStr = s.split(":");
			Feature f = null;
			String id = null;
			
			if (fStr[0].equals(NUMERIC_TYPE)) {
				id = fStr[1];
				f = new NumericFeature(id);
				if (fStr.length > 2) {
					((NumericFeature)f).setValue(Double.parseDouble(fStr[2]));
				}
			} else if (fStr[0].substring(0, CAT_TYPE.length()).equals(CAT_TYPE)) {
				String[] cats = fStr[0].substring(fStr[0].indexOf((int)'=') + 1)
						.split(",");
				id = fStr[1];
				f = new CatFeature(id, cats);
				if (fStr.length > 2) {
					((CatFeature)f).setValue(fStr[2]);
				}
			}
			
			if (null != f && null != id) {
				schema.addFeature(id, f);
			}
		}
		
		return schema;
	}
	
	/**
	 * Given a schema line returns a Schema object representation of
	 * the schema.
	 * 
	 * @param line a schemaline
	 * @return
	 */
	private Schema getInvenioSchema(String line) throws DataFormatException {
		String[] tokens = line.split("\t");
		ComparableFeatureContainer c = new ComparableFeatureContainer();
		
		// setup ID
		// TODO: name for id feature
		c.addStringFeatureDescriptor("id");
		
		for (String s : tokens) {
			String[] fStr = s.split(":");
			
			String id = null;
			
			if (fStr.length == 1 && NO_FEATURES.equalsIgnoreCase( fStr[0]) ) {
				// do nothing
			}
			else {
				
				if (fStr[0].equals(NUMERIC_TYPE)) {
					id = fStr[1];
					NumericFeatureDescriptor f = c.addNumericFeatureDescriptor(id);
					if (fStr.length > 2) {
						f.setDefaultValue( Double.parseDouble(fStr[2]) );
					}
				} 
				else if (fStr[0].equals(STRING_TYPE)) {
					id = fStr[1];
					StringFeatureDescriptor f = c.addStringFeatureDescriptor(id);
					if (fStr.length > 2) {
						f.setDefaultValue( fStr[2] );
					}
				} 
				else if (fStr[0].substring(0, CAT_TYPE.length()).equals(CAT_TYPE)) {
					String[] cats = fStr[0].substring(fStr[0].indexOf((int)'=') + 1)
							.split(",");
					id = fStr[1];
					CategoricalFeatureDescriptor f = c.addCategoricalFeatureDescriptor(id);
					f.setCategories(cats);
					if (fStr.length > 2) {
						f.setDefaultCategory( f.getCategory(fStr[2]) );
					}
				}
				else
					throw new UnsupportedFormatException("Unsupported feature: " + line);
			}
		}
		
		c.lockSchema();
		return c.getSchema();
	}

	/**
	 * Parses an UNDIRECTED or DIRECTED file.
	 * 
	 * @param fr
	 */
	private void readEdges(BufferedReader fr) throws DataFormatException, IOException {
		String line = fr.readLine(); //read in first line
		parseEdgeHeader(line);
		
		line = fr.readLine(); //read in schema line
		
//		edgeSchema = getSchema(line);
//		assert(edgeSchema != null);
		
		eSchema = getInvenioSchema(line);
		assert(eSchema != null);
		
//		HashMap<String, Feature> features = edgeSchema.getSchema();
//		
//		//create node schema in graph object
//		for (String key : features.keySet()) {
//			//features.get(key).addColumn(graph.getEdgeTable());
//		}
		
		//add probability and ID columns to node table
//		graph.getEdgeTable().addColumn("prob", float.class);
//		graph.getEdgeTable().addColumn("ID", String.class);
		
		int lineNum = 0;
		long startTime = System.currentTimeMillis();
		
		//add edges to graph
		while ((line = fr.readLine()) != null) {
			parseEdge(line);
			
			if ( ++lineNum % 10000 == 0) {
				long duration = System.currentTimeMillis() - startTime;
				System.out.println("Read lines: " + lineNum + " in (msec: " + duration);
				startTime = System.currentTimeMillis();
			}
		}
		
	}
	
	private void setupUserData(invenio.operator.data.Feature feature, String data) throws UnsupportedFormatException, MissingElementException {
		if (feature instanceof invenio.operator.data.NumericFeature)
			setupFeature((invenio.operator.data.NumericFeature) feature, data);
		else if (feature instanceof invenio.operator.data.StringFeature)
			setupFeature((invenio.operator.data.StringFeature) feature, data);
		else if (feature instanceof CategoricalFeature )
			setupFeature((CategoricalFeature) feature, data);
		else
			throw new UnsupportedFormatException(
					"Unsupported feature type for feature: " + feature.getFeatureDescriptor().getName());
	}
	
	private void setupFeature(invenio.operator.data.NumericFeature feature, String data) {
		String[] val = data.split(":");
		
		feature.setValue( Double.parseDouble(val[0]) );
	}
	
	private void setupFeature(invenio.operator.data.StringFeature feature, String data) {
		feature.setValue( data );
	}
	
	private void setupFeature(CategoricalFeature feature, String data) throws MissingElementException {
		String[] tokens = data.split(":");
		//HashMap<String, Float> values = new HashMap<String, Float>();
		
		feature.setSelectedValue(tokens[0]);
		
		if (tokens.length > 1) {
			//set probabilities
			String[] probs =
				tokens[1].substring(tokens[1].indexOf("=") + 1).split(",");
//			String[] probs = tokens; //should change to adhere to tabio specs
			
			assert(probs.length == feature.getFeatureDescriptor().getNumCategories());
			
			for (int i = 0; i < probs.length; i++) {
				feature.setProbability(i, Float.parseFloat(probs[i]) );
			}
		}
		else {
			feature.setProbability( feature.getSelectedIndex(), 1);
		}
	}
	
	/**
	 * Given an instanceline in an UNDIRECTED or DIRECTED file, create a new
	 * Edge, add the appropriate data to it, and add the node to the graph
	 * 
	 * @param line an instanceline in an UNDIRECTED or DIRECTED file.
	 */
	private void parseEdge(String line) throws MissingElementException, DuplicateElementException, UnsupportedFormatException {
//		String[] tokens = line.split("\t");
//		assert(tokens.length >= 3);
//		HashMap<String, Feature> features = edgeSchema.getSchema();
//		String edgeID = tokens[0];
//		String node1 = tokens[1];
//		String node2 = tokens[2];
//		
//		node1 = node1.substring(node1.indexOf(":") + 1);
//		node2 = node2.substring(node2.indexOf(":") + 1);
//		
//		String[] attributes = subArray(tokens, 4, tokens.length);
//		InvenioVertex n1 = nodeIDHash.get(node1);
//		InvenioVertex n2 = nodeIDHash.get(node2);
//		InvenioEdge edge = new InvenioEdge(n1, n2);
//		graph.addEdge( edge );
//		
//		edge.setKeyAttribute(GiaGraphConverter.DEFAULT_KEY_ATTRIBUTE);
//		edge.setKey(edgeID);
//		edge.setName(edgeID);
//		edge.setInfoLabel(edgeID);
//		
//		for (int i = 0; i < attributes.length; i++) {
//			int eqI = attributes[i].indexOf('=');
//			String id = attributes[i].substring(0, eqI);
//			String data = attributes[i].substring(eqI + 1, attributes[i].length());
//			
//			features.get(id).addToGraph(edge, data);
//		}
		
		String[] tokens = line.split("\t");
		assert(tokens.length >= 3);
		
		//skip incorrect edges
		if (tokens.length < 3)
			return;
		String edgeID = tokens[0];
		String node1 = tokens[1];
		String node2 = tokens[2];
		
		//skip incorrect edges
		if (node1 == null || node2 == null || node1.isEmpty() || node2.isEmpty())
			return;
		
		node1 = node1.substring(node1.indexOf(":") + 1);
		node2 = node2.substring(node2.indexOf(":") + 1);
		
		String[] attributes = subArray(tokens, 4, tokens.length);
		InvenioVertex n1 = nodeIDHash.get(node1);
		InvenioVertex n2 = nodeIDHash.get(node2);
		
		assert ( n1 != null && n2 != null ) : 
			"Missing node for edge: " + edgeID + "(" + node1 +", " + node2 + ")";
		
//		InvenioEdge edge = new InvenioEdge(n1, n2);
		InvenioEdge edge = new InvenioDirectedEdge(n1, n2);
		graph.addEdge( edge );
		
		edge.setKeyAttribute(GiaGraphConverter.DEFAULT_KEY_ATTRIBUTE);
		edge.setKey(edgeID);
		edge.setName(edgeID);
		edge.setInfoLabel(edgeID);
		
		DataMapper dm = new InvenioDataMapper(edge);
		ComparableFeatureContainer c = new ComparableFeatureContainer(eSchema, dm);
		edge.addInvenioDatum(Constants.CONTAINER_ATTRIBUTE, c, UserData.SHARED);
		
		invenio.operator.data.Feature feature = c.instantiateFeature("id");
		setupUserData(feature, edgeID);
		
		if (attributes != null) {
			for (int i = 0; i < attributes.length; i++) {
				int eqI = attributes[i].indexOf('=');
				String id = attributes[i].substring(0, eqI);
				String data = attributes[i].substring(eqI + 1, attributes[i].length());
				
				feature = c.instantiateFeature(id);
				setupUserData(feature, data);
			}
		}
	}

	/**
	 * Helper function to get a line of text given a FileReader
	 * 
	 * @param fr
	 * @return either the next line of text in fr or null if there is no more
	 *   input.
	 */
	private String getLine(FileReader fr) {
		StringBuffer input = new StringBuffer();
		
		try {
			char c = (char)fr.read();
			
			while ('\n' != c && -1 != (byte)c) {
				input.append(c);
				c = (char)fr.read();
			}
			
			if (input.length() > 0) {
				return input.toString();
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Helper function to return a subarray.
	 * 
	 * @param a
	 * @param start
	 * @param end
	 * @return
	 */
	private String[] subArray(String[] a, int start, int end) {
		if (start < 0 || end > a.length || start >= end) {
			return null;
		}
		
		int length = end - start;
		String[] b = new String[length];
		
		for (int i = 0;i < length; i++) {
			b[i] = new String(a[i + start]);
		}
		
		return b;
	}
	
}
