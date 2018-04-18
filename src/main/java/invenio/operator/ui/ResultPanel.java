package invenio.operator.ui;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import qng.tabular.Schema;
import qng.tabular.Table;
import qng.tabular.Tuple;

public class ResultPanel extends JPanel {
	private final Table table;
	
	JScrollPane resultScrollPane = null;
	JTable resultTable;
	
	public ResultPanel(Table t) {
		super();
		this.table = t;
		init();
	}

	private void init() {
		setLayout( new BorderLayout() );
		add(getResultScrollPane(), BorderLayout.CENTER);
	}

	private JScrollPane getResultScrollPane() {
		if (resultScrollPane == null) {
			resultScrollPane = new JScrollPane(getResultTable(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return resultScrollPane;
	}

	private JTable getResultTable() {
		if (resultTable == null) {
			resultTable = new JTable();
			resultTable.setModel( getTableModel() );
			resultTable.setFillsViewportHeight(true);
			resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			resultTable.setCellSelectionEnabled(true);
		}
		return resultTable;
	}

	private TableModel getTableModel() {
		DefaultTableModel mod = new DefaultTableModel();
		
		Schema schema = table.getSchema();
		String[] colNames = schema.getColumnNames();
		for ( String s : colNames) {
			mod.addColumn( s );
		}
		
		Iterator<? extends Tuple> iter = table.getIterator();
		while (iter.hasNext()) {
			Tuple t = iter.next();
			
			Object[] rowData = new Object[colNames.length];
			for ( int i = 0; i < colNames.length; i++) {
				rowData[i] = t.getValue(i);
			}
			mod.addRow(rowData);
		}
		return mod;
	}
	
	public Object getSelectedData() {
		if (resultTable != null) {
			int col = resultTable.getSelectedColumn();
			int row = resultTable.getSelectedRow();
			
			if (col < 0 || row < 0)
				return null;
			else
				return resultTable.getValueAt(row, col);
		}
		else
			return null;
	}
	
	public TableModel getModel() {
		if (resultTable != null) {
			return resultTable.getModel();
		}
		else
			return null;
	}
}
