package dnl.utils.text;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Daniel Orr
 *
 */
public class TextTable {

	private TableModel tableModel;

	public TextTable(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TextTable(String[] columnNames, String[][] data) {
		this.tableModel = new DefaultTableModel(data, columnNames);
	}
	
//	public static void main(String[] args) {
//		String[] titles = { "cotttumn1", "colu345345mn2", "columnfff3" };
//
//		String[][] tst = { 
//				{ "val10", "val20", "val30" }, 
//				{ "val11", "val21", "val31" },
//				{ "val12", "val22", "val32" }, };
//		TextTable tt = new TextTable(titles, tst);
//		tt.printTable();
//	}
	
	public void printTable() {

		// Find the maximum length of a string in each column
		int[] lengths = new int[tableModel.getColumnCount()];
		
		for (int col = 0; col < tableModel.getColumnCount(); col++) {
			for (int row = 0; row < tableModel.getRowCount(); row++) {
				Object val = tableModel.getValueAt(row, col);
				String valStr = val == null ? "" : String.valueOf(val); 
				lengths[col] = Math.max(valStr.length(), lengths[col]);
			}
		}

		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			lengths[j] = Math.max(tableModel.getColumnName(j).length(), lengths[j]);
		}

		// Generate a format string for each column and calc totalLength
		int totLength = 0;
		String[] formats = new String[lengths.length];
		for (int i = 0; i < lengths.length; i++) {
			formats[i] = "%1$-" + lengths[i] + "s|" + (i + 1 == lengths.length ? "\n" : " ");
			totLength += lengths[i]; 
		}

		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			System.out.printf(formats[j], tableModel.getColumnName(j));
		}
		
		String sep = StringUtils.repeat("=", totLength+5);
		System.out.println(sep);
		
		// Print 'em out
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				System.out.printf(formats[j], tableModel.getValueAt(i, j));
			}
		}
	}
	
}
