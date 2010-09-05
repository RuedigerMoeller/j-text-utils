package dnl.utils.text.tree;

import java.io.File;

public class FsObject {
	File f;

	public FsObject(File f) {
		this.f = f;
	}

	@Override
	public String toString() {
		String s = f.getName();
		if (f.isDirectory()) {
			s += "/";
		}
		return s;
	}
}
