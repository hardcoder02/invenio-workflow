package qng.core.query;

import java.util.HashMap;
import java.util.Map;

public class DefaultContext implements Context {
	private final Map<String, Object> variables = new HashMap<String, Object>();
	private final Map<String, String> params = new HashMap<String, String>();

	@Override
	public Object getVariable(String key) {
		return variables.get(key);
	}

	@Override
	public Object setVariable(String key, Object value) {
		return variables.put(key, value);
	}

	@Override
	public String getParam(String name) {
		return params.get(name);
	}

	@Override
	public boolean hasParam(String name) {
		return params.containsKey(name);
	}

	@Override
	public String putParam(String name, String value) {
		return params.put(name, value);
	}

	@Override
	public String removeParam(String name) {
		return params.remove(name);
	}

	
}
