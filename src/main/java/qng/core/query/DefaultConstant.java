package qng.core.query;

public class DefaultConstant extends AbstractElement implements CompiledConstant {
	private Object value;
	
	public DefaultConstant(String id, String name) {
		super(id, name);
	}
	
	public DefaultConstant(String id, String name, Object value) {
		super(id, name);
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	public String toString() {
		return "Compiled constant: " + getName() + "=" + getValue();
	}
}
