package invenio.operator.pig;

import invenio.operator.OperatorExecutionException;
import invenio.operator.data.ArrayFeature;
import invenio.operator.data.ArrayFeatureDescriptor;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.FeatureDescriptorFactory;
import invenio.operator.data.IncorrectTypeException;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeature;
import invenio.operator.data.StringFeatureDescriptor;

public class ComparativeBinOperator extends AbstractBinaryOperator implements
		BinaryOperator {
	
	public static final String NAME = "ComparativeBin";
	public static final String BIN_HIGH = "high";
	public static final String BIN_OPPOSITE = "opposite";
	public static final String BIN_LOW = "low";
	
	private final String generalBinOpName;
	
	public ComparativeBinOperator() {
		this( GeneralBinOperator.NAME );
	}
	
	public ComparativeBinOperator(String generalBinOperatorName) {
		super( NAME );
		this.generalBinOpName = generalBinOperatorName;
	}
	
	public ComparativeBinOperator(DerivedFeatureNameResolver res) {
		this( res, GeneralBinOperator.NAME );
	}
	
	public ComparativeBinOperator(DerivedFeatureNameResolver res, String generalBinOperatorName) {
		super( NAME, res );
		this.generalBinOpName = generalBinOperatorName;
	}
	
	
	@Override
	protected void processCategorical(
			CategoricalFeature f1, CategoricalFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			CategoricalFeature f2, CategoricalFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		// fd1 should be same as fd2 - perhaps add check?
		String newFeatureName = getNameResolver().getDerivedFeatureName( getName(), fd1.getName() );
		ArrayFeatureDescriptor newFD = c.addDerivedArrayFeatureDescriptor( newFeatureName );
		newFD.setLabels( fd1.getCategories() );
		
		String derivedGeneralBinName = getNameResolver().getDerivedFeatureName(generalBinOpName, fd1.getName());
		ArrayFeature df1 = getDerivedStringArrayFeature(c1, derivedGeneralBinName);
		ArrayFeature df2 = getDerivedStringArrayFeature(c2, derivedGeneralBinName);
		
		if (df1 == null || df2 == null)
			return;
		
		ArrayFeature newFeature = (ArrayFeature) c.instantiateDerivedFeature( newFeatureName );
		StringFeatureDescriptor strFD = 
			(StringFeatureDescriptor) FeatureDescriptorFactory.instantiateFeature(StringFeatureDescriptor.class, fd1.getName());
		newFD.setContainedFeatureDescriptor(strFD);
		
		if ( !df1.getFeatureDescriptor().isEquivalentTo( df2.getFeatureDescriptor() ) )
			throw new DataFormatException("Operand StringArrayFeatures are incompatible");
		
		for (int i = 0; i < df1.getFeatureDescriptor().getSize(); i++) {
			String val1 = ((StringFeature)df1.getContainedValue(i)).getValue();
			String val2 = ((StringFeature)df2.getContainedValue(i)).getValue();
			
			String result = null;
			if ( isHigh(val1) ) {
				if (isHigh(val2))
					result = BIN_HIGH;
				else if (isLow(val2))
					result = BIN_OPPOSITE;
			}
			else if (isLow(val1)) {
				if (isHigh(val2))
					result = BIN_OPPOSITE;
				else if (isLow(val2))
					result = BIN_LOW;
			}
			
			if (result != null)
				((StringFeature)newFeature.instantiateContainedFeature(i)).setValue(result);
			else
				throw new DataFormatException("Unrecognized operand bin values");
		}
	}
	
	
	protected ArrayFeature getDerivedStringArrayFeature(ComparableFeatureContainer cont, String name) throws DataFormatException {
		if ( !cont.hasFeature(name))
			return null;
		
		Feature f = cont.getDerivedFeature(name);
		if ( cont.getDerivedSchema().canGetAsArray( name ) ) {
			ArrayFeature af = (ArrayFeature) f;
			if ( !(af.getFeatureDescriptor().getContainedFeatureDescriptor() instanceof StringFeatureDescriptor) )
				throw new IncorrectTypeException("The derived ArrayFeature [" + name + "] must contain StringFeatures");
			return af;
		}
		else
			throw new IncorrectTypeException("The derived feature [" + name + "] must be an ArrayFeature");
	}
	
	protected boolean isHigh(String val) {
		if (val == null)
			return false;
		return GeneralBinOperator.BIN_HIGH.equals(val);
	}
	
	protected boolean isLow(String val) {
		if (val == null)
			return false;
		return GeneralBinOperator.BIN_LOW.equals(val);
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

		if (c1.getDerivedSchema() == null)
			c1.setDerivedSchema(new Schema());
		
		if (c2.getDerivedSchema() == null)
			c2.setDerivedSchema(new Schema());
		
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
