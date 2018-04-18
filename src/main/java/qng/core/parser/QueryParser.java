package qng.core.parser;

import qng.client.QueryException;

public interface QueryParser<L, O> {
	O parse(L query) throws QueryException;
}
