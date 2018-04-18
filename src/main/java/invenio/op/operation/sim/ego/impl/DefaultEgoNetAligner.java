package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;
import invenio.op.operation.impl.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.DuplicateException;

public class DefaultEgoNetAligner extends AbstractEgoNetAligner {

	@Override
	protected void doAlign(InvenioVertex n1, InvenioVertex n2) throws QueryException {
		alignGraphs(n1, n2, (n1.getGraph() == n2.getGraph() ) );
	}

	
	/**
	 * Same logic as for single graph, but makes sure that edge ids also match.
	 * @param n1
	 * @param n2
	 * @throws QueryException
	 */
	private void alignGraphs(InvenioVertex n1, InvenioVertex n2, boolean sameGraph) throws QueryException {

		ac.startAlignment(n1, n2);
		
		final Map<Object, Pair<InvenioVertex, InvenioEdge>> pairs1 = buildEgoMap(n1);
		final Map<Object, Pair<InvenioVertex, InvenioEdge>> pairs2 = buildEgoMap(n2);
		
		// iterate over pairs1, removing matches from pairs2
		Iterator<Pair<InvenioVertex, InvenioEdge>> iter = pairs1.values().iterator();
		while (iter.hasNext()) {
			Pair<InvenioVertex, InvenioEdge> p1 = iter.next();
			InvenioVertex v1 = p1.first;
			InvenioEdge e1 = p1.second;
			
			Pair<InvenioVertex, InvenioEdge> p2 = pairs2.remove( v1.getKey() );
			InvenioVertex v2 = (p2 == null) ? null : p2.first;
			InvenioEdge e2 = (p2 == null) ? null : p2.second;
			
//			if ( !sameGraph && e2 != null && (e1.getKey() != e2.getKey()) )
//				throw new QueryException("Mismatched edge ids " + e1.getKey() + " and " + e2.getKey() + 
//						" for vertex with id " + v1.getKey() );
						
			ac.startAlignVertex(v1, v2);
			ac.alignEdge(e1, e2);
			ac.endAlignVertex(v1, v2);
		}
		
		// iterate over remaining (unaligned) elements of pairs2
		iter = pairs2.values().iterator();
		while (iter.hasNext()) {
			Pair<InvenioVertex, InvenioEdge> p2 = iter.next();
			InvenioVertex v2 = p2.first;
			InvenioEdge e2 = p2.second;
			
			ac.startAlignVertex(null, v2);
			ac.alignEdge(null, e2);
			ac.endAlignVertex(null, v2);
		}
		
		ac.endAlignment();
	}
	
	private Map<Object, Pair<InvenioVertex, InvenioEdge>> buildEgoMap(InvenioVertex n) throws QueryException {
		
		final Map<Object, Pair<InvenioVertex, InvenioEdge>> pairs = new HashMap<Object, Pair<InvenioVertex, InvenioEdge>>();

		// loop over edge / vertex pairs
		Iterator<InvenioEdge> iter = n.getIncidentEdges().iterator();
		while (iter.hasNext()) {
			InvenioEdge e = iter.next();
			InvenioVertex oppN = getOppositeVertex(n, e);
			
			// discard if a loop to the center
			if ( n == oppN )
				continue;
			
			if ( pairs.containsKey( oppN.getKey() ) )
				continue;
//				throw new DuplicateException(
//						"Ego-nets can only contain a single edge between center node " + n + " and node " + oppN);
			
			pairs.put( oppN.getKey(), new Pair<InvenioVertex, InvenioEdge> (oppN, e) );
		}
		
		return pairs;
	}


	private InvenioVertex getOppositeVertex(InvenioVertex n1, InvenioEdge e) {
		edu.uci.ics.jung.utils.Pair p = e.getEndpoints();
		if ( n1 == p.getFirst() )
			return (InvenioVertex)p.getSecond();
		else
			return (InvenioVertex)p.getFirst();
	}
}
