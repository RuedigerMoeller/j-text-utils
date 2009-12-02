package dnl.utils.text;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

	protected RowSorter<?> rowSorter;
	
	protected String[] formats;
	
	protected boolean headless;

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

	public void setSort(int column) {
		setSort(column, SortOrder.ASCENDING);
	}

	public void setSort(int column, SortOrder sortOrder) {
		rowSorter = new TableRowSorter<TableModel>(this.tableModel);
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		// sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(column, sortOrder));
		rowSorter.setSortKeys(sortKeys);
	}

	public void printTable() {
		printTable(System.out, 0);
	}

	public void printTable(PrintStream ps, int indent) {
		String indentStr = StringUtils.repeat(" ", indent);
		
		// Find the maximum length of a string in each column
		int[] lengths = resolveColumnLengths();
		String separator = resolveSeparator(lengths);

		int rowCount = tableModel.getRowCount();
		int rowCountStrSize = Integer.toString(rowCount).length();
		String indexFormat1 = "%1$-" + rowCountStrSize + "s  ";
		String indexFormat2 = "%1$" + rowCountStrSize + "s. ";

		// Generate a format string for each column and calc totalLength
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
			if (addRowNumbering) {
				if(!addRowNumberingAt(rowCount)){
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

	protected boolean addRowNumberingAt(int row){
		if (tableModel instanceof TextTableModel) {
			TextTableModel ttm = (TextTableModel) tableModel;
			return ttm.addSeparatorAt(row);
		}
		return false;
	}
	
	protected void printValue(PrintStream ps, int row, int col, boolean empty){
		int rowIndex = row;
		if (rowSorter != null) {
			rowIndex = rowSorter.convertRowIndexToModel(row);
		}
		Object value = empty ? "" : tableModel.getValueAt(rowIndex, col);
		ps.printf(formats[col], value);
	}
	
	protected Object getValueAt(int row, int column){
		int rowIndex = row;
		if (rowSorter != null) {
			rowIndex = rowSorter.convertRowIndexToModel(row);
		}
		return tableModel.getValueAt(rowIndex, column);
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
		if (!separatorPolicies.isEmpty() && hasSeparatorAt(i)) {
			indentAccordingToNumbering(ps, indexFormat1);
			ps.print(indentStr);
			ps.println(separator);
		}
	}

	protected boolean hasSeparatorAt(int row) {
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
