package qng.core.executor;

import qng.client.QueryException;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.Result;

public interface QueryExecutor<O> {
	Result execute(CompiledQuery<O> query, Context ctx) throws QueryException;
}
