package invenio.wf.items.vis.nodelabel;

import invenio.operator.data.CategoricalFeature;

import java.awt.Color;

public class CategoricalFeatureColorGradient {
	
	public static Color getColor(CategoricalFeature f1, CategoricalFeature f2) {
		if ( f1.getSelectedIndex() == f2.getSelectedIndex() )
			return getMatchingColor( f1, f2 );
		else
			return getMismatchingColor( f1, f2 );
	}
	
	public static Color getMatchingColor(CategoricalFeature f1, CategoricalFeature f2) {
		int numCategories = f1.getFeatureDescriptor().getNumCategories();
		double minProb = 1d / numCategories;
		
		double p1 = scale(minProb, 1, f1.getProbabilities()[f1.getSelectedIndex()]);
		double p2 = scale(minProb, 1, f2.getProbabilities()[f2.getSelectedIndex()]);
		
		return getColor( 0.45, 0.25, 1 - Math.abs(p1 - p2) );
	}
	
	public static Color getMismatchingColor(CategoricalFeature f1, CategoricalFeature f2) {
		int numCategories = f1.getFeatureDescriptor().getNumCategories();
		double minProb = 1d / numCategories;
		
		double p1 = scale(minProb, 1, f1.getProbabilities()[f1.getSelectedIndex()]);
		double p2 = scale(minProb, 1, f2.getProbabilities()[f2.getSelectedIndex()]);
		
		return getColor( 0, 0.18, Math.abs(p1 - p2) );
	}
	
    public static Color getColor(double start, double range, double power)
        {
            double H = power * range + start; // Hue (note 0.4 = Green, see huge chart below)
            double S = 0.9; // Saturation
            double B = 0.9; // Brightness

            return Color.getHSBColor((float)H, (float)S, (float)B);
        }

    private static double scale( double minProb, double maxProb, double val) {
    	return (val - minProb) * maxProb / (maxProb - minProb);
    }
}

