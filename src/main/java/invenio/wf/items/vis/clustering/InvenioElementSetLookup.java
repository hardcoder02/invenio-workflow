package invenio.wf.items.vis.clustering;

import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.wf.item.visual.VisualUtils;

import java.util.ArrayList;
import java.util.List;

import prefuse.data.Tuple;
import prefuse.visual.VisualItem;

public class InvenioElementSetLookup<E> {
	private List<InvenioElementSet> listOfSets = new ArrayList<InvenioElementSet>();
	private List<E> listOfObjects = new ArrayList<E>();
	
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
   
   public E getObject(VisualItem item) {
	   InvenioElement e = VisualUtils.getInvenioElement(item);
	   	if (e == null)
	   		return null;
	   	return lookupElement(e);
   }
   
   public E getObject(Tuple t) {
	   InvenioElement e = VisualUtils.getInvenioElement(t);
	   	if (e == null)
	   		return null;
	   	return lookupElement(e);
   }
   
   private E lookupElement(InvenioElement e) {
	   int i = 0;
	   	for (InvenioElementSet s : listOfSets) {
	   		if (s.containsElement( e ))
	   			return listOfObjects.get(i);
	   		else
	   			i++;
	   	}
	   return null;
   }
    
    public void clearSets() {
    	listOfSets.clear();
    	listOfObjects.clear();
    }
    
    public boolean isEmpty() {
    	return listOfSets.isEmpty();
    }
    
    public int setsSize() {
    	return listOfSets.size();
    }
    
    public void addSet(InvenioElementSet set, E obj) {
    	listOfSets.add(set);
    	listOfObjects.add(obj);
    }
    
//    public void addSets(Collection<InvenioElementSet> sets) {
//    	listOfSets.addAll(sets);
//    }
}
