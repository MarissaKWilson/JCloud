package display;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graphmap.Glyph;

/**
 * Parses through all glyphs and assigns font sizes
 * Finds the largest weighted edge, sets that as maximum
 * Finds the smallest weighted edge, sets that as minimum
 * Offset is created by comparing the largest weight of a glyph
 * And the remaining weigths of the same glyph
 * In descending order the 
 * @author M
 *
 */
public class FontSizer {
	int max=0;
	int min=1;
	/*
	 * Sets max and min to the largest and smallest weights
	 */
	public FontSizer(UndirectedSparseGraph g){
		//for(Glyph glyph:g){
		//	if(g.Weight(glyph)>max){
		//		max=g.Weight;
		//	}
		//}
		
	}
	/*
	 * Takes in a Glyph to view all it's edges
	 */
	public void makeSize(Glyph glyph){
		//Makes font size
		int size = ceiling(log(((72*(glyph.Weight - min))/max-min)));
		glyph.setSize(size);
		
	}
	
	
}
