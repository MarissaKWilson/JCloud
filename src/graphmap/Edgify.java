package graphmap;
/**
 * Edgify class adds glyphs to glyph graph
 * Parses through Author's list of source files
 * Takes source files glyphs, adds them as a vertex
 * Connects vertex to author with a weighted edge
 * if vertex is already present but not associated 
 * with specific author Edgify creates new weighted edge
 * If already connected to author, increasese weight on edge
 * @author M
 *
 */
public class Edgify {
	int fileNum;
	public Edgify(Author a){
		fileNum = a.numberOfFiles();
		for(int i=0; i<=fileNum; i++){
			sourceCodeFile f = a.getOneFile(i);
			f.getFile();
		}
	}
	
}
