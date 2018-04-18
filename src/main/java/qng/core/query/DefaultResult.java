package qng.core.query;

public class DefaultResult<T> implements Result<T> {
	private T value;

	public DefaultResult() {}
	public DefaultResult(T value) {
		this.value = value;
	}
	
	@Override
	public T getResultValue() {
		return value;
	}

}
