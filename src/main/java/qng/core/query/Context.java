package qng.core.query;

// TODO: add scopes
public interface Context {
	Object getVariable(String key);
	Object setVariable(String key, Object value);
	
	String getParam(String name);
	boolean hasParam(String name);
	String putParam(String name, String value);
	String removeParam(String name);
}
