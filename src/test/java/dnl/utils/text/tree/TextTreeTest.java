package dnl.utils.text.tree;

import java.io.File;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

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

	private static class FsObject {
		File f;

		public FsObject(File f) {
			this.f = f;
		}

		public String toString() {
			String s = f.getName();
			if(f.isDirectory()){
				s += "/";
			}
			return s;
		}
	}

	private static class FsModel implements TreeModel {

		FsObject root;

		public FsModel(File root) {
			this.root = new FsObject(root);
		}

		@Override
		public Object getRoot() {
			return root;
		}

		@Override
		public Object getChild(Object parent, int index) {
			FsObject fsObject = (FsObject) parent;
			File[] files = fsObject.f.listFiles();
			return new FsObject(files[index]);
		}

		@Override
		public int getChildCount(Object parent) {
			FsObject fsObject = (FsObject) parent;
			if (!fsObject.f.isDirectory()) {
				return 0;
			}
			return fsObject.f.list().length;
		}

		@Override
		public boolean isLeaf(Object node) {
			FsObject fsObject = (FsObject) node;
			return !fsObject.f.isDirectory();
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			return 0;
		}

		@Override
		public void addTreeModelListener(TreeModelListener l) {
		}

		@Override
		public void removeTreeModelListener(TreeModelListener l) {
		}

	}
}
