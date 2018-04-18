package qng.core.query;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import qng.client.QueryException;

// TODO: add null argument validation
public abstract class AbstractOperation extends AbstractElement implements CompiledOperation {
	
	private Context ctx;
	
	private Map<String, String> contextParams = new HashMap<String, String>();
	
	public AbstractOperation(String id, String name) {
		super(id, name);
	}

	@Override
	public void setContext(Context ctx) throws QueryException {
		this.ctx = ctx;
	}

	public Context getContext() {
		return ctx;
	}

	@Override
	public Object execute(Object[] args) throws QueryException {
		validateArguments(args);
		
		long startTime = System.currentTimeMillis();
//		System.out.println("Starting execution of: " + getName());
		try {
			Object result = doExecute(args);
			long duration = System.currentTimeMillis() - startTime;
//			System.out.println("Finished execution of " + getName() + " in (msec): " + duration);
			return result;
		}
		catch (QueryException ex) {
			long duration = System.currentTimeMillis() - startTime;
			System.out.println("Error during execution of " + getName() + " at (msec): " + duration);
			throw ex;
		}
	}
	
	protected void validateArguments(Object[] args) throws QueryException {
		Class[] expTypes = getExpectedTypes();
		if (expTypes == null)
			return;
		
		if ( hasExactNumberOfArguments() )
		{
			if ( expTypes.length != args.length )
				throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
						"]. Expected arguments count is " + expTypes.length + ", actual count is " + args.length);
		}
		else	// at least that many arguments
		{
			if ( expTypes.length > args.length )
			throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
					"]. Expected arguments count is at least " + expTypes.length + ", actual count is " + args.length);
		}
		
		for (int i = 0; i < expTypes.length; i++) {
			if ( args[i] != null && ! expTypes[i].isAssignableFrom( args[i].getClass() )) {
				throw new QueryException("Error executing operation [" + getName() + "], id[" + getId() + 
						"]. Expected argument " + i + " is of type " + expTypes[i].getName() +
						", actual type is " + args[i].getClass().getName() );
			}
		}
	}
	
	protected abstract Object doExecute(Object[] args) throws QueryException;
	
	public Class[] getExpectedTypes() {
		return null;
	}
	
	public boolean hasExactNumberOfArguments() {
		return true;
	}
	
	@Override
	public boolean isAggregate() {
		return false;
	}
	
	@Override
	public String getContextParam(String name) {
		return contextParams.get(name);
	}

	@Override
	public Set<Entry<String, String>> getContextParams() {
		return Collections.unmodifiableSet( contextParams.entrySet() );
	}


	@Override
	public int getNumberOfContextParams() {
		return contextParams.size();
	}


	@Override
	public boolean hasContextParam(String name) {
		return contextParams.containsKey(name);
	}


	@Override
	public boolean hasContextParams() {
		return !contextParams.isEmpty();
	}


	@Override
	public String setContextParam(String name, String value) {
		return contextParams.put(name, value);
	}

	public String toString() {
		return "Compiled operation: " + getName(); 
	}
}
