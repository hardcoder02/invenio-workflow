package qng.core.compiler;

import qng.client.QueryException;
import qng.core.query.CompiledQuery;

public interface QueryCompiler<L, O> {
	CompiledQuery<O> compile(L query) throws QueryException;
}
