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
	ArrayList<Author> authors = new ArrayList<Author>(); //TODO Do we really need this? Graph has this list and maybe that's all we need
	ArrayList<Glyph> glyphs = new ArrayList<Glyph>(); //TODO Do we really need this? Graph has this list and maybe that's all we need
	
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
		//FIXME Check to see if it's already there (maybe the caller does this?)
		authors.add(a);
		g.addVertex(a);
	}
	
	public void add(Glyph a){
		glyphs.add(a);
		g.addVertex(a);
	}
	
	public ArrayList<Author> getAuthors(){
		return authors;
	}
	
	public ArrayList<Glyph> getGlyph(){
		return glyphs;
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
