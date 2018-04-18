package invenio.operator.data;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataMapper implements DataMapper {
	
	private List<String> featureNameList = new ArrayList<String>();
	
	protected List<String> getFeatureNameList() {
		return featureNameList;
	}
	
//	@Override
//	public Feature<? extends FeatureDescriptor> getFeature(int index) {
//		return getFeature( featureNameList.get(index) );
//	}
//
//	@Override
//	public boolean hasFeature(int index) {
//		return hasFeature( featureNameList.get(index) );
//	}

	public int size() {
		return featureNameList.size();
	}

}
