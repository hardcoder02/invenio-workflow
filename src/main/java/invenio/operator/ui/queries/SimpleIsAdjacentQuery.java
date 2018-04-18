package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select n1.dolphin_name, n2.dolphin_name, isAdjacentById(n1, n2.id)
 * from (
 * 	(select n1
 * 	from g1.NODE as n1
 * 	where n1.dolphin_name = ?)
 * 	join
 * 	(select n2
 * 	from g1.NODE as n2
 * 	where n2.dolphin_name = ?)
 * )
 * 
 * @author ddimitrov
 *
 */
public class SimpleIsAdjacentQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE = "dolphin.UNDIRECTED.seenwith.tab";
	
	public final static String DOLPHIN_1 = "TOQUE";
	public final static String DOLPHIN_2 = "CEEBIE";
	
	public static LogicalExpression getQuery() {
		return createSelectOuter();
	}
	
	public static LogicalExpression createSelectOuter() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createJoin() );
		
		select.addOperand( new LogicalConstant("select1", createSelectNameExpression("n1")) );
		select.addOperand( new LogicalConstant("select1alias", "n1.dolphin_name"));
		
		select.addOperand( new LogicalConstant("select2", createSelectNameExpression("n2")) );
		select.addOperand( new LogicalConstant("select2alias", "n2.dolphin_name"));
		
		select.addOperand( new LogicalConstant("select3", createSelectIsAdjacentExpression()) );
		select.addOperand( new LogicalConstant("select3alias", "isAdjacent(n1, n2)"));
		
		return select;
	}
	
	public static LogicalExpression createSelectNameExpression(String column) {
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", column) );
		path.addOperand( new LogicalConstant("attribute", "dolphin_name") );
		
		return path;
	}
	
//	public static LogicalExpression createSelectIsAdjacentExpression() {
//		
//		LogicalOperator isAdjacent = new LogicalOperatorImpl("isAdjacentById");
//		
//		LogicalOperator path1 = new LogicalOperatorImpl("path");
//		path1.addOperand( new LogicalConstant("column", "n1") );
//		path1.addOperand( new LogicalConstant("attribute", null) );
//		isAdjacent.addOperand(path1);
//		
//		LogicalOperator path2 = new LogicalOperatorImpl("path");
//		path2.addOperand( new LogicalConstant("column", "n2") );
//		path2.addOperand( new LogicalConstant("attribute", "id") );
//		isAdjacent.addOperand(path2);
//		
//		return isAdjacent;
//	}
	
	public static LogicalExpression createSelectIsAdjacentExpression() {
		
		LogicalOperator adjacentIds = new LogicalOperatorImpl("adjacentIds");
		
		LogicalOperator path1 = new LogicalOperatorImpl("path");
		path1.addOperand( new LogicalConstant("column", "n1") );
		path1.addOperand( new LogicalConstant("attribute", null) );
		adjacentIds.addOperand(path1);
		
		LogicalOperator path2 = new LogicalOperatorImpl("path");
		path2.addOperand( new LogicalConstant("column", "n2") );
		path2.addOperand( new LogicalConstant("attribute", "id") );
		
		LogicalOperator in = new LogicalOperatorImpl("in");
		in.addOperand(path2);
		in.addOperand(adjacentIds);
		
		return in;
	}
	
	
	public static LogicalExpression createJoin() {
		LogicalOperator join = new LogicalOperatorImpl("join");
		
		join.addOperand( createSelectInner1() );
		join.addOperand( createSelectInner2() );
		
		return join;
	}

	
	public static LogicalExpression createSelectInner1() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createWhere(DATA_DIR + DATA_NODE, DATA_DIR + DATA_EDGE, "n1", DOLPHIN_1) );
		
		select.addOperand( new LogicalConstant("selectInner1", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createSelectInner2() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createWhere(DATA_DIR + DATA_NODE, DATA_DIR + DATA_EDGE, "n2", DOLPHIN_2) );
		
		select.addOperand( new LogicalConstant("selectInner2", "*" ) );
		
		return select;
	}
	
	public static LogicalExpression createWhere(String nodeFile, String edgeFile, String alias, String dolphinName) {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom(nodeFile, edgeFile, alias) );
		where.addOperand( new LogicalConstant("clause", createWhereClause(alias, dolphinName)) );
		
		return where;
	}
	

	public static LogicalExpression createWhereClause(String column, String dolphinName) {
		LogicalOperator eq = new LogicalOperatorImpl("equals");
		
		eq.addOperand(new LogicalConstant("left", dolphinName));
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", column) );
		path.addOperand( new LogicalConstant("attribute", "dolphin_name") );
		eq.addOperand(path);
		
		return eq;
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
