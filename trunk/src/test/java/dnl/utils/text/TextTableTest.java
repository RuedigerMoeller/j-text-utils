package dnl.utils.text;

import org.junit.Test;

public class TextTableTest {

	@Test
	public void printTableWithThreeColumns(){
		String[] titles = { "cotttumn1", "colu345345mn2", "columnfff3" };
		String[][] data = { 
				{ "val10", "val20", "val30" }, 
				{ "val13", "val20", "val30" }, 
				{ "val12", "val21", "val31" }, 
				{ "val11", "val21", "val31" }, 
				{ "val12", "val25", "val31" }, 
				{ "val11", "val21", "val31" }, 
				};
		TextTable tt = new TextTable(titles, data);
//		tt.setAddRowNumbering(true);
//		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.setSort(0);
		tt.printTable();		
	}

	@Test
	public void printTableWithTwoColumns(){
		String[] titles = { "cotttumn1", "colu345345mn2"};
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
		String[] titles = { "cotttumn1"};
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
