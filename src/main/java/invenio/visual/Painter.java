package invenio.visual;

import java.util.HashMap;
import java.util.Map;

import prefuse.Visualization;
import prefuse.action.Action;

public class Painter {
	private final Map<String, Action> actionMap = new HashMap<String, Action>();;
	private Visualization visualization;
	
	public void setVisualization(Visualization vis) {
		if (this.visualization != null) {
			removeAllFromVis();
		}
		
		this.visualization = vis;
		addAllToVis();
	}
	
	public void runAll() {
		if (visualization == null)
			return;
		
		for (String s : actionMap.keySet() ) {
			visualization.run(s);
		}
	}
	
	private void addAllToVis() {
		if (visualization == null)
			return;
		
		for (Map.Entry<String, Action> s : actionMap.entrySet() ) {
			visualization.putAction(s.getKey(), s.getValue());
		}
	}

	public void removeAllFromVis() {
		if (visualization == null)
			return;
		
		for (String s : actionMap.keySet() ) {
			visualization.removeAction(s);
		}
	}

	public void removeAction(String name) {
		if (visualization != null) {
			visualization.cancel(name);
		}
		actionMap.remove(name);
	}
	
	public void putAction(String name, Action action, boolean run) {
		removeAction(name);
		
		actionMap.put(name, action);
		if (visualization != null) {
			visualization.putAction(name, action);
			
			if (run)
				visualization.run(name);
		}
	}
	
	public void putAction(String name, Action action) {
		putAction(name, action, false);
	}
	
	public void runAction(String name) {
		if (visualization != null) {
			visualization.run(name);
		}
	}
	
	public Action getAction(String name) {
		return actionMap.get(name);
	}
}
