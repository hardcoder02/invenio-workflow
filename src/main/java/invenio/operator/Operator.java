package invenio.operator;

import java.util.Map;

// TODO: add descriptors to required attributes, parameters, and results
// TODO: refactor access to attributes, parameters, and results
public interface Operator {
	Map<String, Object> getAttributeMap();
	Map<String, Object> getParameterMap();
	void execute();
	Map<String, Object> getResultMap();
}
