package invenio.operator.data;

public class DuplicateElementException extends DataFormatException {
	
	public DuplicateElementException() {
	}
	
	public DuplicateElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateElementException(String message) {
		super(message);
	}

	public DuplicateElementException(Throwable cause) {
		super(cause);
	}

}
