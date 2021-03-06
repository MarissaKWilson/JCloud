import gitparser.GitParser;
import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.GlyphGraphFactory;
import graphmap.SourceCodeFile;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import javaparser.JParser;
import display.Rasterizer;
import display.RenderQueue;
import display.font.FontRegistrar;

/**
 * The main method Executes the main design of the program
 * 
 * @author M
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		int prevDays = 1; // default to nine days
		String path = "C:\\Users\\M\\workspace\\JCloud\\.git"; // TODO put together a test repo
		Dimension resolution = new Dimension(800, 600);

		System.out.println("Register fonts to the local environment, if needed ");
		new FontRegistrar().registerBundledFonts();

		System.out.println("Main: Get the list of recently-modified files, with author info");
		GitParser gitParser = new GitParser(path, prevDays);
		LinkedList<SourceCodeFile> files = gitParser.findRecentFiles();

		System.out.println("Main: Process the list of recently modified files to get all of their Java identifiers");
		JParser jp = new JParser();
		jp.populateGlyphs(files);
	//	jp.testPrint(gitParser.getAuthors());
		

	
		
		System.out.println("Making Factory");
		GlyphGraphFactory factory = new GlyphGraphFactory();
		System.out.println("Making graph");
		GlyphGraph graph = factory.getGraph();
		System.out.println("Main: Edgify glyph to author, with weighted edge. Graph complete.");
		factory.edgify(files);
		
		System.out.println("Main: Network placement algorithm to get starting places");
		BufferedImage image = new RenderQueue(resolution, graph).call();
		ImageIO.write(image, "png", new File("output.png"));
//		System.out.println("Main: Rasterize to png");
//		new Rasterizer().rasterize(resolution, graph);
		//
		// /* Prompt user for input */
		// // TODO Add in a file not found exception here
		//
		// /*
		// * Send filepath to gitparser Gets list of authors
		// */
		// System.out.println("Call GitParser to get the recent authors");
		// GitParser gparse = new GitParser(path);
		// ArrayList<String> parsedAuthors = gparse.getAuthors();
		//
		// /* Creates author objects, adds to graph */
		// for (String auths : parsedAuthors) {
		// graph.addAuthor(new Author(auths));
		// }
		//
		// /*
		// * Grabs file Remembers author Gets diffs Sends diffs to jparser
		// creates
		// * new sourceCodeFile from jparsed file adds to author list
		// */
		// GitDiffs gdiffs = new GitDiffs();
		// JParser jparse = new JParser();
		//
		// while (gparse.getNextFile() != null) {
		// System.out.println("Main: Get each individual file from the repository");
		// File unparsed = gparse.getNextFile();
		// System.out.println("Main: Get the author for the given file");
		// String tmpAuthor = gparse.getAuthor(unparsed);
		// File diffed = gdiffs.getDiffs(unparsed);
		// File jparsed = jparse.parse(diffed);
		// // //I know theres an easy way to do this but can't recall/find how
		// // int e=0;
		// // while(listOfAuthors.get(e).getName() != tmpAuthor){
		// // e++;
		// // }
		// // Author tmp = listOfAuthors.get(e);
		// // tmp.addFile(jparsed);
		// }

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
