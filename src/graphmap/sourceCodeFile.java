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
	//LinkedList<Author> authors = new LinkedList<Author>();
	//I don't think this needs to be a list. Any commit should only have one author, right?
	Author author = new Author(" ");
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
		System.out.println("SourceCodeFile: return stored file");
		return f;
	}
	
	public List<Glyph> getGlyphs(){
		System.out.println("SourceCodeFile: return stored glyphs");
		return glyphs;
	}
	
	public Author getAuthor(){
		System.out.println("SourceCodeFile: return stored author");
		return author;
	}
	
	public void setAuthor(Author a){
		System.out.println("SourceCodeFile: set author");
		author=a;
	}
}
