package dnl.utils.text;

import javax.swing.table.AbstractTableModel;

public abstract class TextTableModel extends AbstractTableModel {

	public abstract boolean showNumberingAt(int row);
	public abstract boolean addSeparatorAt(int row);
}
