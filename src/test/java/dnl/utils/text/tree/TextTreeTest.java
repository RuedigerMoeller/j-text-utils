package dnl.utils.text.tree;

import java.io.File;

import javax.swing.tree.TreeModel;

import org.junit.Test;

/**
 * 
 * @author Daniel Orr
 * 
 */
public class TextTreeTest {

	@Test
	public void printTree() {
		TreeModel tm = new FsModel(new File("./target"));
		TextTree tt = new TextTree(tm);
		System.out.println(tt);
	}

}
