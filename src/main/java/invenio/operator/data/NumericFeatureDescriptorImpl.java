package invenio.operator.data;

public class NumericFeatureDescriptorImpl extends
		AbstractFeatureDescriptor<NumericFeature> implements NumericFeatureDescriptor {
	
	private double defaultValue;
	private boolean defaultSet = false;
	
	protected NumericFeatureDescriptorImpl(String name) {
		super(name);
	}

	public double getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(double defValue) throws IllegalStateException {
		if (defaultSet)
			throw new IllegalStateException("Default value can be only set once");
		
		this.defaultValue = defValue;
		defaultSet = true;
		
	}
	
	public boolean hasDefaultValue() {
		return defaultSet;
	}

	public boolean isEquivalentTo(FeatureDescriptor<NumericFeature> otherFD) {
		if ( !(otherFD instanceof NumericFeatureDescriptor) )
			return false;
		
		NumericFeatureDescriptor other = (NumericFeatureDescriptor) otherFD;
		if ( getName().equals(other.getName()) && getDefaultValue() == other.getDefaultValue())
			return true;
		else
			return false;
	}
	
	public String getTypeDesc() {
		return "Numeric";
	}
	
	public String toString() {
		return getName() + ":Numeric";
	}

}
