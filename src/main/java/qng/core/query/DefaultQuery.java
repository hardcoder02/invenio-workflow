package qng.core.query;

import java.util.Iterator;

import qng.core.datastructures.BasicTree;
import qng.core.datastructures.Position;
import qng.core.logical.LogicalExpression;

public class DefaultQuery<O> implements CompiledQuery<O> {
	private BasicTree<O> queryTree;
	
	public DefaultQuery() {}
	public DefaultQuery(BasicTree<O> queryTree) {
		this.queryTree = queryTree;
	}
	
	@Override
	public BasicTree<O> getQueryTree() {
		return queryTree;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Compiled query = {\n");
			appendChildString(sb, queryTree.root(), 0);
		sb.append("\n}");
		return sb.toString();
	}
	
	private void appendChildString(StringBuffer sb, Position<O> node, int level) {
		for (int i = 0; i < level; i++)
			sb.append(" ");
		
		sb.append( node.element() );
		if ( queryTree.isInternal(node)) {
			Iterator<? extends Position<O>> iter = queryTree.children(node);
			while ( iter.hasNext() ) {
				sb.append("\n");
				Position<O> child = iter.next();
				appendChildString( sb, child, level + 1 );
			}
		}
		//sb.append("\n");
	}
	
}
