package javaparser;

import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.SourceCodeFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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

	public SourceCodeFile parse(SourceCodeFile diffed) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Given all of these source code files, create the glyph objects. Link the files to the glyphs.
	 * 
	 * @param files
	 */
	public void populateGlyphs(List<SourceCodeFile> files) {
		System.out.println("JParser: Parse file for keyword identification");
		// TODO Auto-generated method stub
		int i = 0;
		files.set(i, parse(files.get(i)));
		
		System.out.println("Create new Glyph, add to list");
		int e=0;
		
		//Get each file
		//Get each string
		//make into glyph
		//add glyph to SourceCodeFile list
		
		
	}

	
	/*
	 * Heres a file
	 * Give me the tokens in the glyph graph without edges
	 * Associate file with token
	 */
	
}
