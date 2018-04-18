package invenio.operator.data;

import java.util.Arrays;

public class ArrayFeatureDescriptorImpl extends
	AbstractFeatureDescriptor<ArrayFeature> implements ArrayFeatureDescriptor {
	
	protected int size = 0;
	private String[] labels = null;
	protected boolean isSizeSet = false;
	private FeatureDescriptor containedFD = null;
	
	protected ArrayFeatureDescriptorImpl(String name) {
		super(name);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) throws IllegalStateException, IllegalArgumentException {
		if (isSizeSet)
			throw new IllegalStateException("Array size cannot be reset");
		
		if (size < 0)
			throw new IllegalArgumentException("Size cannot be negative");
		this.size = size;
		isSizeSet = true;
	}

	public String getLabel(int index) {
		return (labels == null) ? null : labels[index];
	}

	public void setLabels(String[] labels) throws IllegalStateException {
		if (labels == null)
			throw new NullPointerException("Labels cannot be null");
		
		if ( this.labels != null )
			throw new IllegalStateException("Labels can only be set once");
		
		if ( isSizeSet() && labels.length != size )
			throw new IllegalStateException("Labels need to have size: " + size );
		
		this.labels = Arrays.copyOf(labels, labels.length);
		
		if ( !isSizeSet() )
			setSize(labels.length);		// also locks
	}
	
	public boolean hasLabels() {
		return labels != null;
	}

	public boolean isSizeSet() {
		return isSizeSet;
	}
	
	public FeatureDescriptor getContainedFeatureDescriptor() {
		return containedFD;
	}

	public void setContainedFeatureDescriptor(FeatureDescriptor fd)
			throws IllegalStateException {
		
		if (fd == null)
			throw new NullPointerException("Contained FeatureDescriptor cannot be null");
		
		if ( this.containedFD != null )
			throw new IllegalStateException("Contained FeatureDescriptor can only be set once");
		
		this.containedFD = fd;
	}

	public boolean isEquivalentTo(FeatureDescriptor<ArrayFeature> otherFD) {
		if ( !(otherFD instanceof ArrayFeatureDescriptor) )
			return false;
		
		ArrayFeatureDescriptor other = (ArrayFeatureDescriptor) otherFD;
		if ( !getName().equals(other.getName()) || getSize() != other.getSize() || hasLabels() != other.hasLabels() )
			return false;
		
		if ( hasLabels() )
			for (int i = 0; i < getSize(); i++) {
				String thisLabel = getLabel(i);
				String otherLabel = other.getLabel(i);
				
				if ( ( thisLabel == null && otherLabel != null )
					|| ( thisLabel != null && !thisLabel.equals(otherLabel) ) )
						return false;
			}
		
		if ( ( containedFD == null && other.getContainedFeatureDescriptor() != null )
				|| ( containedFD != null && other.getContainedFeatureDescriptor() == null )
				|| ( containedFD != null && other.getContainedFeatureDescriptor() != null && 
					!containedFD.isEquivalentTo(other.getContainedFeatureDescriptor()) ) )
			return false;
		
		return true;
	}
	
	public String getTypeDesc() {
		return "Array";
	}
	
	public String toString() {
		return getName() + ":Array";
	}
}
