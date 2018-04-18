package invenio.operator.io.tab;

import invenio.operator.data.ArrayFeature;
import invenio.operator.data.ArrayFeatureImpl;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.CategoricalFeatureImpl;
import invenio.operator.data.DataFormatException;
import invenio.operator.data.Feature;
import invenio.operator.data.NumericFeature;
import invenio.operator.data.NumericFeatureImpl;
import invenio.operator.data.StringFeature;
import invenio.operator.data.StringFeatureImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// TODO: this is a primitive writer - does not handle escaping special characters.
public class FeatureValueWriter {
	
	public static final String SEPARATOR = ":";
	public static final String EQUALS = "=";
	public static final String LIST_SEPARATOR = ",";
	public static final String PROBABILITY = "P";
	public static final String ELEMENT_BEGIN = "(";
	public static final String ELEMENT_END = ")";
	public static final String ARRAY_BEGIN = "{";
	public static final String ARRAY_END = "}";
	
	private final Map<Class<? extends Feature>, FeatureValueProcessor>
		writerMap = new HashMap<Class<? extends Feature>, FeatureValueProcessor>();
	
	public FeatureValueWriter() {
		initProcessors();
	}
	
	protected void initProcessors() {
		writerMap.put(NumericFeatureImpl.class, new NumericFeatureProcessor());
		writerMap.put(StringFeatureImpl.class, new StringFeatureProcessor());
		writerMap.put(CategoricalFeatureImpl.class, new CategoricalFeatureProcessor());
		writerMap.put(ArrayFeatureImpl.class, new ArrayFeatureProcessor());
	}
	
	public void writeFeatureValue(Feature f, BufferedWriter w) throws IOException, DataFormatException {
		if ( !writerMap.containsKey(f.getClass()) )
			throw new DataFormatException("No writers found for feature: " + f.getClass());
		
		FeatureValueProcessor fp = writerMap.get( f.getClass() );
		fp.outputFeatureValue(f, w);
	}
	
	protected interface FeatureValueProcessor {
		void outputFeatureValue(Feature f, BufferedWriter w) throws IOException, DataFormatException;
	}
	
	protected class NumericFeatureProcessor implements FeatureValueProcessor {

		public void outputFeatureValue(Feature f,
				BufferedWriter w) throws IOException {
			
			w.append( f.getFeatureDescriptor().getName() ).append( EQUALS );
			
			NumericFeature castF = (NumericFeature)f;
			
			w.append( castF.getValue() + "" );
		}
	}
	
	protected class StringFeatureProcessor implements FeatureValueProcessor {

		public void outputFeatureValue(Feature f,
				BufferedWriter w) throws IOException {
			
			w.append( f.getFeatureDescriptor().getName() ).append( EQUALS );
			
			StringFeature castF = (StringFeature)f;
			
			w.append( castF.getValue() );
		}
	}
	
	protected class CategoricalFeatureProcessor implements FeatureValueProcessor {

		public void outputFeatureValue(Feature f, BufferedWriter w) throws IOException {

			w.append( f.getFeatureDescriptor().getName() ).append( EQUALS );
			
			CategoricalFeature castF = (CategoricalFeature)f;
			
			w.append( castF.getSelectedValue() );
			w.append( SEPARATOR );
			
			w.append( PROBABILITY ).append( EQUALS );
			for (int i = 0; i < castF.getFeatureDescriptor().getNumCategories(); i++) {
				if (i > 0)
					w.append( LIST_SEPARATOR );
				w.append( castF.getProbability(i) + "" );
			}
			
		}
	}
	
	protected class ArrayFeatureProcessor implements FeatureValueProcessor {

		public void outputFeatureValue(Feature f, BufferedWriter w) throws IOException, DataFormatException {
			
			w.append( f.getFeatureDescriptor().getName() ).append( EQUALS );
			
			ArrayFeature castF = (ArrayFeature)f;
			
			w.append( ARRAY_BEGIN );
			for (int i = 0; i < castF.getFeatureDescriptor().getSize(); i++) {
				if (i > 0)
					w.append( LIST_SEPARATOR );
				w.append( ELEMENT_BEGIN );
				if ( castF.getContainedValue(i) != null)
					writeFeatureValue( castF.getContainedValue(i), w);
				w.append( ELEMENT_END );
			}
			w.append( ARRAY_END );
		}
	}
}
