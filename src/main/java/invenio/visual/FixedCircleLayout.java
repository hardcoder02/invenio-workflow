package invenio.visual;

import invenio.UserSession;

import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import prefuse.action.layout.CircleLayout;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;

/**
 * Layout action that uniformly positions visual items along a circle.
 */
public class FixedCircleLayout extends CircleLayout {
    
    protected double m_radius; // radius of the circle layout
    
    /**
     * Create a CircleLayout; the radius of the circle layout will be computed
     * automatically based on the display size.
     * @param group the data group to layout
     */
    public FixedCircleLayout(String group) {
        super(group);
    }
    
    /**
     * Create a CircleLayout; use the specified radius for the the circle layout,
     * regardless of the display size.
     * @param group the data group to layout
     * @param radius the radius of the circle layout.
     */
    public FixedCircleLayout(String group, double radius) {
        super(group);
        m_radius = radius;
    }
    
    /**
     * Return the radius of the layout circle.
     * @return the circle radius
     */
    public double getRadius() {
        return m_radius;
    }

    /**
     * Set the radius of the layout circle.
     * @param radius the circle radius to use
     */
    public void setRadius(double radius) {
        m_radius = radius;
    }
    
    /**
     * @see prefuse.action.Action#run(double)
     */
    public void run(double frac) {
        TupleSet ts = m_vis.getGroup(m_group); 
        
        int nn = ts.getTupleCount();
//        int nn = -1;
//        if(UserSession.getInstance() != null)
//        	nn = UserSession.getInstance().getVisualGraphSession(m_group).getGraphSession().getGraph().numVertices();
        
        Rectangle2D r = getLayoutBounds();
        double height = r.getHeight();
        double width = r.getWidth();
        double cx = r.getCenterX();
        double cy = r.getCenterY();

        double radius = m_radius;
        if (radius <= 0) {
            radius = 0.45 * (height < width ? height : width);
        }

        Iterator items = ts.tuples();
        for (int i=0; items.hasNext(); i++) {
            VisualItem n = (VisualItem)items.next();
            double angle = (2*Math.PI*i) / nn;
            double x = Math.cos(angle)*radius + cx;
            double y = Math.sin(angle)*radius + cy;
            setX(n, null, x);
            setY(n, null, y);
        }
    }

} // end of class CircleLayout
