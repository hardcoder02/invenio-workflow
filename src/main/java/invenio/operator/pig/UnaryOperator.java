package invenio.operator.pig;

import invenio.operator.OperatorExecutionException;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.DataFormatException;

public interface UnaryOperator extends Operator {
	void execute(String id, ComparableFeatureContainer c) throws OperatorExecutionException, DataFormatException;
}
