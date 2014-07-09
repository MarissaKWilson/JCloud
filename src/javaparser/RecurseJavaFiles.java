package javaparser;

import java.io.File;
import java.io.FilenameFilter;

public class RecurseJavaFiles extends RecurseFiles {
	public RecurseJavaFiles() {
		this(false);
	}

	public RecurseJavaFiles(boolean includeDirectories) {
		super(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		}, includeDirectories);
	}
}
