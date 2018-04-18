package invenio.wf.synthetic;

import java.util.List;
import java.util.Set;

import invenio.data.InvenioElement;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.Constants;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.StringFeature;
import invenio.wf.util.FeatureUtils;

public class SequentialIdGenerator extends GeneratorStep {

	private int startId = 0;
	private int increment = 1;
	private boolean processVertices = true;
	private boolean processEdges = true;
	
	public SequentialIdGenerator() {}
	public SequentialIdGenerator(GeneratorStep next) { super(next); }

	@Override
	protected void performProcessing(InvenioGraph graph) throws Exception {
		System.out.println("Starting id generation...");
		long startTime = System.currentTimeMillis();
		if ( processVertices )
			processElements( graph.getVertices() );
		if ( processEdges )
			processElements( graph.getEdges() );
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Id generation completed in [" + duration + "] msec.");
	}
	
	protected void processElements(List<? extends InvenioElement> elements) throws DataFormatException {
		long nextId = startId;
		
		for ( InvenioElement e : elements ) {
//			InvenioElement e = (InvenioElement) o;
			
			String id = "" + nextId;
			e.setKeyAttribute(Constants.DEFAULT_KEY_ATTRIBUTE);
			e.setKey(id);
			e.setName(id);
			e.setInfoLabel(id);
			
//			ComparableFeatureContainer c = FeatureUtils.getFeatureContainer(e, true);
//			
			//TODO: finish
//			Feature feature = null;
//			if (c.hasFeature( "id" ))
//				feature = c.getFeature( "id" );
//			else
//				feature = c.instantiateFeature("id");
//			((StringFeature)feature).setValue(id);

			nextId += increment;
		}
	}

	public int getStartId() {
		return startId;
	}
	
	public void setStartId(int startId) {
		this.startId = startId;
	}
	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		if (increment == 0)
			throw new IllegalArgumentException("Increment must be non-zero to avoid duplicate ids: " + increment);
		this.increment = increment;
	}
	public boolean isProcessVertices() {
		return processVertices;
	}
	public void setProcessVertices(boolean processVertices) {
		this.processVertices = processVertices;
	}
	public boolean isProcessEdges() {
		return processEdges;
	}
	public void setProcessEdges(boolean processEdges) {
		this.processEdges = processEdges;
	}
	
	
}
