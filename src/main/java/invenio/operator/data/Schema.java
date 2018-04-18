package invenio.operator.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema implements Serializable {
	private List<FeatureDescriptor> featureList = new ArrayList<FeatureDescriptor>();
	private Map<String, FeatureDescriptor> featureMap = new HashMap<String, FeatureDescriptor>();
	private boolean isLocked = false;
	
	public FeatureDescriptor getFeatureDescriptor(int index) {
		return featureList.get(index);
	}
	
	public FeatureDescriptor getFeatureDescriptor(String featureName) {
		return featureMap.get(featureName);
	}
	
	public boolean hasFeatureDescriptor(String featureName) {
		return featureMap.containsKey(featureName);
	}
	
	public int size() {
		return featureList.size();
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	protected void lock() {
		isLocked = true;
	}
	
	protected void addFeatureDescriptor(FeatureDescriptor fd) throws DuplicateElementException {
		checkLocked();
		
		if (featureMap.containsKey(fd.getName()))
			throw new DuplicateElementException("Duplicate feature: " + fd.getName());
		
		featureMap.put(fd.getName(), fd);
		featureList.add(fd);
	}
	
	protected void checkLocked() throws IllegalStateException {
		if (isLocked)
			throw new IllegalStateException("Attempt to modify schema that is locked");
	}
	
	/**
	 * deliberately not overriding equals()
	 * @return
	 */
	public boolean isEquivalentTo(Schema otherSchema) {
		if (this.size() != otherSchema.size())
			return false;
		
		for (int i = 0; i < this.size(); i++) {
			if ( !getFeatureDescriptor(i).isEquivalentTo( otherSchema.getFeatureDescriptor(i) ))
				return false;
		}
		
		return true;
	}
	
	public boolean canGetAsNumeric(int index) {
		if (getFeatureDescriptor(index) instanceof NumericFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public boolean canGetAsNumeric(String featureName) {
		if (getFeatureDescriptor(featureName) instanceof NumericFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public NumericFeatureDescriptor getAsNumeric(int index) throws IncorrectTypeException {
		if ( canGetAsNumeric(index) )
			return (NumericFeatureDescriptor) getFeatureDescriptor(index);
		else
			throw new IncorrectTypeException("Feature descriptor with index [" + index + "] is not numeric");
	}
	
	public NumericFeatureDescriptor getAsNumeric(String featureName) throws IncorrectTypeException {
		if ( canGetAsNumeric(featureName) )
			return (NumericFeatureDescriptor) getFeatureDescriptor(featureName);
		else
			throw new IncorrectTypeException("Feature descriptor with name [" + featureName + "] is not numeric");
	}
	
	public boolean canGetAsCategorical(int index) {
		if (getFeatureDescriptor(index) instanceof CategoricalFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public boolean canGetAsCategorical(String featureName) {
		if (getFeatureDescriptor(featureName) instanceof CategoricalFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public CategoricalFeatureDescriptor getAsCategorical(int index) throws IncorrectTypeException {
		if ( canGetAsCategorical(index) )
			return (CategoricalFeatureDescriptor) getFeatureDescriptor(index);
		else
			throw new IncorrectTypeException("Feature descriptor with index [" + index + "] is not categorical");
	}
	
	public CategoricalFeatureDescriptor getAsCategorical(String featureName) throws IncorrectTypeException {
		if ( canGetAsCategorical(featureName) )
			return (CategoricalFeatureDescriptor) getFeatureDescriptor(featureName);
		else
			throw new IncorrectTypeException("Feature descriptor with name [" + featureName + "] is not categorical");
	}
	
	public boolean canGetAsString(int index) {
		if (getFeatureDescriptor(index) instanceof StringFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public boolean canGetAsString(String featureName) {
		if (getFeatureDescriptor(featureName) instanceof StringFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public StringFeatureDescriptor getAsString(int index) throws IncorrectTypeException {
		if ( canGetAsString(index) )
			return (StringFeatureDescriptor) getFeatureDescriptor(index);
		else
			throw new IncorrectTypeException("Feature descriptor with index [" + index + "] is not string");
	}
	
	public StringFeatureDescriptor getAsString(String featureName) throws IncorrectTypeException {
		if ( canGetAsString(featureName) )
			return (StringFeatureDescriptor) getFeatureDescriptor(featureName);
		else
			throw new IncorrectTypeException("Feature descriptor with name [" + featureName + "] is not string");
	}
	
	public boolean canGetAsArray(int index) {
		if (getFeatureDescriptor(index) instanceof ArrayFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public boolean canGetAsArray(String featureName) {
		if (getFeatureDescriptor(featureName) instanceof ArrayFeatureDescriptor)
			return true;
		else
			return false;
	}
	
	public ArrayFeatureDescriptor getAsArray(int index) throws IncorrectTypeException {
		if ( canGetAsArray(index) )
			return (ArrayFeatureDescriptor) getFeatureDescriptor(index);
		else
			throw new IncorrectTypeException("Feature descriptor with index [" + index + "] is not array");
	}
	
	public ArrayFeatureDescriptor getAsArray(String featureName) throws IncorrectTypeException {
		if ( canGetAsArray(featureName) )
			return (ArrayFeatureDescriptor) getFeatureDescriptor(featureName);
		else
			throw new IncorrectTypeException("Feature descriptor with name [" + featureName + "] is not array");
	}
	
	public static Schema copySchema(Schema source) throws DuplicateElementException {
		Schema target = new Schema();
		
		for ( FeatureDescriptor fd : source.featureList ) {
			target.addFeatureDescriptor(fd);
		}
		
		return target;
	}
}
