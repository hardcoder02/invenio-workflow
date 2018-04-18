package invenio.wf.item.algorithm;

import invenio.algorithms.AbstractDataAlgorithm;
import invenio.algorithms.AbstractVisualAlgorithm;
import invenio.algorithms.Algorithm;
import invenio.algorithms.AlgorithmResult;
import invenio.algorithms.util.AlgorithmParameterPanel;
import invenio.data.InvenioGraph;
import invenio.data.InvenioSchemaTree;
import prefuse.data.expression.BooleanLiteral;

public class AlgorithmManager extends invenio.algorithms.util.AlgorithmManager {
	public AlgorithmManager() {
		super(null);
	}

	@Override
	public boolean processResults(AlgorithmResult results) {
		// TODO Auto-generated method stub
		return super.processResults(results);
	}

	public AlgorithmParameterPanel runAlgorithm(Class algorithm, InvenioGraph graph) {
		try {
			if(!Algorithm.class.isAssignableFrom(algorithm)) throw new UnsupportedOperationException("algorithm parameter not an Algorithm class");

			Algorithm alg;
			alg = (Algorithm)algorithm.getConstructor().newInstance();

			InvenioSchemaTree parentSet = graph != null ? graph.getSets() : null;
			
			if(alg instanceof AbstractVisualAlgorithm) {
				((AbstractVisualAlgorithm)alg).setGraph(null, 
						new BooleanLiteral(true), parentSet);
			}else{
				((AbstractDataAlgorithm)alg).setGraph(graph, 
						new BooleanLiteral(true), parentSet);
			}

			AlgorithmParameterPanel settings = alg.createParameterPanel(0);

			return settings;
		}
		catch(Exception e) {
			System.err.println("AlgorithmManager: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}


	public Algorithm getAlgorithm(Class algorithm, InvenioGraph graph) {
		try {
			if(!Algorithm.class.isAssignableFrom(algorithm)) throw new UnsupportedOperationException("algorithm parameter not an Algorithm class");

			Algorithm alg;
			alg = (Algorithm)algorithm.getConstructor().newInstance();

			InvenioSchemaTree parentSet = graph != null ? graph.getSets() : null;
			
			if(alg instanceof AbstractVisualAlgorithm) {
				((AbstractVisualAlgorithm)alg).setGraph(null, 
						new BooleanLiteral(true), parentSet);
			}else{
				((AbstractDataAlgorithm)alg).setGraph(graph, 
						new BooleanLiteral(true), parentSet);
			}

			return alg;
		}
		catch(Exception e) {
			System.err.println("AlgorithmManager: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	
}
