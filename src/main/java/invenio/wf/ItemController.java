package invenio.wf;

import javax.swing.JComponent;

public interface ItemController<R> {
	boolean hasConfigView();
	boolean hasResultView();
	
	JComponent getConfigView();
	JComponent getResultView();
	
	R updateResult(Object[] inputs);
}
