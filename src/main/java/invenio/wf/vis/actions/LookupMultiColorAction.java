package invenio.wf.vis.actions;

import java.util.logging.Logger;

import prefuse.Constants;
import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class LookupMultiColorAction extends ColorAction {
	private ElementLookup<Integer> colorLookup = new ElementLookup<Integer>();
	private int defaultColor;
    
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
    public LookupMultiColorAction(String group, String colorField)
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
        
        if (colorLookup.hasElement(item))
        	return colorLookup.getObject(item);
        else
        	return defaultColor;
    }

	public ElementLookup getColorLookup() {
		return colorLookup;
	}

	public void setColorLookup(ElementLookup<Integer> colorLookup) {
		this.colorLookup = colorLookup;
	}

	public int getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(int defaultColor) {
		this.defaultColor = defaultColor;
	}
}
