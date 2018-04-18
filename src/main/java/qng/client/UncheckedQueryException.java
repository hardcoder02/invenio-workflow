package qng.client;

/**
 * As a workaround for cases, when a checked exception cannot be thrown, it is wrapped into this exception.
 * 
 * @author ddimitrov
 *
 */
public class UncheckedQueryException extends RuntimeException {

	public UncheckedQueryException() {
	}

	public UncheckedQueryException(String message) {
		super(message);
	}

	public UncheckedQueryException(Throwable cause) {
		super(cause);
	}

	public UncheckedQueryException(String message, Throwable cause) {
		super(message, cause);
	}

}
