package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.operator.data.Feature;
import invenio.operator.data.FeatureDescriptor;
import invenio.operator.data.NumericFeature;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: hint regarding conf attribute name
public class ConfOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "conf";
	public final static String CONF_ATTRIB_NAME = "conf";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioElement.class
	};
	
	public ConfOperation() {
		super(null, OP_NAME);
	}
	
	public ConfOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	
	@Override
	public Object doExecute(Object[] args) throws QueryException {
		if ( args[0] == null)
			return null;
		
		InvenioElement e = (InvenioElement) args[0];
		
		String confAttrName = OperatorUtils.retrieveContextParam(getContext(), OperationConstants.CONF_CONF_NAME, CONF_ATTRIB_NAME);
		
		Feature<? extends FeatureDescriptor> f = OperatorUtils.getFeature(e, confAttrName);
		
		if (f == null)
			return null;
		else if (f instanceof NumericFeature) {
			NumericFeature nf = (NumericFeature)f;
			double value = nf.getValue();
			if (value < 0 || value > 1)
				throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
						"]. Illegal confidence value " + value );
			return value;
			
		}
		else {
			throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Confidence attribute must be of numeric type");
		}
	}

}
