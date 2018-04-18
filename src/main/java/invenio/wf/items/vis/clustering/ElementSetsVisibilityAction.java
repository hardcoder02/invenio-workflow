package invenio.wf.items.vis.clustering;

import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.wf.item.visual.VisualUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import prefuse.Constants;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.VisibilityAction;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class ElementSetsVisibilityAction extends VisibilityAction {
	private InvenioElementSetMembership membership = new InvenioElementSetMembership();
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
    public ElementSetsVisibilityAction(String group)
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
        if ( membership.hasElement(item) )
        	return true;
        else
        	return false;
	}

	public InvenioElementSetMembership getMembership() {
		return membership;
	}

	public void setMembership(InvenioElementSetMembership membership) {
		this.membership = membership;
	}
    
    public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
 
} // end of class DataColorAction
