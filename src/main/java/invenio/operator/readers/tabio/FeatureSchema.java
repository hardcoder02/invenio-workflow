package invenio.operator.readers.tabio;

import java.util.HashMap;

/**
 * Keeps track of the features schema in a simple (id, feature) hash table.
 */
public class FeatureSchema {
	private HashMap<String, Feature> schema = new HashMap<String, Feature>();
	
	public FeatureSchema() {}
	
	public void addFeature(String id, Feature f) {
		schema.put(id, f);
	}
	
	public HashMap<String, Feature> getSchema() {
		return schema;
	}
}
