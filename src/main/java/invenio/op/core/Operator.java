package invenio.op.core;

/**
 * Most general and simple form of operator.
 * An operator must be given a <code>Context</code> before its <code>execute</code>
 * method is called one or more times.
 * 
 * The operator obtains parameters and attributes from the context. It also stores
 * the execution results as context attributes.
 * 
 * @author ddimitrov
 *
 */
public interface Operator {
	void setContext(Context ctx) throws ConfigurationException;
	void execute(Object[] args) throws ExecutionException;
}
