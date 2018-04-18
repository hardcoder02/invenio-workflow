package invenio.operator.data;

public class MissingElementException extends DataFormatException {

	public MissingElementException() {
	}

	public MissingElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingElementException(String message) {
		super(message);
	}

	public MissingElementException(Throwable cause) {
		super(cause);
	}

}
