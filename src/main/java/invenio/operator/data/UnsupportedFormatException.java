package invenio.operator.data;

public class UnsupportedFormatException extends DataFormatException {

	public UnsupportedFormatException() {
	}

	public UnsupportedFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedFormatException(String message) {
		super(message);
	}

	public UnsupportedFormatException(Throwable cause) {
		super(cause);
	}

}
