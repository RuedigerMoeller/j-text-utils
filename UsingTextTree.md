The tree example on the home page, which is from this project tests, is something that resembles the NIX 'tree' command.
<br>
<br>
The tree model:<br>
<pre><code>public class FsModel implements javax.swing.tree.TreeModel {<br>
<br>
	FsObject root;<br>
<br>
	public FsModel(File root) {<br>
		this.root = new FsObject(root);<br>
	}<br>
<br>
	@Override<br>
	public Object getRoot() {<br>
		return root;<br>
	}<br>
<br>
	@Override<br>
	public Object getChild(Object parent, int index) {<br>
		FsObject fsObject = (FsObject) parent;<br>
		File[] files = fsObject.f.listFiles();<br>
		return new FsObject(files[index]);<br>
	}<br>
<br>
	@Override<br>
	public int getChildCount(Object parent) {<br>
		FsObject fsObject = (FsObject) parent;<br>
		if (!fsObject.f.isDirectory()) {<br>
			return 0;<br>
		}<br>
		return fsObject.f.list().length;<br>
	}<br>
<br>
	@Override<br>
	public boolean isLeaf(Object node) {<br>
		FsObject fsObject = (FsObject) node;<br>
		return !fsObject.f.isDirectory();<br>
	}<br>
        ...<br>
}<br>
<br>
public class FsObject {<br>
	File f;<br>
<br>
	public FsObject(File f) {<br>
		this.f = f;<br>
	}<br>
<br>
	public String toString() {<br>
		String s = f.getName();<br>
		if (f.isDirectory()) {<br>
			s += "/";<br>
		}<br>
		return s;<br>
	}<br>
}<br>
</code></pre>


And the code:<br>
<pre><code>TreeModel tm = new FsModel(new File("./target"));<br>
TextTree tt = new TextTree(tm);                  <br>
System.out.println(tt);                          <br>
</code></pre>