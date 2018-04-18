package invenio.wf.items.vis.query;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import invenio.io.db.*;

import invenio.*;


public class QueryResultsDialog extends JDialog implements ActionListener {
	
	protected JFrame _parent;
	
	public QueryResultsDialog(JFrame parent) {
		super(parent, "Query Result");
		_parent = parent;
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(300, 270);
		getContentPane().setBackground(Color.WHITE);
		
		titleLabel = new JLabel("Database Connection");
		titleLabel.setBounds(20, 20, 200, 20);
		add(titleLabel);
		
		typeLabel = new JLabel("Type of database:");
		typeLabel.setBounds(20, 60, 150, 20);
		add(typeLabel);
		
		typeComboBox = new JComboBox(types);
		typeComboBox.setBounds(130, 60, 130, 20);
		typeComboBox.setBackground(Color.WHITE);
		add(typeComboBox);
		
		serverLabel = new JLabel("Server location: ");
		serverLabel.setBounds(20, 90, 150, 20);
		serverTextField = new JTextField(TEST_LOCATION);
		serverTextField.setBounds(130, 90, 130, 20);
		add(serverLabel);
		add(serverTextField);
		
		userLabel = new JLabel("User name:");
		userLabel.setBounds(20, 120, 100, 20);
		userTextField = new JTextField(TEST_USER);
		userTextField.setBounds(130, 120, 130, 20);
		add(userLabel);
		add(userTextField);
		
		passLabel = new JLabel("Password:");
		passLabel.setBounds(20, 150, 100, 20);
		passTextField = new JPasswordField(TEST_PASS);
		passTextField.setBounds(130, 150, 130, 20);
		passTextField.addActionListener(this);
		add(passLabel);
		add(passTextField);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(25, 180, 100, 25);
		connectButton.addActionListener(this);
		add(connectButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(145, 180, 100, 25);
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		setVisible(true);
	}
	
	public DatabaseGraphImporter getGraphImporter() {
		return m_importer;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connectButton || e.getSource() == passTextField) {
			try {
				String server = serverTextField.getText();
				String username = userTextField.getText();
				String password = new String(passTextField.getPassword());				
				
				m_importer = new DatabaseGraphImporter(Constants.DEFAULT_GRAPH_NAME);
				
				int dbType = DatabaseGraphImporter.ORACLE_DB;
				if(typeComboBox.getSelectedItem().equals("MySQL"))
					dbType = DatabaseGraphImporter.MYSQL_DB;
				else if(typeComboBox.getSelectedItem().equals("PostgreSQL"))
					dbType = DatabaseGraphImporter.POSTGRESQL_DB;
				
				m_importer.connect(server, username, password, dbType);
				
				dispose();
				new DatabaseImportDialog(_parent, m_importer);
			}
			catch(SQLException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Connection Error", JOptionPane.WARNING_MESSAGE);
				System.err.println(ex.getMessage());
			}
		}
		else if(e.getSource() == cancelButton) {
			dispose();
		}
	}
	
	final String TEST_LOCATION = "jdbc:oracle:thin:@lilac.cs.georgetown.edu:1521:research";
	final String TEST_USER = "pir";
	final String TEST_PASS = "proteins";
	
	JLabel titleLabel;
	JLabel serverLabel;
	JLabel userLabel;
	JLabel passLabel;
	JLabel typeLabel;
	
	JTextField serverTextField;
	JTextField userTextField;
	JPasswordField passTextField;
	
	JButton connectButton;
	JButton cancelButton;
	
	JComboBox typeComboBox;
	String[] types = {"Oracle", "MySQL", "PostgreSQL"};
}
