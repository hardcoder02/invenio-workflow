package qng.core.executor;

//TODO: fix OperationEntry: replace Class[] with custom object
public interface ConfigurableOperationManager<O> extends OperationManager<O> {
	public class OperationSignature<O> {
		public O operation;
		public Class[] argTypes;
	}
	
	OperationSignature registerOperation(O operation, Class[] argTypes);
}
