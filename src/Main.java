import gitparser.GitDiffs;
import gitparser.GitParser;
import graphmap.Author;
import graphmap.Edgify;
import graphmap.GlyphGraph;
import graphmap.iToken;
import graphmap.sourceCodeFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javaparser.JParser;

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
		//TODO Add in a file not found exception here
		
		/* Send filepath to gitparser
		 * Gets list of authors*/
		GitParser gparse = new GitParser(path);
		ArrayList<String>parsedAuthors = gparse.getAuthors();
		
		/* Creates author objects, adds to graph */
		for(String auths:parsedAuthors){
			iToken a = new Author(auths);
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
			Author tmp = (Author) listOfAuthors.get(e);
			tmp.addFile(jparsed);
			
		}
		
		/* Sends files to Java Parser*/
		//JParser
		
		/*Sends parsed files to get the diffs */
		//GitDiffs
		
		/*Adds the glyph objects to the graph with weighted edges*/
		for(iToken author:listOfAuthors){
			Edgify.addAuthorsGlyphs(author);
		}
		
		
		
		
	
	}
}
