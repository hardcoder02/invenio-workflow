package invenio.wf.items.vis.clustering;

import prefuse.data.Tuple;
import prefuse.data.expression.AbstractPredicate;
import prefuse.data.expression.Predicate;

public class ElementSetsPredicate extends AbstractPredicate implements Predicate {
	private boolean showAll = true;
	private InvenioElementSetMembership membership = new InvenioElementSetMembership();
	
	public boolean getBoolean(Tuple t) {
		if (showAll)
			return true;
		return membership.hasElement(t);
	}
	
	public InvenioElementSetMembership getMembership() {
		return membership;
	}

	public void setMembership(InvenioElementSetMembership membership) {
		this.membership = membership;
	}

	public boolean getShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
	
	
}
