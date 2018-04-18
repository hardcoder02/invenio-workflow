package invenio.operator.io.tab;

import invenio.operator.data.ArrayFeatureDescriptor;
import invenio.operator.data.ArrayFeatureDescriptorImpl;
import invenio.operator.data.CategoricalFeatureDescriptor;
import invenio.operator.data.CategoricalFeatureDescriptorImpl;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.FeatureDescriptor;
import invenio.operator.data.NumericFeatureDescriptor;
import invenio.operator.data.NumericFeatureDescriptorImpl;
import invenio.operator.data.StringFeatureDescriptor;
import invenio.operator.data.StringFeatureDescriptorImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// TODO: this is a primitive writer - does not handle escaping special characters.
public class FeatureDeclarationWriter {
	
	public static final String SEPARATOR = ":";
	public static final String EQUALS = "=";
	public static final String LIST_SEPARATOR = ",";
	public static final String ELEMENT_BEGIN = "{";
	public static final String ELEMENT_END = "}";
	
	public static final String NUMERIC_TYPE = "numeric";
	public static final String STRING_TYPE = "string";
	public static final String CAT_TYPE = "cat";
	public static final String MULTICAT_TYPE = "multicat";
	public static final String ARRAY_TYPE = "array";
	
	private final Map<Class<? extends FeatureDescriptor>, FeatureWriter>
		writerMap = new HashMap<Class<? extends FeatureDescriptor>, FeatureWriter>();
	
	public FeatureDeclarationWriter() {
		initWriters();
	}
	
	protected void initWriters() {
		writerMap.put(NumericFeatureDescriptorImpl.class, new NumericFeatureWriter());
		writerMap.put(StringFeatureDescriptorImpl.class, new StringFeatureWriter());
		writerMap.put(CategoricalFeatureDescriptorImpl.class, new CategoricalFeatureWriter());
		writerMap.put(ArrayFeatureDescriptorImpl.class, new ArrayFeatureWriter());
	}
	
	public void writeFeatureDeclaration(FeatureDescriptor fd, BufferedWriter w) throws IOException, DataFormatException {
		if ( !writerMap.containsKey(fd.getClass()) )
			throw new DataFormatException("No writers found for feature type: " + fd.getClass());
		
		FeatureWriter fw = writerMap.get( fd.getClass() );
		fw.outputFeatureDeclaration(fd, w);
	}
	
	protected interface FeatureWriter {
		void outputFeatureDeclaration(FeatureDescriptor fd, BufferedWriter w) throws IOException, DataFormatException;
	}
	
	protected class NumericFeatureWriter implements FeatureWriter {

		public void outputFeatureDeclaration(FeatureDescriptor fd,
				BufferedWriter w) throws IOException {
			
			w.append( NUMERIC_TYPE ).append( SEPARATOR ).append( fd.getName() );
			
			NumericFeatureDescriptor castFD = (NumericFeatureDescriptor)fd;
			if (castFD.hasDefaultValue()) {
				w.append( SEPARATOR ).append( castFD.getDefaultValue() + "" );
			}
		}
	}
	
	protected class StringFeatureWriter implements FeatureWriter {

		public void outputFeatureDeclaration(FeatureDescriptor fd,
				BufferedWriter w) throws IOException {
			
			w.append( STRING_TYPE ).append( SEPARATOR ).append( fd.getName() );
			
			StringFeatureDescriptor castFD = (StringFeatureDescriptor)fd;
			if (castFD.hasDefaultValue()) {
				w.append( SEPARATOR ).append( castFD.getDefaultValue() );
			}
		}
	}
	
	protected class CategoricalFeatureWriter implements FeatureWriter {

		public void outputFeatureDeclaration(FeatureDescriptor fd,
				BufferedWriter w) throws IOException {

			w.append( CAT_TYPE ).append( EQUALS );
			
			CategoricalFeatureDescriptor castFD = (CategoricalFeatureDescriptor)fd;
			
			for (int i = 0; i < castFD.getNumCategories(); i++) {
				if (i > 0)
					w.append( LIST_SEPARATOR );
				w.append( castFD.getCategory(i));
			}
			
			w.append( SEPARATOR ).append( fd.getName() );
			
			
			if ( castFD.hasDefaultCategory() ) {
				w.append( SEPARATOR ).append( castFD.getDefaultCategory() );
			}
		}
	}
	
	protected class ArrayFeatureWriter implements FeatureWriter {

		public void outputFeatureDeclaration(FeatureDescriptor fd,
				BufferedWriter w) throws IOException, DataFormatException {
			
			w.append( ARRAY_TYPE ).append( EQUALS );
			
			ArrayFeatureDescriptor castFD = (ArrayFeatureDescriptor)fd;
			
			// size
			w.append( ELEMENT_BEGIN );
			w.append( castFD.getSize() + "");
			w.append( ELEMENT_END );
			
			// labels
			w.append( ELEMENT_BEGIN );
			if ( castFD.hasLabels() ) {
				for (int i = 0; i < castFD.getSize(); i++) {
					if (i > 0)
						w.append( LIST_SEPARATOR );
					w.append( castFD.getLabel(i) );
				}
			}
			w.append( ELEMENT_END );
			
			// contained FD
			w.append( ELEMENT_BEGIN );
			FeatureDescriptor containedFD = castFD.getContainedFeatureDescriptor();
			if ( containedFD != null ) {
				writeFeatureDeclaration(containedFD, w);
			}
			w.append( ELEMENT_END );
			
			w.append( SEPARATOR ).append( fd.getName() );
		}
	}
}
