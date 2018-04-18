package qng.core.query.io;

import java.util.StringTokenizer;

public class LogicalEnumBean {
	public final static String DELIMITER = ":";
	public final static String ENUM_CODE = "enum";
	
	private String enumClass;
	private String enumValue;
	
	public String getEnumClass() {
		return enumClass;
	}
	public void setEnumClass(String enumClass) {
		this.enumClass = enumClass;
	}
	public String getEnumValue() {
		return enumValue;
	}
	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
	
	public String encode() {
		return toString();
	}
	
	public static boolean isEnum(String code) {
		if (code.startsWith(ENUM_CODE))
			return true;
		else
			return false;
	}
	
	public void decode(String code) throws IllegalArgumentException {
		StringTokenizer t = new StringTokenizer(code, DELIMITER);
		
		if (t.countTokens() < 2)
			throw new IllegalArgumentException("Illegal code: " + code);
		
		String token = t.nextToken();
		if ( !token.equalsIgnoreCase( ENUM_CODE ))
			throw new IllegalArgumentException("Illegal code: " + code);
		
		setEnumClass( t.nextToken() );
		setEnumValue( t.nextToken() );
	}
	
	public String toString() {
		return ENUM_CODE + DELIMITER + getEnumClass() + DELIMITER + getEnumValue();
	}
}
