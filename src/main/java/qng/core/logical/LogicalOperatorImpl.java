package qng.core.logical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LogicalOperatorImpl extends LogicalExpressionImpl implements LogicalOperator {
	private List<LogicalExpression> operands = new ArrayList<LogicalExpression>();
	private Map<String, String> contextParams = new HashMap<String, String>();
	
	public LogicalOperatorImpl(String name) {
		super(name);
	}
	
	
	public void addOperand(LogicalExpression operand) {
		operands.add(operand);
	}
	
	public LogicalExpression setOperand(int idx, LogicalExpression operand) {
		return operands.set(idx, operand);
	}
	
	public int getNumberOfOperands() {
		return operands.size();
	}
	
	public boolean hasOperands() {
		return operands.isEmpty();
	}
	
	public LogicalExpression getOperand(int idx) {
		return operands.get(idx);
	}
	
	public List<LogicalExpression> getOperands() {
		return Collections.unmodifiableList(operands);
	}
	
	@Override
	public String getContextParam(String name) {
		return contextParams.get(name);
	}

	@Override
	public Set<Entry<String, String>> getContextParams() {
		return Collections.unmodifiableSet( contextParams.entrySet() );
	}


	@Override
	public int getNumberOfContextParams() {
		return contextParams.size();
	}


	@Override
	public boolean hasContextParam(String name) {
		return contextParams.containsKey(name);
	}


	@Override
	public boolean hasContextParams() {
		return !contextParams.isEmpty();
	}


	@Override
	public String setContextParam(String name, String value) {
		return contextParams.put(name, value);
	}


	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Operator ").append(getName()).append("={\n");
		for (LogicalExpression exp : getOperands()) {
			sb.append("\t").append(exp).append("\n");
		}
		for (Map.Entry<String, String> cp : getContextParams()) {
			sb.append("\t").append(cp).append("\n");
		}
		sb.append("}");
		return sb.toString();
	}
}
