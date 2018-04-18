package invenio.operator.ui;

import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.op.parser.TextToLogicalExpressionParser;

import java.io.File;
import java.util.Map;

import qng.client.QueryException;
import qng.core.compiler.DefaultCompiler;
import qng.core.executor.CompiledElementEvaluator;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.DefaultCompiledElementEvaluator;
import qng.core.executor.OperationManager;
import qng.core.executor.SimpleOperationManager;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.DefaultContext;
import qng.core.query.Result;
import qng.core.query.io.QuerySerializer;

public class QueryService {
	
	public static LogicalExpression loadQuery(String fileName) throws QueryException {
		return QuerySerializer.loadQuery( new File(fileName) );
	}
	
	public static LogicalExpression parseQuery(String query) throws QueryException {
		QueryParser<String, LogicalExpression> parser = new TextToLogicalExpressionParser();
		return parser.parse(query);
	}
	
	public static CompiledQuery<CompiledElement> compileExpression( LogicalExpression exp) throws QueryException {
		DefaultCompiler comp = new DefaultCompiler( OperationRegistryFactory.getInstance() );
		
		return comp.compile(exp);
	}
	
	public static Result executeQuery(CompiledQuery<CompiledElement> query, Map<String, String> contextParams) throws QueryException {
		OperationManager<CompiledOperation> opManager = new SimpleOperationManager();
		CompiledElementEvaluator eval = new DefaultCompiledElementEvaluator(opManager);
		CompiledElementExecutor exec = new CompiledElementExecutor(eval);
		
		Context ctx = new DefaultContext();
		loadContextParams(ctx, contextParams);
		ctx.setVariable(OperationConstants.VAR_EXECUTOR, exec);
		ctx.setVariable(OperationConstants.VAR_OPERATION_MANAGER, opManager);
		
		return exec.execute(query, ctx);
	}
	
	private static void loadContextParams(Context ctx, Map<String, String> contextParams) {
		if (contextParams == null)
			return;
		for (Map.Entry<String, String> entry : contextParams.entrySet() ) {
			ctx.putParam(entry.getKey(), entry.getValue());
		}
	}
}
