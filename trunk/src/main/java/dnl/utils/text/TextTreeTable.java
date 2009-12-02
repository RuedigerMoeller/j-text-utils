package dnl.utils.text;

import java.io.PrintStream;

import javax.swing.table.TableModel;

/**
 * 
 * @author Daniel Orr
 * 
 */
public class TextTreeTable extends TextTable {

	private int hierarchicalColumn;
	
	public TextTreeTable(String[] columnNames, String[][] data) {
		super(columnNames, data);
		addSeparatorPolicy(new TreeTableSeparatorPolicy());
	}

	public TextTreeTable(TableModel tableModel, boolean addNumbering) {
		super(tableModel, addNumbering);
		addSeparatorPolicy(new TreeTableSeparatorPolicy());
	}

	public TextTreeTable(TableModel tableModel) {
		super(tableModel);
		addSeparatorPolicy(new TreeTableSeparatorPolicy());
	}

	protected void setHierarchicalColumn(int hierarchicalColumn) {
		this.hierarchicalColumn = hierarchicalColumn;
	}

	@Override
	protected void printValue(PrintStream ps, int row, int col, boolean c){
		boolean empty = false;
		if( row != 0 && col == hierarchicalColumn && !hasSeparatorAt(row)){
			empty = true;
		}
		super.printValue(ps, row, col, empty);
	}
	
	
	private class TreeTableSeparatorPolicy extends SeparatorPolicy{

		@Override
		boolean hasSeparatorAt(int row) {
			if(row == 0)
				return false;
			Object rowAgo = getValueAt(row-1, hierarchicalColumn);
			Object hierarchicalColumnVal = getValueAt(row, hierarchicalColumn);
			//System.out.println(row+ "> "+rowAgo+"-"+hierarchicalColumnVal);
			if(!hierarchicalColumnVal.equals(rowAgo)){
				return true;
			}
			return false;
		}
		
	}
	
}
