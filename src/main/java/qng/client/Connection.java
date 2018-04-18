package qng.client;

import invenio.op.operation.LogicalOperation;

/**
 * A connection to a specific query engine, similar to JDBC <code>java.sql.Connection</code>.
 * 
 * At this point, parsing is not provided: therefore, the queries are represented
 * as objects rather than text. Because remote connections are not required, there is
 * no concern about parameter serializability, remote exceptions, etc.
 * 
 * @author ddimitrov
 *
 */
public interface Connection {
	PreparedQuery prepareQuery(LogicalOperation root) throws QueryException;
	void close() throws QueryException;
	boolean isClosed() throws QueryException;
}
