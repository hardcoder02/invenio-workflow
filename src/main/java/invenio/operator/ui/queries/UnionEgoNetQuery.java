package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select union(ego1, ego2), intersection(ego1, ego2), difference(ego1, ego2), bidirectional_difference(ego1, ego2)
 * from (
 * select to_graph( ego_net(n1) ) as ego1
 * from g1.NODE as n1
 * where n1.dolphin_name = 'JOY'
 * join
 * select to_graph( ego_net(n2) ) as ego2
 * from g2.NODE as n2
 * where n2.dolphin_name = 'JOYSFRIEND'
 * )
 * 
 * @author ddimitrov
 *
 */
public class UnionEgoNetQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE = "dolphin.UNDIRECTED.seenwith.tab";
	
	public final static String DOLPHIN_1 = "JOY";
	public final static String DOLPHIN_2 = "JOYSFRIEND";
	
	public static LogicalExpression getQuery() {
		return createSelectOuter();
	}
	
	public static LogicalExpression createSelectOuter() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createJoin() );
		
		select.addOperand( new LogicalConstant("select1", createSelectExpression("union")) );
		select.addOperand( new LogicalConstant("select1alias", "union"));
		
		select.addOperand( new LogicalConstant("select1", createSelectExpression("intersection")) );
		select.addOperand( new LogicalConstant("select1alias", "intersection"));
		
		select.addOperand( new LogicalConstant("select1", createSelectExpression("difference")) );
		select.addOperand( new LogicalConstant("select1alias", "difference"));
		
		select.addOperand( new LogicalConstant("select1", createSelectExpression("bidirectionalDifference")) );
		select.addOperand( new LogicalConstant("select1alias", "bidirectionalDifference"));
		
		return select;
	}
	
	public static LogicalExpression createSelectExpression(String opName) {
		
		LogicalOperator uid = new LogicalOperatorImpl(opName);
		
		LogicalOperator path1 = new LogicalOperatorImpl("path");
		path1.addOperand( new LogicalConstant("column", "ego1") );
		path1.addOperand( new LogicalConstant("attribute", null) );
		uid.addOperand(path1);
		
		LogicalOperator path2 = new LogicalOperatorImpl("path");
		path2.addOperand( new LogicalConstant("column", "ego2") );
		path2.addOperand( new LogicalConstant("attribute", null) );
		uid.addOperand(path2);
		
		LogicalConstant gName = new LogicalConstant("graphName", opName);
		uid.addOperand( gName);
		
		return uid;
	}
	
	public static LogicalExpression createJoin() {
		LogicalOperator join = new LogicalOperatorImpl("join");
		
		join.addOperand( createSelectInner("n1", DOLPHIN_1, "ego1") );
		join.addOperand( createSelectInner("n2", DOLPHIN_2, "ego2") );
		
		return join;
	}
	
	public static LogicalExpression createSelectInner(String col, String dolphinName, String alias) {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createWhere(col, dolphinName) );
		
		select.addOperand( new LogicalConstant("select", createSelectToGraphExpression( col, dolphinName )) );
		select.addOperand( new LogicalConstant("selectalias", alias ));
		
		return select;
	}
	
	public static LogicalExpression createSelectToGraphExpression(String col, String graphName) {
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", col) );
		path.addOperand( new LogicalConstant("attribute", null) );
		
		LogicalOperator egoNet = new LogicalOperatorImpl("egoNet");
		egoNet.addOperand( path );
		
		LogicalOperator toGraph = new LogicalOperatorImpl("toGraph");
		toGraph.addOperand( egoNet );
		
		LogicalConstant gName = new LogicalConstant("graphName", graphName);
		toGraph.addOperand( gName);
		
		return toGraph;
	}
	

	public static LogicalExpression createWhere(String col, String dolphinName) {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom(col) );
		where.addOperand( new LogicalConstant("clause", createWhereClause(col, dolphinName)) );
		
		return where;
	}
	

	public static LogicalExpression createWhereClause(String col, String dolphinName) {
		LogicalOperator eq = new LogicalOperatorImpl("equals");
		
		eq.addOperand(new LogicalConstant("left", dolphinName));
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", col) );
		path.addOperand( new LogicalConstant("attribute", "dolphin_name") );
		eq.addOperand(path);
		
		return eq;
	}
	
	public static LogicalExpression createFrom(String col) {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
		from.addOperand( createTabReader() );		
		from.addOperand(new LogicalConstant("type", FromOperation.TYPE.VERTEX));
		from.addOperand( new LogicalConstant("alias", col));
		
		return from;
	}
	
	public static LogicalExpression createTabReader() {
	
		LogicalOperator tabReader = new LogicalOperatorImpl("tabReader");
		tabReader.addOperand(new LogicalConstant("nodeFile", DATA_DIR + DATA_NODE ));
		tabReader.addOperand(new LogicalConstant("edgeFile", DATA_DIR + DATA_EDGE ));
		
		return tabReader;
	}
}
