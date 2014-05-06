package graphmap;

import java.util.ArrayList;

/**
 * Edgify class adds glyphs to glyph graph
 * Parses through Author's list of source files
 * Takes source files glyphs, adds them as a vertex
 * Connects vertex to author with a weighted edge
 * if vertex is already present but not associated 
 * with specific author Edgify creates new weighted edge
 * If already connected to author, increases weight on edge
 * @author M
 *
 */
public class Edgify {
	
	public Edgify(){
	}
	
	public static void addGlyphs(Author a, GlyphGraph g){
		int fileNum;
		fileNum = a.numberOfFiles();
		for(int i=0; i<=fileNum; i++){
			sourceCodeFile f = a.getOneFile(i);
			ArrayList<iToken> glyphs = f.getGlyphs();
			for(int e=0; e<glyphs.size(); e++){
				WeightedEdge we = new WeightedEdge();
				g.addGlyph(glyphs.get(e), we, a);
			}
		}
	}
	
}
