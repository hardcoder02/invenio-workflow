package qng.core.query;

import qng.client.QueryException;

public class NotExistsException extends QueryException {

	public NotExistsException() {
	}

	public NotExistsException(String message) {
		super(message);
	}

	public NotExistsException(Throwable cause) {
		super(cause);
	}

	public NotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
