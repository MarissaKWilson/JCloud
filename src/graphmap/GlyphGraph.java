package graphmap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LinkedMap;

import edu.uci.ics.jung.graph.UndirectedGraph;
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
	UndirectedGraph<iToken, WeightedEdge> g;
	Set<iToken> authors = new HashSet<iToken>(); 
	Set<iToken> glyphs = new HashSet<iToken>(); 

	/*
	 * Glyph Graph constructor Creates a new undirected sparse graph
	 */
	public GlyphGraph(UndirectedGraph<iToken, WeightedEdge> undirectedGraph) {
		this.g = undirectedGraph;
	}
	
	public static Factory<UndirectedGraph<iToken, WeightedEdge>> makeFactory(){
		Factory<UndirectedGraph<iToken, WeightedEdge>> fact = UndirectedSparseGraph.getFactory();
		System.out.println(fact.toString());
		return fact;
		
	}

	/*
	 * Creates a new author with the name Could also include a list of
	 * sourceCodeFiles to attach to the author. Not sure where to have those
	 * added Also creates new vertex for author in graph
	 */
	public void addAuthor(Author author) {
		authors.add(author);
//		g.addVertex(author);
	}

	public void addGlyph(Glyph glyph) {
//		System.out.println("GlyphGraph: Add glyph if not present");
		glyphs.add(glyph);
//		g.addVertex(glyph);
	}

	public Set<iToken> getAuthors() {
		System.out.println("GlyphGraph: return list of authors");
		return authors;
	}

	public Set<iToken> getGlyphs() {
		System.out.println("GlyphGraph: return list of glyphs");
		return glyphs;
	}

	/*
	 * Returns true if glyph added successfully Adds a glyph TODO If edge
	 * already exists, increment weight
	 */
//	public boolean addGlyph(iToken glyph, WeightedEdge weight, iToken auth) {
//		System.out.println( "GlyphGraph: Creates the edge association between Author and Glyph using WeightedEdge");
//		g.addVertex(glyph);
////		addGlyph(glyph);
////		addAuthor(auth);
//		return g.addEdge(weight, glyph, auth);
//	}

	public void addVertexAndEdge(WeightedEdge weight, iToken auth, iToken glyph) {
//		g.addVertex(auth);
//		g.addVertex(glyph);
		Pair<iToken> vertices = new Pair<iToken>(auth,glyph);
		System.out.println("Contains Edge? " + g.containsEdge(weight));
		if(g.containsVertex(auth) && g.containsVertex(glyph)){
			System.out.println("Are neighbors? " + g.isNeighbor(auth, glyph));
		}
		boolean done = g.addEdge(weight, vertices);
		System.out.println(done);
//		if(!done){
//			System.exit(1);
//		}
	}
	/**
	 * Finds all edges connected to a vertex
	 * @param token
	 * @return
	 */
	public Collection<WeightedEdge> findIncidentEdges(iToken token){
		return g.getIncidentEdges(token);
	}
	/**
	 * Finds the vertex opposite of the one given
	 * @param token
	 * @param edge
	 * @return
	 */
	public iToken getOppositeVertex(iToken token, WeightedEdge edge){
		System.out.println(g.getEdgeCount());
		System.out.println("GRAPH TOKEN " + token.getName());
		System.out.println("GRAPH EDGE " + edge.getWeight());
		LinkedList<Author> authList = new LinkedList<Author>();
		Iterator authItr = authors.iterator();
		int i =0;
		while(authItr.hasNext()){
			authList.add((Author) authItr.next());
			Author tmpAuthor = authList.get(i);
			Map<Glyph, WeightedEdge> glyphWeights = tmpAuthor.getAllGlyphWeights();
			System.out.println("AUTH HAS KEY -- " + glyphWeights.containsKey(token));
			i++;
		}
		//System.out.println(g.getOpposite(token, edge).getName());
		Pair<iToken> vertices = g.getEndpoints(edge);
		if(vertices.getFirst().equals(null)){
			System.out.println("FOUND NULL");
		}
		if(vertices.getFirst().equals(token)){
			return vertices.getSecond();
		}else{
			return vertices.getFirst();
		}
	}
	/**
	 * Checks all edges of a glyph, finds the largest, sets dominate author
	 * @param glyph
	 * @return
	 */
	public iToken findDominateAuthor(iToken glyph){
		LinkedList<WeightedEdge> allEdges = new LinkedList<WeightedEdge>();
		allEdges.addAll(g.getIncidentEdges(glyph));
		WeightedEdge largestEdge = findLargestEdge(allEdges);
		iToken dom = getOppositeVertex(glyph, largestEdge);
		return dom;
	}
	public WeightedEdge findLargestEdge(LinkedList<WeightedEdge> allEdges){
		WeightedEdge tmpEdge = new WeightedEdge();
		WeightedEdge largestEdge = new WeightedEdge();
		for(int i = 0; i < allEdges.size(); i++){
			tmpEdge=allEdges.get(i);
			if(largestEdge == null){
				largestEdge=tmpEdge;
			}else if(largestEdge.getWeight()<tmpEdge.getWeight()){
				largestEdge=tmpEdge;
			}
		}
		return largestEdge;
	}
	
	public List<Entry<iToken, WeightedEdge>> getWeightedEdges(){
		Map<iToken,WeightedEdge> edgeMap = new LinkedMap<iToken, WeightedEdge>();
		List<Entry<iToken, WeightedEdge>> entries = new LinkedList<Entry<iToken,WeightedEdge>>();
		Iterator<iToken> glyphItr = glyphs.iterator();
		iToken tmpGlyph = new Glyph("");
		while(glyphItr.hasNext()){
			
			tmpGlyph=glyphItr.next();
			LinkedList<WeightedEdge> allEdges = new LinkedList<WeightedEdge>();
			allEdges.addAll(g.getIncidentEdges(tmpGlyph));
			WeightedEdge largestEdge = findLargestEdge(allEdges);
			
			edgeMap.put(tmpGlyph, largestEdge);
			//Add largest edge and glyph to list
		}
		entries.addAll(edgeMap.entrySet());
		return entries;
	}
	
	public LinkedList<Integer> getWeightValue(){
		Collection<WeightedEdge> edges = g.getEdges();
		LinkedList<Integer> edgeValues = new LinkedList<Integer>();
		Iterator<WeightedEdge> edgeit = edges.iterator();
		WeightedEdge tmp = new WeightedEdge();
		while(edgeit.hasNext()){
			tmp=edgeit.next();
			edgeValues.add(tmp.getWeight());
		}
		return edgeValues;
	}
	
	public UndirectedGraph<iToken, WeightedEdge> returnGraph(){
		return g;
	}
	
//	public void testPrint(){
//		System.out.println("GRAPH " + g.getEdgeCount() + " edges");
//		Collection<WeightedEdge>edges = g.getEdges();
//		Iterator<WeightedEdge> edgeItr = edges.iterator();
//		WeightedEdge tmpEdge;
//		while(edgeItr.hasNext()){
//			tmpEdge=edgeItr.next();
//			Pair vertices = g.getEndpoints(tmpEdge);
//			System.out.print(vertices.getFirst() + "--" );
//			System.out.print(tmpEdge.getWeight() + "--");
//			System.out.print(vertices.getSecond());
//			System.out.println(" ");
//		}
//		
//		Collection<iToken> vertices = g.getVertices();
//		Iterator<iToken> vItr = vertices.iterator();
//		iToken tmp; 
//		while(vItr.hasNext()){
//			tmp=vItr.next();
//			System.out.println("GRAPH " + tmp.getName());
//		}
//	}
	
}
