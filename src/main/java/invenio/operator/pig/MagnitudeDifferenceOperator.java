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

public class MagnitudeDifferenceOperator extends AbstractBinaryOperator implements
		BinaryOperator {
	
	public static final String NAME = "MagnitudeDifference";
	
	public MagnitudeDifferenceOperator() {
		super( NAME );
	}
	
	public MagnitudeDifferenceOperator(DerivedFeatureNameResolver res) {
		super( NAME, res );
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
		NumericFeatureDescriptor numFD = 
			(NumericFeatureDescriptor) FeatureDescriptorFactory.instantiateFeature(NumericFeatureDescriptor.class, fd1.getName());
		newFD.setContainedFeatureDescriptor(numFD);
		
		for (int i = 0; i < f1.getFeatureDescriptor().getNumCategories(); i++) {
			double prob1 = f1.getProbability(i);
			double prob2 = f2.getProbability(i);
			
			double result = prob1 - prob2;
			
			((NumericFeature)newFeature.instantiateContainedFeature(i)).setValue(result);
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
			NumericFeature f2, NumericFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		// do nothing
	}
	
	
	@Override
	protected void processString(
			StringFeature f1, StringFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			StringFeature f2, StringFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		// do nothing
	}
	
	@Override
	protected void processArray(
			ArrayFeature f1, ArrayFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			ArrayFeature f2, ArrayFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		// do nothing
	}

}
