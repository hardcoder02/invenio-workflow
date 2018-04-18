package invenio.wf.items.vis.nodelabel;

import invenio.data.InvenioVertex;
import invenio.operator.data.CategoricalFeature;

class BaseEntry {
	private String id;
	private InvenioVertex[] vertices;
	private CategoricalFeature[] features;
	
	public BaseEntry(int size) {
		this.vertices = new InvenioVertex[size];
		this.features = new CategoricalFeature[size];
	}
	
	public int size() {
		return vertices.length;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public InvenioVertex getVertex(int index) {
		return vertices[index];
	}
	
	public CategoricalFeature getFeature(int index) {
		return features[index];
	}
	
	public void setVertex(int index, InvenioVertex v) {
		vertices[index] = v;
	}
	
	public void setFeature(int index, CategoricalFeature f) {
		features[index] = f;
	}
}