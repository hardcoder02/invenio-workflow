package qng.core.compiler;

import java.util.Map;

import qng.client.QueryException;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalVariable;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;
import qng.core.query.DefaultConstant;
import qng.core.query.DefaultVariable;

// TODO: call sub-compiler as service
public class DefaultCompiler extends LogicalExpressionCompiler<CompiledElement>
		implements QueryCompiler<LogicalExpression, CompiledElement> {
	
	private final OperationRegistry opRegistry;
	
	public DefaultCompiler(OperationRegistry opRegistry) {
		this.opRegistry = opRegistry;
	}
	
	@Override
	protected CompiledElement compileConstant(LogicalConstant l) throws QueryException {
		Object value = l.getValue();
		if (value instanceof LogicalExpression) {
			LogicalExpression exp = (LogicalExpression) value;
			return new DefaultConstant( null, l.getName(), compile(exp) );
		}
		else
			return new DefaultConstant(null, l.getName(), value);
	}

	@Override
	protected CompiledElement compileOperator(LogicalOperator l)
			throws QueryException {
		
		CompiledOperation op = opRegistry.createCompiledOperation(l.getName());
		
		// setup context params
		for (Map.Entry<String, String> p : l.getContextParams() )
			op.setContextParam(p.getKey(), p.getValue());
		
		return op;
	}

	@Override
	protected CompiledElement compileVariable(LogicalVariable l)
			throws QueryException {

		return new DefaultVariable( null, l.getName(), l.getType() );
	}
	
	

}
