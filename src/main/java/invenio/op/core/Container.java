package invenio.op.core;

import java.util.Map;

public interface Container {
	void executeOperator(String name, Map<String, Object> parameters);
}
