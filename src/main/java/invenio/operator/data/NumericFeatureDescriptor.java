package invenio.operator.data;

public interface NumericFeatureDescriptor extends FeatureDescriptor<NumericFeature> {
	double getDefaultValue();
	void setDefaultValue(double defValue) throws IllegalStateException;
	boolean hasDefaultValue();
}
