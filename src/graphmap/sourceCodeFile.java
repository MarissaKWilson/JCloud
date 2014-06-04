package graphmap;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
/**
 * sourceCodeFile holds the file associated with each commit and each
 * Author. File is the parsed java file, diffs have been removed already
 * 
 * Could also return just the glyphs from each file?
 * @author M
 *
 */
public class SourceCodeFile {
	File f;
	LinkedList<Glyph> glyphs = new LinkedList<Glyph>();
	LinkedList<Author> authors = new LinkedList<Author>();
	/*
	 * Takes in a file as constructor, sets as file
	 */
	public SourceCodeFile(File f){
		this.f = f;
	}

	/*
	 * Returns the stored file
	 */
	public File getFile(){
		return f;
	}
	
	public List<Glyph> getGlyphs(){
		return glyphs;
	}
	
	public List<Author> getAuthors(){
		return authors;
	}
}
