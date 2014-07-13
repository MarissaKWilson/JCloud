package graphmap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * The class that handles the undirected sparse graph of authors and tokens
 * 
 * @author Marissa Wilson
 *
 */

public class GlyphGraph {
	UndirectedSparseGraph<iToken, WeightedEdge> g;
	Set<Author> authors = new HashSet<Author>(); 
	Set<Glyph> glyphs = new HashSet<Glyph>(); 

	/*
	 * Glyph Graph constructor Creates a new undirected sparse graph
	 */
	public GlyphGraph() {
		g = new UndirectedSparseGraph<iToken, WeightedEdge>();
	}

	/*
	 * Creates a new author with the name Could also include a list of
	 * sourceCodeFiles to attach to the author. Not sure where to have those
	 * added Also creates new vertex for author in graph
	 */
	public void addAuthor(Author author) {
		authors.add(author);
		g.addVertex(author);
	}

	public void addGlyph(Glyph glyph) {
		System.out.println("GlyphGraph: Add glyph if not present");
		glyphs.add(glyph);
		g.addVertex(glyph);
	}

	public Set<Author> getAuthors() {
		System.out.println("GlyphGraph: return list of authors");
		return authors;
	}

	public Set<Glyph> getGlyphs() {
		System.out.println("GlyphGraph: return list of glyphs");
		return glyphs;
	}

	/*
	 * Returns true if glyph added successfully Adds a glyph TODO If edge
	 * already exists, increment weight
	 */
	public boolean addGlyph(iToken glyph, WeightedEdge weight, iToken auth) {
		System.out.println( "GlyphGraph: Creates the edge association between Author and Glyph using WeightedEdge");
		g.addVertex(glyph);
//		addGlyph(glyph);
//		addAuthor(auth);
		return g.addEdge(weight, glyph, auth);
	}

	public void addEdge(WeightedEdge weight, Pair<iToken> pair,
			EdgeType edgeType) {
		addEdge(weight, pair, edgeType);
	}
	
	
}
