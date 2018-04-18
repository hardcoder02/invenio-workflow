package invenio.wf.test;

import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.parser.TextToLogicalExpressionParser;
import invenio.op.parser.XMLToLogicalExpressionParser;
import invenio.wf.items.query.QueryService;
import invenio.wf.util.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.DefaultContext;
import qng.tabular.Schema;
import qng.tabular.Table;
import qng.tabular.Tuple;

public class QueryVisTestTemp {
	public static final String SIMPLE_FILE_NAME = "C:/Workspace/Invenio/DolphinData/Karate/karate.txt";

//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.DIRECTED.cites-fixed.tab";
//	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper.tab";
//	public static final String NODE_FILE1 = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper-m1.tab";
//	public static final String NODE_FILE2 = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper-m2.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.DIRECTED.cites-fixed.tab";
////	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.DIRECTED.cites-fixed.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/2011-09-16 Dolphin-conf.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/2011-09-16 Dolphin-edge-conf.tab";

//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.NODE.dolphin.gt.small.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.UNDIRECTED.seenwith.small.tab";
	
	public static final String NODE_COUNT = "10000";
	public static final String EDGE_COUNT = "100000";
	public static final String EDGE_TYPE = "acyclic";
//	public static final String EDGE_TYPE = "undirected";
	
	public static final boolean RUN_PARALLEL = true;
	
//	public static final String FILE_PATH = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/DEXA2013-JOURNAL/tab/";
	public static final String FILE_PATH = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/DEXA2013-JOURNAL/parallel/";
	public static final String NODE_FILE = FILE_PATH + "synthetic.NODE." + NODE_COUNT + ".tab";
	public static final String EDGE_FILE = FILE_PATH + "synthetic.EDGE." + NODE_COUNT + "." + EDGE_COUNT + "." + EDGE_TYPE + ".tab";
	
	
//	public static final String NODE_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.NODE.100000.tab";
//	public static final String EDGE_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.EDGE.1000.tab";
//	
//	public static final String NODE_FILE1 = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.NODE.100000-2.tab";
//	public static final String NODE_FILE2 = "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.NODE.1000000-2.tab";
	
	
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/01_CountNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/02_SelectGraph.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/03_SelectNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/04_SelectEgoGraph.sql";
	
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL/01_CountNodes.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/01_CountSyntheticCategorical.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/02_SelectSyntheticCategorical.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/03_Join.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/04_Model1Model2WrongMatrix.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/05_JoySimilaritySameLocation.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/06_CountFriendsBetweenGender.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/07_TreeSize.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/08_TreeSizeAll.sql";
	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/09_SwitchCount.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/10_FilterMaxValueCertainty.sql";
//	public static final String QUERY_FILE = "/Users/ddimitrov/dev/workspace/eclipse/invenio/QueryEngine/queries/DEXA2013-JOURNAL-REVISION/11_CommonFriends.sql";
	
	public static final String GRAPH_NAME = "gt";
	public static final String GRAPH_NAME1 = "g1";
	public static final String GRAPH_NAME2 = "g2";
			
	private InvenioGraph invGraph;
	
	private QueryVisTestTemp() {}
	private QueryVisTestTemp(InvenioGraph invGraph) {
		this.invGraph = invGraph;
	}
	
	public void cont() {
		System.out.println("continuing");
		try {
			List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
			invGraph.setName(GRAPH_NAME);
			graphs.add(invGraph);
			
		 	System.out.println("Graph size edges: " + invGraph.numVertices());
		 	System.out.println("Graph size vertices: " + invGraph.numEdges());
		 	
			// get query
			String query = getQuery();
	 	
		 	// run query
		 	Table queryResult = executeQuery(graphs, query);
		 	// System.out.println(queryResult);
		 	System.out.println("Finished query execution.");
		 	System.out.println("Number of rows: " + queryResult.getSize());
	 	
		 	printResult(queryResult);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void executeParallel() {
		InvenioGraph ig = new InvenioGraph();
		getGraphParallel(ig, NODE_FILE, EDGE_FILE);
	}
	
	private static void executeSingle() throws Exception {
	 	
        // -- 1. load the data ------------------------------------------------
//	 	InvenioGraph invGraph = getGraph(NODE_FILE, EDGE_FILE);
//	 	invGraph.setName(GRAPH_NAME);
//	 	List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
//	 	graphs.add(invGraph);
	
//		InvenioGraph invGraph = InvenioGraphSerializer.deserialize("/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.100000.ser");
//		List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
//		graphs.add(invGraph);
	 	
//		List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
//		InvenioGraph invGraph = null;
//	 	invGraph = getGraph(NODE_FILE1, EDGE_FILE);
//	 	invGraph.setName(GRAPH_NAME1);
//	 	graphs.add(invGraph);
	
		InvenioGraph invGraph = getGraph(NODE_FILE, EDGE_FILE);
	 	invGraph.setName(GRAPH_NAME);
	 	List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
	 	graphs.add(invGraph);
		
//	 	invGraph = getGraph(NODE_FILE1, EDGE_FILE, NODE_FILE2, EDGE_FILE);
//	 	invGraph.setName(GRAPH_NAME1);
//	 	graphs.add(invGraph);
	 	
	 	
//	 	for (InvenioVertex v : invGraph.getVertices() ) {
//	 		System.out.println("Vertex: " + v.getName());
//	 	}
//	 	
//	 	for (InvenioEdge e : invGraph.getEdges() ) {
//	 		System.out.println("Edge: " + e.getName());
//	 		System.out.println("V1: " + e.getEndpoints().getFirst() + ", v2: " + e.getEndpoints().getSecond());
//	 	}
        
//	 	invGraph = getGraph(NODE_FILE2, EDGE_FILE);
//	 	invGraph.setName(GRAPH_NAME2);
//	 	graphs.add(invGraph);
	 	
//	 	InvenioGraphSerializer.serialize(invGraph, "/Users/ddimitrov/dev/workspace/eclipse/invenio/DolphinData/Workflow/synthetic/synthetic.100000.ser");
	 	System.out.println("Graph size vertices: " + invGraph.numVertices());
	 	System.out.println("Graph size edges: " + invGraph.numEdges());
	 	
	 	// get query
	 	String query = getQuery();
	 	
	 	// run query
	 	Table queryResult = executeQuery(graphs, query);
//	 	System.out.println(queryResult);
	 	System.out.println("Finished query execution.");
	 	System.out.println("Number of rows: " + queryResult.getSize());
	 	
	 	printResult(queryResult);		
	}
	
	public static void main(String[] argv) throws Exception {
		if (RUN_PARALLEL)
			executeParallel();
		else
			executeSingle();
	 }
	
	private static void printResult(Table queryResult) {
		Schema s = queryResult.getSchema();
		for ( String col : s.getColumnNames() ) {
			System.out.print(col + "\t");
		}
		System.out.println("");
		
		Iterator<? extends Tuple> tIter = queryResult.getIterator();
		int i = 0;
		while ( tIter.hasNext() && 30 > i++ ) {
			Tuple t = tIter.next();
			for (int j = 0; j < s.getNumberOfColumns(); j++ ) {
				System.out.print(t.getValue(j) + "\t");
			}
			System.out.println("");
		}
	}

	private static Table executeQuery(List<InvenioGraph> invGraphs, String query) throws QueryException {
		 Map<String, InvenioGraph> graphMap = new HashMap<String, InvenioGraph>();
		 for ( InvenioGraph g : invGraphs) {
			 graphMap.put(g.getName(), g);
		 }
		
		long startTime = System.currentTimeMillis();
		System.out.println("Starting query execution");
		Table t = runQuery(graphMap, query);
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Query executed in (msec): " + duration);
		
		return t;
	 }
	 
	protected static Table runQuery(Map<String, InvenioGraph> graphMap, String query) throws QueryException {
		QueryParser<String, LogicalExpression> parser = null;
		
		// XML query
		if ( query.trim().length() > 4 && query.trim().substring(0, 5).equalsIgnoreCase("<?XML") )
			parser = new XMLToLogicalExpressionParser();
		else
			parser = new TextToLogicalExpressionParser();
		
		LogicalExpression exp = parser.parse(query);
		
		CompiledQuery<CompiledElement> comp = QueryService.compileExpression(exp);
	
		Context ctx = new DefaultContext();
		ctx.setVariable(OperationConstants.VAR_GRAPH_STORE, graphMap);
		
		return (Table) QueryService.executeQuery(comp, ctx).getResultValue();
	}
	
	private static String getQuery( ) throws IOException {
		
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader( QUERY_FILE ));
			StringBuffer sb = new StringBuffer();
			String s = new String();
			while ( (s = r.readLine()) != null )
				sb.append(s).append("\n");
			return sb.toString();
		}
		finally {
			try {
				if (r != null)
					r.close();
			}
			catch (IOException ex) {}
		}
	}

	
	public static InvenioGraph getGraph(final String nodeFile, final String edgeFile) {
		InvenioGraph invGraph = null;

		long startTime = System.currentTimeMillis();
		System.out.println("Loading graph...");
		try {
//			invGraph = IOUtils.readFromSparseGraphFile( SPARSE_FILE_NAME );
		    invGraph = IOUtils.readFromTabFile( nodeFile, edgeFile );
//			invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
//			invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
		    
		    invGraph.setName(GRAPH_NAME);
		    
			long duration = System.currentTimeMillis() - startTime;
			System.out.println("Graph loaded in (msec): " + duration);
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         System.err.println("Error loading graph. Exiting...");
	         System.exit(1);
	     }
		
	     return invGraph;
	 }
	
	public static void getGraphParallel(InvenioGraph ig, String nodeFile, String edgeFile) {
		
		
		String nFile1 = nodeFile + "1";
		String nFile2 = nodeFile + "2";
		String nFile3 = nodeFile + "3";
		String nFile4 = nodeFile + "4";

		try {
		    ParallelTabReader.loadGraph(new QueryVisTestTemp(ig), ig, edgeFile, nodeFile,
		    		nFile1, nFile2, nFile3, nFile4);
		    
		    ig.setName(GRAPH_NAME);		    
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         System.err.println("Error loading graph. Exiting...");
	         System.exit(1);
	     }
	 }
	
	public static InvenioGraph getGraph(final String nodeFile1, final String edgeFile1,
			String nodeFile2, String edgeFile2) {
		InvenioGraph invGraph = new InvenioGraph();
		GraphLoaderThread t1 = new GraphLoaderThread();
		t1.nodeFile = nodeFile1;
		t1.edgeFile = edgeFile1;
		t1.graph = invGraph;
		
		new Thread(t1).start();
		
		GraphLoaderThread t2 = new GraphLoaderThread();
		t2.nodeFile = nodeFile2;
		t2.edgeFile = edgeFile2;
		t2.graph = invGraph;
		
		new Thread(t2).start();
		
		try {
			Thread.sleep(10000000);
		}
		catch (InterruptedException ex) {
			
		}
		
		return invGraph;
	 }
	
	private static class GraphLoaderThread implements Runnable {
		private InvenioGraph graph;
		private String nodeFile;
		private String edgeFile;
		
		public void run() {
			long startTime = System.currentTimeMillis();
			System.out.println("Loading graph...");
			try {
//				invGraph = IOUtils.readFromSparseGraphFile( SPARSE_FILE_NAME );
			    IOUtils.readFromTabFile(graph, nodeFile, edgeFile );
//				invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
//				invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
			    
				long duration = System.currentTimeMillis() - startTime;
				System.out.println("Graph loaded in (msec): " + duration);
		     } catch ( Exception e ) {
		         e.printStackTrace();
		         System.err.println("Error loading graph.");
		     }
		}
	}
}


