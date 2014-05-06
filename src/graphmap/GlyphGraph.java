package graphmap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;

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
	 * to the author. Not sure where to have those added
	 * Also  creates new vertex for author in graph
	 */
	public void addAuthor(String name){
		Author a = new Author(name);
		authors.add(a);
		g.addVertex(a);
	}
	
	/*
	 * Returns true if glyph added successfully
	 * Adds a glyph
	 */
	public boolean addGlyph(iToken glyph, WeightedEdge weight, iToken auth){
		g.addVertex(glyph);
		return g.addEdge(weight, glyph, auth);
		
	}
}
