package invenio.visual;

import java.util.logging.Logger;

import prefuse.action.assignment.VisibilityAction;
import prefuse.visual.VisualItem;

public class RandomVisibilityAction extends VisibilityAction {
	private String field;
	
//	public RandomVisibilityAction() {
//	}

	public RandomVisibilityAction(String group) {
		super(group);
	}
	
	public RandomVisibilityAction(String group, String field) {
		super(group);
		this.field = field;
	}

//	public RandomVisibilityAction(String group, boolean visible) {
//		super(group, visible);
//	}

	@Override
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
        
        String label = getText(item);
        if (label == null || label.equals("EA"))
        	return true;
        else
        	return false;
        
	}
	
    /**
     * Returns the text to draw. Subclasses can override this class to
     * perform custom text selection.
     * @param item the item to represent as a <code>String</code>
     * @return a <code>String</code> to draw
     */
    protected String getText(VisualItem item) {
        String s = null;
        if ( field != null && item.canGetString(field) ) {
            return item.getString(field);            
        }
        return s;
    }
}
