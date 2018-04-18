package qng.core.query;


public abstract class AbstractElement implements CompiledElement {
	private final String id;
	private final String name;
	
	public AbstractElement(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

}
