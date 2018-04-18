package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select union(g1, g2)
 * from (
 * 	(select *
 * 	from g1.GRAPH as g1)
 * 	join
 * 	(select *
 * 	from g2.GRAPH as g2)
 * )
 * 
 * @author ddimitrov
 *
 */
public class UnionQuery {
	public final static String DATA_DIR1 = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE1 = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE1 = "dolphin.UNDIRECTED.seenwith.tab";
	
	public final static String DATA_DIR2 = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE2 = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE2 = "dolphin.UNDIRECTED.seenwith.tab";
	
	public static LogicalExpression getQuery() {
		return createSelectOuter();
	}
	
	public static LogicalExpression createSelectOuter() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createJoin() );
		
		select.addOperand( new LogicalConstant("select1", createUnionExpression()) );
		select.addOperand( new LogicalConstant("select1alias", "union"));
		
		return select;
	}
	
	public static LogicalExpression createUnionExpression() {
		LogicalOperator expr = new LogicalOperatorImpl("union");
		
		LogicalOperator path1 = new LogicalOperatorImpl("path");
		path1.addOperand( new LogicalConstant("column", "g1") );
		path1.addOperand( new LogicalConstant("attribute", null) );
		
		LogicalOperator path2 = new LogicalOperatorImpl("path");
		path2.addOperand( new LogicalConstant("column", "g2") );
		path2.addOperand( new LogicalConstant("attribute", null) );
		
		expr.addOperand( path1 );
		expr.addOperand( path2 );
		
		return expr;
	}
	
	
	public static LogicalExpression createJoin() {
		LogicalOperator join = new LogicalOperatorImpl("join");
		
		join.addOperand( createSelectInner1() );
		join.addOperand( createSelectInner2() );
		
		return join;
	}
	
	public static LogicalExpression createSelectInner1() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom(DATA_DIR1 + DATA_NODE1, DATA_DIR1 + DATA_EDGE1, "g1") );
		
		select.addOperand( new LogicalConstant("selectInner1", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createSelectInner2() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom(DATA_DIR2 + DATA_NODE2, DATA_DIR2 + DATA_EDGE2, "g2") );
		
		select.addOperand( new LogicalConstant("selectInner2", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createFrom(String nodeFile, String edgeFile, String alias) {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
		from.addOperand( createTabReader(nodeFile, edgeFile) );
		from.addOperand(new LogicalConstant("type", FromOperation.TYPE.GRAPH));
		from.addOperand( new LogicalConstant("alias", alias));
		
		return from;
	}
	
	public static LogicalExpression createTabReader(String nodeFile, String edgeFile) {
		
		LogicalOperator tabReader = new LogicalOperatorImpl("tabReader");
		tabReader.addOperand(new LogicalConstant("nodeFile", nodeFile ));
		tabReader.addOperand(new LogicalConstant("edgeFile", edgeFile ));
		
		return tabReader;
	}
}
