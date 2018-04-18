package invenio.wf.items.vis.clustering;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioVertex;
import invenio.wf.item.visual.VisualUtils;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import prefuse.action.layout.Layout;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;

public class ClusterCircleLayout extends Layout {
    
    protected double m_radius; // radius of the circle layout
    protected AllClusters clusters;
    
    /**
     * Create a CircleLayout; the radius of the circle layout will be computed
     * automatically based on the display size.
     * @param group the data group to layout
     */
    public ClusterCircleLayout(String group) {
        super(group);
    }
    
    /**
     * Create a CircleLayout; use the specified radius for the the circle layout,
     * regardless of the display size.
     * @param group the data group to layout
     * @param radius the radius of the circle layout.
     */
    public ClusterCircleLayout(String group, double radius) {
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
    
    public void setGroupingResult(GroupingAlgorithmResult result) {
    	this.clusters = new AllClusters(result);
    }
    
    /**
     * @see prefuse.action.Action#run(double)
     */
    public void run(double frac) {
    	// set-up cluster circles
    	clusters.resetClustersVis();
    	int nnClus = clusters.size;
    	
        Rectangle2D rClus = getLayoutBounds();
        double heightClus = rClus.getHeight();
        double widthClus = rClus.getWidth();
        double cxClus = rClus.getCenterX();
        double cyClus = rClus.getCenterY();

        double radius = m_radius;
        if (radius <= 0) {
            radius = 0.45 * (heightClus < widthClus ? heightClus : widthClus);
        }
        
        int i = 0;
        for (Cluster cl : clusters.clusterList) {
            double angleClus = (2 * Math.PI*i) / nnClus;
            cl.centerX = Math.cos(angleClus)*radius + cxClus;
            cl.centerY = Math.sin(angleClus)*radius + cyClus;
            cl.radius = (Math.PI * radius * cl.size() ) / (2 * nnClus);
            
            i+= cl.size();
        }
    	
    	// draw individual tuples
        positionItems();
    }
    
    public void positionItems() {
        TupleSet ts = m_vis.getGroup(m_group); 
        
//        int nn = ts.getTupleCount();
        
//        Rectangle2D r = getLayoutBounds();
//        double height = r.getHeight();
//        double width = r.getWidth();
//        double cx = r.getCenterX();
//        double cy = r.getCenterY();
//
//        double radius = m_radius;
//        if (radius <= 0) {
//            radius = 0.45 * (height < width ? height : width);
//        }

        Iterator items = ts.tuples();
        for (int i=0; items.hasNext(); i++) {
            VisualItem n = (VisualItem)items.next();
            InvenioElement e = VisualUtils.getInvenioElement(n);
            if (e == null || e instanceof InvenioEdge)
            	continue;
            Cluster cluster = clusters.getCluster(e);
            
            double angle = (2 * Math.PI*cluster.lastIndex) / cluster.size();
            cluster.lastIndex++;
            double x = Math.cos(angle)*cluster.radius + cluster.centerX;
            double y = Math.sin(angle)*cluster.radius + cluster.centerY;
            setX(n, null, x);
            setY(n, null, y);
        }
    }
    
    private static class AllClusters {
        private Map<InvenioElement, Cluster> elementToCluster;
        private List<Cluster> clusterList;
        private int size = 0;
        
        private AllClusters(GroupingAlgorithmResult result) {
        	clusterList = new ArrayList<Cluster>();
        	elementToCluster = new HashMap<InvenioElement, Cluster>();
        	
        	for (InvenioElementSet set : result.getGroupElementSet().getSets() ) {
        		Cluster cluster = new Cluster();
        		cluster.set = set.getVertexSet();
        		clusterList.add(cluster);
        		
        		Iterator<InvenioVertex> iter = cluster.set.iterator(); 
        		while (iter.hasNext()) {
        			InvenioElement e = iter.next();
        			
        			elementToCluster.put(e,  cluster);
        			size++;
        		}
        	}
        }
        
        private void resetClustersVis() {
        	for (Cluster cluster : clusterList) {
        		cluster.resetVis();
        	}
        }
        
        private Cluster getCluster(InvenioElement e) {
        	return elementToCluster.get(e);
        }
    }
    
    private static class Cluster {
    	private int lastIndex = 0;
    	private Set<InvenioVertex> set;
    	private double radius = 0;
    	private double centerX, centerY;
    	
    	private void resetVis() {
    		lastIndex = 0;
    		radius = 0;
    		centerX = 0;
    		centerY = 0;
    	}
    	
    	private int size() {
    		return set.size();
    	}
    }

}
