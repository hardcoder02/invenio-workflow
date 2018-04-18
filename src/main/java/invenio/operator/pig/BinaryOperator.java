package invenio.operator.pig;

import invenio.operator.OperatorExecutionException;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;

public interface BinaryOperator extends Operator {
	void execute(String id1, ComparableFeatureContainer c1,
			String id2, ComparableFeatureContainer c2, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
}
