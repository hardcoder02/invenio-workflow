package invenio.operator.pig;

public abstract class AbstractOperator {
	private final String name;
	private final DerivedFeatureNameResolver resolver;
	
	public AbstractOperator(String name) {
		this( name, new DefaultFeatureNameResolver() );
	}
	
	public AbstractOperator(String name, DerivedFeatureNameResolver res) {
		this.name = name;
		this.resolver = res;
	}
	
	public String getName() {
		return name;
	}
	
	public DerivedFeatureNameResolver getNameResolver() {
		return resolver;
	}
}
