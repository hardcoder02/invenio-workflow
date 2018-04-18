package qng.tabular;

public class TableFactory {
	public static ModifiableTable createTable() {
		// TODO: analyze performance and choose
//		return new TableImpl();
		return new TableArrayListImpl();
	}
	
	static ModifiableTuple createTuple(Table t) {
		return new TupleImpl(t);
	}
	
	static ModifiableSchema createSchema() {
		return new SchemaImpl();
	}
	
	public static AggregateModifiableTable createAggregateTable() {
		return new AggregateTableImpl();
	}
	
	static AggregateModifiableTuple createAggregateTuple(AggregateModifiableTable t) {
		return new AggregateTupleImpl(t);
	}
	
	static AggregateModifiableSchema createAggregateSchema( Schema aggregatedSchema ) {
		return new AggregateSchemaImpl( aggregatedSchema );
	}
}
