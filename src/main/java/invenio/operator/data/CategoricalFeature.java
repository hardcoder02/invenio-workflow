package invenio.operator.data;

public interface CategoricalFeature extends Feature<CategoricalFeatureDescriptor> {
	String getSelectedValue();
	int getSelectedIndex();
	void setSelectedValue(String category) throws MissingElementException;
	void setSelectedIndex(int index);
	
	void setProbability(int index, double probability);
	void setProbabilities(double[] probabilities) throws IllegalArgumentException;	// if probabilities.length is different than the on in the FeatureDescriptor
	double getProbability(int index);
	double[] getProbabilities();
	
}
