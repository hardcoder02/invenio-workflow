package invenio.operator.data;

public class StringFeatureImpl extends AbstractFeature<StringFeatureDescriptor> implements
		StringFeature {
	
	private String value;
	private boolean valueSet;
	
	protected StringFeatureImpl(StringFeatureDescriptor fd) {
		super(fd);
	}
	
	public String getValue() {
		return valueSet ? value : getFeatureDescriptor().getDefaultValue();
	}

	public void setValue(String v) {
		this.value = v;
		valueSet = true;
	}

	public String toString() {
		return getValue();
	}
	
	@Override
	public int hashCode() {
		if (getValue() != null)
			return getValue().hashCode();
		else
			return super.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		String val = getValue();
		if ( val == null || other == null )
			return false;
		if ( ! (other instanceof StringFeature) )
			return false;
		String otherVal = ((StringFeature)other).getValue();
		return val.equals(otherVal);
	}
}
