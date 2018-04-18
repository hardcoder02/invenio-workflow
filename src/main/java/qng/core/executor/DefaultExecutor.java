package qng.core.executor;

import java.util.Iterator;

import qng.client.QueryException;
import qng.core.datastructures.BasicTree;
import qng.core.datastructures.Position;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.Result;

// TODO: replace resolver with service
// TODO: replace DFS with plug-able iterator / traversal template
// TODO: create executor pool
public abstract class DefaultExecutor<O> extends AbstractExecutor<O> {
	
	
	@Override
	public Result execute(CompiledQuery<O> query, Context ctx) throws QueryException {
		BasicTree<O> tree = query.getQueryTree();
		Position<O> root = query.getQueryTree().root();
		
		return packageResult( executeElement(tree, root, ctx), ctx );
	}
	
	protected Object executeElement(BasicTree<O> queryTree, Position<O> elementNode, Context ctx) throws QueryException {
		if (queryTree.isExternal(elementNode)) {
			return evaluateExternal( elementNode.element(), ctx );
		}
		else {
			Object[] args = new Object[ queryTree.numberOfChildren(elementNode) ];
			Iterator<? extends Position<O>> iter = queryTree.children(elementNode);
			int i = 0;
			
			while (iter.hasNext()) {
				args[i] = executeElement(queryTree, iter.next(), ctx);
				i++;
			}
			
			return evaluateInternal( elementNode.element(), args, ctx);
		}
	}
	
	protected abstract Object evaluateExternal(O element, Context ctx) throws QueryException;
	protected abstract Object evaluateInternal(O element, Object[] args, Context ctx) throws QueryException;
	protected abstract Result packageResult(Object result, Context ctx) throws QueryException;
}
