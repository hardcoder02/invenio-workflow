package invenio.wf.items.query;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import invenio.algorithms.GroupingAlgorithmResult;
import invenio.algorithms.matching.GraphComparator;
import invenio.data.InvenioElementSet;
import invenio.data.InvenioGraph;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;


// TODO: stop actions before updating them
public class QueryConfigController implements ActionListener, ItemListener {
	
	protected QueryConfigView shape;
	
	private JFileChooser fileChooser;
	private File lastFile = new File("C:/Workspace/Invenio/QueryEngine/queries");
	
	public QueryConfigController() {
	}
	
	public void setShape(QueryConfigView shape) {
		this.shape = shape;
	}
	
	public QueryConfigView getShape() {
		return shape;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if ( evt.getSource() == shape.getSaveButton() ) {
			processSaveButton();
		}
		else if ( evt.getSource() == shape.getOpenButton() ) {
			processOpenButton();
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();

	    if (source == shape.getPackageCheckBox() ) {
	        updatePackage( shape.getPackageCheckBox().isSelected() );
	    }
	}
	
	private void updatePackage(boolean enabled) {
		shape.getColumnSpinner().setEnabled( enabled );
		shape.getRowSpinner().setEnabled( enabled );
	}

	private void processOpenButton() {
		File f = displayFileChooser();
		if ( f != null)
			loadQuery(f);
	}

	private void processSaveButton() {
		File f = displayFileChooser();
		if ( f != null )
			saveQuery( shape.getQueryPanel().getQuery(), f );
	}
	
	private File displayFileChooser() {
		//Create a file chooser
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastFile);

		//In response to a button click:
	    int returnVal = fileChooser.showOpenDialog( shape );
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        lastFile = fileChooser.getSelectedFile();

	        return lastFile;

	    } else {
	        return null;
	    }
	}
	
	protected void loadQuery(File f) {
		try {
			shape.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			String query = readQuery(f);
			shape.getQueryPanel().setQuery( query );
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(shape, "Error reading query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			shape.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	protected void saveQuery(String query, File f) {
		try {
			shape.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			// check exists
			if ( f.exists() ) {
				int returnVal = JOptionPane.showConfirmDialog( shape, "File " + f.getName() + " already exists. Overwrite?");
				if (returnVal != JOptionPane.OK_OPTION)
					return;
			}
			
			writeQuery(f, query);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(shape, "Error saving query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			shape.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	private String readQuery( File f ) throws IOException {
		
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader( f ));
			StringBuffer sb = new StringBuffer();
			String s = new String();
			while ( (s = r.readLine()) != null )
				sb.append(s).append("\n");
			return sb.toString();
		}
		finally {
			try {
				if (r != null)
					r.close();
			}
			catch (IOException ex) {}
		}
	}
	
	private void writeQuery( File f , String query ) throws IOException {
		
		BufferedWriter w = null;
		try {
			w = new BufferedWriter(new FileWriter( f ));
			//StringBuffer sb = new StringBuffer();
			w.write(query);
		}
		finally {
			try {
				if (w != null)
					w.close();
			}
			catch (IOException ex) {}
		}
	}
	
	
	public void updateModel(){ 
		
	}
	
	public QueryConfigBackgroundModel updateBackgroundModel() {
		QueryConfigBackgroundModel bgModel = new QueryConfigBackgroundModel();
		bgModel.setQuery( getShape().getQueryPanel().getQuery() );
		bgModel.setPackageCell( getShape().getPackageCheckBox().isSelected() );
		bgModel.setRow( (Integer) getShape().getRowSpinner().getValue() );
		bgModel.setColumn( (Integer) getShape().getColumnSpinner().getValue() );
		
		return bgModel;
	}
	
}
