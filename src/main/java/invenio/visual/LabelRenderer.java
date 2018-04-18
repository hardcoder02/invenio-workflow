package invenio.visual;

import invenio.wf.item.visual.VisualUtils;
import prefuse.visual.VisualItem;

public class LabelRenderer extends prefuse.render.LabelRenderer {
    /**
     * Create a new LabelRenderer. By default the field "label" is used
     * as the field name for looking up text, and no image is used.
     */
    public LabelRenderer() {
    }
    
    /**
     * Create a new LabelRenderer. Draws a text label using the given
     * text data field and does not draw an image.
     * @param textField the data field for the text label.
     */
    public LabelRenderer(String textField) {
        super(textField);
    }
	
	@Override
    protected String getText(VisualItem item) {
        String s = null;
        // TODO; finish
        s = VisualUtils.getAttribute(item, getTextField());
        
        if (s == null || s.trim().length() < 1)
        	s = "" + VisualUtils.getKey(item);
        return s;
//		return "     ";
    }

}
