package invenio.operator.readers.tabio;

import javax.swing.text.DefaultEditorKit.CopyAction;

import edu.uci.ics.jung.utils.UserData;
import edu.uci.ics.jung.utils.UserDataContainer;
import invenio.data.InvenioElement;
import prefuse.data.Table;
import prefuse.data.Tuple;

/**
 * Represents the 'numeric' feature declaration.
 */
public class NumericFeature extends Feature {
	double data = 0.0d; //default value of the feature
	float prob = 0.0f;
	
	public NumericFeature() {}
	
	public NumericFeature(String id) {
		this.id = id;
	}
	
	public void setValue(double data) {
		this.data = data;
	}
	
	/**
	 * @see Feature#getProb()
	 */
	@Override
	public float getProb() {
		return prob;
	}

	/**
	 * @see Feature#getValue()
	 */
	@Override
	public Object getValue() {
		return new Double(data);
	}

	/**
	 * @see Feature#addColumn(Table)
	 */
	@Override
	public void addColumn(Table t) {
		t.addColumn(this.id, double.class);
	}

	/**
	 * @see Feature#addToGraph(Tuple, String)
	 */
	@Override
	public void addToGraph(InvenioElement t, String data) {
		float prob = 0.0f;
		String[] val = data.split(":");
		if (val.length > 1) {
			prob = Float.parseFloat(val[1]);
		}
		
		t.addUserDatum(this.id, Double.parseDouble(val[0]), UserData.SHARED);
		//t.setFloat("prob", prob);
	}

}
