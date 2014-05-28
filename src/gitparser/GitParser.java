package gitparser;

import graphmap.SourceCodeFile;

import java.io.File;
import java.util.ArrayList;
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
	ArrayList<String> authors;

	/*
	 * Query to git
	 * gitRecentFiles
	 */
	public GitParser(String path, int prevDays) throws Exception{
	}
	
	public ArrayList<String> getAuthors(){
		return authors;
	}
	
	/*
	 * Grabs the next file in the stack
	 */
	public File getNextFile() {
		// TODO create method for getNextFile()
		return null;
	}

	public String getAuthor(File unparsed) {
		// TODO create method for getAuthor(File)
		return null;
	}

	public List<SourceCodeFile> findRecentFiles() {
		System.out.println("GitParser: Run a git log command to get the most recent files");
		//We'll need today's date and subtract from previous days
		return new LinkedList<SourceCodeFile>();
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
