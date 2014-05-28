import graphmap.GlyphGraph;

/**
 * The main method Executes the main design of the program
 * 
 * @author M
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		int prevDays = 3; // default to three days
		String path = ""; // TODO put together a test repo

		
		System.out.println("Main: Convert files to glyphs");
		
		System.out.println("Main: Link author to files");
		System.out.println("Main: Edgify glyph to author, with weighted edge. Graph complete.");
		GlyphGraph graph = new GlyphGraph();

		System.out.println("Main: Determine font size");
		System.out.println("Main: Network placement algorithm to get starting places");
		System.out.println("Main: Convert glyphs to vectors");
		System.out.println("Main: Spiral placement for final iToken layout");
		System.out.println("Main: Rasterize to png");
//
//		/* Prompt user for input */
//		// TODO Add in a file not found exception here
//
//		/*
//		 * Send filepath to gitparser Gets list of authors
//		 */
//		System.out.println("Call GitParser to get the recent authors");
//		GitParser gparse = new GitParser(path);
//		ArrayList<String> parsedAuthors = gparse.getAuthors();
//
//		/* Creates author objects, adds to graph */
//		for (String auths : parsedAuthors) {
//			graph.addAuthor(new Author(auths));
//		}
//
//		/*
//		 * Grabs file Remembers author Gets diffs Sends diffs to jparser creates
//		 * new sourceCodeFile from jparsed file adds to author list
//		 */
//		GitDiffs gdiffs = new GitDiffs();
//		JParser jparse = new JParser();
//
//		while (gparse.getNextFile() != null) {
//			System.out.println("Main: Get each individual file from the repository");
//			File unparsed = gparse.getNextFile();
//			System.out.println("Main: Get the author for the given file");
//			String tmpAuthor = gparse.getAuthor(unparsed);
//			File diffed = gdiffs.getDiffs(unparsed);
//			File jparsed = jparse.parse(diffed);
//			// //I know theres an easy way to do this but can't recall/find how
//			// int e=0;
//			// while(listOfAuthors.get(e).getName() != tmpAuthor){
//			// e++;
//			// }
//			// Author tmp = listOfAuthors.get(e);
//			// tmp.addFile(jparsed);
//		}
	
		/* Sends files to Java Parser */
		// JParser

		/* Sends parsed files to get the diffs */
		// GitDiffs

		/* Adds the glyph objects to the graph with weighted edges */
		
		// Edgify edgify = new Edgify(graph);
		// for(Author author:listOfAuthors){
		// edgify.addAuthorsGlyphs(author);
		// }

	}
}
