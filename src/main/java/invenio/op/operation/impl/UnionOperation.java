package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.operator.Operator;
import invenio.operator.comparison.BinaryComparisonOperator;
import invenio.operator.comparison.DifferencePredicate;
import invenio.operator.comparison.IntersectionPredicate;
import invenio.operator.comparison.SimpleKeyEdgeTransformationFunction;
import invenio.operator.comparison.SimpleKeyVertexTransformationFunction;
import invenio.operator.comparison.UnionPredicate;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.DuplicateException;
import qng.tabular.ModifiableTable;
import qng.tabular.TableFactory;
import qng.tabular.Tuple;

// TODO: process attributes
public class UnionOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "union";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class,
		InvenioGraph.class,
		String.class
	};
	
	public UnionOperation() {
		super(null, OP_NAME);
	}
	
	public UnionOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		InvenioGraph g1 = (InvenioGraph) args[0];
		InvenioGraph g2 = (InvenioGraph) args[1];
		String graphName = (String) args[2];
		
		return processUnion(g1, g2, graphName);
	}

	private InvenioGraph processUnion(InvenioGraph g1, InvenioGraph g2, String graphName) {

		Operator op = new BinaryComparisonOperator(
				g1,
				g2,
				new UnionPredicate<InvenioElement>(),
				new UnionPredicate<InvenioElement>(),
				new SimpleKeyVertexTransformationFunction(),
				new SimpleKeyEdgeTransformationFunction()
				);
		
		op.execute();
		InvenioGraph graph = (InvenioGraph) op.getResultMap().get(BinaryComparisonOperator.RESULT_GRAPH_KEY);
		graph.setName(graphName);
		return graph;
	}
}
