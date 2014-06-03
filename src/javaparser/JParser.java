package javaparser;

import graphmap.Glyph;
import graphmap.SourceCodeFile;

import java.io.File;
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

	public File parse(File diffed) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Given all of these source code files, create the glyph objects. Link the files to the glyphs.
	 * 
	 * @param files
	 */
	public void populateGlyphs(List<SourceCodeFile> files) {
		System.out.println("JParser: Check each element, if not Java keyword create Glyph");
		// TODO Auto-generated method stub
		
		Glyph g = new Glyph("str");
		
	}

	
	/*
	 * Heres a file
	 * Give me the tokens in the glyph graph without edges
	 * Associate file with token
	 */
	
}
