package invenio.wf.items.vis.query;

import invenio.wf.item.visual.VisualUtils;

import java.util.logging.Logger;

import prefuse.Constants;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class TempBehaviorColorAction extends ColorAction {
	private String attrName1 = "sponging";
	private String attrName2 = "snacking";
    
    /**
     * Create a new DataColorAction
     * @param group the data group to process
     * @param dataField the data field to base size assignments on
     * @param dataType the data type to use for the data field. One of
     * {@link prefuse.Constants#NOMINAL}, {@link prefuse.Constants#ORDINAL},
     * or {@link prefuse.Constants#NUMERICAL}, for whether the data field
     * represents categories, an ordered sequence, or numerical values.
     * @param colorField the color field to assign
     */
    public TempBehaviorColorAction(String group, String colorField)
    {
        super(group, colorField);
    }
    
    // ------------------------------------------------------------------------


    
    /**
     * @see prefuse.action.assignment.ColorAction#getColor(prefuse.visual.VisualItem)
     */
    public int getColor(VisualItem item) {
        // check for any cascaded rules first
        Object o = lookup(item);
        if ( o != null ) {
            if ( o instanceof ColorAction ) {
                return ((ColorAction)o).getColor(item);
            } else if ( o instanceof Integer ) {
                return ((Integer)o).intValue();
            } else {
                Logger.getLogger(this.getClass().getName())
                    .warning("Unrecognized Object from predicate chain.");
            }
        }
        
        Boolean selected1 = getAttributeValue(item, attrName1);
        Boolean selected2 = getAttributeValue(item, attrName2);
//        if (selected == null)
//        	return ColorLib.rgb(0,0,55);	// blue
//        if (selected)
//        	return ColorLib.rgb(0,0,255);	// blue
//        else
//        	return ColorLib.rgb(211,211,211);	// light gray
        if (selected1)
        	return ColorLib.rgb(0,0,255);	// blue
        if (selected2)
        	return ColorLib.rgb(0,255,0);	// green
        else
        	return ColorLib.rgb(211,211,211);	// light gray
    }

	public String getAttributeName() {
		return attrName1;
	}

	public void setAttributeName(String attrName) {
		this.attrName1 = attrName;
	}
    
	protected Boolean getAttributeValue(VisualItem item, String attrName) {
        String s = null;
        // TODO; finish
        s = VisualUtils.getAttribute(item, attrName);
        
        if (s == null || s.trim().length() < 1)
        	return null;
        else if ("TRUE".equalsIgnoreCase(s))
        	return Boolean.TRUE;
        else
        	return Boolean.FALSE;
    }
}