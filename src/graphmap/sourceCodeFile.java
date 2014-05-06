package graphmap;

import java.io.File;
import java.util.ArrayList;
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
	
	public ArrayList<iToken> getGlyphs(){
		ArrayList<iToken> glyphs = new ArrayList<iToken>();
		//Get each string from the file
		//Add to glyphs
		return glyphs;
	}
}
