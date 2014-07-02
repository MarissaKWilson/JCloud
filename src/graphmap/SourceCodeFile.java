package graphmap;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jgit.revwalk.RevCommit;
/**
 * sourceCodeFile holds the file associated with each commit and each
 * Author. File is the parsed java file, diffs have been removed already
 * 
 * Could also return just the glyphs from each file?
 * @author M
 *
 */
public class SourceCodeFile {
	RevCommit c;
	LinkedList<Glyph> glyphs = new LinkedList<Glyph>();
	LinkedList<Author> authors = new LinkedList<Author>();
	LinkedList<String> diffedTokens = new LinkedList<String>();
	//I don't think this needs to be a list. Any commit should only have one author, right?
	Author author = new Author(" ");
	/*
	 * Takes in a file as constructor, sets as file
	 */
	public SourceCodeFile(RevCommit commit){
		this.c = commit;
		System.out.println("		SourceCodeFile: Initiated");
	}

	/*
	 * Returns the stored file
	 */
	public RevCommit getFile(){
		System.out.println("SourceCodeFile: return stored file");
		return c;
	}
	
	public LinkedList<Glyph> getGlyphs(){
		System.out.println("SourceCodeFile: return stored glyphs");
		return glyphs;
	}
	
	public void cullGlyph(Glyph g){
		System.out.println("SourceCodeFile: Remove unused glyph");
		glyphs.remove(g);
	}
	
	public Author getAuthor(){
		System.out.println("SourceCodeFile: return stored author");
		return author;
	}
	
	public void setAuthor(Author a){
		System.out.println("		SourceCodeFile: set author");
		authors.add(a);
	}
	
	public void setDiffed(List<String> tokens){
		diffedTokens = tokens;
	}
	
	public List getTokens(){
		return diffedTokens;
	}
	
	//TODO hashcode
}
