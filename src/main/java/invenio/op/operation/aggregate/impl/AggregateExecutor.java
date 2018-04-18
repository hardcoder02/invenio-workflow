package invenio.op.operation.aggregate.impl;

import invenio.op.operation.impl.OperationConstants;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import qng.client.QueryException;
import qng.core.datastructures.BasicTree;
import qng.core.datastructures.Position;
import qng.core.executor.AbstractExecutor;
import qng.core.executor.CompiledElementEvaluator;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.DefaultCompiledElementEvaluator;
import qng.core.executor.OperationManager;
import qng.core.executor.SimpleOperationManager;
import qng.core.executor.Util;
import qng.core.query.CompiledConstant;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.CompiledVariable;
import qng.core.query.Context;
import qng.core.query.DefaultResult;
import qng.core.query.Result;
import qng.tabular.AggregateTuple;
import qng.tabular.Tuple;

// TODO: replace resolver with service
// TODO: replace DFS with plug-able iterator / traversal template
// TODO: create executor pool
public class AggregateExecutor extends AbstractExecutor<CompiledElement> {
	private CompiledElementExecutor exec;
	OperationManager<CompiledOperation> opManager = new SimpleOperationManager();
	CompiledElementEvaluator evaluator = new DefaultCompiledElementEvaluator(opManager);
	AggregateTuple outerT;
	
	public AggregateExecutor( CompiledElementExecutor exec ) {
		this.exec = exec;
	}
	
	@Override
	public Result execute(CompiledQuery<CompiledElement> query, Context ctx) throws QueryException {
		BasicTree<CompiledElement> tree = query.getQueryTree();
		Position<CompiledElement> root = query.getQueryTree().root();
		outerT = (AggregateTuple) ctx.getVariable( OperationConstants.VAR_TUPLE );
		
		return packageResult( executeElement(tree, root, ctx), ctx );
	}
	
	protected Object executeElement(BasicTree<CompiledElement> queryTree, Position<CompiledElement> elementNode, Context ctx) throws QueryException {
		if ( Util.isAggregate( elementNode.element() )) {
			List result = new ArrayList();
			for ( Tuple t : outerT.getAggregatedTuples() ) {
				ctx.setVariable(OperationConstants.VAR_TUPLE, t);
				result.add(  executeElement( queryTree, queryTree.children( elementNode ).next(), ctx ));
			}
			ctx.setVariable(OperationConstants.VAR_TUPLE, outerT);
			return evaluator.evaluateOperation( (CompiledOperation) elementNode.element(), new Object[] {result}, ctx);
		}
		else {
			if (queryTree.isExternal(elementNode)) {
				return evaluateExternal( elementNode.element(), ctx );
			}
			else {
				Object[] args = new Object[ queryTree.numberOfChildren(elementNode) ];
				Iterator<? extends Position<CompiledElement>> iter = queryTree.children(elementNode);
				int i = 0;
				
				while (iter.hasNext()) {
					args[i] = executeElement(queryTree, iter.next(), ctx);
					i++;
				}
				
				return evaluateInternal( elementNode.element(), args, ctx);
			}
		}
	}
	
	protected Object evaluateExternal(CompiledElement element, Context ctx) throws QueryException {
		if (element instanceof CompiledConstant)
			return evaluator.evaluateConstant( (CompiledConstant) element, ctx);
		else if (element instanceof CompiledVariable)
			return evaluator.evaluateVariable( (CompiledVariable) element, ctx);
		else if (element instanceof CompiledOperation)
			return evaluator.evaluateOperation( (CompiledOperation) element, new Object[0], ctx);
		else
			throw new QueryException("Unknown query element type: " + element.getClass().getName() );
	}

	protected Object evaluateInternal(CompiledElement element, Object[] args, Context ctx) throws QueryException {
		if (element instanceof CompiledConstant)
			return evaluator.evaluateConstant( (CompiledConstant) element, ctx);
		else if (element instanceof CompiledVariable)
			return evaluator.evaluateVariable( (CompiledVariable) element, ctx);
		else if (element instanceof CompiledOperation)
			return evaluator.evaluateOperation( (CompiledOperation) element, args, ctx);
		else
			throw new QueryException("Unknown query element type: " + element.getClass().getName() );
	}

	protected Result packageResult(Object result, Context ctx) throws QueryException {
		return new DefaultResult(result);
	}

}
