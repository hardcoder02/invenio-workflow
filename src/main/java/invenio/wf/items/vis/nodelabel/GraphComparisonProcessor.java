package invenio.wf.items.vis.nodelabel;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraphComparisonProcessor {
	
	public static final int NUM_FIELDS = 3;	// at least two
	public static final int NUM_SLICES = NUM_FIELDS * (NUM_FIELDS - 1) / 2;
	
	public BaseTable createBaseTable(InvenioGraph gt, InvenioGraph g1, InvenioGraph g2, String fName) 
			throws DataFormatException {
		BaseTable result = new BaseTable(gt.numVertices());
		
		processGraph(result, gt, fName, 0);
		processGraph(result, g1, fName, 1);
		processGraph(result, g2, fName, 2);
		
		return result;
	}
	
	public Matrix createMatrix(BaseTable table) {
		assert (NUM_FIELDS >= 2);
		
		String[] header = getHeader(table);
		
		Matrix m = new Matrix(NUM_SLICES, header);
		
		int sliceIndex = 0;
		
		for ( int i = 0; i < NUM_FIELDS - 1; i++) {
			for (int j = i + 1; j < NUM_FIELDS; j++) {
				updateMatrix(table, m.getSlice(sliceIndex), i, j);
				sliceIndex++;
			}
		}
		
		return m;
	}
	
	public float getAccuracy(BaseTable table, int gtIndex, int modelIndex) {
		int correct = 0;
		for (BaseEntry e : table.values() ) {
			CategoricalFeature f1 = e.getFeature(gtIndex);
			CategoricalFeature f2 = e.getFeature(modelIndex);
			
			if ( f1 != null && f2 != null) {
				int sel1 = f1.getSelectedIndex();
				int sel2 = f2.getSelectedIndex();
				
				if (sel1 == sel2)
					correct++;
			}
		}
		return (float) correct / table.size();
	}
	
	
	private void updateMatrix(BaseTable table, MatrixSlice s, int i, int j) {
		for (BaseEntry e : table.values() ) {
			CategoricalFeature f1 = e.getFeature(i);
			CategoricalFeature f2 = e.getFeature(j);
			
			if ( f1 != null && f2 != null) {
				int row = f1.getSelectedIndex();
				int col = f2.getSelectedIndex();
				
				s.getCell(row, col).add(e);
			}
		}
		
	}

	private String[] getHeader(BaseTable table) {
		for (BaseEntry e : table.values() ) {
			for (int i = 0; i < e.size(); i++) {
				CategoricalFeature cf = e.getFeature(i);
				if (cf != null)
					return cf.getFeatureDescriptor().getCategories();
			}
		}
		return new String[0];
	}
	
	
	private void processGraph(BaseTable map, InvenioGraph graph, String fName, int index) throws DataFormatException {
		Iterator<InvenioVertex> iter = graph.getVertices().iterator();
		
		while( iter.hasNext() ) {
			InvenioVertex v = iter.next();
			ComparableFeatureContainer c = getFeatureContainer(v);
			
			BaseEntry entry = getBaseEntry(map, "" + v.getKey() );
			
			// add vertex
			entry.setVertex(index, v);
			
			// set feature
			if ( c.hasFeature(fName) ) {
				CategoricalFeature cf = (CategoricalFeature) c.getFeature(fName);
				entry.setFeature(index, cf);
			}
		}
	}
	
	private BaseEntry getBaseEntry(BaseTable map, String key) {
		if (map.containsKey(key))
			return map.get(key);
		else {
			BaseEntry entry = new BaseEntry(NUM_FIELDS);
			entry.setId(key);
			map.put(key, entry);
			return entry;
		}
	}

	// TODO: extract to utils class (currently in operator utils)
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e) throws DataFormatException {
		ComparableFeatureContainer c = (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
		if (c == null)
			throw new DataFormatException("Error extracting attribute: unrecognized attribute format");
		return c;
	}
}
