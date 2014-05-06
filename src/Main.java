import gitparser.GitParser;
import graphmap.Author;
import graphmap.Edgify;
import graphmap.GlyphGraph;
import graphmap.iToken;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main method
 * Executes the main design of the program
 * @author M
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		int prevDays;
		String path;
		ArrayList<iToken> listOfAuthors;
		GlyphGraph graph = new GlyphGraph();
		
		/* Prompt user for input */
		System.out.println("How many days would you like commits from? ");
		Scanner scan = new Scanner(System.in);
		prevDays = scan.nextInt();
		System.out.println("What is your repository file path? ");
		path = scan.next();
		//Add in a file not found exception here
		
		/* Send filepath to gitparser
		 * Gets list of authors*/
		GitParser gparse = new GitParser(path);
		ArrayList<String>parsedAuthors = gparse.getAuthors();
		
		/* Creates author objects, adds to graph */
		For(String auths:parsedAuthors){
			iToken a = new Author(auths);
			listOfAuthors.add(a);
			graph.addAuthor(a);
		}
		/* Sends files to Java Parser*/
		JParser
		
		/*Sends parsed files to get the diffs */
		GitDiffs
		
		/*Adds the glyph objects to the graph with weighted edges*/
		for(iToken author:listOfAuthors){
			Edgify.addGlyphs(author, graph);
		}
		
		
		
		
	
	}
}
