package qng.core.executor;

import qng.client.QueryException;
import qng.core.query.Context;

public interface OperationManager<O> {
	Object invokeOperation(O operation, Object[] args, Context ctx) throws QueryException;
}
