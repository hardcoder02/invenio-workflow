package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.MissingElementException;
import qng.client.QueryException;
import qng.core.executor.QueryExecutor;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.tabular.Tuple;

public class PathOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "path";

	public static final Class[] EXPECTED_TYPES = {
		String.class,
		String.class
	};
	
	public PathOperation() {
		super(null, OP_NAME);
	}
	
	public PathOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		String colName = (String) args[0];
		String attrName = (String) args[1];
		
		Tuple t = (Tuple) getContext().getVariable(OperationConstants.VAR_TUPLE);
		
		if (t == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find Tuple in Context");
		
		Object colValue = t.getValue(colName);
		
		if (attrName == null)
			return colValue;
		
		if ( ! (colValue instanceof InvenioElement) )
			throw new QueryException("Operator " + getName() + ": expected type is InvenioElement");
		
		InvenioElement invElem = (InvenioElement) colValue;
		return OperatorUtils.getFeature(invElem, attrName);
		
	}
}
