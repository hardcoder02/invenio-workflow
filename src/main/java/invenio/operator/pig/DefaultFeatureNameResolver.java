package invenio.operator.pig;

import invenio.operator.data.MissingElementException;

public class DefaultFeatureNameResolver implements DerivedFeatureNameResolver {
	
	public static final String PREFIX = "@@@";
	public static final String SEPARATOR = "^^^";
	
	public String getDerivedFeatureName(String operatorName,
			String baseFeatureName) throws MissingElementException {

		return PREFIX + baseFeatureName + SEPARATOR + operatorName;
	}

}
