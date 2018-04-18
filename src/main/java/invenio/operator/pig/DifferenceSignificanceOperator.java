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

public class DifferenceSignificanceOperator extends AbstractBinaryOperator implements
		BinaryOperator {
	
	public static final String NAME = "DifferenceSignificance";
	
	public static final double DEFAULT_TAU = 0.3;
	
	private final double tau;
	
	public DifferenceSignificanceOperator() {
		this( DEFAULT_TAU );
	}
	
	public DifferenceSignificanceOperator(double tau) {
		super( NAME );
		if ( tau < 0 || tau > 1)
			throw new IllegalArgumentException("Significant difference threshold must be between 0 and 1");
		this.tau = tau;
	}
	
	public DifferenceSignificanceOperator(DerivedFeatureNameResolver res) {
		this( res, DEFAULT_TAU );
	}
	
	public DifferenceSignificanceOperator(DerivedFeatureNameResolver res, double tau) {
		super( NAME, res );
		if ( tau < 0 || tau > 1)
			throw new IllegalArgumentException("Significant difference threshold must be between 0 and 1");
		this.tau = tau;
	}
	
	
	@Override
	protected void processCategorical(
			CategoricalFeature f1, CategoricalFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			CategoricalFeature f2, CategoricalFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		
		if ( !fd1.isEquivalentTo( fd2 ) )
			throw new DataFormatException("Operand CategoricalFeatures are incompatible");
		
		// fd1 same as fd2
		String newFeatureName = getNameResolver().getDerivedFeatureName( getName(), fd1.getName() );
		ArrayFeatureDescriptor newFD = c.addDerivedArrayFeatureDescriptor( newFeatureName );
		newFD.setLabels( fd1.getCategories() );
		
		if (f1 == null || f2 == null)
			return;
		
		ArrayFeature newFeature = (ArrayFeature) c.instantiateDerivedFeature( newFeatureName );
		StringFeatureDescriptor strFD = 
			(StringFeatureDescriptor) FeatureDescriptorFactory.instantiateFeature(StringFeatureDescriptor.class, fd1.getName());
		newFD.setContainedFeatureDescriptor(strFD);
		
		for (int i = 0; i < f1.getFeatureDescriptor().getNumCategories(); i++) {
			double prob1 = f1.getProbability(i);
			double prob2 = f2.getProbability(i);
			
			String result = "false";
			if( Math.abs(prob1 - prob2) >= tau )
				result = "true";
			
			((StringFeature)newFeature.instantiateContainedFeature(i)).setValue(result);
		}
	}
	
	
	
	@Override
	protected void processId(String id1, String id2)
			throws OperatorExecutionException, DataFormatException {
		// do nothing
	}
	
	@Override
	protected void processContainer(ComparableFeatureContainer c1,
			ComparableFeatureContainer c2, ComparableFeatureContainer c)
			throws OperatorExecutionException, DataFormatException {

		if (c.getDerivedSchema() == null)
			c.setDerivedSchema( new Schema());
	}

	@Override
	protected void processNumeric(
			NumericFeature f1, NumericFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			NumericFeature f2, NumericFeatureDescriptor fd2, String id2,
			ComparableFeatureContainer c2, ComparableFeatureContainer c)
			throws OperatorExecutionException, DataFormatException {
		// do nothing
		
	}
	
	@Override
	protected void processString(
			StringFeature f1, StringFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			StringFeature f2, StringFeatureDescriptor fd2, String id2,
			ComparableFeatureContainer c2, ComparableFeatureContainer c)
			throws OperatorExecutionException, DataFormatException {
		// do nothing
		
	}

	@Override
	protected void processArray(
			ArrayFeature f1, ArrayFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			ArrayFeature f2, ArrayFeatureDescriptor fdd, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		// do nothing
		
	}


	
}
