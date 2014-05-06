package graphmap;

import java.io.File;
/**
 * sourceCodeFile holds the file associated with each commit and each
 * Author. File is the parsed java file, diffs have been removed already
 * 
 * Could also return just the glyphs from each file?
 * @author M
 *
 */
public class sourceCodeFile {
	File f;
	/*
	 * Takes in a file as constructor, sets as file
	 */
	public sourceCodeFile(File f){
		this.f = f;
	}

	/*
	 * Returns the stored file
	 */
	public File getFile(){
		return f;
	}
}
