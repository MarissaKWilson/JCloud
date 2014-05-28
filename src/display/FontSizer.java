package display;

import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.iToken;

/**
 * Parses through all glyphs and assigns font sizes Finds the largest weighted
 * edge, sets that as maximum Finds the smallest weighted edge, sets that as
 * minimum Offset is created by comparing the largest weight of a glyph And the
 * remaining weigths of the same glyph In descending order the
 * 
 * @author M
 * 
 */
abstract public class FontSizer {
	int max = 0;
	int min = 1;

	/*
	 * Sets max and min to the largest and smallest weights
	 */
	public FontSizer(GlyphGraph g) {

	}
	// TODO Design a way to offset the font size based on other author's usage.
	

	abstract public Double calcWeight(iToken t, GlyphGraph g);

	/*
	 * Takes in a Glyph to view all it's edges
	 */
	public void makeSize(Glyph glyph) {

		// Makes font size
		// FIXME Get weighted edge another way
		// int size = ceiling(log(((72*(glyph.Weight - min))/max-min)));
		// glyph.setSize(size);

	}
	

}
