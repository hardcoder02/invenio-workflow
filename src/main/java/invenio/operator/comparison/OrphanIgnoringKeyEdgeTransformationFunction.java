package invenio.operator.comparison;

import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;
import invenio.operator.Pair;

import java.util.Map;

public class OrphanIgnoringKeyEdgeTransformationFunction implements
		EdgeTransformationFunction {
	
	// this actually should be called "key", but Constants.KEY_DATA = "key" already,
	// instead of Constants.KEY_DATA = "key_attribute", because it corresponds
	// to getKeyAttribute()
	//
	// !!! SEE GiaGraphConverter, SimpleKeyEdgeTransformationFunction, same problem
	public static final String DEFAULT_KEY_ATTRIBUTE = "key_value";
	
	public InvenioEdge createEdge( Map<String, InvenioVertex> vertexMap, InvenioEdge gEdge, InvenioEdge hEdge) {
		
		String key = Util.getElementKey(hEdge, gEdge);
		Pair<AlignedPair<InvenioVertex>> pair = Util.alignAndValidateEdgePair(gEdge, hEdge);
		
		String vKey1 = Util.getElementKey(pair.getElement1().getElement1(), pair.getElement1().getElement2());
		String vKey2 = Util.getElementKey(pair.getElement2().getElement1(), pair.getElement2().getElement2());
		
		if ( !vertexMap.containsKey( vKey1 )
				|| !vertexMap.containsKey( vKey2) )
			return null;
		
		InvenioEdge edge = new InvenioEdge( vertexMap.get(vKey1), vertexMap.get(vKey2) );
		edge.setKeyAttribute(DEFAULT_KEY_ATTRIBUTE);
		edge.setKey( key );
		edge.setName( key );
		edge.setInfoLabel( key );
		return edge;
	}

}
