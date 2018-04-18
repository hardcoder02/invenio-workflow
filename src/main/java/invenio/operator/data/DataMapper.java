package invenio.operator.data;

public interface DataMapper {
	//Feature<? extends FeatureDescriptor> getFeature(int index);
	Feature<? extends FeatureDescriptor> getFeature(String name);
	
	void addFeature(Feature<? extends FeatureDescriptor> feature) throws DuplicateElementException;
	void setFeature(Feature<? extends FeatureDescriptor> feature);
	
	int size();
	//boolean hasFeature(int index);
	boolean hasFeature(String name);
}
