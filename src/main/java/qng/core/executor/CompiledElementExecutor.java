package qng.core.executor;

import java.util.Map;

import qng.client.QueryException;
import qng.core.query.CompiledConstant;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledVariable;
import qng.core.query.Context;
import qng.core.query.DefaultResult;
import qng.core.query.Result;

//TODO: insert ElementResolver?
//TODO: un-setup context
//TODO: return Result
public class CompiledElementExecutor extends
		DefaultExecutor<CompiledElement> {
	
	private final CompiledElementEvaluator evaluator;
	
	public CompiledElementExecutor(CompiledElementEvaluator eval) {
		this.evaluator = eval;
	}
	
	@Override
	protected Object evaluateExternal(CompiledElement element, Context ctx) throws QueryException {
		if (element instanceof CompiledConstant)
			return evaluator.evaluateConstant( (CompiledConstant) element, ctx);
		else if (element instanceof CompiledVariable)
			return evaluator.evaluateVariable( (CompiledVariable) element, ctx);
		else if (element instanceof CompiledOperation) {
			setupContext( (CompiledOperation)element, ctx );
			return evaluator.evaluateOperation( (CompiledOperation) element, new Object[0], ctx);
		}
		else
			throw new QueryException("Unknown query element type: " + element.getClass().getName() );
	}

	@Override
	protected Object evaluateInternal(CompiledElement element, Object[] args, Context ctx) throws QueryException {
		if (element instanceof CompiledConstant)
			return evaluator.evaluateConstant( (CompiledConstant) element, ctx);
		else if (element instanceof CompiledVariable)
			return evaluator.evaluateVariable( (CompiledVariable) element, ctx);
		else if (element instanceof CompiledOperation) {
			setupContext( (CompiledOperation)element, ctx );
			return evaluator.evaluateOperation( (CompiledOperation) element, args, ctx);
		}
		else
			throw new QueryException("Unknown query element type: " + element.getClass().getName() );
	}

	private void setupContext(CompiledOperation element, Context ctx) {
		for ( Map.Entry<String, String> p : element.getContextParams() )
			ctx.putParam(p.getKey(), p.getValue());
		
	}

	@Override
	protected Result packageResult(Object result, Context ctx) throws QueryException {
		return new DefaultResult(result);
	}

}
