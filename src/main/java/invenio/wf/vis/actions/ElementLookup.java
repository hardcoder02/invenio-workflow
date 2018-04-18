package invenio.wf.vis.actions;

import invenio.data.InvenioElement;
import invenio.wf.item.visual.VisualUtils;

import java.util.HashMap;
import java.util.Map;

import prefuse.data.Tuple;
import prefuse.visual.VisualItem;

// TODO: re-implement to cache slices
public class ElementLookup<E> {
	private Map<InvenioElement, E> lookupMap = new HashMap<InvenioElement, E>();
	
   protected boolean hasElement(VisualItem item) {
    	InvenioElement e = VisualUtils.getInvenioElement(item);
    	if (e == null)
    		return false;
    	return lookupMap.containsKey( e );
    }
   
   protected boolean hasElement(Tuple t) {
	   	InvenioElement e = VisualUtils.getInvenioElement(t);
	   	if (e == null)
	   		return false;
	   	return lookupMap.containsKey( e );
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
	   return lookupMap.get( e );
   }
    
    public void clear() {
    	lookupMap.clear();
    }
    
    public boolean isEmpty() {
    	return lookupMap.isEmpty();
    }
    
    public int size() {
    	return lookupMap.size();
    }
    
    public void add(InvenioElement e, E obj) {
    	lookupMap.put(e, obj);
    }

}
