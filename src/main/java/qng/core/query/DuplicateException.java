package qng.core.query;

import qng.client.QueryException;

public class DuplicateException extends QueryException {

	public DuplicateException() {
	}

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException(Throwable cause) {
		super(cause);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

}
