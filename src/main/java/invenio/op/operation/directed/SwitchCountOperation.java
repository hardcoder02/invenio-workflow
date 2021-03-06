package invenio.op.operation.directed;

import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.op.operation.aggregate.impl.AggregateExecutor;
import invenio.op.operation.directed.SwitchNumberCalculator.IncrementCountPredicate;
import invenio.op.operation.directed.SwitchNumberCalculator.SwitchNumberCount;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperatorUtils;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.StringFeature;

import java.util.ArrayList;
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

public class SwitchCountOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "switchCount";

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		String.class
	};
	
	public SwitchCountOperation() {
		super(null, OP_NAME);
	}
	
	public SwitchCountOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		InvenioVertex root = (InvenioVertex) args[0];
		String attrName = (String) args[1];
		
		return processSwitchCount( root, attrName ).getSwitchRatio();
		
	}


	private SwitchNumberCount processSwitchCount(InvenioVertex root, final String attrName) throws QueryException {
		SwitchNumberCalculator calc = new SwitchNumberCalculator();
		SwitchNumberCount count = calc.evaluateCount(root, new IncrementCountPredicate() {
			
			public boolean isSwitch(InvenioVertex v1, InvenioVertex v2) throws QueryException {
				StringFeature f1 = OperatorUtils.getAsStringFeature( v1, attrName, true );
				StringFeature f2 = OperatorUtils.getAsStringFeature( v2, attrName, true );
				// TODO fix null case
//				if ( f1 == null || f2 == null )
//					return null;
				return ! f1.equals(f2);
			}
		});
		
		return count;
	}


}
