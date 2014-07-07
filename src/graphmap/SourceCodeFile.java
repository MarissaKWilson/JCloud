package graphmap;

import gitparser.ISummarizable;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javaparser.JavaClassSummarizable;

import org.eclipse.jgit.revwalk.RevCommit;
/**
 * sourceCodeFile holds the file associated with each commit and each
 * Author. File is the parsed java file, diffs have been removed already
 * 
 * Could also return just the glyphs from each file?
 * @author M
 *
 */
public class SourceCodeFile implements ISummarizable{
	RevCommit c;
	LinkedList<Glyph> glyphs = new LinkedList<Glyph>();
	LinkedList<File> files = new LinkedList<File>();
	LinkedList<Author> authors = new LinkedList<Author>();
	LinkedList<String> diffedTokens = new LinkedList<String>();
	//I don't think this needs to be a list. Any commit should only have one author, right?
	Author author = new Author(" ");
	Map<Author, Set<ISummarizable>> contributions;
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
	public RevCommit getCommit(){
		System.out.println("SourceCodeFile: return stored file");
		return c;
	}
	
	public LinkedList<File> getFiles(){
		return files;
	}
	 
	public void addFile(File f){
		files.add(f);
	}
	
	public LinkedList<Glyph> getGlyphs(){
		System.out.println("SourceCodeFile: return stored glyphs");
		return glyphs;
	}
	
	public void cullGlyph(Glyph g){
		System.out.println("SourceCodeFile: Remove unused glyph");
		glyphs.remove(g);
	}
	
	public LinkedList<Author> getAuthors(){
		System.out.println("SourceCodeFile: return stored author");
		return authors;
	}
	
	public void setAuthor(Author a){
		System.out.println("		SourceCodeFile: set author");
		authors.add(a);
	}
	
	public void setDiffed(LinkedList<String> tokens){
		diffedTokens = tokens;
	}
	
	public LinkedList<String> getTokens(){
		return diffedTokens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SourceCodeFile other = (SourceCodeFile) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

	public void addContribution(Author a,Map<Author, Set<ISummarizable>> aContribution) {
		if(contributions.get(a)!=null){
			contributions.get(a).addAll(aContribution.get(a));
		}else if (contributions.get(a) == null){
			contributions.get(a).addAll(aContribution.get(a));
		}
		//This ended up being the same, this doesn't seem right
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
