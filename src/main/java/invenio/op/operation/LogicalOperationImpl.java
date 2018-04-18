package invenio.op.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicalOperationImpl implements LogicalOperation {
	private final String name;
	private List<LogicalOperation> children = new ArrayList<LogicalOperation>();
	private List<Object> parameters = new ArrayList<Object>();
	
	
	public LogicalOperationImpl(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void addChildOperation(LogicalOperation operand) {
		children.add(operand);
	}
	
	@Override
	public LogicalOperation setChildOperation(int idx, LogicalOperation operand) {
		return children.set(idx, operand);
	}
	
	@Override
	public int getNumberOfChildren() {
		return children.size();
	}
	
	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	@Override
	public LogicalOperation getChildOperation(int idx) {
		return children.get(idx);
	}
	
	@Override
	public List<LogicalOperation> getChildOperations() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public void addParameter(Object param) {
		parameters.add(param);
		
	}

	@Override
	public Object setParameter(int idx, Object param)
			throws IndexOutOfBoundsException {
		return parameters.set(idx, param);
	}
	
	@Override
	public int getNumberOfParameters() {
		return parameters.size();
	}

	@Override
	public boolean hasParameters() {
		return !parameters.isEmpty();
	}

	@Override
	public Object getParameter(int idx) throws IndexOutOfBoundsException {
		return parameters.get(idx);
	}

	@Override
	public List<Object> getParameters() {
		return Collections.unmodifiableList(parameters);
	}
	
}
