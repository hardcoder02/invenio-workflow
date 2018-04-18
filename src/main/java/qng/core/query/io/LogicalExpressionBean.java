package qng.core.query.io;

public class LogicalExpressionBean {
	private String name;
	private LogicalOperationBean operation;
	private LogicalConstantBean constant;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LogicalOperationBean getOperation() {
		return operation;
	}

	public void setOperation(LogicalOperationBean operation) {
		this.operation = operation;
	}

	public LogicalConstantBean getConstant() {
		return constant;
	}

	public void setConstant(LogicalConstantBean constant) {
		this.constant = constant;
	}
}
