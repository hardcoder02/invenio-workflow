package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select count(*)
 * from g1.NODE
 * 
 * @author ddimitrov
 *
 */
public class GroupCountQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE = "dolphin.UNDIRECTED.seenwith.tab";
	
	public static LogicalExpression getQuery() {
		return createSelect();
	}
	
	public static LogicalExpression createSelect() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom() );
		
		select.addOperand( new LogicalConstant("select2", createCountExpression()) );
		select.addOperand( new LogicalConstant("select2alias", "count"));
		
		return select;
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
	
	public static LogicalExpression createCountExpression() {
		LogicalOperator count = new LogicalOperatorImpl("count");
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", null) );
		count.addOperand(path);
		
		return count;
	}
}
