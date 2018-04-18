package qng.core.query;

import java.util.Map;
import java.util.Set;

import qng.client.QueryException;

// TODO: separate operation itself from CompiledOperation, to allow for overriding
// TODO: refactor operation description (isAggregate) from operation itself
// TODO: refactor context params from operation itself. Consider that operations in the tree may be re-ordered during optimization.
public interface CompiledOperation extends CompiledElement {
	void setContext(Context ctx) throws QueryException;
	Object execute(Object[] args) throws QueryException;
	
	boolean isAggregate();
	
	String setContextParam(String name, String value);
	int getNumberOfContextParams();
	boolean hasContextParams();
	boolean hasContextParam(String name);
	String getContextParam(String name);
	Set<Map.Entry<String, String>> getContextParams();
}
