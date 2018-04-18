package qng.core.executor;

import qng.client.QueryException;
import qng.core.query.CompiledConstant;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledVariable;
import qng.core.query.Context;

public interface CompiledElementEvaluator {
	Object evaluateConstant(CompiledConstant c, Context ctx) throws QueryException;
	Object evaluateVariable(CompiledVariable v, Context ctx) throws QueryException;
	Object evaluateOperation(CompiledOperation o, Object[] args, Context ctx) throws QueryException;
}
