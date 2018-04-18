package invenio.operator.comparison;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.AbstractOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BinaryComparisonOperator extends AbstractOperator {
	
	public final static String RESULT_GRAPH_KEY = "result_graph_key";
	
	protected final InvenioGraph gGraph, hGraph;
	
	protected final AlignedPairPredicate<InvenioElement> vertexPredicate, edgePredicate;
	
	protected final VertexTransformationFunction vtf;
	protected final EdgeTransformationFunction etf;
	
	protected InvenioGraph resultGraph;

	public BinaryComparisonOperator(InvenioGraph gGraph, InvenioGraph hGraph,
			AlignedPairPredicate<InvenioElement> vertexPredicate, AlignedPairPredicate<InvenioElement> edgePredicate,
			VertexTransformationFunction vtf, EdgeTransformationFunction etf) {
		this.gGraph = gGraph;
		this.hGraph = hGraph;
		this.vertexPredicate = vertexPredicate;
		this.edgePredicate = edgePredicate;
		this.vtf = vtf;
		this.etf = etf;
	}
	
	public void execute() {
		Map<String, InvenioElement> gVertices = Util.createElementMap( gGraph.getVertices() );
		Map<String, InvenioElement> hVertices = Util.createElementMap( hGraph.getVertices() );
		Map<String, InvenioElement> gEdges = Util.createElementMap( gGraph.getEdges() );
		Map<String, InvenioElement> hEdges = Util.createElementMap( hGraph.getEdges() );
		
		List<AlignedPair<InvenioElement>> vPairs = getAlignedPairSelection(gVertices, hVertices, vertexPredicate);
		List<AlignedPair<InvenioElement>> ePairs = getAlignedPairSelection(gEdges, hEdges, edgePredicate);
		
		resultGraph = createGraph(vPairs, ePairs);
		resultMap = new HashMap<String, Object>();
		resultMap.put(RESULT_GRAPH_KEY, resultGraph);

	}
	
	protected List<AlignedPair<InvenioElement>> getAlignedPairSelection(
			Map<String, InvenioElement> gMap, Map<String, InvenioElement> hMap, AlignedPairPredicate<InvenioElement> p) {
		List<AlignedPair<InvenioElement>> result = new ArrayList<AlignedPair<InvenioElement>>();
		
		for ( Entry<String, InvenioElement> entryGMap : gMap.entrySet() ) {
			String key = entryGMap.getKey();
			AlignedPair<InvenioElement> pair = null;
			
			if (hMap.containsKey( key ))
				pair = new AlignedPair<InvenioElement>( entryGMap.getValue(), hMap.get(key) );
			else
				pair = new AlignedPair<InvenioElement>( entryGMap.getValue(), null );
			
			if (p.satisfies( pair ))
				result.add(pair);
		}
		
		for ( Entry<String, InvenioElement> entryHMap : hMap.entrySet() ) {
			String key = entryHMap.getKey();
			AlignedPair<InvenioElement> pair = null;
			
			if (! gMap.containsKey( key )) {
				pair = new AlignedPair<InvenioElement>( null, entryHMap.getValue() );
				
				if (p.satisfies( pair ))
					result.add(pair);
			}
		}
		
		return result;
	}
	
	protected InvenioGraph createGraph(List<AlignedPair<InvenioElement>> vPairs, List<AlignedPair<InvenioElement>> ePairs) {
		InvenioGraph invGraph = new InvenioGraph();
		Map<String, InvenioVertex> vertexMap = new HashMap<String, InvenioVertex>(vPairs.size());

		for ( AlignedPair<InvenioElement> v : vPairs) {
			InvenioVertex newV = vtf.createVertex( (InvenioVertex) v.getElement1(), (InvenioVertex) v.getElement2() );
			invGraph.addVertex( newV );
			vertexMap.put( newV.getKey().toString(), newV);
		}
		
		for ( AlignedPair<InvenioElement> e : ePairs) {
			InvenioEdge newE = etf.createEdge( vertexMap, (InvenioEdge) e.getElement1(), (InvenioEdge) e.getElement2() ) ;
			if (newE != null)
				invGraph.addEdge( newE );
		}
		
		return invGraph;
	}

}
