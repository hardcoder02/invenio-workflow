package qng.core.query.io;

import java.util.ArrayList;
import java.util.List;

public class LogicalOperationBean {
	private List<LogicalExpressionBean> operandList = new ArrayList<LogicalExpressionBean>();
	
	
	public List<LogicalExpressionBean> getOperand() {
		return operandList;
	}
	
	public void setOperand(List<LogicalExpressionBean> operand) {
		this.operandList = operand;
	}

}
