package invenio.wf;

import java.util.Arrays;

public abstract class BaseNodeItem<R> extends BaseItem implements NodeItem {
	private final Class[] inputDescriptor;
	private final Object[] input;
	private boolean isSuspended = false;
	private boolean isPreviouslyProcessed = false;

	public BaseNodeItem() {
		inputDescriptor = new Class[0];
		input = new Object[0];
	}
	
	public BaseNodeItem(Class[] inputDescriptor) {
		this.inputDescriptor = Arrays.copyOf(inputDescriptor, inputDescriptor.length);
		this.input = new Object[inputDescriptor.length];
	}
	
	@Override
	public int getDeclaredNumberOfInputs() {
		return inputDescriptor.length;
	}

	@Override
	public Class getDeclaredInputType(int index) {
		return inputDescriptor[index];
	}

	@Override
	public void clearInputs() {
		Arrays.fill(input, null);
	}

	@Override
	public void setInput(int index, Object value) {
		input[index] = value;
	}

	@Override
	public Object getInput(int index) {
		return input[index];
	}
	
	@Override
	public Object[] getInputs() {
		return Arrays.copyOf(input, input.length);
	}

	protected void suspend() {
		setSuspended(true);
	}
	
	public boolean resume() {
		setSuspended(false);
		return processResume();
	}
	
	protected boolean processResume() {
		return true;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	protected void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
	
	public boolean isPreviouslyProcessed() {
		return isPreviouslyProcessed;
	}

	public void setPreviouslyProcessed(boolean isPreviouslyProcessed) {
		this.isPreviouslyProcessed = isPreviouslyProcessed;
	}
}
