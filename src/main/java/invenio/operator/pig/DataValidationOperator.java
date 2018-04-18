package invenio.operator.pig;

import invenio.operator.data.ArrayFeature;
import invenio.operator.data.ArrayFeatureDescriptor;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.Schema;
import invenio.operator.data.StringFeature;
import invenio.operator.data.StringFeatureDescriptor;

public class DataValidationOperator extends AbstractUnaryOperator implements
		UnaryOperator {
	
	public static final String NAME = "DataValidation";
	
	public DataValidationOperator() {
		super( NAME );
	}
	
	protected void processId(String id) throws DataFormatException {
		if (id == null || id.trim().length() <= 0)
			throw new DataFormatException("Id cannot be null or empty");
	}
	
	protected void processContainer(ComparableFeatureContainer c) throws DataFormatException {
		if (c == null)
			throw new DataFormatException("Data container cannot be null");
		processSchema (c.getSchema());
	}
	
	protected void processSchema(Schema s) throws DataFormatException {
		if (s == null || !s.isLocked())
			throw new DataFormatException("Schema must be non-null and locked");
	}
	
	protected void processCategorical(
			CategoricalFeature catFeat, CategoricalFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws DataFormatException {
		
		if (catFeat == null)
			return;
		
		double sumProb = 0;
		double maxProb = -1;
		int maxProbIndex = -1;
		for (int i = 0; i < fd.getNumCategories(); i++) {
			double prob = catFeat.getProbability(i);
			if (prob < 0 || prob > 1)
				throw new DataFormatException("Probability must be between 0 and 1 for feature with name: " + fd.getName() );
			sumProb += prob;
			if (prob > maxProb) {
				maxProb = prob;
				maxProbIndex = i;
			}
		}
		
		if ( Math.abs(sumProb - 1) > 0.000001)
			throw new DataFormatException("Probabilities do not add up to 1 for feature with name: " + fd.getName());
		
		if ( catFeat.getSelectedIndex() != maxProbIndex && fd.getDefaultCategoryIndex() != maxProbIndex)
			throw new DataFormatException("Category with highest probability must be selected for feature with name: " 
					+ fd.getName());
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
	
	/**
	 * Currently does nothing
	 */
	protected void processArray(
			ArrayFeature f, ArrayFeatureDescriptor fd,
			String id, ComparableFeatureContainer c) throws DataFormatException {
		
		if (f == null)
			return;
		
		// do nothing
	}
}
