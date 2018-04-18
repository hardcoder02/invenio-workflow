package invenio.operator.data;

public interface NumericFeature extends Feature<NumericFeatureDescriptor> {
	double getValue();
	void setValue(double v);
}
