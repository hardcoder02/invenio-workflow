package invenio.operator.data;

import java.util.Arrays;

public class CategoricalFeatureImpl extends AbstractFeature<CategoricalFeatureDescriptor> implements
		CategoricalFeature {
	
	private int selectedIndex = -1;
	private double[] probabilities;
	
	protected CategoricalFeatureImpl(CategoricalFeatureDescriptor fd) throws IllegalStateException {
		super(fd);
		if ( !fd.isLocked() )
			throw new IllegalStateException("Cannot create feature on unlocked feature descriptor " + fd.getName());
		
		probabilities = new double[fd.getNumCategories()];
	}

	public double getProbability(int index) {
		return probabilities[index];
	}
	
	public void setProbability(int index, double probability) {
		probabilities[index] = probability;
	}

	public void setProbabilities(double[] probabilities)
			throws IllegalArgumentException {
		if (this.probabilities.length != probabilities.length)
			throw new IllegalArgumentException("Size of probability array (" + 
							probabilities.length + ") does not match the expected size (" + this.probabilities.length + ")");
		
		for ( int i = 0; i < probabilities.length; i++ )
			setProbability(i, probabilities[i]);
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public String getSelectedValue() {
		return (selectedIndex >= 0) ?
				getFeatureDescriptor().getCategory( selectedIndex ) : getFeatureDescriptor().getDefaultCategory();
	}

	public void setSelectedIndex(int index) {
		if (index < 0 || index >= getFeatureDescriptor().getNumCategories())
			throw new IndexOutOfBoundsException(
					"Index must be between " + 0 + " and " + (getFeatureDescriptor().getNumCategories() - 1) );
		
		selectedIndex = index;
	}

	public void setSelectedValue(String category)
			throws MissingElementException {
		
		selectedIndex = getFeatureDescriptor().getCategory(category);
	}
	
	public double[] getProbabilities() {
		return Arrays.copyOf(probabilities, probabilities.length);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append( getSelectedValue() ).append(":P=");
		for ( int i = 0; i < probabilities.length; i++ ) {
			sb.append(probabilities[i]);
			if ( i < probabilities.length-1 )
				sb.append(",");
		}
		return sb.toString();
	}
}
