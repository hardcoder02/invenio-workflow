package invenio.operator.comparison;

import invenio.data.InvenioVertex;

public class SimpleKeyVertexTransformationFunction implements
		VertexTransformationFunction {
	// this actually should be called "key", but Constants.KEY_DATA = "key" already,
	// instead of Constants.KEY_DATA = "key_attribute", because it corresponds
	// to getKeyAttribute()
	//
	// !!! SEE GiaGraphConverter, SimpleKeyEdgeTransformationFunction, same problem
	public static final String DEFAULT_KEY_ATTRIBUTE = "key_value";

	public InvenioVertex createVertex(InvenioVertex gVertex,
			InvenioVertex hVertex) {
		
		InvenioVertex v = new InvenioVertex();
		String key = Util.getElementKey(gVertex, hVertex);
		v.setKeyAttribute(DEFAULT_KEY_ATTRIBUTE);
		v.setKey( key );
		v.setName( key );
		v.setInfoLabel( key );
//		v.importUserData(gVertex);
//		v.importInvenioData(gVertex);
		return v;
	}

}
