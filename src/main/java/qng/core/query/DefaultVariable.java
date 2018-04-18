package qng.core.query;

public class DefaultVariable extends AbstractElement implements CompiledVariable {
	private Class type;
	
	public DefaultVariable(String id, String name, Class type) {
		super(id, name);
		this.type = type;
	}
	
	@Override
	public Class getType() {
		return type;
	}

	public String toString() {
		return "Compiled variable: " + getName() + "=" + type.getName();
	}
}
