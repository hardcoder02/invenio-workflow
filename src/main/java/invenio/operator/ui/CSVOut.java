package invenio.operator.ui;

public class CSVOut {
	public static final String DELIMITER = ",";
	public static final String FIELD_BOUNDARY = "\"";
	public static final String ESCAPE_CHAR = "\"";
	
	private final String delimiter;
	private final String boundary;
	private final String escape;
	
	public CSVOut() {
		this(DELIMITER, FIELD_BOUNDARY, ESCAPE_CHAR);
	}
	
	public CSVOut(String delim, String boundary, String escape) {
		this.delimiter = delim;
		this.boundary = boundary;
		this.escape = escape;
	}
	
	public String writeLine(String[] fields) {
		StringBuffer sb = new StringBuffer();
		writeLine(sb, fields);
		
		return sb.toString();
	}
	
	public void writeLine(StringBuffer sb, String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if ( i > 0 )
				sb.append(delimiter);
			sb.append( writeField(fields[i]) );
		}
		sb.append("\n");
	}
	
	public String writeField(String field) {
		if (field == null)
			return "";
		
		if ( field.contains(delimiter) ) {
			if ( field.contains(boundary) ) {
				// escape
				field.replaceAll(boundary, escape+boundary);
			}
			field = boundary + field + boundary;
		}
		
		return field;
	}
}
