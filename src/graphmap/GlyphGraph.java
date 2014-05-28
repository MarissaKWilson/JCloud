package graphmap;
import java.util.ArrayList;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

/**
 * The class that handles the undirected sparse graph of authors and tokens
 * 
 * @author Marissa Wilson
 *
 */

public class GlyphGraph {
	UndirectedSparseGraph<iToken,WeightedEdge> g;
	ArrayList<iToken> authors;
	/*
	 * Glyph Graph constructor
	 * Creates a new undirected sparse graph
	 */
	public GlyphGraph(){
		g = new UndirectedSparseGraph<iToken,WeightedEdge>();
	}
	/*
	 * Creates a new author with the name
	 * Could also include a list of sourceCodeFiles to attach
	 * to the author.
	 * Not sure where to have those added
	 * Also  creates new vertex for author in graph
	 */
	public void addAuthor(iToken a){
		authors.add(a);
		g.addVertex(a);
	}
	
	public ArrayList<iToken> getAuthors(){
		return authors;
	}
	
	/*
	 * Returns true if glyph added successfully
	 * Adds a glyph
	 * TODO If edge already exists, increment weight
	 */
	public boolean addGlyph(iToken glyph, WeightedEdge weight, iToken auth){
		g.addVertex(glyph);
		return g.addEdge(weight, glyph, auth);
		
	}
}
