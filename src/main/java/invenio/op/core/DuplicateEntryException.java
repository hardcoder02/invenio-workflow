package invenio.op.core;

public class DuplicateEntryException extends ConfigurationException {

	public DuplicateEntryException() {
	}

	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(Throwable cause) {
		super(cause);
	}

}
