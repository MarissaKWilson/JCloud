package graphmap;

import java.util.LinkedList;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * Edgify class adds glyphs to glyph graph Parses through Author's list of
 * source files Takes source files glyphs, adds them as a vertex Connects vertex
 * to author with a weighted edge if vertex is already present but not
 * associated with specific author Edgify creates new weighted edge If already
 * connected to author, increases weight on edge
 * 
 * @author M
 * 
 */
public class GlyphGraphFactory {

	public GlyphGraphFactory() {
	}

	/**
	 * Given a list of source code files, make a GlyphGraph where authors are
	 * connected to glyphs via the same source code files.
	 * 
	 * @param files
	 */
	public GlyphGraph edgify(LinkedList<SourceCodeFile> files) {
		System.out
				.println("	GlyphGraph: Iterate through SCFs, associate glyph with author using weighted edge, increase weight as needed.");
		GlyphGraph graph = new GlyphGraph();
		for (SourceCodeFile f : files) {
			LinkedList<Author> authors = f.getAuthors();
			for (Author tmpAuthor : authors) {
				LinkedList<Glyph> glyphs = tmpAuthor.getGlyphs();
				for (Glyph tmpGlyph : glyphs) {
					WeightedEdge weight = tmpAuthor.getGlyphWeight(tmpGlyph);
					graph.addEdge(weight,
							new Pair<iToken>(tmpAuthor, tmpGlyph),
							EdgeType.UNDIRECTED);
				}
			}
		}
		return graph;
	}

}
