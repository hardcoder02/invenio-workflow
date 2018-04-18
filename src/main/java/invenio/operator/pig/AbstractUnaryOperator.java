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

public abstract class AbstractUnaryOperator extends AbstractOperator implements
		UnaryOperator {
	
	public AbstractUnaryOperator(String name) {
		super( name );
	}
	
	public AbstractUnaryOperator(String name, DerivedFeatureNameResolver res) {
		super( name, res );
	}
	
	public void execute(String id, ComparableFeatureContainer c)
			throws OperatorExecutionException, DataFormatException {

		performExecute(id, c);
	}
	
	protected void performExecute(String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException {
		
		processId(id);
		processContainer(c);
		
		Schema s = c.getSchema();
		for ( int i = 0; i < s.size(); i++) {
			FeatureDescriptor fd = s.getFeatureDescriptor( i );
			boolean hasFeature = c.hasFeature( fd.getName() );

			if (s.canGetAsNumeric(i)) processNumeric( 
					hasFeature ? (NumericFeature) c.getFeature( i ) : null, (NumericFeatureDescriptor) fd, id, c );
			else if (s.canGetAsString(i)) processString( 
					hasFeature ? (StringFeature) c.getFeature( i ) : null, (StringFeatureDescriptor) fd, id, c );
			else if (s.canGetAsCategorical(i) ) processCategorical(
					hasFeature ? (CategoricalFeature) c.getFeature(i) : null, (CategoricalFeatureDescriptor) fd, id, c );
			else if (s.canGetAsArray(i) ) processArray(
					hasFeature ? (ArrayFeature) c.getFeature(i) : null, (ArrayFeatureDescriptor) fd, id, c );
			else
				throw new UnsupportedFormatException(
						"Unrecognized feature description: " + s.getFeatureDescriptor(i).getName());
		}
	}
	
	protected abstract void processId(String id) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processContainer(ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processNumeric(
			NumericFeature numFeat, NumericFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processString(
			StringFeature strFeat, StringFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processCategorical(
			CategoricalFeature catFeat, CategoricalFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
	
	protected abstract void processArray(
			ArrayFeature arrFeat, ArrayFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
}
