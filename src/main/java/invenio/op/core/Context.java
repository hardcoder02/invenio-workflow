package invenio.op.core;

public interface Context {

	public enum Scope {
		GLOBAL, OPERATOR
	}
	
	String getParameter(String name, Scope s);
	
	Object getAttribute(String name);
	Object setAttribute(String name);
}
