package invenio.operator.comparison;

import invenio.data.InvenioVertex;

public interface VertexTransformationFunction {
	InvenioVertex createVertex(InvenioVertex gVertex, InvenioVertex hVertex);
}
