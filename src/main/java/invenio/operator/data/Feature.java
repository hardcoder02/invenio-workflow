package invenio.operator.data;

import java.io.Serializable;

public interface Feature<T extends FeatureDescriptor> extends Serializable {
	T getFeatureDescriptor();
}
