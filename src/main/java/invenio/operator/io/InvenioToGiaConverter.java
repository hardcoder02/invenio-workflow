package invenio.operator.io;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DuplicateElementException;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.StringFeatureDescriptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uci.ics.jung.utils.UserData;

import qng.client.QueryException;

import linqs.gia.configurable.BaseConfigurable;
import linqs.gia.exception.ConfigurationException;
import linqs.gia.exception.FileFormatException;
import linqs.gia.feature.CategFeature;
import linqs.gia.feature.DerivedFeature;
import linqs.gia.feature.ExplicitFeature;
import linqs.gia.feature.Feature;
import linqs.gia.feature.MultiCategFeature;
import linqs.gia.feature.NumFeature;
import linqs.gia.feature.StringFeature;
import linqs.gia.feature.decorable.Decorable;
import linqs.gia.feature.explicit.ExplicitCateg;
import linqs.gia.feature.explicit.ExplicitMultiCateg;
import linqs.gia.feature.explicit.ExplicitNum;
import linqs.gia.feature.explicit.ExplicitString;
import linqs.gia.feature.schema.Schema;
import linqs.gia.feature.schema.SchemaType;
import linqs.gia.feature.values.CategValue;
import linqs.gia.feature.values.FeatureValue;
import linqs.gia.feature.values.MultiCategValue;
import linqs.gia.feature.values.NumValue;
import linqs.gia.feature.values.StringValue;
import linqs.gia.feature.values.UnknownValue;
import linqs.gia.graph.DirectedEdge;
import linqs.gia.graph.Graph;
import linqs.gia.graph.GraphItem;
import linqs.gia.graph.Node;
import linqs.gia.graph.UndirectedEdge;
import linqs.gia.identifiable.GraphID;
import linqs.gia.identifiable.GraphItemID;
import linqs.gia.identifiable.ID;
import linqs.gia.log.Log;
import linqs.gia.util.ArrayUtils;
import linqs.gia.util.Dynamic;
import linqs.gia.util.FileIO;
import linqs.gia.util.IteratorUtils;
import linqs.gia.util.ListUtils;
import linqs.gia.util.SimplePair;

/**
 * Tab delimited input format consisting of files in the form of:
 * <p>
 * <code>
 * [GRAPH|NODE|DIRECTED|UNDIRECTED]\t&lt;schemaid&gt;<br>
 * &lt;schemaline&gt;<br>
 * &lt;&lt;instanceline\n&gt;*&gt;<br>
 * </code>
 * <p>
 * where
 * <p>
 * <code>
 * &lt;schemaline&gt; := &lt;feature declaration&gt;\t&lt;&lt;feature declaration&gt;+&gt;<br>
 * &lt;schemaline&gt; := NO_FEATURES<br>
 * &lt;feature declaration&gt; := 
 * [string,numeric,cat=&lt;categories&gt;,multicat=&lt;categories&gt;]:&lt;featureid&gt;[:&lt;defaultvalue&gt;]<br>
 * NO_FEATURES means this schema has no features defined for it<br>
 * &lt;categories&gt; is a comma delimited list of string categories<br>
 * &lt;defaultvalue&gt; is an optional setting for each feature<br>
 * </code>
 * <p>
 * For GRAPH and NODE, the instance lines are of the form:
 * <p>
 * <code>
 * &lt;instanceline&gt; := &lt;objectid&gt;\t&lt;featurevalue&gt;+<br>
 * &lt;featurevalue&gt; := &lt;value&gt[:P=&lt;probability&gt;]<br>
 * &lt;probability&gt; is a comma delimited list of probabilities.  See cat and multicat below for details.
 * </code>
 * <p>
 * Edge files have a different form whether or not it is a DIRECTED or UNDIRECTED edge.
 * An UNDIRECTED edge files need an additional term to include all the nodes it is adjacent to
 * in the form:
 * <p>
 * <code>
 * &lt;instanceline&gt; := &lt;objectid&gt;\t&lt;nodeid&gt;+\t|\t&lt;featurevalue&gt;<br>
 * nodeid := &lt;schemaid&gt;:&lt;objectid&gt;
 * </code>
 * <p>
 * An DIRECTED edge files need two additional terms to include all the nodes it is adjacent to
 * (the first set being the source nodes and the second set being the target nodes)
 * in the form:
 * <p>
 * <code>
 * &lt;instanceline&gt; := &lt;objectid&gt;\t&lt;nodeid&gt;+\t|\t&lt;nodeid&gt;+\t|\t&lt;featurevalue&gt;<br>
 * nodeid := &lt;schemaid&gt;:&lt;objectid&gt;
 * </code>
 * <p>
 * To simplify formats for very simple edge types with all nodes belonging to the same schema,
 * immediately before the &lt;schemaline&gt;, you can specify a line of the form:
 * <p>
 * <code>
 * NODESCHEMAID\t&lt;nodeschemaid&gt;
 * </code>
 * <p>
 * where nodeschemaid is the schema id all nodes in this edge file
 * should be assigned.  If this header is set, instances of &lt;nodeid&gt;
 * should only contain &lt;objectid&gt;.
 * <p>
 * The input format consists of multiple files which contain the declaration for
 * the graph, nodes, and edges of the graph being loaded.  The files contain information
 * about the schema of each item, as well as the declaration of the node and edge existence
 * and feature values.
 * <p>
 * There are four supported feature types:
 * <UL>
 * <LI> string-String valued feature
 * <LI> numeric-Numeric valued feature (treated as a Double)
 * <LI> cat-Categorical valued feature.  Similar to string but the values are limited to the specified categories.
 * Categorical features have corresponding probabilities assigned to them.  For a feature with n categories,
 * n numbers can be specified to represent the likelihood of each category.
 * <LI> multicat-Multi Categorical valued features.  Similar to cat but can hold multiple values.  Multi categorical
 * features have corresponding probabilities assigned to them.  If an instance has n categories as values, n
 * numbers can be specified to represent the likelihood of each category.
 * </UL>
 * <p>
 * Notes:
 * <UL>
 * <LI> Sample files available under resource/SampleFiles/TabDelimIOSamples.
 * <LI> All lines which begin with a pound sign (#)
 * and all lines which are empty (including those consisting only of whitespace) are skipped.
 * <LI> You can declare items with the same schema across multiple files
 * (i.e., declare people nodes across multiple files for manageability).  The schema declaration
 * is assumed to match across all those files and exception is thrown if it isn't.
 * <LI> If a node specified in an edge file does not exist in the graph, a warning is printed
 * and that edge is not added.
 * </UL>
 * 
 * Required Parameters:
 * <UL>
 * <LI> For loading:
 *      <UL>
 *      <LI> graphclass-Full java class for the graph.
 *      Defaults is linqs.gia.graph.datagraph.DataGraph.
 *      <LI> files-Comma delimited list of the files to use.
 *      Files must be listed in order Graph file, Node files and Edge files.
 *      This parameter is used over filedir if both are specified.
 *      Not required if filedir is specified.
 *      <LI> filedirectory-Directory of files to load.  The input will try to load
 *      all files in the directory and will throw a warning for files it cannot load.
 *      Not required if files is specified.
 * </UL>
 * <LI> For saving:
 *      <UL>
 *      <LI> filedirectory-Directory to store all the resulting files
 *      <LI> fileprefix-Prefix to use in naming the resulting files
 *      </UL>
 * </UL>
 * 
 * Optional Parameters:
 * <UL>
 * <LI> For loading:
 * 		<UL>
 * 		<LI> loadfids-Comma delimited list of feature ids.  If set,
 * 		load only the feature values for the specified feature ids.
 * 		This will save both time and memory for loading graphs
 * 		with large numbers of features.
 * 		</UL>
 * <LI> For saving:
 * 		<UL>
 * 		<LI> savesids-Comma delimited list of feature schema IDs.  If specified,
 * 		during saving, only the graph items with the specified
 * 		schema ID will be saved
 * 		<LI> savederived-If yes, save the values of derived features.  If no,
 * 		do not save the value of derived features.  Default is no.
 * 		</UL>
 * </UL>
 */
public class InvenioToGiaConverter extends BaseConfigurable {

	public final static String UNKNOWNVALUE = "?";
	public final static String PROB_DELIMITER = ":P=";
	private final static String DEFAULT_GRAPH_CLASS = "linqs.gia.graph.datagraph.DataGraph";

	/**
	 * Supported Types
	 * 
	 */
	private enum SupportedTypes {
		string,
		numeric,
		cat,
		multicat
	}

	public Graph loadGraph(InvenioGraph invGraph, String graphSchemaId, String defNodeSchemaId, String defEdgeSchemaId) throws DataFormatException {
		// Handle graph and graph features first
		Graph g = this.handle_graph_file(invGraph, graphSchemaId);

		
		// get node and edge schema id, if any
		String nId = getNodeSchemaId(invGraph);
		nId = (nId != null) ? nId : defNodeSchemaId;
		String eId = getEdgeSchemaId(invGraph);
		eId = (eId != null) ? eId : defEdgeSchemaId;
		
		importVertices(invGraph, g, graphSchemaId, nId);
		importEdges(invGraph, g, graphSchemaId, nId, eId);

		return g;
	}

	
	/**
	 * Private method to handle the insertion of graph features into graph
	 *
	 */
	private Graph handle_graph_file(InvenioGraph invGraph, String schemaID) {

		Graph g = null;
		
		// Create Graph
		String objID = invGraph.getName();
		GraphID id = new GraphID(schemaID, objID);
		Class<?>[] argsClass = new Class[]{GraphID.class};
		Object[] argValues = new Object[]{id};

		String graphclass = InvenioToGiaConverter.DEFAULT_GRAPH_CLASS;
		
		g = (Graph) Dynamic.forName(Graph.class,
				graphclass,
				argsClass,
				argValues);

//		// TODO: Create schema for graph
//		handle_schema_line(g, SchemaType.GRAPH, schemaID, secondline);
//		this.addFeatureValues(g, (String[]) ArrayUtils.subarray(valparts, 1), loadfids);

		return g;
	}

	
	/**
	 * Import vertices from InvenioGraph to Gia Graph.
	 * 
	 * @param invGraph
	 * @param giaGraph
	 * 
	 */
	private void importVertices(InvenioGraph invGraph, Graph giaGraph, String graphSchemaId, String nSchemaId) 
				throws DataFormatException {

		invenio.operator.data.Schema prevSchema = null;
		Iterator<InvenioVertex> vIter  = invGraph.getVertices().iterator();
		
		while (vIter.hasNext()) {
			InvenioVertex v = vIter.next();
			
			prevSchema = handle_schema_line(giaGraph, SchemaType.NODE, nSchemaId, v, prevSchema);
			handle_node_file(giaGraph, nSchemaId, v);
		}

		// TODO: add relation? See DatabaseGraphImporter
	}
	
	
	/**
	 * 
	 * 
	 */
	private void importEdges(InvenioGraph invGraph, Graph giaGraph, String graphSchemaId, String nSchemaId, String eSchemaId) throws DataFormatException {
		
		invenio.operator.data.Schema prevSchema = null;
		Iterator<InvenioEdge> eIter  = invGraph.getEdges().iterator();
		
		while (eIter.hasNext()) {
			InvenioEdge e = eIter.next();
			
			prevSchema = handle_schema_line(giaGraph, SchemaType.UNDIRECTED, eSchemaId, e, prevSchema);
			handle_undirected_file(giaGraph, nSchemaId, eSchemaId, e);
		}
	}
	
	
	
	/**
	 * Parse the schema line and add the graph.
	 * 
	 * @param g
	 * @param type
	 * @param defSchemaID
	 * @param e
	 */
	private invenio.operator.data.Schema handle_schema_line(Graph g, SchemaType type, 
			String schemaID, InvenioElement e, invenio.operator.data.Schema prevSchema) throws DataFormatException {
		
		ComparableFeatureContainer c = getFeatureContainer(e);
		invenio.operator.data.Schema curSchema = c.getSchema();
		
		if ( prevSchema != null ) {
			if ( prevSchema.isEquivalentTo(curSchema) )		// schema already handled
				return curSchema;
			else
				throw new DataFormatException("Unexpected unequivalent schema");
		}
		
		
		// If the schema is already defined, assume thats the one we're using.
		if(g.hasSchema(schemaID)){
			Log.DEBUG("Schema already defined: "+schemaID);
			return curSchema;
		}

		// Parse schema 
		Schema schema = new Schema(type);
		for ( int i = 0; i < curSchema.size(); i++ ) {
			
			Feature newfeature = null;

			// TODO: re-program skipping id
			if ("id".equals( curSchema.getFeatureDescriptor(i).getName()) )
				continue;
			
			if( curSchema.canGetAsNumeric(i) ) {
				NumericFeatureDescriptor fd = curSchema.getAsNumeric(i);
				if(fd.hasDefaultValue()) {
					newfeature = new ExplicitNum(
							new NumValue(fd.getDefaultValue() ));
				} else {
					newfeature = new ExplicitNum();
				}
			} else if ( curSchema.canGetAsString(i) ) {
				StringFeatureDescriptor fd = curSchema.getAsString(i);
				if(fd.hasDefaultValue()) {
					newfeature = new ExplicitString(
							new StringValue( fd.getDefaultValue() ));
				} else {
					newfeature = new ExplicitString();
				}
			} else if ( curSchema.canGetAsCategorical(i) ) {
				CategoricalFeatureDescriptor fd = curSchema.getAsCategorical(i);
				String[] categ =  fd.getCategories();
				
				List<String> categories = Arrays.asList(categ);

				if( fd.hasDefaultCategory() ) {
					newfeature = new ExplicitCateg(categories,
							this.parseFeatureValue(new ExplicitCateg(categories), fd.getDefaultCategory()));
				} else {
					newfeature = new ExplicitCateg(categories);
				}
// TODO: add support for multicat					
//				} else if(ftype[0].startsWith(SupportedTypes.multicat.name())){
//					List<String> categories = Arrays.asList(ftype[1].split(","));
//
//					if(closeddefault==null) {
//						newfeature = new ExplicitMultiCateg(categories);
//					} else {
//						newfeature = new ExplicitMultiCateg(categories,
//								this.parseFeatureValue(new ExplicitMultiCateg(categories), closeddefault));
//					}
			} else {
				throw new DataFormatException("Unsupported feature type");
			}

			schema.addFeature(curSchema.getFeatureDescriptor(i).getName(), newfeature);
		}

		g.addSchema(schemaID, schema);
		return curSchema;
	}
	
	/**
	 * Private method to handle the insertion of all nodes from a given
	 * filename into graph
	 *
	 */
	private void handle_node_file(Graph g, String schemaID, InvenioVertex v) throws DataFormatException {

		String nodeId = v.getKey().toString();
		GraphItemID gid = new GraphItemID((GraphID) g.getID(), schemaID, nodeId);
		Node node = g.addNode(gid);

		// Add feature
		this.addFeatureValues(node, getFeatureContainer(v));
	}
	
	
	
	/**
	 * Private method to handle the insertion of all edges from a given
	 * filename into graph
	 *
	 */
	private void handle_undirected_file(Graph g, String nSchemaID, String eSchemaId, InvenioEdge e) throws DataFormatException {

		String edgeId = e.getKey().toString();
		
		Set<Node> nodes = new HashSet<Node>();
		Iterator<InvenioVertex> invNodesIter = e.getIncidentVertices().iterator();

		// Gather Nodes
		boolean skipedge = false;

		while(invNodesIter.hasNext()){
			InvenioVertex v = invNodesIter.next();
			String vId = v.getKey().toString();

			// Add nodes to edge
			Node n = this.getNode(g, nSchemaID, vId);
			if(n == null) {
				Log.WARN("Node given by string does not exist: " + vId);
				skipedge = true;
				break;
			} else {
				nodes.add(n);
			}
		}

		// Add edge only if all nodes are valid
		if(!skipedge) {
			// Create Edge
			GraphItemID gid = new GraphItemID((GraphID) g.getID(), eSchemaId, edgeId);
			UndirectedEdge ue = g.addUndirectedEdge(gid, nodes.iterator());
			this.addFeatureValues(ue, getFeatureContainer(e));
		}
	}
	
	/**
	 * Process the feature values for the given decorable item
	 */
	private void addFeatureValues(Decorable di, ComparableFeatureContainer c) throws DataFormatException {
		invenio.operator.data.Schema invSchema = c.getSchema();
		Schema schema = di.getSchema();
		
		for (int i = 0; i < invSchema.size(); i ++) {
			String fid = invSchema.getFeatureDescriptor(i).getName();
			
			if ("id".equals(fid))
				continue;
			
			Feature f = schema.getFeature(fid);
			FeatureValue fvalue = convertFeatureValue(f, c, i);
			
			di.setFeatureValue(fid, fvalue);
		}
	}
	
	
	/**
	 * Convert the value from a given invenio Feature
	 * 
	 * @param f
	 * @param c
	 * @param i
	 * @return
	 */
	private FeatureValue convertFeatureValue(Feature f, ComparableFeatureContainer c, int i) throws DataFormatException {
		FeatureValue fvalue = null;
		invenio.operator.data.Schema schema = c.getSchema();

		if( !c.hasFeature(i) ){
			fvalue = FeatureValue.UNKNOWN_VALUE;
		} else if( schema.canGetAsNumeric(i) ) {
			double val = ((NumericFeature)c.getFeature(i)).getValue();
			fvalue = new NumValue(val);
		} else if( schema.canGetAsString(i) ) {
			String val = ((invenio.operator.data.StringFeature)c.getFeature(i)).getValue();
			fvalue = new StringValue( val );
		} else if( schema.canGetAsCategorical(i) ) {
			CategoricalFeature cat = (CategoricalFeature) c.getFeature(i);
			String selectedVal = cat.getSelectedValue();
			double[] probs = cat.getProbabilities();
			fvalue = new CategValue(selectedVal, probs);
// TODO: add multicat support
//		} else if(f instanceof MultiCategFeature){
//			SimplePair<String,double[]> probpair =
//				this.parseProb(value, ((MultiCategFeature) f).getAllCategories().iterator());
//			Set<String> catset = new HashSet<String>(Arrays.asList(probpair.getFirst().split(",")));
//			fvalue = new MultiCategValue(catset, probpair.getSecond());
		} else {
			throw new DataFormatException("Unsupported Feature Type");
		}

		return fvalue;
	}
	
	
	public static String getNodeSchemaId(InvenioGraph g) {
		Object schemaId = g.getUserDatum(Constants.NODE_SCHEMAID_ATTRIBUTE);
		return schemaId == null ? null : schemaId.toString(); 
	}
	
	public static String getEdgeSchemaId(InvenioGraph g) {
		Object schemaId = g.getUserDatum(Constants.EDGE_SCHEMAID_ATTRIBUTE);
		return schemaId == null ? null : schemaId.toString(); 
	}
	
	
	// TODO: extract to utils class (currently in operator utils)
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e) throws DataFormatException {
		ComparableFeatureContainer c = (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
		if (c == null)
			throw new DataFormatException("Error extracting attribute: unrecognized attribute format");
		return c;
	}	


	/**
	 * Parse the value from a given value entry
	 * 
	 * @param f
	 * @param value
	 * @return
	 */
	private FeatureValue parseFeatureValue(Feature f, String value) {
		FeatureValue fvalue = null;

		if(value.equals(UNKNOWNVALUE)){
			fvalue = null;
		} else if(f instanceof NumFeature){
			Double val = Double.parseDouble(value);
			fvalue = new NumValue(val);
		} else if(f instanceof StringFeature){
			fvalue = new StringValue(value);
		} else if(f instanceof CategFeature){
			SimplePair<String,double[]> probpair =
				this.parseProb(value, ((CategFeature) f).getAllCategories().iterator());
			fvalue = new CategValue(probpair.getFirst(), probpair.getSecond());
		} else if(f instanceof MultiCategFeature){
			SimplePair<String,double[]> probpair =
				this.parseProb(value, ((MultiCategFeature) f).getAllCategories().iterator());
			Set<String> catset = new HashSet<String>(Arrays.asList(probpair.getFirst().split(",")));
			fvalue = new MultiCategValue(catset, probpair.getSecond());
		} else {
			throw new FileFormatException("Unsupported Feature Type: "
					+f.getClass().getCanonicalName());
		}

		return fvalue;
	}

	/**
	 * Get node with the given id to parse.
	 * A null is returned if the node cannot be found
	 * 
	 * @param g Graph
	 * @param nodeschemaID Default schema ID for nodes.  If null, parse nodeid for schema ID.
	 * @param nodeid String representation of node id
	 * @return Node
	 */
	private Node getNode(Graph g, String nodeschemaID, String nodeid){
		GraphItemID nid = new GraphItemID((GraphID) g.getID(), nodeschemaID, nodeid);
		return g.getNode(nid);
	}

	
	/**
	 * Parse the probabilities from the value.  If not specified,
	 * set the value matching the category to 1 and all other 0.
	 * 
	 * @param value
	 * @param categories
	 * @return
	 */
	private SimplePair<String,double[]> parseProb(String value, Iterator<String> categories) {
		String[] valueparts = value.split(PROB_DELIMITER);
		List<Double> probs = new LinkedList<Double>();

		if(valueparts.length==2){
			String[] probvals = valueparts[1].split(",");
			for(int i=0; i<probvals.length; i++){
				probs.add(Double.parseDouble(probvals[i]));
			}
		} else {
			while(categories.hasNext()){
				if(categories.next().equals(valueparts[0])) {
					probs.add(new Double(1));
				} else {
					probs.add(new Double(0));
				}
			}
		}

		return new SimplePair<String,double[]>(valueparts[0], ListUtils.DoubleList2Array(probs));
	}
}
