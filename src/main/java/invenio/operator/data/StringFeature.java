package invenio.operator.data;

public interface StringFeature extends Feature<StringFeatureDescriptor> {
	String getValue();
	void setValue(String v);
}
