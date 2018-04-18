package invenio.op.core;

public class ParameterDefImpl implements InputParameterDef, OutputParameterDef {
	private String name;
	private Class type;
	private boolean isCollection;
	private boolean isUnique;
	private boolean isOrdered;
	private boolean isMap;
	private boolean isRequired;

	
	//------------------------------- getters ------------------------//
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class getType() {
		return type;
	}

	@Override
	public boolean isCollection() {
		return isCollection;
	}
	
	@Override
	public boolean isUnique() {
		return isUnique;
	}

	@Override
	public boolean isOrdered() {
		return isOrdered;
	}
	
	@Override
	public boolean isMap() {
		return isMap;
	}

	@Override
	public boolean isRequired() {
		return isRequired;
	}

	//------------------------------- setters ------------------------//
	public void setName(String name) {
		this.name = name;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

}
