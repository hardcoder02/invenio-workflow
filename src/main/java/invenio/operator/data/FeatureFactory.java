package invenio.operator.data;

public class FeatureFactory {
	public static Feature instantiateFeature(FeatureDescriptor fd) throws UnsupportedFormatException {

		if ( fd instanceof NumericFeatureDescriptor )
			return new NumericFeatureImpl( (NumericFeatureDescriptor) fd );
		else if ( fd instanceof StringFeatureDescriptor )
			return new StringFeatureImpl( (StringFeatureDescriptor) fd);
		else if ( fd instanceof CategoricalFeatureDescriptor )
			return new CategoricalFeatureImpl( (CategoricalFeatureDescriptor) fd);
		else if ( fd instanceof ArrayFeatureDescriptor )
			return new ArrayFeatureImpl( (ArrayFeatureDescriptor) fd);
		else
			throw new UnsupportedFormatException("Unsupported feature descriptor: " + fd.getName() );
	}
}
