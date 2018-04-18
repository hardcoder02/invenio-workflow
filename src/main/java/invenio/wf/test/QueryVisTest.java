package invenio.wf.test;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.data.bridge.GraphTranslater;
import invenio.io.file.PajekFileReader;
import invenio.io.file.SparseGraphFileReader;
import invenio.op.operation.directed.DirectedUtils;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.parser.TextToLogicalExpressionParser;
import invenio.op.parser.XMLToLogicalExpressionParser;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Schema;
import invenio.visual.DisplayShape;
import invenio.visual.VisualGraphSession;
import invenio.wf.item.algorithm.cluster.EdgeBetweennessClusterer;
import invenio.wf.item.algorithm.cluster.KMeansClusterer2;
import invenio.wf.items.file.SimpleFileReader;
import invenio.wf.items.query.QueryConfigView;
import invenio.wf.items.query.QueryResult;
import invenio.wf.items.query.QueryService;
import invenio.wf.items.vis.clustering.ClusterLayoutManager;
import invenio.wf.items.vis.clustering.ClusteringResultController;
import invenio.wf.items.vis.clustering.ClusteringResultView;
import invenio.wf.items.vis.query.QueryResultController;
import invenio.wf.items.vis.query.QueryResultView;
import invenio.wf.util.FeatureUtils;
import invenio.wf.util.IOUtils;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.DefaultContext;
import qng.tabular.Table;

public class QueryVisTest {
	public static final String SIMPLE_FILE_NAME = "C:/Workspace/Invenio/DolphinData/Karate/karate.txt";

//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora/cora.DIRECTED.cites-fixed.tab";
//	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper.tab";
	public static final String NODE_FILE1 = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper-m1.tab";
	public static final String NODE_FILE2 = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.NODE.paper-m2.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/cora-subset/cora.DIRECTED.cites-fixed.tab";
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.NODE.paper.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/citeseer/citeseer.DIRECTED.cites-fixed.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.gt.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/2011-09-16 Dolphin-conf.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/2011-09-16 Dolphin-edge-conf.tab";

//	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.NODE.dolphin.gt.small.tab";
//	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/set-20110308/dolphin-location-subset/dolphin.UNDIRECTED.seenwith.small.tab";
	
	public static final String NODE_FILE = "C:/Workspace/Invenio/DolphinData/sponging-snacking/surveyNode_Denis3.tab";
	public static final String EDGE_FILE = "C:/Workspace/Invenio/DolphinData/sponging-snacking/surveyEdge_Denis3.tab";
	
	
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/01_CountNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/02_SelectGraph.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/03_SelectNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/04_SelectEgoGraph.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/PKDD2013/11_SelectHighConfWrongBoth.sql";
	
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/01_CountNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/02_CountEdges.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/03_CountDirectedEdges.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/04_CountUndirectedEdges.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/05_CountSponging.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/06_CountSnacking.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/07_SpongingByGender.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/08_SnackingByGender.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/11_CountSingleNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/12_CountRootNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/13_CountRoots.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/14_CountChildNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/15_SelectRoots.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/16_SelectTrees.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/17_TreeSizeDepth.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/18_CountNodesWithMultiParents.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/19_MothersByGender.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/21_CountNoEgoNodes.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/22_EgoNetSize.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/23_EgoNets.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/30_EgoNetSpongers.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/31_EgoNetSnackers.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/32_MotherChildSpongersGroupBy.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/33_MotherChildSnackersGroupBy.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/34_MotherChildGenderSpongersGroupBy.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/35_MotherChildGenderSnackersGroupBy.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/43_MotherChildSnackersGroupBy.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/40_EgoNetSpongingBreakdown.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/41_SiblingsSpongingBreakdown.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/50_CountSpongingSnacking.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/51_BreakdownByGender.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/61_SpongingSwitchTrees.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/62_SnackingSwitchTrees.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/63_SpongingFeature.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/64_SnackingFeature.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/65_SpongersSnackersChildSize.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/66_SpongersSnackersChildStats.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/67_SpongersStatsSpongingMothers.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/68_SnackersStatsSnackingMothers.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/69_MothersWithMixedChildren.sql";
	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/71_SpongersSpongingSiblings.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/72_SnackersSnackingSiblings.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/73_SpongersSpongingEgo.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/74_SnackersSnackingEgo.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/77_SpongersEgoStats.sql";
//	public static final String QUERY_FILE = "C:/Workspace/Invenio/QueryEngine/queries/DEXA2013-JOURNAL/78_SnackersEgoStats.sql";
	
	public static final String GRAPH_NAME = "g1";
			
	 public static void main(String[] argv) throws Exception {

		 	
	        // -- 1. load the data ------------------------------------------------
		 	InvenioGraph invGraph = getGraph(NODE_FILE, EDGE_FILE);
		 	invGraph.setName("gt");
		 	List<InvenioGraph> graphs = new ArrayList<InvenioGraph>();
		 	graphs.add(invGraph);
		 	
//		 	invGraph = getGraph(NODE_FILE1, EDGE_FILE);
//		 	invGraph.setName("gg1");
//		 	graphs.add(invGraph);
//	        
//		 	invGraph = getGraph(NODE_FILE2, EDGE_FILE);
//		 	invGraph.setName("gg2");
//		 	graphs.add(invGraph);
		 	
		 	// get query
		 	String query = getQuery();
		 	
		 	// run query
		 	QueryResult queryResult = executeQuery(graphs, query);
		 	
		 	// set up visualization
	        QueryResultController controller = setupVis(queryResult);
	        
	        
	        // -- 6. launch the visualization -------------------------------------
	        showFrame(controller.getShape());
	     System.out.println(queryResult);
	 }

	private static void showFrame( JComponent shape ) {
		// create a new window to hold the visualization
	    JFrame frame = new JFrame("prefuse example");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( shape );
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
	 }
	 
	 private static QueryResultController setupVis(QueryResult result) {
        QueryResultView shape = new QueryResultView();
        QueryResultController resultController = new QueryResultController();
        resultController.setShape(shape);
        shape.setController(resultController);
        resultController.updateModel(result);
        
		return resultController;
	}
	 
	 private static QueryResult executeQuery(List<InvenioGraph> invGraphs, String query) throws QueryException {
		 Map<String, InvenioGraph> graphMap = new HashMap<String, InvenioGraph>();
		 for ( InvenioGraph g : invGraphs) {
			 graphMap.put(g.getName(), g);
		 }
		 
		Table t = runQuery(graphMap, query);
		QueryResult result = new QueryResult();
		result.setQuery(query);
		result.setTable(t);
		return result;
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

	
	public static InvenioGraph getGraph(String nodeFile, String edgeFile) {
		InvenioGraph invGraph = null;
		 
		try {
//			invGraph = IOUtils.readFromSparseGraphFile( SPARSE_FILE_NAME );
		    invGraph = IOUtils.readFromTabFile( nodeFile, edgeFile );
//			invGraph = IOUtils.readFromPajekFile( PAJEK_FILE_NAME, GRAPH_NAME );
//			invGraph = IOUtils.readFromSimpleFile(SIMPLE_FILE_NAME, GRAPH_NAME);
		    
		    invGraph.setName(GRAPH_NAME);
		    
		    DirectedUtils.addDirectedEdges(invGraph, "id", "mother_id");
	     } catch ( Exception e ) {
	         e.printStackTrace();
	         System.err.println("Error loading graph. Exiting...");
	         System.exit(1);
	     }
	     return invGraph;
	 }
}


