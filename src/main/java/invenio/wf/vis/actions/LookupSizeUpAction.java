package invenio.wf.vis.actions;

import prefuse.Constants;
import prefuse.action.assignment.SizeAction;
import prefuse.visual.VisualItem;

public class LookupSizeUpAction extends SizeAction {
	private ElementLookup<Object> lookup = new ElementLookup<Object>();

    public LookupSizeUpAction(String group, double size) {
        super(group, size);
    }
	
	@Override
	public double getSize(VisualItem item) {
		boolean selected = lookup.hasElement(item);
        if (selected)
        	return m_defaultSize * 2;
        else
        	return m_defaultSize;
	}

	public ElementLookup getLookup() {
		return lookup;
	}

	public void setLookup(ElementLookup<Object> lookup) {
		this.lookup = lookup;
	}
}
