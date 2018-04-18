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

public class RootsOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "roots";

	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class
	};
	
	public RootsOperation() {
		super(null, OP_NAME);
	}
	
	public RootsOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public boolean hasExactNumberOfArguments() {
		return false;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		InvenioGraph graph  = (InvenioGraph) args[0];
		
		if (graph == null)
			return null;
		
		if (args.length < 2)
			return DirectedUtils.getRoots(graph, false);
		else if (args.length > 2)
			throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Expected arguments count is 1 or 2, actual count is " + args.length);
		
		// args length is exactly 2
		if (args[1] == null)
			throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Second argument may be omitted, but cannot be null");
		
		if ( ! (args[1] instanceof Boolean) )
			throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Expected argument 2 is of type Boolean, actual type is " + args[1].getClass().getName() );
		
		return DirectedUtils.getRoots(graph, (Boolean)args[1]);
	}
}
