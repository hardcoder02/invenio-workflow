package qng.client;

/**
 * Similar to JDBC <code>PreparedStatement</code>, an object
 * that represents a precompiled query. It can be used to
 * efficiently execute this query multiple times. 
 *
 * @see Connection#prepareQuery
 * @see ResultSet
 * 
 * @author ddimitrov
 *
 */
public interface PreparedQuery {
	ResultSet executeQuery() throws QueryException;
	
	int getNumberOfVariables();
	int hasVariables();
	Object setVariable() ;
}
