package invenio.operator.ui.queries;

public class PresetQueries {
	public static final String QUERY_TEMPLATE = "select [* / col / func(col) as colName]\n"
		+ "from type [graph / element / node / edge / attribute] as col\n"
		+ "tab reader \"C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.NODE.dolphin.wvrn.tab\" \"C:/Workspace/Eclipse/Invenio/DolphinData/set-20110308/dolphin-location/dolphin.UNDIRECTED.seenwith.tab\"\n"
		+ "where func(col)\n"
		+ "merge (distinct) by (col / func(col) as colName])\n"
		+ "split by col\n"
		+ "group by (col / func(col) as colName])\n"
		+ ";";
}
