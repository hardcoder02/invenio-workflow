package invenio.wf.items.vis.clustering.compare;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ClusterComparisonProcessor {
	
	
	public CCBaseTable createBaseTable(InvenioGraph graph) {
		CCBaseTable result = new CCBaseTable(graph.numVertices());
		
		Iterator<InvenioVertex> iter = graph.getVertices().iterator();	
		while( iter.hasNext() ) {
			InvenioVertex v = iter.next();
			result.put( "" + v.getKey(), v);
		}
		
		return result;
	}
	
	public static CCMatrix createMatrix(GroupingAlgorithmResult r0, GroupingAlgorithmResult r1) {
		InvenioElementSet[] xSets =
				r0.getGroupElementSet().getSets().toArray(new InvenioElementSet[0]);
		InvenioElementSet[] ySets =
				r1.getGroupElementSet().getSets().toArray(new InvenioElementSet[0]);
		
		CCMatrix m = new CCMatrix(xSets, ySets);
		
		// update cell stats
		for (int row = 0; row < ySets.length; row++) {
			for (int col = 0; col < xSets.length; col++) {
				ClusterIntersect ci = intersectClusters(xSets[col], ySets[row]);
				
				CCMatrixCell cell = m.getCell(row, col);
				cell.setCountXOnly( ci.xOnly.size() );
				cell.setCountYOnly( ci.yOnly.size() );
				cell.setCountShared( ci.xyBoth.size() );
			}
		}
		
		return m;
	}
	
	public static ClusterIntersect intersectClusters(InvenioElementSet x, InvenioElementSet y) {
		ClusterIntersect intersect = new ClusterIntersect();
		
		// create x cluster map
		Set<String> xIDs = new HashSet<String>();
		for ( InvenioVertex v : x.getVertexSet() ) {
			xIDs.add("" + v.getKey());
		}
		
		// process elements from y cluster
		for (InvenioVertex v : y.getVertexSet() ) {
			String key = "" + v.getKey();
			if ( xIDs.contains( key ) ) {
				intersect.xyBoth.add( key );
				xIDs.remove( key );
			}
			else
				intersect.yOnly.add( key );
		}
		
		// process remaining elements from x cluster
		intersect.xOnly.addAll(xIDs);
		
		return intersect;
	}
	
//	public float getAccuracy(BaseTable table, int gtIndex, int modelIndex) {
//		int correct = 0;
//		for (BaseEntry e : table.values() ) {
//			CategoricalFeature f1 = e.getFeature(gtIndex);
//			CategoricalFeature f2 = e.getFeature(modelIndex);
//			
//			if ( f1 != null && f2 != null) {
//				int sel1 = f1.getSelectedIndex();
//				int sel2 = f2.getSelectedIndex();
//				
//				if (sel1 == sel2)
//					correct++;
//			}
//		}
//		return (float) correct / table.size();
//	}
	
	public static class ClusterIntersect {
		public Set<String> xOnly = new HashSet<String>();
		public Set<String> yOnly = new HashSet<String>();
		public Set<String> xyBoth = new HashSet<String>();
	}
}
