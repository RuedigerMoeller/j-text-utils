package dnl.utils.text.table;

import java.io.PrintStream;

import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

public class TextTableRenderer {

	protected String[] formats;
	protected int[] lengths;
	
	protected TextTable textTable;
	
	protected TableModel tableModel;
	
	public TextTableRenderer(TextTable textTable) {
		this.textTable = textTable;
		this.tableModel = textTable.getTableModel();
	}		
	
	public void render(PrintStream ps, int indent){
		TableModel tableModel = textTable.getTableModel();
		String indentStr = StringUtils.repeat(" ", indent);
		
		// Find the maximum length of a string in each column
		resolveColumnLengths();
		String separator = resolveSeparator(lengths);

		int rowCount = tableModel.getRowCount();
		int rowCountStrSize = Integer.toString(rowCount).length();
		String indexFormat1 = "%1$-" + rowCountStrSize + "s  ";
		String indexFormat2 = "%1$" + rowCountStrSize + "s. ";

		// Generate a format string for each column and calc totalLength
		int totLength = resolveFormats();

		String headerStartSep = StringUtils.repeat("_", totLength + tableModel.getColumnCount() * 2);
		ps.print(indentStr);
		indentAccordingToNumbering(ps, indexFormat1);
		ps.println(headerStartSep);

		ps.print(indentStr);
		indentAccordingToNumbering(ps, indexFormat1);
		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			ps.printf(formats[j], tableModel.getColumnName(j));
		}

		indentAccordingToNumbering(ps, indexFormat1);
		String headerSep = StringUtils.repeat("=", totLength + tableModel.getColumnCount() * 2 - 1);
		ps.print(indentStr);
		ps.print("|");
		ps.print(headerSep);
		ps.println("|");

		// Print 'em out
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			addSeparatorIfNeeded(ps, separator, indexFormat1, i, indentStr);
			ps.print(indentStr);
			if (textTable.addRowNumbering) {
				if(!modelAllowsNumberingAt(i)){
					indentAccordingToNumbering(ps, indexFormat1);
				}
				else {
					ps.printf(indexFormat2, i + 1);
				}
			}
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				printValue(ps, i, j, false);
			}
		}
	}
	
	private void resolveColumnLengths() {
		lengths = new int[tableModel.getColumnCount()];

		for (int col = 0; col < tableModel.getColumnCount(); col++) {
			for (int row = 0; row < tableModel.getRowCount(); row++) {
				Object val = tableModel.getValueAt(row, col);
				String valStr = val == null ? "" : String.valueOf(val);
				lengths[col] = Math.max(valStr.length(), lengths[col]);
			}
		}
	}

	private String resolveSeparator(int[] lengths) {
		StringBuilder sepSb = new StringBuilder();

		for (int j = 0; j < tableModel.getColumnCount(); j++) {
			if(j == 0){
				sepSb.append("|");
			}
			lengths[j] = Math.max(tableModel.getColumnName(j).length(), lengths[j]);
			// add 1 because of the leading space in each column
			sepSb.append(StringUtils.repeat("-", lengths[j] + 1));
			sepSb.append("|");
		}
		String separator = sepSb.toString();
		return separator;
	}

	private void addSeparatorIfNeeded(PrintStream ps, String separator, String indexFormat1, int i, String indentStr) {
		if (!textTable.separatorPolicies.isEmpty() && textTable.hasSeparatorAt(i)
				|| ((tableModel instanceof TextTableModel) && ((TextTableModel) tableModel)
						.addSeparatorAt(i))		
		) {
			indentAccordingToNumbering(ps, indexFormat1);
			ps.print(indentStr);
			ps.println(separator);
		}
	}
	
	protected boolean modelAllowsNumberingAt(int row){
		if(row == 8){
			System.out.print("");
		}
		if (tableModel instanceof TextTableModel) {
			TextTableModel ttm = (TextTableModel) tableModel;
			return ttm.allowNumberingAt(row);
		}
		return textTable.addRowNumbering;
	}
	
	protected void printValue(PrintStream ps, int row, int col, boolean empty){
		int rowIndex = row;
		if (textTable.rowSorter != null) {
			rowIndex = textTable.rowSorter.convertRowIndexToModel(row);
		}
		Object value = empty ? "" : tableModel.getValueAt(rowIndex, col);
		ps.printf(formats[col], value);
	}
	
	private int resolveFormats() {
		int totLength = 0;
		formats = new String[lengths.length];
		for (int i = 0; i < lengths.length; i++) {
			StringBuilder sb = new StringBuilder();
			if (i == 0) {
				sb.append("|");
			}
			sb.append(" %1$-");
			sb.append(lengths[i]);
			sb.append("s|");
			sb.append(i + 1 == lengths.length ? "\n" : "");
			formats[i] = sb.toString();
			totLength += lengths[i];
		}
		return totLength;
	}
	
	private void indentAccordingToNumbering(PrintStream ps, String indexFormat1) {
		if (textTable.addRowNumbering) {
			ps.printf(indexFormat1, "");
		}
	}
}
