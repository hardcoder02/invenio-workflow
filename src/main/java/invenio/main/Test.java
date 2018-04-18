package invenio.main;
import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;


public class Test {
	public static void main(String[] args) {
		// Create an internal frame
		boolean resizable = true;
		boolean closeable = true;
		boolean maximizable  = true;
		boolean iconifiable = true;
		String title = "Frame Title";
		JInternalFrame iframe = new JInternalFrame(title, resizable, closeable, maximizable, iconifiable);

		// Set an initial size
		int width = 200;
		int height = 50;
		iframe.setSize(width, height);

		// By default, internal frames are not visible; make it visible
		iframe.setVisible(true);

		// Add components to internal frame...
		iframe.getContentPane().add(new JTextArea());

		// Add internal frame to desktop
		JDesktopPane desktop = new JDesktopPane();
		desktop.add(iframe);

		// Display the desktop in a top-level frame
		JFrame frame = new JFrame();
		frame.getContentPane().add(desktop, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
