package invenio.operator.data;


public interface ArrayFeatureDescriptor extends FeatureDescriptor<ArrayFeature> {
	int getSize();
	void setSize(int size) throws IllegalStateException, IllegalArgumentException;
	String getLabel(int index);
	void setLabels(String[] labels) throws IllegalStateException;
	boolean hasLabels();
	FeatureDescriptor getContainedFeatureDescriptor();
	void setContainedFeatureDescriptor(FeatureDescriptor fd) throws IllegalStateException;
	boolean isSizeSet();
}
