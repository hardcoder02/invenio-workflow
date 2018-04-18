package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select to_graph(e)
 * from g1.ELEMENT as e
 * where isVertex(e) or (isEdge(e) and not(bin(e, 0.6)))
 * merge all
 * 
 * @author ddimitrov
 *
 */
public class ToGraphByConfQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/2011-09-16 (data from Prof. Singh)/";
	public final static String DATA_NODE = "2011-09-16 Dolphin-conf.tab";
	public final static String DATA_EDGE = "2011-09-16 Dolphin-edge-conf.tab";
	
	public static LogicalExpression getQuery() {
		return createSelect();
	}
	
	public static LogicalExpression createSelect() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createMerge() );
		select.addOperand( new LogicalConstant("select1", createExpression1()) );
		select.addOperand( new LogicalConstant("select1alias", "toGraph"));
		
		return select;
	}
	
	public static LogicalExpression createMerge() {
		LogicalOperator merge = new LogicalOperatorImpl("merge");
		
		merge.addOperand( createWhere() );
		merge.addOperand( new LogicalConstant("distinct", false ) );
		
		return merge;
	}
	
	public static LogicalExpression createWhere() {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom() );
		where.addOperand( new LogicalConstant("clause", createWhereClause()) );
		
		return where;
	}
	
	public static LogicalExpression createWhereClause() {
		
		LogicalOperator not = new LogicalOperatorImpl("not");
		not.addOperand( getBinExpression() );
		
		LogicalOperator isEdge = new LogicalOperatorImpl("isEdge");
		isEdge.addOperand( getPathExpression() );
		
		LogicalOperator and = new LogicalOperatorImpl("and");
		and.addOperand( isEdge );
		and.addOperand( not );
		
		LogicalOperator isVertex = new LogicalOperatorImpl("isVertex");
		isVertex.addOperand( getPathExpression() );
		
		LogicalOperator or = new LogicalOperatorImpl("or");
		or.addOperand( and );
		or.addOperand( isVertex );
		
		return or;
	}
	
	public static LogicalExpression getBinExpression() {
		LogicalOperator bin = new LogicalOperatorImpl("bin");
		bin.addOperand( getPathExpression() );
		bin.addOperand( new LogicalConstant("left", 0.75) );
		
		return bin;
	}
	
	public static LogicalExpression getPathExpression() {
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "e") );
		path.addOperand( new LogicalConstant("attribute", null) );
		
		return path;
	}
	
	
	public static LogicalExpression createFrom() {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
		from.addOperand( createTabReader() );
		from.addOperand( new LogicalConstant("type", FromOperation.TYPE.ELEMENT) );
		from.addOperand( new LogicalConstant("alias", "e"));
		
		return from;
	}
	
	public static LogicalExpression createTabReader() {
		
		LogicalOperator tabReader = new LogicalOperatorImpl("tabReader");
		tabReader.addOperand(new LogicalConstant("nodeFile", DATA_DIR + DATA_NODE ));
		tabReader.addOperand(new LogicalConstant("edgeFile", DATA_DIR + DATA_EDGE ));
		
		return tabReader;
	}
	
	public static LogicalExpression createExpression1() {
		LogicalOperator expr = new LogicalOperatorImpl("toGraph");
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "e") );
		path.addOperand( new LogicalConstant("attribute", null) );
		
		LogicalConstant graphName = new LogicalConstant("graphName", "Graph1");
		
		expr.addOperand( path );
		expr.addOperand( graphName );
		
		return expr;
	}

}
