package invenio.wf.items.vis.clustering;

import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.wf.item.visual.VisualUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import prefuse.data.Tuple;
import prefuse.visual.VisualItem;

public class InvenioElementSetMembership {
	private List<InvenioElementSet> listOfSets = new ArrayList<InvenioElementSet>();
	
   protected boolean hasElement(VisualItem item) {
    	InvenioElement e = VisualUtils.getInvenioElement(item);
    	if (e == null)
    		return false;
    	for (InvenioElementSet s : listOfSets) {
    		if (s.containsElement( e ))
    			return true;
    	}
    	return false;
    }
   
   protected boolean hasElement(Tuple t) {
	   	InvenioElement e = VisualUtils.getInvenioElement(t);
	   	if (e == null)
	   		return false;
	   	for (InvenioElementSet s : listOfSets) {
	   		if (s.containsElement( e ))
	   			return true;
	   	}
	   	return false;
   }
    
    public void clearSets() {
    	listOfSets.clear();
    }
    
    public boolean isEmpty() {
    	return listOfSets.isEmpty();
    }
    
    public int setsSize() {
    	return listOfSets.size();
    }
    
    public void addSet(InvenioElementSet set) {
    	listOfSets.add(set);
    }
    
    public void addSets(Collection<InvenioElementSet> sets) {
    	listOfSets.addAll(sets);
    }
}
