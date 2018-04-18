package invenio.operator.data;

import java.io.Serializable;

import edu.uci.ics.jung.utils.UserData;
import invenio.data.InvenioElement;

public class InvenioDataMapper extends AbstractDataMapper implements DataMapper, Serializable {
	private final InvenioElement element;
	
	public InvenioDataMapper(InvenioElement e) {
		this.element = e;
	}

	public void addFeature(
			Feature<? extends FeatureDescriptor> feature)
			throws DuplicateElementException {
		
		String name = feature.getFeatureDescriptor().getName();
		if ( hasFeature(name) )
			throw new DuplicateElementException("Feature with name [" + name + "] already exists");
		
		element.addUserDatum(name, feature, UserData.SHARED);
		getFeatureNameList().add(name);
	}
	
	
	public void setFeature(Feature<? extends FeatureDescriptor> feature) {
		String name = feature.getFeatureDescriptor().getName();
		if ( !hasFeature(name) )
			getFeatureNameList().add(name);
		
		element.setUserDatum(name, feature, UserData.SHARED);
		
		
	}

	public Feature<? extends FeatureDescriptor> getFeature(String name) {
		return (Feature<? extends FeatureDescriptor>) element.getUserDatum(name);
	}


	public boolean hasFeature(String name) {
		return element.containsUserDatumKey(name);
	}


}
