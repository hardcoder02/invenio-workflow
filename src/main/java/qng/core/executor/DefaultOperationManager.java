package qng.core.executor;

import java.util.HashMap;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.Context;

// TODO: add overloaded operations
// TODO: fix registerOperation to make a copy of OperationSignature's fields and not to return null
public class DefaultOperationManager<O> implements
		ConfigurableOperationManager<O> {
	
	public interface KeyResolver<K> {
		String getKey(K operation);
	}
	
	public interface OperationInvoker<T> {
		Object invokeOperation(OperationSignature<T> sig, Object[] args, Context ctx);
	}
	
	private final Map<String, OperationSignature<O>> operationMap = new HashMap<String, OperationSignature<O>>();
	private final KeyResolver<O> keyResolver;
	private final OperationInvoker<O> invoker;
	
	public DefaultOperationManager(KeyResolver<O> keyResolver, OperationInvoker<O> invoker) {
		this.keyResolver = keyResolver;
		this.invoker = invoker;
	}
	
	@Override
	public OperationSignature<O> registerOperation(
			O operation, Class[] argTypes) {
		
		String key = keyResolver.getKey(operation);
		OperationSignature<O> entry = new OperationSignature<O>();
		entry.operation = operation;
		entry.argTypes = argTypes;
		operationMap.put(key, entry);
		return null;
	}

	@Override
	public Object invokeOperation(O operation, Object[] args, Context ctx)
			throws QueryException {
		
		OperationSignature<O> sig = getMatchingOperation(operation, args);
		if (sig == null)
			throw new QueryException("Operation [" + operation + "] not found for arguments [" + args + "]");
		return invoker.invokeOperation(sig, args, ctx);
	}
	
	protected OperationSignature<O> getMatchingOperation(O operation, Object[] args) {
		return operationMap.get(keyResolver.getKey(operation));
	}
}
