package qng.core.logical;

public class LogicalConstant extends LogicalExpressionImpl implements LogicalExpression {
	private final Object value;
	
	public LogicalConstant(Object value) {
		this(null, value);
	}
	
	public LogicalConstant(String name, Object value) {
		super(name);
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public String toString() {
		return "Logical constant: " + getName() + "=" + getValue(); 
	}
}
