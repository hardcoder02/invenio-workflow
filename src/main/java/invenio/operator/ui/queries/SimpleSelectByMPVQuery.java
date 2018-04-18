package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select dolphin_name
 * from g1.NODE
 * where 'RCB' in mpv(location)
 * 
 * @author ddimitrov
 *
 */
public class SimpleSelectByMPVQuery {
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
		select.addOperand( new LogicalConstant("select1alias", "dolphin_name"));
		
		return select;
	}
	
	public static LogicalExpression createSelectExpression() {
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", "dolphin_name") );
		
		return path;
	}
	
	public static LogicalExpression createWhere() {
		LogicalOperator where = new LogicalOperatorImpl("where");
		
		where.addOperand( createFrom() );
		where.addOperand( new LogicalConstant("clause", createWhereClause()) );
		
		return where;
	}
	
	// 'RCB' in mpv(location)
	public static LogicalExpression createWhereClause() {
		LogicalOperator in = new LogicalOperatorImpl("in");
		
		in.addOperand(new LogicalConstant("left", "RCB"));
		
		LogicalOperator mpv = new LogicalOperatorImpl("mpv");
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", "location") );
		mpv.addOperand(path);
		
		in.addOperand( mpv );
		
		return in;
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
