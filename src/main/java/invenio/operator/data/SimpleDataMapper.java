package invenio.operator.data;

import java.util.HashMap;
import java.util.Map;

public class SimpleDataMapper extends AbstractDataMapper implements DataMapper {

	private Map<String, Feature> featureMap = new HashMap<String, Feature>();
	
	
	public void addFeature(
			Feature<? extends FeatureDescriptor> feature)
			throws DuplicateElementException {
		
		String name = feature.getFeatureDescriptor().getName();
		if ( hasFeature(name) )
			throw new DuplicateElementException("Feature with name [" + name + "] already exists");
		
		featureMap.put(name, feature);
		getFeatureNameList().add(name);
	}
	
	public void setFeature(Feature<? extends FeatureDescriptor> feature) {
		
		String name = feature.getFeatureDescriptor().getName();
		
		if ( !hasFeature(name) )
			getFeatureNameList().add(name);
		
		featureMap.put(name, feature);
	}
	

	public Feature<? extends FeatureDescriptor> getFeature(String name) {
		return featureMap.get(name);
	}


	public boolean hasFeature(String name) {
		return featureMap.containsKey(name);
	}

	
}
