package invenio.operator.data;

public abstract class AbstractFeature<T extends FeatureDescriptor> implements Feature<T> {
	private final T featureDescriptor;
	
	protected AbstractFeature(T featureDescriptor) {
		this.featureDescriptor = featureDescriptor;
	}

	public T getFeatureDescriptor() {
		return featureDescriptor;
	}
	
}
