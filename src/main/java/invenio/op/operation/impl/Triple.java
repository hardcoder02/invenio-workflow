package invenio.op.operation.impl;

public class Triple<U, V, I> {
	public U first;
	public V second;
	public I third;
	
	public Triple() { }
	
	public Triple(U first, V second, I third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
}
