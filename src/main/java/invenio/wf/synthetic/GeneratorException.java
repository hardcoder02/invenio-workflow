package invenio.wf.synthetic;

public class GeneratorException extends RuntimeException {

	public GeneratorException() {
	}

	public GeneratorException(String ex) {
		super(ex);
	}

	public GeneratorException(Throwable ex) {
		super(ex);
	}

	public GeneratorException(String ex, Throwable t) {
		super(ex, t);
	}

}
