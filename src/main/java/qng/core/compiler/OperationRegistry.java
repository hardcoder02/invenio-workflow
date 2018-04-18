package qng.core.compiler;

import java.util.HashMap;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.CompiledOperation;
import qng.core.query.DuplicateException;
import qng.core.query.NotExistsException;

// TODO: interface
public class OperationRegistry {
	private final Map<String, Class<? extends CompiledOperation>> operationMap = new HashMap<String, Class<? extends CompiledOperation>>();
	
	public CompiledOperation createCompiledOperation(String name) throws QueryException {
		if ( !operationMap.containsKey(name) )
			throw new NotExistsException("Operation with name " + name + " does not exist");
		Class<? extends CompiledOperation> opClass = operationMap.get( name );
		
		try {
			CompiledOperation op = opClass.newInstance();
			return op;
		}
		catch (IllegalAccessException e) {
			throw new QueryException("Failed to create operation " + name, e);
		} catch (InstantiationException e) {
			throw new QueryException("Failed to create operation " + name, e);
		}
	}
	
	public void registerCompiledOperation(String name, Class<? extends CompiledOperation> clazz) throws DuplicateException {
		if ( operationMap.containsKey(name) )
			throw new DuplicateException("Operation with name " + name + " already exist");
		
		operationMap.put(name, clazz);
	}
}
