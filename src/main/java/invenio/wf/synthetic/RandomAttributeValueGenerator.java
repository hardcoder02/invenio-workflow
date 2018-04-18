package invenio.wf.synthetic;

import java.util.List;
import java.util.Random;
import java.util.Set;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeature;
import invenio.wf.util.FeatureUtils;

public class RandomAttributeValueGenerator extends GeneratorStep {
	private Random rand = new Random();
	
	private boolean processVertices = true;
	private boolean processEdges = true;
	
	public RandomAttributeValueGenerator() {}
	public RandomAttributeValueGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting attribute value generation...");
		long startTime = System.currentTimeMillis();
		if ( processVertices )
			processElements( graph.getVertices() );
		if ( processEdges )
			processElements( graph.getEdges() );
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Attribute value generation completed in [" + duration + "] msec.");
	}
	
	protected void processElements(List<? extends InvenioElement> elements) throws DataFormatException {
		
		for ( InvenioElement e : elements ) {
			ComparableFeatureContainer c = FeatureUtils.getFeatureContainer(e, true);
			
			Schema s = c.getSchema();
			int size = s.size();
			
			for (int i = 0; i < size; i++) {
				if ( "id".equals( s.getFeatureDescriptor(i).getName() ) )
					continue;
				
				if ( s.canGetAsNumeric( i ) ) {
					generateNumericFeature( (NumericFeature) c.instantiateFeature( i ) );
				}
				else if ( s.canGetAsString( i ) ) {
					generateStringFeature( (StringFeature) c.instantiateFeature( i ) );
				}
				else if ( s.canGetAsCategorical( i ) ) {
					generateCategoricalFeature( (CategoricalFeature) c.instantiateFeature( i ) );
				}
			}
		}
	}
	
	protected void generateNumericFeature( NumericFeature feature) {
			feature.setValue( rand.nextDouble() * (10 ^ rand.nextInt(3)) );
	}
	
	protected void generateStringFeature( StringFeature feature) {
		int length = rand.nextInt( 10 );
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char nextChar = (char) ('a' + rand.nextInt(26));
			sb.append(nextChar);
		}
		feature.setValue( sb.toString() );
	}
	
	protected void generateCategoricalFeature( CategoricalFeature feature) {
		int numCategories = feature.getFeatureDescriptor().getNumCategories();
		double[] probs = new double[numCategories];
		int selIndex = -1;
		double max = -1;
		double sum = 0;
		
		// fill with raw values
		for (int i = 0; i < probs.length; i++ ) {
			double newVal = rand.nextDouble();
			probs[i] = newVal;
			sum += newVal;
			
			if ( max < newVal ) {
				max = newVal;
				selIndex = i;
			}
		}
		
		// normalize
		// fill with raw values
		for (int i = 0; i < probs.length; i++ ) {
			probs[i] = probs[i] / sum;
		}
		
		feature.setProbabilities( probs );
		feature.setSelectedIndex( selIndex );
	}


	public boolean isProcessVertices() {
		return processVertices;
	}
	public void setProcessVertices(boolean processVertices) {
		this.processVertices = processVertices;
	}
	public boolean isProcessEdges() {
		return processEdges;
	}
	public void setProcessEdges(boolean processEdges) {
		this.processEdges = processEdges;
	}
	
	
}
