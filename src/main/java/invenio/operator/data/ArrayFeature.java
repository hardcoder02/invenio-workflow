package invenio.operator.data;

public interface ArrayFeature extends Feature<ArrayFeatureDescriptor> {
	Feature getContainedValue(int index);
	Feature instantiateContainedFeature(int index) throws UnsupportedFormatException;
}
