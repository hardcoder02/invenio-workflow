package invenio.op.operation.impl;

import invenio.data.InvenioElement;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.Feature;
import invenio.operator.data.FeatureDescriptor;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.StringFeature;
import qng.client.QueryException;
import qng.core.query.Context;

public class OperatorUtils {
	public static Feature<? extends FeatureDescriptor> getFeature(InvenioElement invElem, String attrName) throws QueryException {
		return getFeature(invElem, attrName, false);
	}
	
	public static Feature<? extends FeatureDescriptor> getFeature(
			InvenioElement invElem, String attrName, boolean requiredInSchema) throws QueryException {
		ComparableFeatureContainer c = getFeatureContainer(invElem);
		 
		try {
			if ( !c.hasFeature(attrName)) {
				if (requiredInSchema)
					throw new QueryException("Missing attribute " + attrName);
				else
					return null;
			}
			else
				return c.getFeature(attrName);
		} catch (MissingElementException e) {		// should not happen
			throw new QueryException("Missing attribute " + attrName);
		}
	}
	
	public static CategoricalFeature getAsCategoricalFeature(
			InvenioElement invElem, String attrName ) throws QueryException {
		return getAsCategoricalFeature(invElem, attrName, false);
	}
	
	public static CategoricalFeature getAsCategoricalFeature(
			InvenioElement invElem, String attrName, boolean requiredInSchema ) throws QueryException {
		Feature f = getFeature(invElem, attrName, requiredInSchema);
		if ( ! (f instanceof CategoricalFeature) )
			throw new QueryException("Attribute " + attrName + " is not categorical");
		else
			return  (CategoricalFeature) f;
	}
	
	public static StringFeature getAsStringFeature(
			InvenioElement invElem, String attrName ) throws QueryException {
		return getAsStringFeature(invElem, attrName, false);
	}
	
	public static StringFeature getAsStringFeature(
			InvenioElement invElem, String attrName, boolean requiredInSchema ) throws QueryException {
		Feature f = getFeature(invElem, attrName, requiredInSchema);
		if ( ! (f instanceof StringFeature) )
			throw new QueryException("Attribute " + attrName + " is not string");
		else
			return  (StringFeature) f;
	}
	
	
	
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e) throws QueryException {
		ComparableFeatureContainer c = (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
		if (c == null)
			throw new QueryException("Error extracting attribute: unrecognized attribute format");
		return c;
	}
	
	public static String retrieveContextParam(Context ctx, String paramName, String defaultValue) {
		if (ctx.hasParam(paramName))
			return ctx.getParam(paramName);
		else
			return defaultValue;
	}
	
	public static int retrieveContextParamAsInt(Context ctx, String paramName, int defaultValue) throws NumberFormatException {
		if ( ctx.hasParam( paramName ) ) {
			String pString = ctx.getParam( paramName );
			if ( pString != null )
				return Integer.parseInt( pString );
			else
				return defaultValue;
		}
		else
			return defaultValue;
	}
	
	public static boolean retrieveContextParamAsBoolean(Context ctx, String paramName, boolean defaultValue) {
		if (ctx.hasParam(paramName)) {
			String pString = ctx.getParam( paramName );
			if ( pString != null )
				return Boolean.parseBoolean( pString );
			else
				return defaultValue;
		}
		else
			return defaultValue;
	}
}
