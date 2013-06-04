package dnl.utils.text.table;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import dnl.utils.text.table.GuavaTableModel;
import dnl.utils.text.table.TextTable;

public class GuavaTableModelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFullTable() {
		Table<Integer, String, String> table = HashBasedTable.create();
		table.put(0, "a", "0a");
		table.put(0, "b", "0b");
		table.put(1, "a", "1a");
		table.put(1, "b", "1b");
		table.put(2, "a", "2a");
		table.put(2, "b", "2b");
		GuavaTableModel<String> tableModel = new GuavaTableModel<>(table);
		assertEquals(2, tableModel.getColumnCount());
		assertEquals("a", tableModel.getColumnName(0));
		assertEquals("b", tableModel.getColumnName(1));
		assertEquals(3, tableModel.getRowCount());
		assertEquals("1a", tableModel.getValueAt(1, 0));
		TextTable tt = new TextTable(tableModel);
		tt.printTable();
	}

	@Test
	public void testPartialTable() {
		Table<Integer, String, String> table = HashBasedTable.create();
		table.put(1, "a", "1a");
		table.put(3, "a", "2a");
		table.put(3, "b", "2b");
		GuavaTableModel<String> tableModel = new GuavaTableModel<>(table);
		assertEquals(2, tableModel.getColumnCount());
		assertEquals("a", tableModel.getColumnName(0));
		assertEquals("b", tableModel.getColumnName(1));
		// guava's table contract enables empty lines. lines with index 0 and 2
		// are virtual.
		assertEquals(4, tableModel.getRowCount());
		// assertEquals("1a", tableModel.getValueAt(1, 0));
		TextTable tt = new TextTable(tableModel);
		tt.printTable();
	}
}
