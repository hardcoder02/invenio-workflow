package invenio.op.core;

public interface ParameterDef {
	String getName();
	Class getType();
	boolean isCollection();
	boolean isUnique();
	boolean isOrdered();
	boolean isMap();
}
