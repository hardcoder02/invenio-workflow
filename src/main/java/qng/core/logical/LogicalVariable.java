package qng.core.logical;

public class LogicalVariable extends LogicalExpressionImpl implements LogicalExpression {
	private final Class type;
	
	public LogicalVariable(Class type) {
		this(null, type);
	}
	
	public LogicalVariable(String name, Class type) {
		super(name);
		this.type = type;
	}
	
	public Class getType() {
		return type;
	}
	
	public String toString() {
		return "Logical variable: " + getName() + "=" + type.getName(); 
	}
}
