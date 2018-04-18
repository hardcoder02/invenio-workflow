package invenio.wf;

import java.util.HashMap;
import java.util.Map;



public abstract class BaseWorkflowItem<R> extends BaseNodeItem {
	private static final Map<String, Integer> keyMap = new HashMap<String, Integer>();
	
	private Object cell;
	protected final String name;
	protected final String key;
	protected final String configKey;
	protected final String resultKey;
	
	protected R lastResult;
	
	protected BaseWorkflowItem(String name, String key) {
		this.name = name;
		this.key = key;
		this.configKey = "config" + key;
		this.resultKey = "result" + key;
	}
	
	protected BaseWorkflowItem(String name, String key, Class[] inputDescriptor) {
		super(inputDescriptor);
		this.name = name;
		this.key = key;
		this.configKey = "config" + key;
		this.resultKey = "result" + key;
	}
	
	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}
	
	public String getName() {
		return name;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getConfigKey() {
		return configKey;
	}

	public String getResultKey() {
		return resultKey;
	}

	public String toString() {
		return key;
	}
	
	public abstract ItemController<R> getController();
	
	protected static String nextKey(String itemName) {
		if ( keyMap.containsKey( itemName ) ) {
				int currentKey = keyMap.get( itemName );
				keyMap.put(itemName, currentKey + 1);
				return "" + currentKey;
		}
		else {
			keyMap.put( itemName, 1);
			return "";
		}
	}

	@Override
	public boolean update() {
		Object[] inputs = getInputs();
		
		lastResult = getController().updateResult(inputs);
		return true;
	}

	@Override
	public Object getOutput() {
		return lastResult;
	}
	
	
}
