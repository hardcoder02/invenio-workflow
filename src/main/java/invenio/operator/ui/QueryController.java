package invenio.operator.ui;

import invenio.GraphSession;
import invenio.UserSession;
import invenio.data.InvenioGraph;
import invenio.op.parser.TextToLogicalExpressionParser;
import invenio.op.parser.XMLToLogicalExpressionParser;
import invenio.ui.MainFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.QueryParser;
import qng.core.query.CompiledElement;
import qng.core.query.CompiledQuery;
import qng.tabular.Table;

public class QueryController implements ActionListener {
	
	private Map<String, String> contextParams;
	
	private final MainFrame frame;
	
	// TODO: fix
	private JDialog queryDialog;
	private JTabbedPane queryTabbedPane;
	private JPanel queryPane;
	private QueryPanel queryPanel;
	private JPanel resultPane;
	private ResultPanel resultPanel;
	private JButton executeButton;
	private JButton cancelButton;
	private JButton saveButton;
	private JButton openButton;
	private JButton displayButton;
	private JButton copyClipboardButton;
	
	private JFileChooser fileChooser;
	private File lastFile = new File("C:/Workspace/Eclipse/Invenio/QueryEngine/queries/");
	
	public QueryController(MainFrame frame) {
		this.frame = frame;
	}
	
	public void processQuery(String query) {
		processQuery(query, null);
	}
	
	public void processQuery(File f) {
		try {
			processQuery( readQuery( f ), null);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void processQuery(File f, Map<String, String> contextParams) {
		try {
			processQuery( readQuery( f ), contextParams);
			lastFile = f;
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void processQuery(String query, Map<String, String> contextParams) {
		try {
			this.contextParams = contextParams;
			displayQuery( query );
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void displayQuery(String query) {
		queryDialog = new JDialog(frame);
		queryDialog.setContentPane( getQueryTabbedPane( query ) );
		queryDialog.setModalityType(ModalityType.APPLICATION_MODAL);

		queryDialog.setPreferredSize(new Dimension(1200, 900));
		queryDialog.setLocation(200, 100);
		queryDialog.pack();
		queryDialog.setVisible(true);
	}
	
	protected JTabbedPane getQueryTabbedPane(String query) {
		queryTabbedPane = new JTabbedPane();
		queryTabbedPane.addTab("query", getQueryPane( query ) );
		
		return queryTabbedPane;
	}
	
	protected JPanel getQueryPane( String query ) {
		queryPane = new JPanel();
		
		queryPane.setLayout( new BorderLayout() );
		
		queryPane.add(createQueryPanel( query ), BorderLayout.CENTER);
		queryPane.add(createQueryButtonPanel(), BorderLayout.SOUTH);
		
		return queryPane;
	}
	
	protected QueryPanel createQueryPanel( String query ) {
		queryPanel = new QueryPanel( query );
		return queryPanel;
	}
	
	protected JPanel createQueryButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		executeButton = new JButton("Execute");
		executeButton.addActionListener(this);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
		openButton = new JButton("Open query...");
		openButton.addActionListener(this);
		
		saveButton = new JButton("Save as...");
		saveButton.addActionListener(this);
		
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createHorizontalGlue());
		panel.add(executeButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(cancelButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(openButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(saveButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createHorizontalGlue());
		
		return panel;
	}
	
	protected void displayResult(Table t, String q) {
		queryTabbedPane.add( "Result", getResultPane(t, q) );
	}
	
	protected JPanel getResultPane(Table t, String q) {
		JPanel result = new JPanel();
		result.setLayout( new BorderLayout() );
		
		result.add(createResultSplitPane(t, q), BorderLayout.CENTER);
		result.add(createResultButtonPanel(), BorderLayout.SOUTH);
		
		return result;
	}
	
	protected JSplitPane createResultSplitPane(Table t, String q) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				createResultPanel(t),
				createResultQueryScrollPane(q)
				);
		
		return splitPane;
	}
	
	protected JScrollPane createResultQueryScrollPane(String q) {
		JScrollPane p = new JScrollPane(createResultQueryTextArea(q),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				);
		
		return p;
	}
	
	protected JTextArea createResultQueryTextArea(String q) {
		JTextArea t = new JTextArea(q);
		t.setEditable(false);
		
		return t;
	}
	
	protected JPanel createResultPanel(Table t) {
		resultPanel = new ResultPanel(t);
		
		return resultPanel;
	}
	
	protected JPanel createResultButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		displayButton = new JButton("Show graph");
		displayButton.addActionListener(this);
		
		copyClipboardButton = new JButton("Copy to clipboard");
		copyClipboardButton.addActionListener(this);
		
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createHorizontalGlue());
		panel.add(displayButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(copyClipboardButton);
		panel.add(Box.createHorizontalGlue());
		panel.add(Box.createHorizontalGlue());
		
		return panel;
	}

	protected Table runQuery(String query) {
		try {
			frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			QueryParser<String, LogicalExpression> parser = null;
			
			// XML query
			if ( query.trim().length() > 4 && query.trim().substring(0, 5).equalsIgnoreCase("<?XML") )
				parser = new XMLToLogicalExpressionParser();
			else
				parser = new TextToLogicalExpressionParser();
			
			LogicalExpression exp = parser.parse(query);
			
			CompiledQuery<CompiledElement> comp = QueryService.compileExpression(exp);
		
			//frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return (Table) QueryService.executeQuery(comp, contextParams).getResultValue();
		}
		catch (QueryException ex) {
			JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		finally {
			frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == displayButton) {
			displayGraph();
		}
		else if(e.getSource() == copyClipboardButton) {
			copyToClipboard();
		}
		else if(e.getSource() == executeButton) {
			String q = queryPanel.getQuery();
			Table t = runQuery( q );
			if ( t!= null )
				displayResult( t, q );
		}
		else if (e.getSource() == openButton ) {
			File f = displayFileChooser();
			if ( f != null)
				loadQuery(f);
		}
		else if (e.getSource() == saveButton) {
			File f = displayFileChooser();
			if ( f != null )
				saveQuery( queryPanel.getQuery(), f );
		}
		else if(e.getSource() == cancelButton) {
			if(queryDialog != null) {
				//queryDialog.setVisible(false);
				queryDialog.dispose();
			}
		}
	}

	private void displayGraph() {
		if (resultPanel == null || resultPanel.getSelectedData() == null) {
			JOptionPane.showMessageDialog(queryDialog, "Please make a selection");
			return;
		}
		
		Object selData = resultPanel.getSelectedData();
		if ( ! (selData instanceof InvenioGraph) ) {
			JOptionPane.showMessageDialog(queryDialog, "Please select a field that represents a graph");
			return;
		}
		
		GraphSession session = new GraphSession( ((InvenioGraph) selData));
		UserSession.getInstance().addAndActivateGraphSessionInNewDisplay(session);
	}
	
	private void copyToClipboard() {
		if (resultPanel == null) {
			JOptionPane.showMessageDialog(queryDialog, "Nothing to copy");
			return;
		}
		
		TableModel mod = resultPanel.getModel();
		if ( mod == null ) {
			return;
		}
		
		CSVOut csv = new CSVOut();
		StringBuffer sb = new StringBuffer();
		
		String[] cols = new String[mod.getColumnCount()];
		for (int i = 0; i < cols.length; i++)
			cols[i] = mod.getColumnName(i);
		csv.writeLine(sb, cols);
		
		for (int i = 0; i < mod.getRowCount(); i++) {
			String[] line = new String[cols.length];
			for (int j = 0; j < cols.length; j++) {
				Object val = mod.getValueAt(i, j);
				line[j] = (val == null) ? null : val.toString();
			}
			csv.writeLine(sb, line);
		}
		
		String selection = sb.toString();
		StringSelection data = new StringSelection(selection);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(data, data);
	}
	
	protected void saveQuery(String query, File f) {
		try {
			frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			// check exists
			if ( f.exists() ) {
				int returnVal = JOptionPane.showConfirmDialog( queryDialog, "File " + f.getName() + " already exists. Overwrite?");
				if (returnVal != JOptionPane.OK_OPTION)
					return;
			}
			
			writeQuery(f, query);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(frame, "Error saving query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	protected void loadQuery(File f) {
		try {
			frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			String query = readQuery(f);
			queryPanel.setQuery( query );
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(frame, "Error reading query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	private File displayFileChooser() {
		//Create a file chooser
		if (fileChooser == null)
			fileChooser = new JFileChooser(lastFile);

		//In response to a button click:
	    int returnVal = fileChooser.showOpenDialog(queryDialog);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        lastFile = fileChooser.getSelectedFile();

	        return lastFile;

	    } else {
	        return null;
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
			StringBuffer sb = new StringBuffer();
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
}
