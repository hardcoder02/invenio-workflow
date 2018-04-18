package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.operator.Operator;
import invenio.operator.comparison.BinaryComparisonOperator;
import invenio.operator.comparison.DifferencePredicate;
import invenio.operator.comparison.OrphanIgnoringKeyEdgeTransformationFunction;
import invenio.operator.comparison.SimpleKeyEdgeTransformationFunction;
import invenio.operator.comparison.SimpleKeyVertexTransformationFunction;
import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: process attributes
public class DifferenceOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "difference";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioGraph.class,
		InvenioGraph.class,
		String.class
	};
	
	public DifferenceOperation() {
		super(null, OP_NAME);
	}
	
	public DifferenceOperation(String id) {
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
		
		return processDifference(g1, g2, graphName);
	}

	private InvenioGraph processDifference(InvenioGraph g1, InvenioGraph g2, String graphName) {

		Operator op = new BinaryComparisonOperator(
				g1,
				g2,
				new DifferencePredicate<InvenioElement>(),
				new DifferencePredicate<InvenioElement>(),
				new SimpleKeyVertexTransformationFunction(),
				new OrphanIgnoringKeyEdgeTransformationFunction()
				);
		
		op.execute();
		InvenioGraph graph = (InvenioGraph) op.getResultMap().get(BinaryComparisonOperator.RESULT_GRAPH_KEY);
		graph.setName(graphName);
		return graph;
	}

}
