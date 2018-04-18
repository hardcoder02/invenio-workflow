package invenio.op.core;

import java.util.List;

public interface DefaultOperator extends Operator {
	List<String> getInputParameterNames();
	int getInputParameterCount();
	InputParameterDef getInputParameterDef(String name);
	InputParameterDef getInputParameter(int index);
	void setInputParameter(String name, Object value);
	void setInputParameter(int index, Object value);
	
	OutputParameterDef getOutputParameterDef();
	
	
}
