package invenio.op.operation;

import java.util.List;

public interface LogicalOperation {
	String getName();
	
	void addChildOperation(LogicalOperation sub);
	LogicalOperation setChildOperation(int idx, LogicalOperation operand) throws IndexOutOfBoundsException;
	
	int getNumberOfChildren();
	
	boolean hasChildren();
	
	LogicalOperation getChildOperation(int idx) throws IndexOutOfBoundsException;
	
	List<LogicalOperation> getChildOperations();
	
	void addParameter(Object param);
	Object setParameter(int idx, Object param) throws IndexOutOfBoundsException;
	
	int getNumberOfParameters();
	
	boolean hasParameters();
	
	Object getParameter(int idx) throws IndexOutOfBoundsException;
	
	List<Object> getParameters();
}
