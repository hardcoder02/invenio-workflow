package invenio.operator.data;

public class FeatureDescriptorFactory {
	public static FeatureDescriptor instantiateFeature(
			Class<? extends FeatureDescriptor> fdClass, String fdName) throws UnsupportedFormatException {

		if (fdClass.equals(NumericFeatureDescriptor.class))
			return new NumericFeatureDescriptorImpl(fdName);
		else if (fdClass.equals(StringFeatureDescriptor.class))
			return new StringFeatureDescriptorImpl(fdName);
		else if (fdClass.equals(CategoricalFeatureDescriptor.class))
			return new CategoricalFeatureDescriptorImpl(fdName);
		else if (fdClass.equals(ArrayFeatureDescriptor.class))
			return new ArrayFeatureDescriptorImpl(fdName);
		else
			throw new UnsupportedFormatException("Unsupported feature descriptor with class: " + fdClass.getName());
	}
}
