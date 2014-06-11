package gitparser;
import graphmap.Author;
import graphmap.SourceCodeFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Takes in the date from the main method
 * Retrieves all commits from present to the date
 * Passes commits to java parser
 * @author M
 *
 */

public class GitParser {
	LinkedList<Author> authors;
	private File path;
	private Repository repo;
	private int prevDays;
	
	/*
	 * Query to git
	 * gitRecentFiles
	 */
	public GitParser(String pathstr, int prevDays) throws Exception{
		System.out.println("	GitParser: Initialize GitParser");
		path = new File(pathstr);
		this.prevDays=prevDays;
		repo =  new FileRepositoryBuilder().setGitDir(path).readEnvironment().findGitDir().build();
	}
	
	public Author getAuthor(String name){
		System.out.println("GitParser: Find the authors from each commit, create Author objects");
		Author a = new Author(name);
		//TODO connect author and SCF
		return a;
	}
	
	
	/**
	 * Run a git log command to get the most recent files, populating with author information
	 * 
	 * @return
	 */
	public List<SourceCodeFile> findRecentFiles() {
		
		System.out.println("	GitParser: Run a git log command to get the most recent files");
		//We'll need today's date and subtract from previous days
		//checkDate goes here in a while-loop
		//e.g.
//		SourceCodeFile scf = new SourceCodeFile(new File("abc.cpp"));
//		Author author = new Author("Bobby Tables");
//		scf.getAuthors().add(author); //link them both ways!
//		author.getFiles().add(scf);

		//Author auth = getAuthor("Bill");
		
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
		System.out.println("	GitParser: Identify changed glyphs in each diff, remove all other glyphs from SCF");
		
		
		
	}
	
	/*
	 * Iterate over recent files
	 * Send to JParser
	 */
	

}
