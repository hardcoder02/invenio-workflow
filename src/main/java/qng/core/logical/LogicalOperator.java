package qng.core.logical;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LogicalOperator extends LogicalExpression {
		
		void addOperand(LogicalExpression operand);
		LogicalExpression setOperand(int idx, LogicalExpression operand) throws IndexOutOfBoundsException;
		
		int getNumberOfOperands();
		
		boolean hasOperands();
		
		LogicalExpression getOperand(int idx) throws IndexOutOfBoundsException;
		
		List<LogicalExpression> getOperands();
		
		String setContextParam(String name, String value);
		
		int getNumberOfContextParams();
		
		boolean hasContextParams();
		boolean hasContextParam(String name);
		
		String getContextParam(String name);
		
		Set<Map.Entry<String, String>> getContextParams();
}
