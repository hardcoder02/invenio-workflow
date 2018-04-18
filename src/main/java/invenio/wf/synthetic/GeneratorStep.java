package invenio.wf.synthetic;

import invenio.data.InvenioGraph;

public abstract class GeneratorStep {
	protected GeneratorStep next; 
	
	public GeneratorStep() {}
	public GeneratorStep( GeneratorStep next ) {
		this.next = next;
	}
	
	public void processGraph(InvenioGraph graph) throws Exception {
		performProcessing(graph);
		
		if ( next != null )
			next.processGraph(graph);
	}
	
	abstract protected void performProcessing(InvenioGraph graph) throws Exception;
	
	public GeneratorStep getNext() {
		return next;
	}

	public void setNext(GeneratorStep next) {
		this.next = next;
	}

}
