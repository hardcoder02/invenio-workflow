package invenio.wf.vis.actions;

import java.util.logging.Logger;

import prefuse.action.assignment.VisibilityAction;
import prefuse.visual.VisualItem;

public class LookupVisibilityAction extends VisibilityAction {
	private ElementLookup<Object> lookup = new ElementLookup<Object>();
    private boolean showAll = true;
	
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
    public LookupVisibilityAction(String group)
    {
        super(group);
    }
    
    // ------------------------------------------------------------------------


    
    /**
     * @see prefuse.action.assignment.VisibilityAction#getVisibility(VisualItem)
     */
	public boolean getVisibility(VisualItem item) {
        Object o = lookup(item);
        if ( o != null ) {
            if ( o instanceof VisibilityAction ) {
                return ((VisibilityAction)o).getVisibility(item);
            } else if ( o instanceof Boolean ) {
                return ((Boolean)o).booleanValue();
            } else {
                Logger.getLogger(this.getClass().getName())
                    .warning("Unrecognized Object from predicate chain.");
            }
        }
        
        if (showAll)
        	return true;
        if ( lookup.isEmpty() )
        	return true;
        if ( lookup.hasElement(item) )
        	return true;
        else
        	return false;
	}

	public ElementLookup<Object> getLookup() {
		return lookup;
	}

	public void setLookup(ElementLookup<Object> lookup) {
		this.lookup = lookup;
	}
    
    public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
 
} // end of class DataColorAction
