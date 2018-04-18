package invenio.operator.data;

import java.io.Serializable;

public class ComparableFeatureContainer implements Serializable {
	private final Schema schema;
	private Schema derivedSchema;
	private final DataMapper mapper;
	
	
	public ComparableFeatureContainer() {
		this ( new Schema(), new SimpleDataMapper() );
	}
	
	public ComparableFeatureContainer(Schema schema) throws NullPointerException {
		this ( schema, new SimpleDataMapper() );
	}
	
	public ComparableFeatureContainer(DataMapper mapper) throws NullPointerException {
		this( new Schema(), mapper );
	}
	
	public ComparableFeatureContainer(Schema schema, DataMapper mapper) throws NullPointerException {
		if (schema == null || mapper == null)
			throw new NullPointerException("Schema and data mapper cannot be null");
		this.schema = schema;
		this.mapper = mapper;
	}
	
	public Schema getSchema() {
		return schema;
	}
	
	public Schema getDerivedSchema() {
		return derivedSchema;
	}
	
	public void lockSchema() {
		schema.lock();
	}
	
	public void lockDerivedSchema() {
		derivedSchema.lock();
	}
	
	
	
	protected FeatureDescriptor<? extends Feature> addFeatureDescriptor(
			String name, Class<? extends FeatureDescriptor> fdClass,
			Schema s) throws DuplicateElementException, UnsupportedFormatException {
		checkLocked(s);
		checkDuplicateFD(name);
		
		FeatureDescriptor<? extends Feature> fd = FeatureDescriptorFactory.instantiateFeature(fdClass, name);
		s.addFeatureDescriptor(fd);
		return fd;
	}
	
	public FeatureDescriptor<? extends Feature> addFeatureDescriptor(
			String name, Class<? extends FeatureDescriptor> fdClass) throws DuplicateElementException, UnsupportedFormatException {
		
		return addFeatureDescriptor(name, fdClass, schema);
	}
	
	public FeatureDescriptor<? extends Feature> addDerivedFeatureDescriptor(
			String name, Class<? extends FeatureDescriptor> fdClass)
			throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		return addFeatureDescriptor(name, fdClass, derivedSchema);
	}
	
	
	
	protected NumericFeatureDescriptor addNumericFeatureDescriptor(String name, Schema s)
				throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		NumericFeatureDescriptor fd =
			(NumericFeatureDescriptor) addFeatureDescriptor(name, NumericFeatureDescriptor.class, s);
		return fd;
	}
	
	public NumericFeatureDescriptor addNumericFeatureDescriptor(String name)
				throws DuplicateElementException, UnsupportedFormatException {

		return addNumericFeatureDescriptor(name, schema);
	}
	
	public NumericFeatureDescriptor addDerivedNumericFeatureDescriptor(String name)
				throws DuplicateElementException, NullPointerException, UnsupportedFormatException {

		return addNumericFeatureDescriptor(name, derivedSchema);
	}
	
	
	
	protected CategoricalFeatureDescriptor addCategoricalFeatureDescriptor(String name, Schema s)
			throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		CategoricalFeatureDescriptor fd =
			(CategoricalFeatureDescriptor) addFeatureDescriptor(name, CategoricalFeatureDescriptor.class, s);
		return fd;
	}
		
	public CategoricalFeatureDescriptor addCategoricalFeatureDescriptor(String name)
			throws DuplicateElementException, UnsupportedFormatException {
		
		return addCategoricalFeatureDescriptor(name, schema);
	}
		
	public CategoricalFeatureDescriptor addDerivedCategoricalFeatureDescriptor(String name)
			throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		return addCategoricalFeatureDescriptor(name, derivedSchema);
	}
	
	
	
	protected StringFeatureDescriptor addStringFeatureDescriptor(String name, Schema s)
			throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
	
		StringFeatureDescriptor fd =
			(StringFeatureDescriptor) addFeatureDescriptor(name, StringFeatureDescriptor.class, s);
		return fd;
	}
	
	public StringFeatureDescriptor addStringFeatureDescriptor(String name)
			throws DuplicateElementException, UnsupportedFormatException {
	
		return addStringFeatureDescriptor(name, schema);
	}
	
	public StringFeatureDescriptor addDerivedStringFeatureDescriptor(String name)
			throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
	
		return addStringFeatureDescriptor(name, derivedSchema);
	}
	
	
	
	protected ArrayFeatureDescriptor addArrayFeatureDescriptor(String name, Schema s)
				throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		ArrayFeatureDescriptor fd =
				(ArrayFeatureDescriptor) addFeatureDescriptor(name, ArrayFeatureDescriptor.class, s);
		return fd;
	}

	public ArrayFeatureDescriptor addArrayFeatureDescriptor(String name)
				throws DuplicateElementException, UnsupportedFormatException {

		return addArrayFeatureDescriptor(name, schema);
	}

	public ArrayFeatureDescriptor addDerivedArrayFeatureDescriptor(String name)
				throws DuplicateElementException, NullPointerException, UnsupportedFormatException {
		
		return addArrayFeatureDescriptor(name, derivedSchema);
	}
	
	
	
	protected Feature<? extends FeatureDescriptor> instantiateFeature(int index, Schema s) throws DuplicateElementException, UnsupportedFormatException {
		FeatureDescriptor<? extends Feature> fd = s.getFeatureDescriptor(index);
		
		if ( hasFeature( fd.getName() ))
			throw new DuplicateElementException("Feature with index [" + index + "already exists");
		
		Feature<? extends FeatureDescriptor> feature = fd.instantiateFeature();
		
		mapper.addFeature(feature);
		
		return feature;
	}
	
	public Feature<? extends FeatureDescriptor> instantiateFeature(int index) throws DuplicateElementException, UnsupportedFormatException {
				
		return instantiateFeature(index, schema);
	}
	
	public Feature<? extends FeatureDescriptor> instantiateDerivedFeature(int index) throws DuplicateElementException, UnsupportedFormatException {
		
		return instantiateFeature(index, derivedSchema);
	}
	
	
	
	protected Feature<? extends FeatureDescriptor> instantiateFeature(
			String name, Schema s) throws DuplicateElementException, MissingElementException, UnsupportedFormatException {
		if ( hasFeature(name) )
			throw new DuplicateElementException("Feature with name [" + name + "already exists");
		
		if ( !s.hasFeatureDescriptor(name))
			throw new MissingElementException("Feature Descriptor with name [" + name + "] not found");
		
		FeatureDescriptor<? extends Feature> fd = s.getFeatureDescriptor(name);
		Feature<? extends FeatureDescriptor> feature = fd.instantiateFeature();
		
		mapper.addFeature(feature);
		
		return feature;
	}
	
	public Feature<? extends FeatureDescriptor> instantiateFeature(
			String name) throws DuplicateElementException, MissingElementException, UnsupportedFormatException {
				
		return instantiateFeature(name, schema);
	}
	
	public Feature<? extends FeatureDescriptor> instantiateDerivedFeature(
			String name) throws DuplicateElementException, MissingElementException, UnsupportedFormatException, NullPointerException {
				
		return instantiateFeature(name, derivedSchema);
	}
	
	
	
	public void setDerivedSchema(Schema schema) throws IllegalStateException {
		if (this.derivedSchema != null)
			throw new IllegalStateException("Re-setting derived schema is not allowed");
		
		this.derivedSchema = schema;
	}
	
	
	
	protected Feature<? extends FeatureDescriptor> getFeature(int index, Schema s) throws MissingElementException {
		FeatureDescriptor<? extends Feature> fd = s.getFeatureDescriptor(index);
		
		if ( !mapper.hasFeature( fd.getName() ))
			throw new MissingElementException("Feature with index [" + index + "] does not exist");
		
		return mapper.getFeature( fd.getName() );
	}
	
	public Feature<? extends FeatureDescriptor> getFeature(int index) throws MissingElementException {
				
		return getFeature(index, schema);
	}
	
	public Feature<? extends FeatureDescriptor> getDerivedFeature(int index) throws MissingElementException {
		
		return getFeature(index, derivedSchema);
	}
	
	protected Feature<? extends FeatureDescriptor> getFeature(String name, Schema s) throws MissingElementException {
		if ( !mapper.hasFeature( name ))
			throw new MissingElementException("Feature with name [" + name + "does not exist");
		
		if ( !s.hasFeatureDescriptor(name))
			throw new MissingElementException("Feature Descriptor with name [" + name + "] not found");
		
		return mapper.getFeature( name );
	}
	
	public Feature<? extends FeatureDescriptor> getFeature(String name) throws MissingElementException {
				
		return getFeature(name, schema);
	}
	
	public Feature<? extends FeatureDescriptor> getDerivedFeature(String name) throws MissingElementException {
		
		return getFeature(name, derivedSchema);
	}
	
	
	
	public boolean hasFeatureDescriptor(String featureName) {
		if (schema.hasFeatureDescriptor(featureName) ||
				(derivedSchema != null && derivedSchema.hasFeatureDescriptor(featureName)) )
			return true;
		else
			return false;
	}
	
	protected boolean hasFeature(int index, Schema s) {
		FeatureDescriptor<? extends Feature> fd = s.getFeatureDescriptor(index);
		
		return hasFeature( fd.getName() );
	}
	
	public boolean hasFeature(int index) {
		return hasFeature( index, schema);
	}
	
	public boolean hasFeature(String featureName) {
		return (mapper.hasFeature( featureName ) && hasFeatureDescriptor(featureName));
	}
	
	protected void checkLocked(Schema schema) throws IllegalStateException {
		if (schema.isLocked())
			throw new IllegalStateException("Attempt to modify schema that is locked");
	}
	
	protected void checkDuplicateFD(String featureName) throws DuplicateElementException {
		if (hasFeatureDescriptor(featureName))
			throw new DuplicateElementException("Duplicate feature: " + featureName);
	}
	
	// TODO: move copying feature values to each Feature implementation
	public void loadValues(ComparableFeatureContainer source) throws DataFormatException {
		Schema sourceSchema = source.getSchema();

		
		for (int i = 0; i < sourceSchema.size(); i++) {
			FeatureDescriptor sourceFD = sourceSchema.getFeatureDescriptor(i);
			
			if ( !schema.hasFeatureDescriptor( sourceFD.getName() ))
				throw new DataFormatException("Cannot copy value for feature: " + sourceFD + ", feature does not exist in target schema");
			FeatureDescriptor targetFD = schema.getFeatureDescriptor( sourceFD.getName() );
			if ( !sourceFD.isEquivalentTo(targetFD) )
				throw new DataFormatException("Cannot copy value for feature:" + sourceFD + ", feature types are not equivalent");
			
			if ( source.hasFeature(i) ) {
				Feature sourceF = source.getFeature( i );
				Feature targetF = null;
				
				if ( hasFeature(sourceFD.getName()) )
					targetF = getFeature( sourceFD.getName() );
				else
					targetF = instantiateFeature( sourceFD.getName() );
				
				if (sourceF instanceof NumericFeature && targetF instanceof NumericFeature)
					((NumericFeature) targetF).setValue( ((NumericFeature)sourceF).getValue() );
				else if (sourceF instanceof StringFeature && targetF instanceof StringFeature)
					((StringFeature) targetF).setValue( ((StringFeature)sourceF).getValue() );
				else if (sourceF instanceof CategoricalFeature && targetF instanceof CategoricalFeature) {
					int selIndex = ((CategoricalFeature) sourceF).getSelectedIndex();
					double[] probs = ((CategoricalFeature) sourceF).getProbabilities();
					
					((CategoricalFeature) targetF).setSelectedIndex(selIndex);
					((CategoricalFeature) targetF).setProbabilities(probs);
				}
				else
					throw new UnsupportedFormatException(
							"Unsupported feature type for feature: " + sourceFD.getName());
			}
		}
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append( "ComparableFeatureContainer={");
		for ( int i = 0; i < schema.size(); i++ ) {
			if ( i > 0)
				sb.append( ", ");
			sb.append( "[");
			sb.append( schema.getFeatureDescriptor(i));
			sb.append("=");
			if ( hasFeature( i ) ) {
				try {
					sb.append( "" + getFeature( i ) );
				}
				catch (MissingElementException ex) {
					ex.printStackTrace();	//should not happen
				}
			}
			else
				sb.append("null");
			sb.append("]");
		}
		
		if ( derivedSchema != null )
			for ( int i = 0; i < derivedSchema.size(); i++ ) {
				sb.append( ", [");
				sb.append( derivedSchema.getFeatureDescriptor(i));
				if ( hasFeature( i, derivedSchema ) ) {
					try {
						sb.append( "" + getFeature( i, derivedSchema ) );
					}
					catch (MissingElementException ex) {
						ex.printStackTrace();	//should not happen
					}
				}
				else
					sb.append("null");
				sb.append("]");
			}
		sb.append("}");
		return sb.toString();
	}
}
