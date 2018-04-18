package invenio.operator.data;

public interface StringFeatureDescriptor extends FeatureDescriptor<StringFeature> {
	String getDefaultValue();
	void setDefaultValue(String defValue) throws IllegalStateException;
	boolean hasDefaultValue();
}
