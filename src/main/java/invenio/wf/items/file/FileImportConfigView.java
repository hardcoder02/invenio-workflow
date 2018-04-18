package invenio.wf.items.file;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileImportConfigView extends JPanel {
	private FileImportController controller;
	
	private JLabel fileNameLabel;
	private JTextField fileNameTextField;
	
	private JLabel graphNameLabel;
	private JTextField graphNameTextField;
	
	private JLabel fileTypeLabel;
	private JComboBox fileTypeComboBox;
	
	private JButton openButton;
	
	public static enum FileType {
		TEXT("text file (.txt)"),
		SPARSE("sparse file (.sgf)"),
		PAJEK("pajek file (.paj)"),
		SIMPLE("simple format (.txt)");
		
		FileType(String type) {
			this.type = type;
		}
		
		private String type;
		
		public String toString() {
			return type;
		}
	}
	
	public FileImportConfigView() {
		init();
	}

	private void init() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(4, 4, 4, 4);
		c.gridx = 0;
		c.gridy = 0;
		add( getFileNameLabel(), c );
		
		c.gridx = 1;
		add( getFileNameTextField(), c );
		
		c.gridx = 2;
		add( getOpenButton(), c );
		
		c.gridx = 0;
		c.gridy = 1;
		add( getFileTypeLabel(), c );
		
		c.gridx = 1;
		add( getFileTypeComboBox(), c );
				
		c.gridx = 0;
		c.gridy = 2;
		add( getGraphNameLabel(), c );
		
		c.gridx = 1;
		add( getGraphNameTextField(), c );
			
	}
	
	protected void setController(FileImportController newController) {
		if (controller != null) {
			// cleanup
			getOpenButton().removeActionListener(controller);
		}
		controller = newController;
		if (controller != null) {
			// setup
			getOpenButton().addActionListener(controller);
		}
	}
	
	protected JLabel getFileNameLabel() {
		if ( fileNameLabel == null ) {
			fileNameLabel = new JLabel("File:");
		}
		return fileNameLabel;
	}
	
	protected JTextField getFileNameTextField() {
		if ( fileNameTextField == null ) {
			fileNameTextField = new JTextField("Enter file name or click button");
		}
		return fileNameTextField;
	}
	
	protected JLabel getFileTypeLabel() {
		if ( fileTypeLabel == null ) {
			fileTypeLabel = new JLabel("File type:");
		}
		return fileTypeLabel;
	}
	
	protected JComboBox getFileTypeComboBox() {
		if ( fileTypeComboBox == null ) {
			fileTypeComboBox = new JComboBox(FileType.values());
		}
		return fileTypeComboBox;
	}
	
	protected JLabel getGraphNameLabel() {
		if ( graphNameLabel == null ) {
			graphNameLabel = new JLabel("Graph name:");
		}
		return graphNameLabel;
	}
	
	protected JTextField getGraphNameTextField() {
		if ( graphNameTextField == null ) {
			graphNameTextField = new JTextField("g1", 20);
		}
		return graphNameTextField;
	}
	
	protected JButton getOpenButton() {
		if ( openButton == null ) {
			openButton = new JButton("Open...");
		}
		return openButton;
	}
}
