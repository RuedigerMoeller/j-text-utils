package dnl.utils.text;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Daniel Orr
 * 
 */
public class TextTable {

	protected TableModel tableModel;
	private List<SeparatorPolicy> separatorPolicies = new ArrayList<SeparatorPolicy>();

	private boolean addRowNumbering;

	public TextTable(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TextTable(TableModel tableModel, boolean addNumbering) {
		this.tableModel = tableModel;
		this.addRowNumbering = addNumbering;
	}

	public TextTable(String[] columnNames, String[][] data) {
		this.tableModel = new DefaultTableModel(data, columnNames);
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setAddRowNumbering(boolean addNumbering) {
		this.addRowNumbering = addNumbering;
	}

	public void addSeparatorPolicy(SeparatorPolicy separatorPolicy) {
		separatorPolicies.add(separatorPolicy);
		separatorPolicy.setTableModel(tableModel);
	}

	public static void main(String[] args) {
		String[] titles = { "cotttumn1", "colu345345mn2", "columnfff3" };

		String[][] tst = { { "val10", "val20", "val30" }, { "val11", "val21", "val31" },
							{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
							{ "val11", "val21", "val31" }, { "val13", "val21", "val31" },
							{ "val12", "val25", "val31" }, { "val13", "val21", "val31" },
							{ "val11", "val21", "val31" }, { "val11", "val21", "val31" },
							{ "val12", "val23", "val31" }, { "val11", "val29", "val31" },
							{ "val11", "val21", "val31" }, { "val12", "val22", "val32" }, };
		TextTable tt = new TextTable(titles, tst);
		tt.setAddRowNumbering(true);
		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.printTable();
	}

	public void printTable() {
		printTable(System.out);
	}

	public void printTable(PrintStream ps) {

		// Find the maximum length of a string in each column
		int[] lengths = resolveColumnLengths();
		String separator = resolveSeparator(lengths);

		int rowCount = tableModel.getRowCount();
		int rowCountStrSize = Integer.toString(rowCount).length();
		String indexFormat1 = "%1$-" + rowCountStrSize + "s  |";
		String indexFormat2 = "%1$" + rowCountStrSize + "s. |";

		// Generate a format string for each column and calc totalLength
		int totLength = 0;
		String[] formats = new String[lengths.length];
		for (int i = 0; i < lengths.length; i++) {
			formats[i] = " %1$-" + lengths[i] + "s|" + (i + 1 == lengths.length ? "\n" : "");
			totLength += lengths[i];
		}

		indentAccordingToNumbering(ps, indexFormat1);
		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			ps.printf(formats[j], tableModel.getColumnName(j));
		}

		indentAccordingToNumbering(ps, indexFormat1);
		String headerSep = StringUtils.repeat("=", totLength + 5);
		ps.print(headerSep);
		ps.println("|");

		// Print 'em out
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			addSeparatorIfNeeded(ps, separator, indexFormat1, i);
			if (addRowNumbering) {
				ps.printf(indexFormat2, i + 1);
			}
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				ps.printf(formats[j], tableModel.getValueAt(i, j));
			}
		}
	}

	private int[] resolveColumnLengths() {
		int[] lengths = new int[tableModel.getColumnCount()];

		for (int col = 0; col < tableModel.getColumnCount(); col++) {
			for (int row = 0; row < tableModel.getRowCount(); row++) {
				Object val = tableModel.getValueAt(row, col);
				String valStr = val == null ? "" : String.valueOf(val);
				lengths[col] = Math.max(valStr.length(), lengths[col]);
			}
		}
		return lengths;
	}

	private String resolveSeparator(int[] lengths) {
		StringBuilder sepSb = new StringBuilder();

		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			lengths[j] = Math.max(tableModel.getColumnName(j).length(), lengths[j]);
			// add 1 because of the leading space in each column
			sepSb.append(StringUtils.repeat("-", lengths[j] + 1));
			sepSb.append("|");
		}
		String separator = sepSb.toString();
		return separator;
	}

	private void addSeparatorIfNeeded(PrintStream ps, String separator, String indexFormat1, int i) {
		if (!separatorPolicies.isEmpty() && hasSeparatorAt(i)) {
			indentAccordingToNumbering(ps, indexFormat1);
			ps.println(separator);
		}
	}

	private boolean hasSeparatorAt(int row) {
		for (SeparatorPolicy separatorPolicy : separatorPolicies) {
			if (separatorPolicy.hasSeparatorAt(row)) {
				return true;
			}
		}
		return false;
	}

	private void indentAccordingToNumbering(PrintStream ps, String indexFormat1) {
		if (addRowNumbering) {
			ps.printf(indexFormat1, "");
		}
	}

}
