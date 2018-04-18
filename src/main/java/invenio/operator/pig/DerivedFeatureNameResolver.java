package invenio.operator.pig;

import invenio.operator.data.MissingElementException;

public interface DerivedFeatureNameResolver {
	String getDerivedFeatureName(String operatorName, String baseFeatureName) throws MissingElementException;
}
