package javaparser;

import gitparser.ISummarizable;
import graphmap.Author;
import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.SourceCodeFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Handles the parsing of the java
 * Takes out Java keywords
 * 
 * Contains a Java keyword dictionary
 * Checks each element within the parsed git file to see if in the dictionary
 * if not in dictionary create glyph
 * 
 * @author M
 *
 */
public class JParser {
	//java Dictionary?
	LinkedList<Glyph> unfilteredGlyphs = new LinkedList<Glyph>();
	Map<Author, Set<ISummarizable>> contributions = null;
	private final String javaDelimiters = "[ ,;\\(\\)\\[\\]<>\\{\\}\\.:&\\|\\/\\+\\-]";
	
	public JParser(){
		
	}

	
	/**
	 * Given all of these source code files, create the glyph objects. Link the files to the glyphs.
	 * 
	 * @param files
	 * @throws FileNotFoundException 
	 */
	public void populateGlyphs(List<SourceCodeFile> sourceFiles) throws FileNotFoundException {
		System.out.println("	JParser: Parse file for keyword identification");
		for(SourceCodeFile f: sourceFiles){
			contributions = f.getContributions();
			for(Author a: f.getAuthors()){
				Set<ISummarizable> allFiles = contributions.get(a);
				Iterator<ISummarizable> itr = allFiles.iterator();
				while(itr.hasNext()){
					ISummarizable fileSummary= itr.next();
					File file = fileSummary.getFile();
					String filePath = file.getPath();
					FileInputStream in = new FileInputStream(filePath);
					
					
				}
			}
		}
		// TODO Auto-generated method stub
		
		//get each commit
			//new buffered reader
			//for each token
				//is !in ignore list
					//make new glyph
					//add to SCF glyph list
			//close buffered reader
		
		System.out.println("	JParser: For each SCF create new Glyph, add to SCF list");
		
		//Get each file
		//Get each string
		//make into glyph
		//add glyph to SourceCodeFile list
		
		
	}
	
	public void makeTokens(LinkedList<String> tokens, Author a, SourceCodeFile sfc){
		for(String t: tokens){
			Glyph g = new Glyph (t);
			sfc.addGlyph(g);
			a.addWeight(g, 1);
		}
	}
	
}
