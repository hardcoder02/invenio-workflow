package invenio.wf.test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prefuse.util.ColorLib;

public class HeatMapTest {
        
        public static void main(String[] args) {
        	// create a new window to hold the visualization
    	    JFrame frame = new JFrame("heat map");
            // ensure application exits when window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add( new JScrollPane(getHeatMap()) );
            frame.pack();           // layout components in window
            frame.setVisible(true); // show the window
        }
        
        public static JPanel getHeatMap() {
        	JPanel panel = new JPanel();
        	int min = 1, max = 200;
//        	Color[] color = generateColors(min, max);
        	
        	for (int i = min; i < max; i++) {
        		JLabel label = new JLabel("" + i);
        		label.setForeground( dataPointToColor(i, min, max));
        		panel.add(label);
        	}
        	
        	return panel;
        }
        
        public static Color[] generateColors(int n)
        {
        	Color[] cols = new Color[n];
        	for(int i = 0; i < n; i++)
        	{
        		cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        	}
        	return cols;
        }
        
        public static Color[] generateColors(int min, int max) {
        	final Color[] color = new Color[max-min+1];
        	for (int i = min; i <= max; i++) {
        		int red = ((i-min)/(max-min))*255;
        		int blue = 255 - red;
        		color[i-min] = new Color(red, 255, blue);
        	}
        	return color;
        }
        
        /*
         * Use this one
         */
        public static Color dataPointToColor(float Value, float MinValue, float MaxValue) {
        
           float WaveLength;
           
           // TODO: shifted (to avoid dark colours) - CROM
           Value = Value - MinValue;
           MaxValue = MaxValue - MinValue;
           MinValue = 0;
           Value = (Value + MaxValue) / 2;
           
           WaveLength = getWaveLengthFromDataPoint(Value, MinValue, MaxValue);
           return WavelengthToRGB(WaveLength);
		}
        
        public static float getWaveLengthFromDataPoint(float Value, float MinValue, float MaxValue) {
        
           final float MinVisibleWaveLength = 350.0f;
           final float MaxVisibleWaveLength = 650.0f;

           //Convert data value in the range of MinValues..MaxValues to the 
           //range 350..650

           float result = (Value - MinValue) / (MaxValue-MinValue) *
                 (MaxVisibleWaveLength - MinVisibleWaveLength) +
                 MinVisibleWaveLength;
           return result;
        }
        
        
        public static final float Gamma  =   0.80f;
    	public static final float IntensityMax = 255;
        
    	public static float Adjust(float Color, float Factor) {
    		if (Color == 0)
    			return 0;     // Don't want 0^x = 1 for x <> 0
    		else
    			return Math.round( IntensityMax * Math.pow(Color * Factor, Gamma));
    	}
    	
        public static Color WavelengthToRGB(float Wavelength) {
        	float Red=0, Green=0, Blue=0, factor=0;

        	if ( Wavelength < 380 || Wavelength > 780) {
        		Red  = 0;
        		Green = 0;
        		Blue  = 0;
        	}
        	else if ( Wavelength < 440 )
        	{
        		Red   = -(Wavelength - 440) / (440 - 380);
        		Green = 0;
        		Blue  = 1;
        	}
        	else if (Wavelength < 490 ) {
        		Red   = 0;
        		Green = (Wavelength - 440) / (490 - 440);
        		Blue  = 1;
        	}
        	else if (Wavelength < 510) {
        		Red   = 0;
        		Green = 1;
        		Blue  = -(Wavelength - 510) / (510 - 490);
        	}
        	else if ( Wavelength < 580 ) {
        		Red   = (Wavelength - 510) / (580 - 510);
        		Green = 1;
        		Blue  = 0;
        	}
        	else if (Wavelength < 645) {
        		Red   = 1;
        		Green = -(Wavelength - 645) / (645 - 580);
        		Blue  = 0;
        	}
        	else if (Wavelength < 780 ) {
        		Red   = 1;
        		Green = 0;
        		Blue  = 0;
        	}

        	// Let the intensity fall off near the vision limits
        	if (Wavelength < 380 || Wavelength > 780) {
        		factor = 0;
        	}
        	else if (Wavelength < 420)
        		factor = 0.3f + 0.7f*(Wavelength - 380) / (420 - 380);
        	else if ( Wavelength < 700)
        		factor = 1;
        	else if (Wavelength < 780)
        		factor = 0.3f + 0.7f*(780 - Wavelength) / (780 - 700);
        	
        	Red = Adjust(Red, factor);
        	Green = Adjust(Green, factor);
        	Blue = Adjust(Blue,  factor);
        	
        	return new Color((int)Red, (int)Green, (int)Blue);
        }
}

