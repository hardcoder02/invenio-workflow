package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * 
 * select mpv(location)
 * from g1.NODE
 * where dolphin_name = 'OCEANIA'
 * 
 * @author ddimitrov
 *
 */
public class SimpleSelectByIdQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE = "dolphin.UNDIRECTED.seenwith.tab";
	
	public static LogicalExpression getQuery() {
		return createSelect();
	}
	
	public static LogicalExpression createSelect() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createWhere() );
		
		//select.addOperand( new LogicalConstant("select1", "*"));
		select.addOperand( new LogicalConstant("select1", createSelectExpression()) );
		select.addOperand( new LogicalConstant("select1alias", "mpv(location)"));
		
		return select;
	}
	
	public static LogicalExpression createSelectExpression() {
		
		LogicalOperator mpv = new LogicalOperatorImpl("mpv");
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", "location") );
		mpv.addOperand(path);
		
		return mpv;
	}

	public static LogicalExpression createWhere() {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom() );
		where.addOperand( new LogicalConstant("clause", createWhereClause()) );
		
		return where;
	}
	
	public static LogicalExpression createWhereClause() {
		LogicalOperator eq = new LogicalOperatorImpl("equals");
		
		eq.addOperand(new LogicalConstant("left", "OCEANIA"));
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", "dolphin_name") );
		eq.addOperand(path);
		
		return eq;
	}
	
	
	public static LogicalExpression createFrom() {
		LogicalOperator from = new LogicalOperatorImpl("from");
		
				
		from.addOperand( createTabReader() );
		from.addOperand( new LogicalConstant("type", FromOperation.TYPE.VERTEX) );
		from.addOperand( new LogicalConstant("alias", "n"));
		
		return from;
	}
	
	public static LogicalExpression createTabReader() {
		
		LogicalOperator tabReader = new LogicalOperatorImpl("tabReader");
		tabReader.addOperand(new LogicalConstant("nodeFile", DATA_DIR + DATA_NODE ));
		tabReader.addOperand(new LogicalConstant("edgeFile", DATA_DIR + DATA_EDGE ));
		
		return tabReader;
	}
	
}
