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
public class AlignOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "align";
	public static enum TYPE {
		UNION,
		INTERSECTION,
		DIFFERENCE
	}
	public static final Class[] EXPECTED_TYPES = {
		ModifiableTable.class,
		ModifiableTable.class,
		TYPE.class,
		String.class
	};
	
	public AlignOperation() {
		super(null, OP_NAME);
	}
	
	public AlignOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		ModifiableTable t1 = (ModifiableTable) args[0];
		ModifiableTable t2 = (ModifiableTable) args[1];
		
		if (t1.getSchema().getNumberOfColumns() != 1 || 
				! InvenioGraph.class.isAssignableFrom( t1.getSchema().getDeclaredType( 0 ) ) )
			throw new QueryException("Operator " + getName() + ", InvenioGraph type is expected");
		if (t2.getSchema().getNumberOfColumns() != 1 || 
				! InvenioGraph.class.isAssignableFrom( t2.getSchema().getDeclaredType( 0 ) ) )
			throw new QueryException("Operator " + getName() + ", InvenioGraph type is expected");
		
		InvenioGraph g1 = (InvenioGraph) t1.getTuples().get(0).getValue(0);
		InvenioGraph g2 = (InvenioGraph) t2.getTuples().get(0).getValue(0);
		TYPE t = (TYPE) args[2];
		String alias = (String) args[3];
		
		switch (t) {
			case UNION: return packageGraph(processUnion(g1, g2), alias);
			case INTERSECTION: return packageGraph(processIntersection(g1, g2), alias);
			case DIFFERENCE: return packageGraph(processDifference(g1, g2), alias);
		}
		// should not happen
		throw new QueryException("Unrecognized TYPE: " + t);
	}
	
	private Object packageGraph(InvenioGraph graph, String alias) {
		try {
			ModifiableTable t = TableFactory.createTable();
			t.addColumn(alias, InvenioGraph.class);
			Tuple tuple = t.addTuple();
			tuple.setValue(0, graph);
			return t;
		}
		// should not happen
		catch (DuplicateException ex) {
			throw new RuntimeException("Unexpected duplicate exception: " + ex);
		}
	}

	private InvenioGraph processUnion(InvenioGraph g1, InvenioGraph g2) {

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
		return graph;
	}

	private InvenioGraph processIntersection(InvenioGraph g1, InvenioGraph g2) {

		Operator op = new BinaryComparisonOperator(
				g1,
				g2,
				new IntersectionPredicate<InvenioElement>(),
				new IntersectionPredicate<InvenioElement>(),
				new SimpleKeyVertexTransformationFunction(),
				new SimpleKeyEdgeTransformationFunction()
				);
		
		op.execute();
		InvenioGraph graph = (InvenioGraph) op.getResultMap().get(BinaryComparisonOperator.RESULT_GRAPH_KEY);
		return graph;
	}
	
	private InvenioGraph processDifference(InvenioGraph g1, InvenioGraph g2) {

		Operator op = new BinaryComparisonOperator(
				g1,
				g2,
				new DifferencePredicate<InvenioElement>(),
				new DifferencePredicate<InvenioElement>(),
				new SimpleKeyVertexTransformationFunction(),
				new SimpleKeyEdgeTransformationFunction()
				);
		
		op.execute();
		InvenioGraph graph = (InvenioGraph) op.getResultMap().get(BinaryComparisonOperator.RESULT_GRAPH_KEY);
		return graph;
	}

}
