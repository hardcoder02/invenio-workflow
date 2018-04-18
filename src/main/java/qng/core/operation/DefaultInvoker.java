package qng.core.operation;

import qng.core.executor.ConfigurableOperationManager.OperationSignature;
import qng.core.executor.DefaultOperationManager.OperationInvoker;
import qng.core.query.CompiledOperation;
import qng.core.query.Context;

// TODO: finish
public class DefaultInvoker implements OperationInvoker<CompiledOperation> {

	@Override
	public Object invokeOperation(OperationSignature<CompiledOperation> sig,
			Object[] args, Context ctx) {
		
		return null;
	}

}
