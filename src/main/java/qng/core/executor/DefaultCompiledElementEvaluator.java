package qng.core.executor;

import qng.client.QueryException;
import qng.core.query.CompiledConstant;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledVariable;
import qng.core.query.Context;

public class DefaultCompiledElementEvaluator implements
		CompiledElementEvaluator {

	private final OperationManager<CompiledOperation> opManager;
	
	public DefaultCompiledElementEvaluator(OperationManager<CompiledOperation> opManager) {
		this.opManager = opManager;
	}
	
	@Override
	public Object evaluateConstant(CompiledConstant c, Context ctx)	throws QueryException {
		return c.getValue();
	}

	@Override
	public Object evaluateOperation(CompiledOperation o, Object[] args, Context ctx) throws QueryException {
		return opManager.invokeOperation(o, args, ctx);
	}

	@Override
	public Object evaluateVariable(CompiledVariable v, Context ctx) throws QueryException {
		return ctx.getVariable( v.getName() );
	}

}
