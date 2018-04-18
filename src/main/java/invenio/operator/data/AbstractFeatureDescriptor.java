package invenio.operator.data;

abstract class AbstractFeatureDescriptor<T extends Feature> implements
		FeatureDescriptor<T> {
	
	private final String name;
	
	protected AbstractFeatureDescriptor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public T instantiateFeature() throws UnsupportedFormatException {
		return (T) FeatureFactory.instantiateFeature(this);
	}
}
