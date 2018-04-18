package qng.core.compiler;

import java.util.List;

import qng.client.QueryException;
import qng.core.datastructures.Position;
import qng.core.datastructures.Tree;
import qng.core.datastructures.impl.LinkedNodeTree;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalVariable;
import qng.core.query.CompiledQuery;
import qng.core.query.DefaultQuery;
import qng.core.query.NotExistsException;

public abstract class LogicalExpressionCompiler<O> extends AbstractCompiler<LogicalExpression, O> {

	@Override
	public CompiledQuery<O> compile(LogicalExpression query) throws QueryException {
		Tree<O> tree = new LinkedNodeTree<O>();
		
		compile(query, tree.root(), tree);
		
		return new DefaultQuery<O>(tree);
	}
	
	protected void compile(LogicalExpression l, Position<O> out, Tree<O> tree) throws QueryException {
		if (l instanceof LogicalConstant)
			out.setElement( compileConstant( (LogicalConstant) l) );
		else if (l instanceof LogicalVariable)
			out.setElement( compileVariable( (LogicalVariable) l) );
		else if (l instanceof LogicalOperator) {
			LogicalOperator op = (LogicalOperator) l;
			out.setElement( compileOperator( op ) );
			List<LogicalExpression> operands = op.getOperands();
			for (LogicalExpression exp : operands) {
				Position<O> newChild = tree.addChild(out, null);
				compile( exp, newChild, tree);
			}
		}
		else
			throw new NotExistsException( "Undefined type of Logical Query Expression with class "  + l.getClass().getName()
					+ " and name " + l.getName() );
		
	}

	protected abstract O compileConstant(LogicalConstant l) throws QueryException;
	protected abstract O compileVariable(LogicalVariable l) throws QueryException;
	protected abstract O compileOperator(LogicalOperator l) throws QueryException;
}
