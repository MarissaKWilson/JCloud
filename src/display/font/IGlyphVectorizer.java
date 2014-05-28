package display.font;

import graphmap.Glyph;

import java.awt.Font;
import java.awt.font.GlyphVector;

/**
 * Parses through all glyphs and assigns font sizes Finds the largest weighted
 * edge, sets that as maximum Finds the smallest weighted edge, sets that as
 * minimum Offset is created by comparing the largest weight of a glyph And the
 * remaining weigths of the same glyph In descending order the
 * 
 * @author M
 * 
 */
public interface IGlyphVectorizer {


	// TODO Design a way to offset the font size based on other author's usage.


	/*
	 * Takes in a Glyph to view all it's edges
	 */
	public GlyphVector forge(Glyph glyph); 
	

}
