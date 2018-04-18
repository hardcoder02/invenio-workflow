package invenio.op.expression.eval;

import qng.core.logical.LogicalExpression;

public class ExpressionEvaluator {
	private ExpressionEvaluator() {
		
	}
	
	public Object evaluateExpression(LogicalExpression expr) {
		return null;
	}
	
	public static ExpressionEvaluator getInstance() {
		return new ExpressionEvaluator();
	}
}
