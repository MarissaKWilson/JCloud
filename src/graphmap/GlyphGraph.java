package graphmap;
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
	/*
	 * Glyph Graph constructor
	 * Creates a new undirected sparse graph
	 */
	public GlyphGraph(){
		g = new UndirectedSparseGraph<iToken,WeightedEdge>();
	}
	
	public Collection<iToken> getTokens(){
		return g.getVertices();
	}
	/*
	 * addAuthor method takes in author iToken
	 * Creates a new vertex on the graph for the author token
	 */
	public void addAuthor(iToken author){
		//Vertex v1 = (Vertex) g.addVertex(new UndirectedSparseVertex(author));
		//return v1
		g.addVertex(author);
	}
	
	/*
	 * addGlyph takes in an iToken of a glyph and the vertex of an author
	 * Creates a new vertex for the glyph
	 * Creates an edge between glyph and author
	 */
	public void addGlyph(iToken glyph, iToken author){
		//Vertex v2 = (Vertex) g.addVertex(new UndirectedSparseVertex(glyph));
		//UndirectedEdge e1 = (Edge) g.addEdge(author, v2);
		//g.addEdge(weight, author, glyph);
	}
	
}
