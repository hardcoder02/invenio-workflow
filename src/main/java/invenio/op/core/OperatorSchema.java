package invenio.op.core;

import java.util.List;

public interface OperatorSchema {
	List<String> getInputParameterNames();
	int getInputParameterCount();
	InputParameterDef getInputParameterDef(String name);
	InputParameterDef getInputParameter(int index);
	
	OutputParameterDef getOutputParameterDef();
}
