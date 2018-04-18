package invenio.wf.item.visual;

import invenio.data.InvenioElement;
import invenio.data.bridge.BridgeTuple;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.MissingElementException;
import prefuse.data.Tuple;
import prefuse.visual.VisualItem;

public class VisualUtils {
    public static String getText(VisualItem item, String dataField) {
        String s = null;
        if ( item.canGetString(dataField) ) {
            return item.getString(dataField);            
        }
        return s;
    }
    
    public static InvenioElement getInvenioElement(VisualItem item) {
    	if (item instanceof BridgeTuple) {
    		return ((BridgeTuple)item).getElement();
    	}
    	return null;
    }
    
    public static InvenioElement getInvenioElement(Tuple t) {
    	if (t instanceof BridgeTuple) {
    		return ((BridgeTuple)t).getElement();
    	}
    	return null;
    }
    
    public static Object getKey(VisualItem item) {
    	InvenioElement e = getInvenioElement(item);
    	if (e == null)
    		return "";
    	return e.getKey();
    }
    
    public static String getAttribute(VisualItem item, String attName) {
    	InvenioElement e = getInvenioElement(item);
    	if (e == null)
    		return "";
    	ComparableFeatureContainer c = getFeatureContainer(e);
    	if (c == null)
    		return "";
    	try { 
    		Feature f = c.getFeature(attName);
    		return (f != null) ? f.toString() : "";
    	}
    	catch (MissingElementException ex) {
    		return "";
    	}
    }
    
	// TODO: extract to utils class (currently in operator utils)
	public static ComparableFeatureContainer getFeatureContainer(InvenioElement e) {
		return (ComparableFeatureContainer) e.getInvenioDatum(Constants.CONTAINER_ATTRIBUTE);
	}
}
