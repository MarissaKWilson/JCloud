package display;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graphmap.iToken;

/**
 * Handles the graph and iteratively parses through it
 * Calls the other display classes as needed
 * Sort of a display package main method
 * @author M
 *
 */
public class DisplayGraph {
	UndirectedSparseGraph<iToken,Long> g;
	
	public DisplayGraph(UndirectedSparseGraph<iToken,Long> glyphgraph){
		g=glyphgraph;
		createFontSizes();
		getAuthorShape();
		/*
		 * How to iterate over GlyphGraph?
		 */
//		for(Glyph i:g){
//			Spiral.spiralOut(i);
//		}
		
		ColorAssigner.AssignColors();
	}
	
	/*
	 * For each glyph in the graph
	 * call FontSizer on the glyph
	 */
	public void createFontSizes(){
		
	}
	/*
	 * Calls AuthorShape class 
	 * Determines the authors central points
	 * Used for the spiral start
	 */
	public void getAuthorShape(){
		
	}
	
	
}
