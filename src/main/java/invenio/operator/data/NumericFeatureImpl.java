package invenio.operator.data;

public class NumericFeatureImpl extends AbstractFeature<NumericFeatureDescriptor> implements
		NumericFeature {
	
	private double value;
	private boolean valueSet;
	
	protected NumericFeatureImpl(NumericFeatureDescriptor fd) {
		super(fd);
	}
	
	public double getValue() {
		return valueSet ? value : getFeatureDescriptor().getDefaultValue();
	}

	public void setValue(double v) {
		this.value = v;
		valueSet = true;
	}
	
	public String toString() {
		return "" + getValue();
	}
}
