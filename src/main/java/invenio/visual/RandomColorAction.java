package invenio.visual;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import prefuse.Constants;
import prefuse.action.assignment.ColorAction;
import prefuse.data.tuple.TupleSet;
import prefuse.util.ColorLib;
import prefuse.util.ColorMap;
import prefuse.util.DataLib;
import prefuse.util.MathLib;
import prefuse.visual.VisualItem;

public class RandomColorAction extends ColorAction {
	private String m_dataField;
    private int    m_type;

    
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
    public RandomColorAction(String group, String dataField, 
                             int dataType, String colorField)
    {
        super(group, colorField);
        this.m_dataField = dataField;
        this.m_type = dataType;

    }
    
    /**
     * Create a new DataColorAction
     * @param group the data group to process
     * @param dataField the data field to base size assignments on
     * @param dataType the data type to use for the data field. One of
     * {@link prefuse.Constants#NOMINAL}, {@link prefuse.Constants#ORDINAL},
     * or {@link prefuse.Constants#NUMERICAL}, for whether the data field
     * represents categories, an ordered sequence, or numerical values.
     * @param colorField the color field to assign
     * @param palette the color palette to use. See
     * {@link prefuse.util.ColorLib} for color palette generators.
     */
    public RandomColorAction(String group, String dataField, 
            int dataType, String colorField, int[] palette)
    {
        super(group, colorField);
        this.m_dataField = dataField;
        this.m_type = dataType;

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
        
        String label = getText(item);
        if (label == null || label.equals("null"))
        	return ColorLib.rgb(190,190,255);
        else
        	return ColorLib.rgb(255,180,180);
    }
    
    protected String getText(VisualItem item) {
        String s = null;
        if ( item.canGetString(m_dataField) ) {
            return item.getString(m_dataField);            
        }
        return s;
    }    
    
}