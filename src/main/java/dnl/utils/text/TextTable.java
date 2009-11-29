package dnl.utils.text;

import java.io.PrintStream;

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

	private boolean addNumbering;

	public TextTable(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TextTable(TableModel tableModel, boolean addNumbering) {
		this.tableModel = tableModel;
		this.addNumbering = addNumbering;
	}

	public TextTable(String[] columnNames, String[][] data) {
		this.tableModel = new DefaultTableModel(data, columnNames);
	}

	public void setAddNumbering(boolean addNumbering) {
		this.addNumbering = addNumbering;
	}

	public static void main(String[] args) {
		String[] titles = { "cotttumn1", "colu345345mn2", "columnfff3" };

		String[][] tst = { { "val10", "val20", "val30" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
				{ "val11", "val21", "val31" }, { "val12", "val22", "val32" }, };
		TextTable tt = new TextTable(titles, tst);
		tt.setAddNumbering(true);
		tt.printTable();
	}

	public void printTable() {
		printTable(System.out);
	}

	public void printTable(PrintStream ps) {

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

		int rowCount = tableModel.getRowCount();
		int rowCountStrSize = Integer.toString(rowCount).length();
		String indexFormat1 = "%1$-" + rowCountStrSize + "s  |";
		String indexFormat2 = "%1$" + rowCountStrSize + "s. |";

		// Generate a format string for each column and calc totalLength
		int totLength = 0;
		String[] formats = new String[lengths.length];
		for (int i = 0; i < lengths.length; i++) {
			formats[i] = "%1$-" + lengths[i] + "s|" + (i + 1 == lengths.length ? "\n" : " ");
			totLength += lengths[i];
		}

		if (addNumbering) {
			ps.printf(indexFormat1, "");
		}
		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			ps.printf(formats[j], tableModel.getColumnName(j));
		}

		if (addNumbering) {
			ps.printf(indexFormat1, "");
		}
		String sep = StringUtils.repeat("=", totLength + 5);
		ps.println(sep);

		// Print 'em out
		for (int i = 0; i < tableModel.getRowCount(); i++) {

			if (addNumbering) {
				ps.printf(indexFormat2, i + 1);
			}
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				ps.printf(formats[j], tableModel.getValueAt(i, j));
			}
		}
	}

}
