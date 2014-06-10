package graphmap;
import java.util.LinkedList;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

/**
 * The class that handles the undirected sparse graph of authors and tokens
 * 
 * @author Marissa Wilson
 *
 */

public class GlyphGraph {
	UndirectedSparseGraph<iToken,WeightedEdge> g;
	LinkedList<Author> authors = new LinkedList<Author>(); //TODO Do we really need this? Graph has this list and maybe that's all we need
	LinkedList<Glyph> glyphs = new LinkedList<Glyph>(); //TODO Do we really need this? Graph has this list and maybe that's all we need
	
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
	public void add(Author a){
		System.out.println("GlyphGraph: Add author if not present");
		//FIXME Check to see if it's already there (maybe the caller does this?)
		authors.add(a);
		g.addVertex(a);
	}
	
	public void add(Glyph a){
		System.out.println("GlyphGraph: Add glyph if not present");
		glyphs.add(a);
		g.addVertex(a);
	}
	
	public LinkedList<Author> getAuthors(){
		System.out.println("GlyphGraph: return list of authors");
		return authors;
	}
	
	public LinkedList<Glyph> getGlyph(){
		System.out.println("GlyphGraph: return list of glyphs");
		return glyphs;
	}
	
	/*
	 * Returns true if glyph added successfully
	 * Adds a glyph
	 * TODO If edge already exists, increment weight
	 */
	public boolean addGlyph(iToken glyph, WeightedEdge weight, iToken auth){
		System.out.println("GlyphGraph: Creates the edge association between Author and Glyph using WeightedEdge");
		g.addVertex(glyph);
		return g.addEdge(weight, glyph, auth);
		
	}
}
