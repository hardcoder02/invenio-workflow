package invenio.wf.items.vis.nodelabel;

import java.awt.Color;

public class FeatureToColorMapper {
	public static Color[] createColorArray(int size) {
		Color[] result = new Color[size];
		
		for (int i = 0; i < size; i++) 
			result[i] = getColor(0, 1, (double) i / size );
			
		return result;
	}
	
	public static Color getColor( double start, double range, double power)
	{
        double H = power * range + start; // Hue (note 0.4 = Green, see huge chart below)
        double S = 0.9; // Saturation
        double B = 0.9; // Brightness

        return Color.getHSBColor((float)H, (float)S, (float)B);
    }
}
