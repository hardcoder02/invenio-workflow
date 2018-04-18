package invenio.operator;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOperator implements Operator {
	protected Map<String, Object> attributeMap = new HashMap<String, Object>();
	protected Map<String, Object> parameterMap = new HashMap<String, Object>();
	protected Map<String, Object> resultMap;

	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

}
