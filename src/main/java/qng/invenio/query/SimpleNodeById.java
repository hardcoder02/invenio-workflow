package qng.invenio.query;

import invenio.op.operation.impl.FromOperation;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import qng.client.QueryException;
import qng.core.compiler.DefaultCompiler;
import qng.core.executor.CompiledElementEvaluator;
import qng.core.executor.CompiledElementExecutor;
import qng.core.executor.DefaultCompiledElementEvaluator;
import qng.core.executor.OperationManager;
import qng.core.executor.SimpleOperationManager;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledOperation;
import qng.core.query.CompiledQuery;
import qng.core.query.Context;
import qng.core.query.DefaultContext;
import qng.core.query.Result;

/**
 * 
 * select *
 * from g1.NODE
 * where 'RCB' in mpv(location)
 * 
 * @author ddimitrov
 *
 */
public class SimpleNodeById {
	
	public static final String NODE_1 =
		"C:/Workspace/Eclipse/Invenio/QueryEngine/set-20110308/dolphin-location/dolphin.NODE.dolphin.wvrn.tab";
	public static final String EDGE_1 =
		"C:/Workspace/Eclipse/Invenio/QueryEngine/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab";
	
		

	public static LogicalExpression createSelect() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createWhere() );
		select.addOperand( new LogicalConstant("select1", "*"));
		
		return select;
	}
	
	public static LogicalExpression createWhere() {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom(NODE_1, EDGE_1));
		where.addOperand(  new LogicalConstant("clause", createWhereClause()) );
		
		return where;
	}
	
	public static LogicalExpression createWhereClause() {
		LogicalOperator whereClause = new LogicalOperatorImpl("equals");
		
		LogicalConstant inLiteral = new LogicalConstant(946);
		
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "g1Node") );
		path.addOperand( new LogicalConstant("attribute", "id") );
		
		whereClause.addOperand( inLiteral );
		whereClause.addOperand( path );
		
		return whereClause;
	}
	
	public static LogicalExpression createFrom(String nodeFile, String edgeFile) {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
		LogicalOperator tabReader = new LogicalOperatorImpl("tabReader");
		tabReader.addOperand(new LogicalConstant("nodeFile",
				nodeFile));
		tabReader.addOperand(new LogicalConstant("edgeFile",
				edgeFile));
		
		from.addOperand( tabReader );
		from.addOperand(new LogicalConstant("type", FromOperation.TYPE.VERTEX));
		from.addOperand( new LogicalConstant("alias", "g1Node"));
		
		return from;
	}
	
	
	public static CompiledQuery<CompiledElement> compileExpression( LogicalExpression exp) throws QueryException {
		DefaultCompiler comp = new DefaultCompiler( OperationRegistryFactory.getInstance() );
		
		return comp.compile(exp);
	}
	
	public static Result executeQuery(CompiledQuery<CompiledElement> query) throws QueryException {
		OperationManager<CompiledOperation> opManager = new SimpleOperationManager();
		CompiledElementEvaluator eval = new DefaultCompiledElementEvaluator(opManager);
		CompiledElementExecutor exec = new CompiledElementExecutor(eval);
		
		Context ctx = new DefaultContext();
		ctx.setVariable(OperationConstants.VAR_EXECUTOR, exec);
		
		return exec.execute(query, ctx);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws QueryException {
		
		LogicalExpression q = createSelect();
		System.out.println(q);
		
		CompiledQuery<CompiledElement> comp = compileExpression(q);
		System.out.println(comp);
		
		Object res = executeQuery(comp).getResultValue();
		System.out.println(res);
	}
}
