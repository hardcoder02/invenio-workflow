package invenio.operator.pig;

import invenio.operator.OperatorExecutionException;
import invenio.operator.data.ArrayFeature;
import invenio.operator.data.ArrayFeatureDescriptor;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.FeatureDescriptor;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeature;
import invenio.operator.data.StringFeatureDescriptor;
import invenio.operator.data.UnsupportedFormatException;

public abstract class AbstractBinaryOperator extends AbstractOperator implements
		BinaryOperator {
	
	public AbstractBinaryOperator(String name) {
		super( name );
	}
	
	public AbstractBinaryOperator(String name, DerivedFeatureNameResolver res) {
		super( name, res );
	}
	
	public void execute(String id1, ComparableFeatureContainer c1,
			String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {

		performExecute(id1, c1, id2, c2, c);
	}
	
	protected void performExecute(String id1, ComparableFeatureContainer c1,
			String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		processIds(id1, id2);
		processContainers(c1, c2, c);
		
		Schema s1 = c1.getSchema();
		Schema s2 = c2.getSchema();
		for ( int i = 0; i < s1.size(); i++) {
			FeatureDescriptor fd1 = s1.getFeatureDescriptor( i );
			FeatureDescriptor fd2 = s2.getFeatureDescriptor( i );
			boolean has1 = c1.hasFeature( fd1.getName() );
			boolean has2 = c2.hasFeature( fd2.getName() );
			
			if (s1.canGetAsNumeric(i))
				processNumeric(
						has1 ? (NumericFeature) c1.getFeature( i ) : null,  (NumericFeatureDescriptor) fd1, id1, c1,
						has2 ? (NumericFeature) c2.getFeature( i ) : null, (NumericFeatureDescriptor) fd2, id2, c2, c );
			else if (s1.canGetAsString(i) )
				processString(
						has1 ? (StringFeature) c1.getFeature( i ) : null,  (StringFeatureDescriptor) fd1, id1, c1,
						has2 ? (StringFeature) c2.getFeature( i ) : null, (StringFeatureDescriptor) fd2, id2, c2, c );
			else if (s1.canGetAsCategorical(i) )
				processCategorical(
						has1 ? (CategoricalFeature) c1.getFeature( i ) : null,  (CategoricalFeatureDescriptor) fd1, id1, c1,
						has2 ? (CategoricalFeature) c2.getFeature( i ) : null, (CategoricalFeatureDescriptor) fd2, id2, c2, c );
			else if (s1.canGetAsArray(i) )
				processArray(
						has1 ? (ArrayFeature) c1.getFeature( i ) : null,  (ArrayFeatureDescriptor) fd1, id1, c1,
						has2 ? (ArrayFeature) c2.getFeature( i ) : null, (ArrayFeatureDescriptor) fd2, id2, c2, c );
			else
				throw new UnsupportedFormatException(
						"Unrecognized feature description: " + s1.getFeatureDescriptor(i).getName());
		}
	}
	
	protected void processIds(String id1, String id2) throws OperatorExecutionException, DataFormatException {
		if (id1 == null || id1.trim().length() <= 0)
			throw new DataFormatException("Id cannot be null or empty");
		
		if (id2 == null || id2.trim().length() <= 0)
			throw new DataFormatException("Id cannot be null or empty");
		
		if ( !id1.equals(id2) )
			throw new DataFormatException("Mismatched IDs");
		
		processId(id1, id2);
	}
	
	protected abstract void processId(String id1, String id2) throws OperatorExecutionException, DataFormatException;
	
	protected void processContainers(
			ComparableFeatureContainer c1, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		if (c1 == null || c2 == null || c == null)
			throw new DataFormatException("Data container cannot be null");
		
		Schema s1 = c1.getSchema();
		if (s1 == null || !s1.isLocked())
			throw new DataFormatException("Schema must be non-null and locked");
		
		Schema s2 = c2.getSchema();
		if (s2 == null || !s2.isLocked())
			throw new DataFormatException("Schema must be non-null and locked");
		if ( !s1.isEquivalentTo(s2) )
			throw new DataFormatException("Mismatched schemas");
		
		processContainer(c1, c2, c);
	}
	
	protected abstract void processContainer(
			ComparableFeatureContainer c1, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processNumeric(
			NumericFeature f1, NumericFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			NumericFeature f2, NumericFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processString(
			StringFeature f1, StringFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			StringFeature f2, StringFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processCategorical(
			CategoricalFeature f1, CategoricalFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			CategoricalFeature f2, CategoricalFeatureDescriptor fd2, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processArray(
			ArrayFeature f1, ArrayFeatureDescriptor fd1, String id1, ComparableFeatureContainer c1,
			ArrayFeature f2, ArrayFeatureDescriptor fdd, String id2, ComparableFeatureContainer c2,
			ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
}
