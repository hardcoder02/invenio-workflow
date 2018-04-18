package invenio.wf;

public interface NodeItem<R> extends Item {
	boolean isInternallyUpdateable();
	boolean isExternallyUpdateable();
	
	int getDeclaredNumberOfInputs();
	Class getDeclaredInputType(int index);
	void clearInputs();
	void setInput(int index, Object value);
	Object getInput(int index);
	Object[] getInputs();
	
	boolean update();
	R getOutput();
	
	boolean isSuspended();
	boolean resume();
	boolean isPreviouslyProcessed();
	void setPreviouslyProcessed(boolean processed);
}
