package invenio.operator.io.tab;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.Schema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import edu.uci.ics.jung.utils.Pair;

public class TabDelimWriter {
	
	public static final String SEPARATOR = "\t";
	public static final String SCHEMA_SEPARATOR = ":";
	public static final String EDGE_SEPARATOR = "|";
	
	private final FeatureDeclarationWriter featureDeclarationWriter = new FeatureDeclarationWriter();
	private final FeatureValueWriter featureValueWriter = new FeatureValueWriter();
	
	public void writeGraph(
			InvenioGraph graph, String nodesFile, String edgesFile, boolean overwrite) throws IOException, DataFormatException {
		
		BufferedWriter nodeWriter = null;
		BufferedWriter edgeWriter = null;
		
		try {
			nodeWriter = getWriter(nodesFile, overwrite);
			edgeWriter = getWriter(edgesFile, overwrite);
			
			writeNodes(graph, nodeWriter);
			writeEdges(graph, edgeWriter);
		}
		finally {
			closeWriter(nodeWriter);
			closeWriter(edgeWriter);
		}
		
	}
	
	private BufferedWriter getWriter(String fileName, boolean overwrite) throws IOException {
		File f = new File(fileName);

		if ( f.exists() && !overwrite)
			throw new IOException("File [" + fileName + "] already exists");
		
		return new BufferedWriter(new FileWriter( f ));
	}
	
	private void closeWriter(Writer w) {
		try {
			if (w != null) {
				w.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	protected void writeNodes(InvenioGraph g, BufferedWriter w) throws DataFormatException, IOException {
		writeNodeHeader( getNodeSchemaId(g), w);
		
		Schema s = null;
		Schema dS = null;
		if (g.numVertices() > 0) {
			InvenioVertex v = (InvenioVertex) g.getVertices().iterator().next();
			ComparableFeatureContainer c = getFeatureContainer(v);
			s = c.getSchema();
			dS = c.getDerivedSchema();
		}
		
		writeSchemaLine(s, dS, w);
		
		writeNodeInstanceLines(s, dS, g.getVertices(), w);
		
	}
	
	protected void writeNodeHeader(String schemaId, BufferedWriter w) throws IOException {
		w.append("NODE");
		if (schemaId != null && schemaId.trim().length() > 0)
			w.append(SEPARATOR).append(schemaId);
		w.newLine();
	}
	
	protected void writeSchemaLine(Schema s, Schema derivedSchema, BufferedWriter w) throws DataFormatException, IOException {
		if (s == null) {
			w.append("NO_FEATURES");
			w.newLine();
			return;
		}
		
		for (int i = 0; i < s.size(); i++) {
			if (i > 0)
				w.append( SEPARATOR );
			writeFeatureDeclaration( s, i, w );
		}
		
		if (derivedSchema != null)
			for (int i = 0; i < derivedSchema.size(); i++) {
				w.append( SEPARATOR );
				writeFeatureDeclaration( derivedSchema, i, w );
			}
		
		w.newLine();
	}
	
	protected void writeFeatureDeclaration( Schema s, int index, BufferedWriter w) throws DataFormatException, IOException {
		featureDeclarationWriter.writeFeatureDeclaration( s.getFeatureDescriptor(index), w);
	}
	
	protected void writeFeatureValue( Feature f, BufferedWriter w) throws DataFormatException, IOException {
		featureValueWriter.writeFeatureValue( f, w);
	}
	
	protected void writeNodeInstanceLines(
			Schema s, Schema derivedSchema, List vertices, BufferedWriter w) throws DataFormatException, IOException {
		
		for (Object o : vertices) {
			if ( !(o instanceof InvenioVertex) )
				throw new DataFormatException("Unexpected vertex type");	// should not happen
			InvenioVertex v = (InvenioVertex) o;
			
			ComparableFeatureContainer c = getFeatureContainer(v);
			if ( !s.isEquivalentTo( c.getSchema() ) 
					|| ( derivedSchema != null && !derivedSchema.isEquivalentTo( c.getDerivedSchema() )) )
				throw new DataFormatException("Mismatched schema");
			
			writeNodeInstance( v.getKey().toString(), c, w);
		}
	}
	
	protected void writeNodeInstance(
			String id, ComparableFeatureContainer c, BufferedWriter w) throws DataFormatException, IOException {
		
		w.append( id );
		
		Schema s = c.getSchema();
		Schema derivedSchema = c.getDerivedSchema();
		
		for (int i = 0; i < s.size(); i++) {
			if ( c.hasFeature( s.getFeatureDescriptor(i).getName() ) ) {
				w.append( SEPARATOR );
				writeFeatureValue( c.getFeature( i ), w );
			}
		}
		
		if (derivedSchema != null) {
			for (int i = 0; i < derivedSchema.size(); i++) {
				if ( c.hasFeature( derivedSchema.getFeatureDescriptor(i).getName() ) ) {
					w.append( SEPARATOR );
					writeFeatureValue( c.getDerivedFeature( i ), w );
				}
			}
		}
		
		w.newLine();
	}
	
	
	protected void writeEdges(InvenioGraph g, BufferedWriter w) throws DataFormatException, IOException {
		writeEdgeHeader( getEdgeSchemaId(g), w);
		
		Schema s = null;
		Schema dS = null;
		if (g.numEdges() > 0) {
			InvenioEdge e = (InvenioEdge) g.getEdges().iterator().next();
			ComparableFeatureContainer c = getFeatureContainer(e);
			s = c.getSchema();
			dS = c.getDerivedSchema();
		}
		
		writeSchemaLine(s, dS, w);
		
		writeEdgeInstanceLines(s, dS, g.getEdges(), g, w);
		
	}
	
	protected void writeEdgeHeader(String schemaId, BufferedWriter w) throws IOException {
		w.append("UNDIRECTED");
		if (schemaId != null && schemaId.trim().length() > 0)
			w.append(SEPARATOR).append(schemaId);
		w.newLine();
	}
	
	protected void writeEdgeInstanceLines(
			Schema s, Schema derivedSchema, List edges, InvenioGraph g, BufferedWriter w) throws DataFormatException, IOException {
		
		for (Object o : edges) {
			if ( !(o instanceof InvenioEdge) )
				throw new DataFormatException("Unexpected edge type");	// should not happen
			InvenioEdge e = (InvenioEdge) o;
			
			ComparableFeatureContainer c = getFeatureContainer(e);
			if ( !s.isEquivalentTo( c.getSchema() ) 
					|| ( derivedSchema != null && !derivedSchema.isEquivalentTo( c.getDerivedSchema() )) )
				throw new DataFormatException("Mismatched schema");
			
			writeEdgeInstance( e.getKey().toString(), c, e, g, w);
		}
	}
	
	
	protected void writeEdgeInstance(
			String id, ComparableFeatureContainer c, InvenioEdge e,
			InvenioGraph g, BufferedWriter w) throws DataFormatException, IOException {
		
		w.append( id ).append( SEPARATOR );
		
		writeEndpoints( e, g, w);
		
		Schema s = c.getSchema();
		Schema derivedSchema = c.getDerivedSchema();
		
		for (int i = 0; i < s.size(); i++) {
			if ( c.hasFeature( s.getFeatureDescriptor(i).getName() ) ) {
				w.append( SEPARATOR );
				writeFeatureValue( c.getFeature( i ), w );
			}
		}
		
		if (derivedSchema != null) {
			for (int i = 0; i < derivedSchema.size(); i++) {
				if ( c.hasFeature( derivedSchema.getFeatureDescriptor(i).getName() ) ) {
					w.append( SEPARATOR );
					writeFeatureValue( c.getDerivedFeature( i ), w );
				}
			}
		}
		
		w.newLine();
	}
	
	protected void writeEndpoints( InvenioEdge e, InvenioGraph g, BufferedWriter w) throws DataFormatException, IOException {
		InvenioVertex e1 = e.getSourceVertex();
		InvenioVertex e2 = e.getTargetVertex();
		
		String schemaId = getNodeSchemaId(g);
		String key1 = (String) e1.getKey();
		String key2 = (String) e2.getKey();
		
		w.append( schemaId ).append( SCHEMA_SEPARATOR ).append( key1 ).append( SEPARATOR );
		w.append( schemaId ).append( SCHEMA_SEPARATOR ).append( key2 ).append( SEPARATOR );
		
		w.append( EDGE_SEPARATOR );
	}
	
	// TODO: fix workaround
	private String getNodeSchemaId(InvenioGraph g) {
		return "" + g.getUserDatum(Constants.NODE_SCHEMAID_ATTRIBUTE);
	}
	
	private String getEdgeSchemaId(InvenioGraph g) {
		return g.getUserDatum(Constants.EDGE_SCHEMAID_ATTRIBUTE) + "";
	}
	
	private ComparableFeatureContainer getFeatureContainer(InvenioElement e) {
		return (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
	}
	
	/**
	 * Test app.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String nodeFile =
			"C:/Workspace/Eclipse/Invenio/TabDelimIOReader/synthetic/out/g-1-2-1.NODE.person.tab";
		String edgeFile =
			"C:/Workspace/Eclipse/Invenio/TabDelimIOReader/synthetic/out/g-1-2-1.UNDIRECTED.friend.tab";
		(new TabDelimWriter()).writeGraph(null, nodeFile, edgeFile, false);
		
	}
}
