Gives text representations of common data views such as tree and table.
<br>
<br>
<b>A table example:</b>
<pre><code>______________________________________________________________<br>
| First Name| Last Name| Sport        | # of Years| Vegetarian|<br>
|=============================================================|<br>
| Jane      | White    | Speed reading| 20        | true      |<br>
| Joe       | Brown    | Pool         | 10        | false     |<br>
| John      | Doe      | Rowing       | 3         | true      |<br>
| Kathy     | Smith    | Snowboarding | 5         | false     |<br>
| Sue       | Black    | Knitting     | 2         | false     |<br>
</code></pre>
<br>
<br>
Read<br>
<a href='http://code.google.com/p/j-text-utils/wiki/UsingTextTable'>Using TextTable</a>
to see how it is done.<br>
<br>
<br>
<br>
<b>A tree example:</b>
<pre><code>-- target/<br>
   |-- j-text-utils-0.3.jar<br>
   |-- maven-archiver/<br>
   |   `-- pom.properties<br>
   |-- classes/<br>
   |   `-- dnl/<br>
   |      `-- utils/<br>
   |         `-- text/<br>
   |            |-- TextTreeTable$TreeTableSeparatorPolicy.class<br>
   |            |-- SeparatorPolicy.class<br>
   |            |-- TextTable.class<br>
   |            |-- TextTreeTable.class<br>
   |            |-- TextTableRenderer.class<br>
   |            |-- tree/<br>
   |            |   `-- TextTree.class<br>
   |            |-- TextTableModel.class<br>
   |            |-- TextTreeTable$1.class<br>
   |            |-- LastRowSeparatorPolicy.class<br>
   |            `-- TextTreeTableRenderer.class<br>
   |-- test-classes/<br>
   |   `-- dnl/<br>
   |      `-- utils/<br>
   |         `-- text/<br>
   |            |-- TextTreeTableTest.class<br>
   |            |-- TextTableTest.class<br>
   |            `-- tree/<br>
   |               |-- FsObject.class<br>
   |               |-- TextTreeTest.class<br>
   |               `-- FsModel.class<br>
   `-- surefire-reports/<br>
      |-- dnl.utils.text.TextTreeTableTest.txt<br>
      |-- TEST-dnl.utils.text.tree.TextTreeTest.xml<br>
      |-- TEST-dnl.utils.text.TextTreeTableTest.xml<br>
      |-- TEST-dnl.utils.text.TextTableTest.xml<br>
      |-- dnl.utils.text.TextTableTest.txt<br>
      `-- dnl.utils.text.tree.TextTreeTest.txt<br>
</code></pre>

Example Code:<br>
<a href='http://code.google.com/p/j-text-utils/wiki/UsingTextTree'>Using TextTree</a>

<hr />


<h3>Adding j-text-utils to your Maven POM</h3>

The dependency:<br>
<pre><code>&lt;dependency&gt;<br>
	&lt;groupId&gt;dnl.utils&lt;/groupId&gt;<br>
	&lt;artifactId&gt;j-text-utils&lt;/artifactId&gt;<br>
	&lt;version&gt;0.3.3&lt;/version&gt;<br>
&lt;/dependency&gt;<br>
</code></pre>

And add the following repository:<br>
<pre><code>&lt;repositories&gt;<br>
	&lt;repository&gt;<br>
		&lt;id&gt;d-maven&lt;/id&gt;<br>
		&lt;url&gt;http://d-maven.googlecode.com/svn/trunk/repo&lt;/url&gt;<br>
	&lt;/repository&gt;<br>
&lt;/repositories&gt;<br>
</code></pre>
<hr />
<b>'Provided' dependencies:</b>
<br><br>
Note that if you use the <code>GuavaTableModel</code> you'll need to add a dependency for guava in your pom.<br>
<br>Same goes for CSV functionality which requires opencsv, for example:<br>
<pre><code>&lt;dependency&gt;<br>
	&lt;groupId&gt;net.sf.opencsv&lt;/groupId&gt;<br>
	&lt;artifactId&gt;opencsv&lt;/artifactId&gt;<br>
	&lt;version&gt;2.3&lt;/version&gt;<br>
&lt;/dependency&gt;<br>
</code></pre>