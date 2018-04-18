package invenio.operator.data;

public class StringFeatureDescriptorImpl extends
		AbstractFeatureDescriptor<StringFeature> implements StringFeatureDescriptor {
	
	private String defaultValue;
	private boolean defaultSet = false;
	
	protected StringFeatureDescriptorImpl(String name) {
		super(name);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defValue) throws IllegalStateException {
		if (defaultSet)
			throw new IllegalStateException("Default value can be only set once");
		
		this.defaultValue = defValue;
		defaultSet = true;
		
	}
	
	public boolean hasDefaultValue() {
		return defaultSet;
	}

	public boolean isEquivalentTo(FeatureDescriptor<StringFeature> otherFD) {
		if ( !(otherFD instanceof StringFeatureDescriptor) )
			return false;
		
		StringFeatureDescriptor other = (StringFeatureDescriptor) otherFD;
		if ( !getName().equals(other.getName()) )
			return false;
		
		if ( getDefaultValue() == null ) {
			return other.getDefaultValue() == null;
		}
		else {
			return getDefaultValue().equals( other.getDefaultValue() );
		}
	}
	
	public String getTypeDesc() {
		return "String";
	}
	
	public String toString() {
		return getName() + ":String";
	}

}
