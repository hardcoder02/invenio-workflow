package invenio.op.parser;

import java.util.HashMap;
import java.util.Map;

public class Operators {
	private static Map<String, String> opMap = new HashMap<String, String>();
	private static Map<String, String> opMapUpper = new HashMap<String, String>();
	
	{
		opMap.put("to_graph", "toGraph");
		
		for ( Map.Entry<String, String> entry : opMap.entrySet() ) {
			opMapUpper.put( entry.getKey().toUpperCase(), entry.getValue() );
		}
	}
	
	public static String getOperatorName(String languageName) {
		return getOperatorName(languageName, false, false);
	}
	
	/*
	 * returns null if languageName is null.
	 */
	public static String getOperatorName(String languageName, boolean ignoreCase, boolean returnDefault) {
		if (languageName == null)
			return null;
		
		String opName = null;
		if ( ignoreCase )
			opName = opMapUpper.get( languageName.toUpperCase() );
		else
			opMap.get( languageName );
		
		if ( opName != null)
			return opName;
		
		// opName == null
		if ( returnDefault )
			return languageName;
		
		return opName; //null
	}
	
	
}
