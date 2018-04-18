package invenio.op.parser;

import invenio.op.operation.aggregate.impl.GroupByOperation;
import invenio.op.operation.aggregate.impl.HavingOperation;
import invenio.op.operation.aggregate.impl.MergeOperation;
import invenio.op.operation.aggregate.impl.SplitOperation;
import invenio.op.operation.impl.FromOperation;
import invenio.op.operation.impl.JoinOperation;
import invenio.op.operation.impl.OrderByOperation;
import invenio.op.operation.impl.SelectOperation;
import invenio.op.operation.impl.TabReaderOperation;
import invenio.op.operation.impl.WhereOperation;

public enum Operations {
	JOIN(JoinOperation.OP_NAME),
	SELECT(SelectOperation.OP_NAME),
	ORDER_BY(OrderByOperation.OP_NAME),
	HAVING(HavingOperation.OP_NAME),
	GROUP_BY(GroupByOperation.OP_NAME),
	MERGE_BY(MergeOperation.OP_NAME),
	SPLIT_BY(SplitOperation.OP_NAME),
	WHERE(WhereOperation.OP_NAME),
	FROM(FromOperation.OP_NAME),
	TAB_READER(TabReaderOperation.OP_NAME);
	
	
	private String name;
	
	private Operations(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
