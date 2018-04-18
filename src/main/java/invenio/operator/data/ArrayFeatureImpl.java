package invenio.operator.data;

public class ArrayFeatureImpl extends AbstractFeature<ArrayFeatureDescriptor>
				implements ArrayFeature {
	
	protected Feature[] containedFeatures;
	
	protected ArrayFeatureImpl(ArrayFeatureDescriptor fd) {
		super(fd);
		containedFeatures = new Feature[fd.getSize()];
	}

	public Feature getContainedValue(int index) {
		return containedFeatures[index];
	}

	public Feature instantiateContainedFeature(int index) throws UnsupportedFormatException {
		Feature f = getFeatureDescriptor().getContainedFeatureDescriptor().instantiateFeature();
		containedFeatures[index] = f;
		return f;
	}
	
	
}
