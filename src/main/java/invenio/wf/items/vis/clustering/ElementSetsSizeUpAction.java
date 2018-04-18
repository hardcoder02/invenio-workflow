package invenio.wf.items.vis.clustering;

import prefuse.Constants;
import prefuse.action.assignment.SizeAction;
import prefuse.visual.VisualItem;

public class ElementSetsSizeUpAction extends SizeAction {
	private InvenioElementSetMembership membership = new InvenioElementSetMembership();
    

    public ElementSetsSizeUpAction(String group, double size) {
        super(group, size);
    }
	
	@Override
	public double getSize(VisualItem item) {
		boolean selected = membership.hasElement(item);
        if (selected)
        	return m_defaultSize * 2;
        else
        	return m_defaultSize;
	}

	public InvenioElementSetMembership getMembership() {
		return membership;
	}

	public void setMembership(InvenioElementSetMembership membership) {
		this.membership = membership;
	}
    
}