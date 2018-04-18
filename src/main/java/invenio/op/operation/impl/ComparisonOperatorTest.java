package invenio.op.operation.impl;

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
import invenio.operator.data.DataFormatException;
import invenio.operator.io.GiaFileReader;
import invenio.operator.io.GiaGraphConverter;

import java.io.IOException;
import java.util.Iterator;

public class ComparisonOperatorTest {
	public static final String DEFAULT_CONFIG_FILE = "resource/SampleFiles/GraphVisualizationExperimentSample/experiment.cfg";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ComparisonOperatorTest test = new ComparisonOperatorTest();
		InvenioGraph t = test.testCompare();
		Iterator vIter = t.getVertices().iterator();
		int i = 0;
		while (vIter.hasNext()) System.out.println (i++ + "" + vIter.next());
		
	}
	
	public InvenioGraph testCompare() throws Exception {
		Operator op = new BinaryComparisonOperator(
				createGraph1(),
				createGraph2(),
				new IntersectionPredicate<InvenioElement>(),
				new IntersectionPredicate<InvenioElement>(),
				new SimpleKeyVertexTransformationFunction(),
				new SimpleKeyEdgeTransformationFunction()
				);
		
//		Operator op = new BinaryComparisonOperator(
//				readGraph("resource/SampleFiles/GraphVisualizationExperimentSample/SimpleExperiment1.cfg"),
//				readGraph("resource/SampleFiles/GraphVisualizationExperimentSample/SimpleExperiment2.cfg"),
//				new UnionPredicate<InvenioElement>(),
//				new UnionPredicate<InvenioElement>(),
//				new SimpleKeyVertexTransformationFunction(),
//				new SimpleKeyEdgeTransformationFunction()
//				);
		
		op.execute();
		InvenioGraph graph = (InvenioGraph) op.getResultMap().get(BinaryComparisonOperator.RESULT_GRAPH_KEY);
		
		for ( Object v : graph.getVertices() ) {
			System.out.println( ((InvenioVertex)v).getKey());
		}
		
		return graph;
	}
	
	public InvenioGraph createGraph1() {
		InvenioGraph graph = new InvenioGraph();
		addVertex(graph, "A");
		addVertex(graph, "B");
		addVertex(graph, "C");
		
		
		return graph;
	}
	
	public InvenioGraph createGraph2() {
		InvenioGraph graph = new InvenioGraph();
		addVertex(graph, "A");
		addVertex(graph, "B");
		addVertex(graph, "D");
		
		return graph;
	}
	
	public void addVertex(InvenioGraph graph, String key) {
		InvenioVertex v = new InvenioVertex();
		v.setKeyAttribute("key_attr");
		v.setKey(key);
		graph.addVertex(v);
	}
	
//	public InvenioGraph readGraph(String configFile) throws DataFormatException, IOException {
//		
//		return (new GiaGraphConverter()).convertfromGia( GiaFileReader.readGraph(configFile) );
//	}
}
