package qng.core.executor;

import qng.client.QueryException;
import qng.core.query.CompiledOperation;
import qng.core.query.Context;

public class SimpleOperationManager implements
		OperationManager<CompiledOperation> {

	@Override
	public Object invokeOperation(CompiledOperation operation, Object[] args,
			Context ctx) throws QueryException {
		operation.setContext(ctx);
		return operation.execute(args);
	}

	
}
