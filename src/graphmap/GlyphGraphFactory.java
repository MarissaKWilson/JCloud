package graphmap;

import java.util.List;

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
	public GlyphGraph edgify(List<SourceCodeFile> files) {
		// int fileNum;
		// fileNum = a.numberOfFiles();
		// for(int i=0; i<=fileNum; i++){
		// SourceCodeFile f = a.getOneFile(i);
		// List<Glyph> glyphs = f.getGlyphs();
		// for(int e=0; e<glyphs.size(); e++){
		// WeightedEdge we = new WeightedEdge();
		// g.addGlyph(glyphs.get(e), we, a);
		// }
		// }
		return new GlyphGraph();
	}

}
