package dnl.utils.text.table;

import org.junit.Test;

import dnl.utils.text.table.TextTable;
import dnl.utils.text.table.TextTreeTable;

public class TextTreeTableTest {

	@Test
	public void printTableWithThreeColumns(){
		String[] titles = { "column1", "column2", "column3" };
		String[][] data = { 
				{ "val10", "val20", "val30" }, 
				{ "val13", "val20", "val30" }, 
				{ "val12", "val21", "val31" }, 
				{ "val11", "val21", "val31" }, 
				{ "val12", "val25", "val31" }, 
				{ "val11", "val21", "val31" }, 
				};
		TextTreeTable tt = new TextTreeTable(titles, data);
		tt.setAddRowNumbering(true);
//		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.setSort(0);
		tt.printTable(System.out, 5);		
	}

	@Test
	public void printTableWithTwoColumns(){
		String[] titles = { "column1", "column2"};
		String[][] data = { 
				{ "val10", "val20"}, 
				{ "val13", "val20"}, 
				{ "val12", "val21"}, 
				{ "val11", "val21"}, 
				{ "val12", "val25"}, 
				{ "val11", "val21"}, 
		};
		TextTable tt = new TextTable(titles, data);
//		tt.setAddRowNumbering(true);
//		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.setSort(0);
		tt.printTable();		
	}

	@Test
	public void printTableWithOneColumn(){
		String[] titles = { "column1"};
		String[][] data = { 
				{ "val10"}, 
				{ "val13"}, 
				{ "val12"}, 
				{ "val11"}, 
				{ "val12"}, 
				{ "val11"}, 
		};
		TextTable tt = new TextTable(titles, data);
//		tt.setAddRowNumbering(true);
//		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.setSort(0);
		tt.printTable();		
	}
}
