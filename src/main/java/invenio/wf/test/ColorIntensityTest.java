package invenio.wf.test;

import invenio.wf.ui.components.VerticalLabelUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prefuse.util.ColorLib;

public class ColorIntensityTest {
        
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
        	int min = 1, max = 255;
//        	Color[] color = generateColors(min, max);
        	
        	for (int i = min; i < max; i+= 10) {
        		JButton label = new JButton("" + i);
//        		JLabel label = new JLabel("" + i);
//        		label.setUI( new VerticalLabelUI());
        		label.setBackground(  getColor( (double) i / 255) );
//        		label.setBackground(  getColor( i ) );
        		panel.add(label);
        	}
        	
        	return panel;
        }
        
        public static Color getColor(int i) {
        	float blue = 0;
        	float green = 0;
        	float red = 0;
//        	if  (0 <= i && i < 127) {
//        		// first, green stays at 100%, red raises to 100%
//        		green = 255;
//        		red = 2 * i;
//        	}
//        	else if ( 127 <= i && i <= 255) {
//        		// then red stays at 100%, green decays
//        		red = 255;
//        		green = 255 - 2 * (i-127);
//        	}
        	
        	red = (float) i;
        	green = 255 - i; 

        	return ColorLib.getColor( red, green, blue);
        }
        
        public static Color getColor(double power)
        {
            double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
            double S = 0.9; // Saturation
            double B = 0.9; // Brightness

            return Color.getHSBColor((float)H, (float)S, (float)B);
        }

}

