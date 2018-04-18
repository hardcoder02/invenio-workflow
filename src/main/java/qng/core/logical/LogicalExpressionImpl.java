package qng.core.logical;

public abstract class LogicalExpressionImpl implements LogicalExpression {
	protected final String name;
	
	public LogicalExpressionImpl(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
