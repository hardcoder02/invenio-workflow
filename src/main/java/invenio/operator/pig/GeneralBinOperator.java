package invenio.operator.pig;

import invenio.operator.OperatorExecutionException;
import invenio.operator.data.ArrayFeature;
import invenio.operator.data.ArrayFeatureDescriptor;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.FeatureDescriptorFactory;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeature;
import invenio.operator.data.StringFeatureDescriptor;

public class GeneralBinOperator extends AbstractUnaryOperator implements
		UnaryOperator {
	
	public static final String NAME = "GeneralBin";
	public static final double DEFAULT_PROB_THRESHOLD = 0.5;
	public static final String BIN_HIGH = "true";
	public static final String BIN_LOW = "false";
	
	private final double probThreshold;
	
	public GeneralBinOperator() {
		this( DEFAULT_PROB_THRESHOLD );
	}
	
	public GeneralBinOperator(double probabilityThreshold) {
		super( NAME );
		if (probabilityThreshold < 0 || probabilityThreshold > 1)
			throw new IllegalArgumentException("probability threshold must be between 0 and 1");
		this.probThreshold = probabilityThreshold;
	}
	
	public GeneralBinOperator(DerivedFeatureNameResolver res) {
		this( res, DEFAULT_PROB_THRESHOLD );
	}
	
	public GeneralBinOperator(DerivedFeatureNameResolver res, double probabilityThreshold) {
		super( NAME, res );
		if (probabilityThreshold < 0 || probabilityThreshold > 1)
			throw new IllegalArgumentException("probability threshold must be between 0 and 1");
		this.probThreshold = probabilityThreshold;
	}
	
	protected void processId(String id) throws DataFormatException {
		// do nothing
	}
	
	protected void processContainer(ComparableFeatureContainer c) throws DataFormatException {
		// do nothing
	}
	
	protected void processNumeric(
			NumericFeature numFeat, NumericFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws DataFormatException {
		// do nothing
	}
	
	protected void processString(
			StringFeature strFeat, StringFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws DataFormatException {
		// do nothing
	}
	
	protected void processArray(
			ArrayFeature f, ArrayFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws DataFormatException {
		// do nothing
	}
	
	protected void processCategorical(
			CategoricalFeature catFeat, CategoricalFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		
		if (c.getDerivedSchema() == null)
			c.setDerivedSchema( new Schema());
		
		String newFeatureName = getNameResolver().getDerivedFeatureName( getName(), fd.getName() );
		ArrayFeatureDescriptor newFD = c.addDerivedArrayFeatureDescriptor( newFeatureName );
		newFD.setLabels( fd.getCategories() );
		
		if (catFeat == null)
			return;
		
		ArrayFeature newFeature = (ArrayFeature) c.instantiateDerivedFeature( newFeatureName );
		
		StringFeatureDescriptor strFD = 
			(StringFeatureDescriptor) FeatureDescriptorFactory.instantiateFeature(StringFeatureDescriptor.class, fd.getName());
		newFD.setContainedFeatureDescriptor(strFD);
		
		for (int i = 0; i < fd.getNumCategories(); i++) {
			double prob = catFeat.getProbability(i);
			String bin = (prob >= probThreshold) ? BIN_HIGH : BIN_LOW;
			
			StringFeature sf = (StringFeature) newFeature.instantiateContainedFeature( i );
			sf.setValue( bin );
		}
	}
	
}
