import gitparser.GitDiffs;
import gitparser.GitParser;
import graphmap.Author;
import graphmap.Edgify;
import graphmap.GlyphGraph;

import java.io.File;
import java.util.ArrayList;

import javaparser.JParser;

/**
 * The main method
 * Executes the main design of the program
 * @author M
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		int prevDays = 3; //default to three days
		String path = ""; //TODO put together a test repo
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		GlyphGraph graph = new GlyphGraph();
		
		/* Prompt user for input */
		//TODO Add in a file not found exception here
		
		/* Send filepath to gitparser
		 * Gets list of authors*/
		GitParser gparse = new GitParser(path);
		ArrayList<String>parsedAuthors = gparse.getAuthors();
		
		/* Creates author objects, adds to graph */
		for(String auths:parsedAuthors){
			Author a = new Author(auths);
			listOfAuthors.add(a);
			graph.addAuthor(a);
		}
		
		/* Grabs file
		 * Remembers author
		 * Gets diffs
		 * Sends diffs to jparser
		 * creates new sourceCodeFile from jparsed file
		 * adds to author list
		 */
		GitDiffs gdiffs = new GitDiffs();
		JParser jparse = new JParser();
		
		while (gparse.getNextFile() != null){
			File unparsed = gparse.getNextFile();
			String tmpAuthor = gparse.getAuthor(unparsed);
			File diffed = gdiffs.getDiffs(unparsed);
			File jparsed = jparse.parse(diffed);
			
			//I know theres an easy way to do this but can't recall/find how
			int e=0;
			while(listOfAuthors.get(e).getName() != tmpAuthor){
				e++;
			}
			Author tmp = listOfAuthors.get(e);
			tmp.addFile(jparsed);
		}
		
		/* Sends files to Java Parser*/
		//JParser
		
		/*Sends parsed files to get the diffs */
		//GitDiffs
	
		/*Adds the glyph objects to the graph with weighted edges*/
		Edgify edgify = new Edgify(graph);
		for(Author author:listOfAuthors){
			edgify.addAuthorsGlyphs(author);
		}
	
	}
}
