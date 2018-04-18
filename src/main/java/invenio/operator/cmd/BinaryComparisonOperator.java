package invenio.operator.cmd;

import invenio.data.InvenioGraph;
import invenio.operator.ConfigurationException;
import invenio.operator.OperatorExecutionException;
import invenio.operator.data.DataFormatException;
import invenio.operator.io.tab.TabDelimWriter;
import invenio.operator.pig.ComparativeBinOperator;
import invenio.operator.pig.ComparisonOperatorExecutor;
import invenio.operator.pig.DataValidationOperator;
import invenio.operator.pig.DifferenceSignificanceOperator;
import invenio.operator.pig.GeneralBinOperator;
import invenio.operator.pig.MagnitudeDifferenceOperator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BinaryComparisonOperator {
	
	public final static String PROPERTY_FILE = "binary_comp.props";
	
	public final static String KEY_IN_NODES1 = "graph.in.1.node.file";
	public final static String KEY_IN_EDGES1 = "graph.in.1.edge.file";
	public final static String KEY_IN_NODES2 = "graph.in.2.node.file";
	public final static String KEY_IN_EDGES2 = "graph.in.2.edge.file";
	
	public final static String KEY_OUT_NODES1 = "graph.out.1.node.file";
	public final static String KEY_OUT_EDGES1 = "graph.out.1.edge.file";
	public final static String KEY_OUT_NODES2 = "graph.out.2.node.file";
	public final static String KEY_OUT_EDGES2 = "graph.out.2.edge.file";
	public final static String KEY_OUT_NODES3 = "graph.out.3.node.file";
	public final static String KEY_OUT_EDGES3 = "graph.out.3.edge.file";
	
	public final static String KEY_OVERWRITE_FILES = "process.overwrite";
	
	public final static String KEY_GB_PROB_THRESHOLD = "operators.gb.probability";
	
	private String inNodes1, inEdges1;
	private String inNodes2, inEdges2;
	private String outNodes1, outEdges1;
	private String outNodes2, outEdges2;
	private String outNodes3, outEdges3;
	
	private boolean overwrite = false;
	
	private boolean gbProbabilityThreshold;
	
	private ComparisonOperatorExecutor executor = null;
	
	public BinaryComparisonOperator() throws ConfigurationException {
		InputStream is = getClass().getResourceAsStream(PROPERTY_FILE);
		Properties props = new Properties();
		if (is == null)
			throw new ConfigurationException("Cannot load properties from file " + PROPERTY_FILE);
		try {
			props.load(is);
			setup(props);
		}
		catch (IOException ex) {
			throw new ConfigurationException(ex.getMessage(), ex.getCause());
		}
	}
	
	public BinaryComparisonOperator(Properties props) throws ConfigurationException {
		setup(props);
	}
	
	protected void setup(Properties props) throws ConfigurationException {
		inNodes1 = props.getProperty(KEY_IN_NODES1);
		inEdges1 = props.getProperty(KEY_IN_EDGES1);
		inNodes2 = props.getProperty(KEY_IN_NODES2);
		inEdges2 = props.getProperty(KEY_IN_EDGES2);
		
		outNodes1 = props.getProperty(KEY_OUT_NODES1);
		outEdges1 = props.getProperty(KEY_OUT_EDGES1);
		outNodes2 = props.getProperty(KEY_OUT_NODES2);
		outEdges2 = props.getProperty(KEY_OUT_EDGES2);
		outNodes3 = props.getProperty(KEY_OUT_NODES3);
		outEdges3 = props.getProperty(KEY_OUT_EDGES3);
		
		if ( inNodes1 == null || inEdges1 == null 
				|| inNodes2 == null || inEdges2 == null
				|| outNodes1 == null || outEdges1 == null
				|| outNodes2 == null || outEdges2 == null
				|| outNodes3 == null || outEdges3 == null)
			throw new ConfigurationException("File names are all required parameters");
		
			overwrite = Boolean.parseBoolean( props.getProperty(KEY_OVERWRITE_FILES, "false") );
			
			setupOperators();
	}
	
	protected void setupOperators() {
		executor = new ComparisonOperatorExecutor();
		executor.addUnaryOperator( new DataValidationOperator() );
		executor.addUnaryOperator( new GeneralBinOperator() );
		
		executor.addBinaryOperator( new ComparativeBinOperator() );
		executor.addBinaryOperator( new MagnitudeDifferenceOperator() );
		executor.addBinaryOperator( new DifferenceSignificanceOperator() );
	}
	
	
	public void execute() throws DataFormatException, OperatorExecutionException, IOException {
		InvenioGraph g1 = readGraph(inNodes1, inEdges1);
		InvenioGraph g2 = readGraph(inNodes2, inEdges2);
		
		InvenioGraph g3 = executor.execute(g1, g2);
		
		writeGraph(g1, outNodes1, outEdges1);
		writeGraph(g2, outNodes2, outEdges2);
		writeGraph(g3, outNodes3, outEdges3);
	}
	
	protected InvenioGraph readGraph(String nodeFile, String edgeFile) throws IOException, DataFormatException {
		return (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
	}
	
	protected void writeGraph(InvenioGraph g, String nodeFile, String edgeFile) throws IOException, DataFormatException {
		(new TabDelimWriter()).writeGraph(g, nodeFile, edgeFile, overwrite);
	}
	
	public static void main(String[] args) {
		try {
			BinaryComparisonOperator op = new BinaryComparisonOperator();
			op.execute();
		}
		catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
