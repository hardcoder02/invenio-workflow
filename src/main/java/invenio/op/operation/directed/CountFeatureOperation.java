package invenio.op.operation.directed;

import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.op.operation.aggregate.impl.AggregateExecutor;
import invenio.op.operation.directed.GraphFeatureProcessor.FeaturePredicate;
import invenio.op.operation.directed.SwitchNumberCalculator.IncrementCountPredicate;
import invenio.op.operation.directed.SwitchNumberCalculator.SwitchNumberCount;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperatorUtils;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.StringFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import qng.client.QueryException;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.QueryExecutor;
import qng.core.executor.Util;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.DuplicateException;
import qng.core.query.NotExistsException;
import qng.tabular.AggregateModifiableTable;
import qng.tabular.AggregateTable;
import qng.tabular.AggregateTuple;
import qng.tabular.ModifiableTable;
import qng.tabular.Schema;
import qng.tabular.Table;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

public class CountFeatureOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "countFeature";

	public static final Class[] EXPECTED_TYPES = {
		Collection.class,
		String.class
	};
	
	public CountFeatureOperation() {
		super(null, OP_NAME);
	}
	
	public CountFeatureOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		Collection vertices = (Collection) args[0];
		final String attrName = (String) args[1];
		
		if (vertices == null)
			return null;
		
		return GraphFeatureProcessor.countFeature(vertices, new FeaturePredicate() {			
			public boolean hasFeature(InvenioVertex v) throws QueryException {
				StringFeature f = OperatorUtils.getAsStringFeature( v, attrName, true );
				return "TRUE".equalsIgnoreCase( f.getValue() );
			}
		});
	}
}
