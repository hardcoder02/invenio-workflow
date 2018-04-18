package invenio.wf.synthetic;

import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.uci.ics.jung.utils.UserData;
import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DataMapper;
import invenio.operator.data.DuplicateElementException;
import invenio.operator.data.InvenioDataMapper;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeatureDescriptor;
import invenio.operator.data.UnsupportedFormatException;
import invenio.wf.util.FeatureUtils;

public class RandomSchemaGenerator extends GeneratorStep {
	private Random rand = new Random();

	private int numNumeric;
	private int numString;
	private int numCategotical;
	private int maxCatSize;
		
	private boolean processVertices = true;
	private boolean processEdges = true;
	
	public RandomSchemaGenerator() {}
	public RandomSchemaGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting schema generation...");
		long startTime = System.currentTimeMillis();
		if ( processVertices )
			processElements( graph.getVertices() );
		if ( processEdges )
			processElements( graph.getEdges() );
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Schema generation completed in [" + duration + "] msec.");
	}

	protected void processElements(List<? extends InvenioElement> elements) throws DataFormatException {
		Schema schema = generateInvenioSchema();
		
		for ( InvenioElement e : elements ) {
			FeatureUtils.setContainer(e, schema);
		}
	}
	
	
	protected Schema generateInvenioSchema() throws DataFormatException {

		ComparableFeatureContainer c = new ComparableFeatureContainer();
		
//		// setup ID
//		c.addStringFeatureDescriptor("id");
		
		// add numeric features
		for (int i = 0; i < numNumeric; i++) {
			String id = "num_" + i;
			NumericFeatureDescriptor f = c.addNumericFeatureDescriptor(id);
		}
		
		// add string features
		for (int i = 0; i < numString; i++ ) {
			String id = "string_" + i;
			StringFeatureDescriptor f = c.addStringFeatureDescriptor(id);
		}
		
		// add categorical features
		for (int i = 0; i < numCategotical; i++ ) {
			String id = "cat_" + i;
			String[] cats = generateCategories( maxCatSize );
			CategoricalFeatureDescriptor f = c.addCategoricalFeatureDescriptor(id);
			f.setCategories(cats);
		}
		
		c.lockSchema();
		return c.getSchema();
	}
	
	private String[] generateCategories(int maxCatSize2) {
		if (maxCatSize < 2)
			throw new IllegalArgumentException("Number of categories must be at least two");
		
		final int catSize = rand.nextInt(maxCatSize-1) + 2;
		String[] cats = new String[catSize];
		
		for (int i = 0; i < cats.length; i++)
			cats[i] = "" + (char)('a' + i);
		
		return cats;
	}

	public int getNumNumeric() {
		return numNumeric;
	}

	public void setNumNumeric(int numNumeric) {
		if (numNumeric < 0)
			throw new IllegalArgumentException("Number of numeric attributes must be non-negative: " + numNumeric);
		this.numNumeric = numNumeric;
	}


	public int getNumString() {
		return numString;
	}


	public void setNumString(int numString) {
		if (numString < 0)
			throw new IllegalArgumentException("Number of string attributes must be non-negative: " + numString);
		this.numString = numString;
	}


	public int getNumCategorical() {
		return numCategotical;
	}


	public void setNumCategorical(int numCat) {
		if (numCat < 0)
			throw new IllegalArgumentException("Number of categorical attributes must be non-negative: " + numCat);
		this.numCategotical = numCat;
	}


	public int getMaxCatSize() {
		return maxCatSize;
	}


	public void setMaxCatSize(int maxCatSize) {
		if (maxCatSize < 2)
			throw new IllegalArgumentException("Number of categories must be at least two: " + maxCatSize);
		this.maxCatSize = maxCatSize;
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
