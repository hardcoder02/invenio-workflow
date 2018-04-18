package invenio.operator.readers.tabio;

import invenio.data.InvenioElement;
import prefuse.data.Table;
import prefuse.data.Tuple;

/**
 * Represents a feature object.
 */
public abstract class Feature {
	protected String id;
	
	/**
	 * Returns the value of the feature.
	 * 
	 * @return
	 */
	public abstract Object getValue();
	
	/**
	 * Returns the objectid
	 * 
	 * @return
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Returns the probability of the feature.
	 * 
	 * @return
	 */
	public abstract float getProb();
	
	/**
	 * Adds this feature as a data column to a Prefuse table (such as
	 * a GraphTable).
	 * 
	 * @param t
	 */
	public abstract void addColumn(Table t);
	
	/**
	 * Parses data and sets the corresponding data column backing the tuple t.
	 * 
	 * @param t a Tuple (a Node or Edge for example)
	 * @param data This is assumed to be a 	featurevalue as described in
	 *   TabDelimIO.txt.
	 */
	public abstract void addToGraph(InvenioElement e, String data);
}
