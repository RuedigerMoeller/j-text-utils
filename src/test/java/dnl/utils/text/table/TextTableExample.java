package dnl.utils.text.table;

public class TextTableExample {

	public static void main(String[] args) {
		String[] columnNames = {
				"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		
		TextTable tt = new TextTable(columnNames, data);
		tt.setAddRowNumbering(true);
//		tt.addSeparatorPolicy(new LastRowSeparatorPolicy());
		tt.setSort(0);
		tt.printTable();
	}
	
}
