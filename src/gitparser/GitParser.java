package gitparser;
import graphmap.Author;
import graphmap.SourceCodeFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Takes in the date from the main method
 * Retrieves all commits from present to the date
 * Passes commits to java parser
 * @author M
 *
 */

public class GitParser {
	LinkedList<Author> authors;
	private String path;
	private int prevDays;
	
	/*
	 * Query to git
	 * gitRecentFiles
	 */
	public GitParser(String path, int prevDays) throws Exception{
		System.out.println("GitParser: Initialize GitParser");
		this.path=path;
		this.prevDays=prevDays;
	}
	
	public Author getAuthor(String name){
		System.out.println("GitParser: Finds the authors from each commit, creates Author objects");
		Author a = new Author(name);
		//TODO connect author and SCF
		return a;
	}
	
	/*
	 * Grabs the next file in the stack
	 */
	public File getNextFile() {
		// TODO create method for getNextFile()
		//Is this method still necessary?
		return null;
	}

	public String getAuthor(File unparsed) {
		// TODO create method for getAuthor(File)
		//Is this method still necessary?
		return null;
	}

	/**
	 * Run a git log command to get the most recent files, populating with author information
	 * 
	 * @return
	 */
	public List<SourceCodeFile> findRecentFiles() {
		
		System.out.println("GitParser: Run a git log command to get the most recent files");
		//We'll need today's date and subtract from previous days
		//checkDate goes here in a while-loop
		//e.g.
//		SourceCodeFile scf = new SourceCodeFile(new File("abc.cpp"));
//		Author author = new Author("Bobby Tables");
//		scf.getAuthors().add(author); //link them both ways!
//		author.getFiles().add(scf);

		authors = getAuthors();
		
		return new LinkedList<SourceCodeFile>();
	}
	
	/*
	 * Checks the provided date against the range to be collected from
	 * If within the intended range return true
	 * else return false
	 */
	public boolean checkDate(int day){
		System.out.println("GitParser: Check date of commit against requested date range");
		return true;
	}

	/**
	 * Given a list of source code files, remove the Glyphs that are not present in recent diffs
	 * @param files
	 */
	public void cull(List<SourceCodeFile> files) {
		
		
	}
	
	/*
	 * Iterate over recent files
	 * Send to JParser
	 */
	

}
