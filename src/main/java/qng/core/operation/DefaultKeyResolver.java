package qng.core.operation;

import qng.core.executor.DefaultOperationManager.KeyResolver;
import qng.core.query.CompiledOperation;

public class DefaultKeyResolver implements KeyResolver<CompiledOperation> {

	@Override
	public String getKey(CompiledOperation operation) {
		return operation.getName();
	}

}
