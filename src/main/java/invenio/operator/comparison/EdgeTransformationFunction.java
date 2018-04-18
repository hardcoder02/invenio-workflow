package invenio.operator.comparison;

import invenio.data.InvenioEdge;
import invenio.data.InvenioVertex;

import java.util.Map;

public interface EdgeTransformationFunction {
	InvenioEdge createEdge( Map<String, InvenioVertex> vertexMap, InvenioEdge gEdge, InvenioEdge hEdge);
}
