package invenio.operator.data;

public class IncorrectTypeException extends DataFormatException {

	public IncorrectTypeException() {
	}

	public IncorrectTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectTypeException(String message) {
		super(message);
	}

	public IncorrectTypeException(Throwable cause) {
		super(cause);
	}

}
