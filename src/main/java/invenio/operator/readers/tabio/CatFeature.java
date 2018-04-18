package invenio.operator.readers.tabio;

import invenio.data.InvenioElement;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uci.ics.jung.utils.UserData;

import prefuse.data.Table;
import prefuse.data.Tuple;

/**
 * Represents the 'cat' feature declaration.
 * 
 * The value is stored in the value field, while the data hash keeps track of
 * (value, probability) pairs.
 * 
 */
public class CatFeature extends Feature {
	HashMap<String, Float> data = new HashMap<String, Float>();
	ArrayList<String> dataIDOrder = new ArrayList<String>();
	float prob = 0.0f;
	String value = ""; //default value of the feature
	
	public CatFeature() {}
	
	public CatFeature(String id, String[] cats) {
		this.id = id;
		
		for (String c : cats) {
			dataIDOrder.add(c);
		}
	}
	
	/**
	 * @see Feature#getProb()
	 */
	@Override
	public float getProb() {
		return data.get(value);
	}
	
	public float getProb(String value) {
		if (data.containsKey(value)) {
			return data.get(value);
		} else {
			return -1.0f;
		}
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	/**
	 * @see Feature#addColumn(Table)
	 */
	@Override
	public void addColumn(Table t) {
		t.addColumn(this.id, HashMap.class);
	}

	/**
	 * @see Feature#addToGraph(Tuple, String)
	 */
	@Override
	public void addToGraph(InvenioElement t, String data) {
		String[] tokens = data.split(":");
		HashMap<String, Float> values = new HashMap<String, Float>();
		
		if (tokens.length > 1) {
			//set probabilities
			String[] probs =
				tokens[1].substring(tokens[1].indexOf("=") + 1).split(",");
//			String[] probs = tokens; //should change to adhere to tabio specs
			
			assert(probs.length == this.dataIDOrder.size());
			
			for (int i = 0; i < probs.length; i++) {
				values.put(dataIDOrder.get(i), Float.parseFloat(probs[i]));
			}
			t.addUserDatum(this.id, values, UserData.SHARED);
		} else {
			t.addUserDatum(this.id, this.data, UserData.SHARED);
		}
	}

}
