package invenio.operator.ui.queries;

import invenio.op.operation.impl.FromOperation;
import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

/**
 * select loc, count(*)
 * from (
 * 	select *
 * 	from (
 * 		select n, mpv(n.location) as location
 * 		from g1.NODE as n )
 * 	split by N.location)
 * group by location as loc
 * 
 * @author ddimitrov
 *
 */
public class GroupByLocationQuery {
	public final static String DATA_DIR = "C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/";
	public final static String DATA_NODE = "dolphin.NODE.dolphin.wvrn.tab";
	public final static String DATA_EDGE = "dolphin.UNDIRECTED.seenwith.tab";
	
	public static LogicalExpression getQuery() {
		return createSelectOuter();
	}
	
	public static LogicalExpression createSelectOuter() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createGroupBy() );
		
		select.addOperand( new LogicalConstant("selectOuter2", createCountExpression()) );
		select.addOperand( new LogicalConstant("selectOuter2alias", "count"));
		
		return select;
	}
	
	public static LogicalExpression createCountExpression() {
		LogicalOperator count = new LogicalOperatorImpl("count");
		
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", null) );
		count.addOperand(path);
		
		return count;
	}
	
	public static LogicalExpression createGroupBy() {
		LogicalOperator groupBy = new LogicalOperatorImpl("groupBy");
		
		groupBy.addOperand( createSelectMid() );
		groupBy.addOperand( new LogicalConstant("groupBy", createLocationExpression()) );
		groupBy.addOperand( new LogicalConstant("groupByAlias", "loc"));
		
		return groupBy;
	}
	
	public static LogicalExpression createLocationExpression() {
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "location") );
		path.addOperand( new LogicalConstant("attribute", null) );
		
		return path;
	}
	
	public static LogicalExpression createSelectMid() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createSplitBy() );
		
		select.addOperand( new LogicalConstant("selectMid1", "*") );
		
		return select;
	}
	
	public static LogicalExpression createSplitBy() {
		LogicalOperator split = new LogicalOperatorImpl("split");
		
		split.addOperand( createSelectInner() );
		split.addOperand( new LogicalConstant("by", "location" ) );
		
		return split;
	}
	
	public static LogicalExpression createSelectInner() {
		LogicalOperator select = new LogicalOperatorImpl("select");
		
		select.addOperand( createFrom() );
		
		select.addOperand( new LogicalConstant("selectInner1", createMPVLocationExpression()) );
		select.addOperand( new LogicalConstant("selectInner1alias", "location"));
		
		return select;
	}
	
	public static LogicalExpression createMPVLocationExpression() {
		LogicalOperator mpv = new LogicalOperatorImpl("mpv");
		LogicalOperator path = new LogicalOperatorImpl("path");
		path.addOperand( new LogicalConstant("column", "n") );
		path.addOperand( new LogicalConstant("attribute", "location") );
		mpv.addOperand(path);
		
		return mpv;
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
