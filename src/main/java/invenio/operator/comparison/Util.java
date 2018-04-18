package invenio.operator.comparison;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import invenio.data.InvenioEdge;
import invenio.data.InvenioElement;
import invenio.data.InvenioVertex;
import invenio.operator.Pair;

public class Util {
	public static String getElementKey(InvenioElement element) {
		if (element instanceof InvenioEdge)
			return ((InvenioEdge) element).getKey() + "";
		else if (element instanceof InvenioVertex) 
			return ((InvenioVertex) element).getKey() + "";
		
		throw new IllegalArgumentException("Unknown subclass of InvenioElement");
	}
	
	public static String getElementKey(InvenioElement element1, InvenioElement element2) {
		if (element1 == null && element2 == null)
			throw new IllegalArgumentException("Both elements cannot be null");
		
		if (element1 == null)
			return getElementKey(element2);
		if (element2 == null)
			return getElementKey(element1);
		
		String key1 = getElementKey(element1);;
		String key2 = getElementKey(element2);
		if ( !key1.equals(key2))
			throw new IllegalArgumentException("Keys do not match");
		
		return key1;	// same as key2
	}
	
	public static Map<String, InvenioElement> createElementMap( List<? extends InvenioElement> elements) {
		Map<String, InvenioElement> result = new HashMap<String, InvenioElement> (elements.size());
		
		for ( InvenioElement e : elements) {
			result.put( getElementKey(e), e);
		}
		
		return result;
	}
	
	public static Pair<AlignedPair<InvenioVertex>> 
			alignAndValidateEdgePair(InvenioEdge gEdge, InvenioEdge hEdge) {
		
		if (gEdge == null && hEdge == null)
			throw new IllegalArgumentException("Both edges cannot be null");
		
		if (gEdge == null && hEdge != null) {
			return createPair(null, hEdge.getSourceVertex(), null, hEdge.getTargetVertex());
		}
		
		if (gEdge != null && hEdge == null) {
			return createPair( gEdge.getSourceVertex(), null, gEdge.getTargetVertex(), null );
		}
		
		if ( !gEdge.getKey().equals(hEdge.getKey()) )
			throw new IllegalArgumentException("Keys do not match");
		
		if (gEdge.getSourceVertex().getKey().equals( hEdge.getSourceVertex().getKey() )
				&& gEdge.getTargetVertex().getKey().equals( hEdge.getTargetVertex().getKey() ))
				
				return createPair( gEdge.getSourceVertex(), hEdge.getSourceVertex(), gEdge.getTargetVertex(), hEdge.getTargetVertex());
		
		if (gEdge.getSourceVertex().getKey().equals( hEdge.getTargetVertex().getKey() )
				&& gEdge.getTargetVertex().getKey().equals( hEdge.getSourceVertex().getKey() ))
				
				return createPair( gEdge.getSourceVertex(), hEdge.getTargetVertex(), gEdge.getTargetVertex(), hEdge.getSourceVertex());
		
		throw new IllegalArgumentException("Edge endpoint keys do not match");
	}

	private static Pair<AlignedPair<InvenioVertex>> 
		createPair(InvenioVertex v1, InvenioVertex v2, InvenioVertex v3, InvenioVertex v4) {
		
		AlignedPair<InvenioVertex> pair1 = new AlignedPair<InvenioVertex>(v1, v2);
		AlignedPair<InvenioVertex> pair2 = new AlignedPair<InvenioVertex>(v3, v4);
		return new Pair<AlignedPair<InvenioVertex>>(pair1, pair2);
	}
	
}
