package invenio.op.operation.sim.ego.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
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
public class EgoNetAlignOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "egoNetAlign";
	
	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public EgoNetAlignOperation() {
		super(null, OP_NAME);
	}
	
	public EgoNetAlignOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		InvenioVertex ego1 = (InvenioVertex) args[0];
		InvenioVertex ego2 = (InvenioVertex) args[1];
		
		if (n1.getGraph() == n2.getGraph() )
			return alignOneGraph(ego1, ego2);
		else
			return alignTwoGraphs(ego1, ego2);
		
		
		
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
	
	private Object calculateOneGraph(InvenioVertex n1, InvenioVertex n2) {
		Set<InvenioVertex> vertices1 = n1.getNeighbors();
		Set<InvenioVertex> vertices2 = n2.getNeighbors();
		
		int numCommon = 0;
		int numTotal = 0;
		for ( InvenioVertex v : vertices1 ) {
			numTotal++;
			if (vertices2.contains( v )) {
				numCommon ++;
				vertices2.remove( v );
			}
		}
		numTotal += vertices2.size();
		return numCommon / (double) numTotal;
	}

	private Object calculateTwoGraphs(InvenioVertex n1, InvenioVertex n2) {
		final Map<Object, InvenioVertex> vertices1 = new HashMap<Object, InvenioVertex>();
		Iterator<InvenioVertex> iter = n1.getNeighbors().iterator();
		while (iter.hasNext()) {
			InvenioVertex v = iter.next();
			vertices1.put( v.getKey(), v );
		}
		
		int numCommon = 0;
		int numTotal = 0;
		iter = n2.getNeighbors().iterator();
		while (iter.hasNext()) {
			numTotal++;
			InvenioVertex v = iter.next();
			if (vertices1.containsKey( v.getKey() )) {
				numCommon ++;
				vertices1.remove( v.getKey() );
			}
		}
		numTotal += vertices1.size();
		return numCommon / (double) numTotal;
	}

}
