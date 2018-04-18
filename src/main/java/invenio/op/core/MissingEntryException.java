package invenio.op.core;

public class MissingEntryException extends ConfigurationException {

	public MissingEntryException() {
	}

	public MissingEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingEntryException(String message) {
		super(message);
	}

	public MissingEntryException(Throwable cause) {
		super(cause);
	}

}
