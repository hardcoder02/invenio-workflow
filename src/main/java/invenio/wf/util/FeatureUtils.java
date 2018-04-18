package invenio.wf.util;

import edu.uci.ics.jung.utils.UserData;
import invenio.data.InvenioElement;
import invenio.data.InvenioSchemaTree;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.DataMapper;
import invenio.operator.data.Feature;
import invenio.operator.data.FeatureDescriptor;
import invenio.operator.data.InvenioDataMapper;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.Schema;
import qng.client.QueryException;
import qng.core.query.Context;

public class FeatureUtils {
	
	
	//------------------------ Extracting specific Feature methods ------------------------------//
	public static CategoricalFeature getAsCategoricalFeature(
			InvenioElement invElem, String attrName ) throws DataFormatException {
		return getAsCategoricalFeature(invElem, attrName, false);
	}
	
	
	public static CategoricalFeature getAsCategoricalFeature(
			InvenioElement invElem, String attrName, boolean failOnMissing ) throws DataFormatException {
		Feature f = getFeature(invElem, attrName, failOnMissing);
		if ( ! (f instanceof CategoricalFeature) )
			throw new DataFormatException("Attribute " + attrName + " is not categorical");
		else
			return  (CategoricalFeature) f;
	}
	
	//------------------------ general Feature methods ------------------------------//
	public static boolean hasFeature(
			InvenioElement invElem, String attrName, boolean failOnMissing) throws DataFormatException {
		
		ComparableFeatureContainer c = getFeatureContainer(invElem, failOnMissing);
		
		if ( c == null)
			return false;	// failOnMissing must be false because else the code above would have failed
		
		return !c.hasFeature(attrName);
	}
	
	public static Feature<? extends FeatureDescriptor> getFeature(InvenioElement invElem, String attrName) throws DataFormatException {
		return getFeature(invElem, attrName, false);
	}
	
	public static Feature<? extends FeatureDescriptor> getFeature(
			InvenioElement invElem, String attrName, boolean failOnMissing) throws DataFormatException {
		ComparableFeatureContainer c = getFeatureContainer(invElem, failOnMissing);
		
		if ( c == null)
			return null;	// failOnMissing must be false because else the code above would have failed
		 
		return getFeature( c, attrName, failOnMissing);
	}
	
	public static Feature<? extends FeatureDescriptor> getFeature(
			ComparableFeatureContainer c, String attrName, boolean failOnMissing) throws DataFormatException {
	
		try {
			if ( !c.hasFeature(attrName)) {
				if (failOnMissing)
					throw new DataFormatException("Missing attribute " + attrName);
				else
					return null;
			}
			else
				return c.getFeature(attrName);
		} catch (MissingElementException e) {		// should not happen
			throw new DataFormatException("Missing attribute " + attrName);
		}
	}
	
	
	
	//------------------------ Feature Container methods ------------------------------//
	public static ComparableFeatureContainer setContainer(InvenioElement e, Schema schema) {
		DataMapper dm = new InvenioDataMapper(e);
		ComparableFeatureContainer c = new ComparableFeatureContainer(schema, dm);
		e.addInvenioDatum(Constants.CONTAINER_ATTRIBUTE, c, UserData.SHARED);
		return c;
	}
	
	public static boolean hasFeatureContainer(InvenioElement e) {
		return ( null != e.getInvenioDatum( Constants.CONTAINER_ATTRIBUTE ) );
	}
	
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e) throws DataFormatException {
		return (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
	}
	
	
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e, boolean failOnMissing) throws DataFormatException {
		ComparableFeatureContainer c = (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
		if (c == null && failOnMissing)
			throw new DataFormatException("Error extracting attribute: unrecognized attribute format");
		return c;
	}
	
}
