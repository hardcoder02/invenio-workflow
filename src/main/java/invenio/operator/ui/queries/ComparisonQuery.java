package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select g1Node, g2Node, max_value_certainty(g1Node.location), max_value_certainty(g2Node.location)
 * from (
 * 	(select g1Node
 * 	from g1.NODE as g1Node)
 * 	join
 * 	(select g2Node
 * 	from g2.NODE as g2Node)
 * 	on g1Node.id = g2Node.id
 * 	)
 * order by abs_value(max_value_certainty(g1Node.location) - max_value_certainty(g2Node.location)) DESC
 * 
 * @author ddimitrov
 *
 */
public class ComparisonQuery {
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
		
		select.addOperand( new LogicalConstant("select1", createMaxConfExpression("g1Node")) );
		select.addOperand( new LogicalConstant("select1alias", "maxConf1"));
		
		select.addOperand( new LogicalConstant("select1", createMaxConfExpression("g2Node")) );
		select.addOperand( new LogicalConstant("select1alias", "maxConf2"));
		
		return select;
	}
	
	public static LogicalExpression createMaxConfExpression(String col) {
		LogicalOperator expr = new LogicalOperatorImpl("maxConf");
		
		LogicalOperator path1 = new LogicalOperatorImpl("path");
		path1.addOperand( new LogicalConstant("column", col) );
		path1.addOperand( new LogicalConstant("attribute", "location") );
		
		expr.addOperand( path1 );
		
		return expr;
	}
	
	
	public static LogicalExpression createJoin() {
		LogicalOperator join = new LogicalOperatorImpl("join");
		
		join.addOperand( createSelectInner1() );
		join.addOperand( createSelectInner2() );
		
		join.addOperand( new LogicalConstant("left", createJoinIdExpression("g1Node")) );
		join.addOperand( new LogicalConstant("right", createJoinIdExpression("g2Node")) );
				
		return join;
	}
	
	public static LogicalExpression createJoinIdExpression(String expr) {
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", expr) );
		path.addOperand( new LogicalConstant("attribute", "id") );
		
		return path;
	}
	
	public static LogicalExpression createSelectInner1() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom(DATA_DIR1 + DATA_NODE1, DATA_DIR1 + DATA_EDGE1, "g1Node") );
		
		select.addOperand( new LogicalConstant("selectInner1", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createSelectInner2() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom(DATA_DIR2 + DATA_NODE2, DATA_DIR2 + DATA_EDGE2, "g2Node") );
		
		select.addOperand( new LogicalConstant("selectInner2", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createFrom(String nodeFile, String edgeFile, String alias) {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
		from.addOperand( createTabReader(nodeFile, edgeFile) );
		from.addOperand(new LogicalConstant("type", FromOperation.TYPE.VERTEX));
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
