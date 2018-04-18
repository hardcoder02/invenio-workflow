package qng.core.executor;

import java.util.Iterator;

import qng.core.datastructures.BasicTree;
import qng.core.datastructures.Position;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;

// TODO: refactor
public class Util {

	
	public static boolean isAggregate(BasicTree<CompiledElement> queryTree, Position<CompiledElement> elementNode) {
		
		if ( queryTree.isExternal( elementNode ) )
			return isAggregate( elementNode.element() );
		
		if ( isAggregate( elementNode.element() ) )
			return true;
		
		Iterator<? extends Position<CompiledElement>> iter = queryTree.children(elementNode);
		while (iter.hasNext()) {
			if ( isAggregate( queryTree, iter.next() ) )
				return true;
		}
		
		return false;
	}
	
	public static boolean isAggregate(CompiledElement e) {
		if ( e instanceof CompiledOperation)
			return ((CompiledOperation)e).isAggregate();
		else
			return false;
	}
}
