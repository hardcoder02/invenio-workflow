package invenio.op.core;

public interface ContainerConfig {
	void addOperator(String name, Class<Operator> operatorClass) throws DuplicateEntryException;
	Class<Operator> setOperator(String name, Class<Operator> operatorClass);
	
	void addParameter(String name, String value) throws DuplicateEntryException;
	String setParameter(String name, String value);
	
	void addParameter(String opName, String paramName, String value) throws DuplicateEntryException, MissingEntryException;
	String setParameter(String opName, String paramName, String value) throws MissingEntryException;
}
