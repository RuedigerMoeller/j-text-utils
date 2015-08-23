Here is an example that is based on data from JTable's tutorial

```
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
// this adds the numbering on the left      
tt.setAddRowNumbering(true);      
// sort by the first column                              
tt.setSort(0);                                                 
tt.printTable();                                               
```
<br>
The output table looks similar to the one on the home page, only it has numbering.<br>
<br>
<br>
<pre><code>   ______________________________________________________________<br>
   | First Name| Last Name| Sport        | # of Years| Vegetarian|<br>
   |=============================================================|<br>
1. | Jane      | White    | Speed reading| 20        | true      |<br>
2. | Joe       | Brown    | Pool         | 10        | false     |<br>
3. | John      | Doe      | Rowing       | 3         | true      |<br>
4. | Kathy     | Smith    | Snowboarding | 5         | false     |<br>
5. | Sue       | Black    | Knitting     | 2         | false     |<br>
</code></pre>