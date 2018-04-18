package invenio.operator.data;

import java.io.Serializable;

public interface FeatureDescriptor<T extends Feature> extends Serializable {
	String getName();
	String getTypeDesc();
	
	/**
	 *  deliberately not overriding equals()
	 * @param otherFD
	 * @return
	 */
	boolean isEquivalentTo(FeatureDescriptor<T> otherFD);
	
	T instantiateFeature() throws UnsupportedFormatException;
}
