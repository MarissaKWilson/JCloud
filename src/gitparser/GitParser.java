package gitparser;
import graphmap.Author;
import graphmap.SourceCodeFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Takes in the date from the main method
 * Retrieves all commits from present to the date
 * Passes commits to java parser
 * @author M
 *
 */

public class GitParser {	
	private LinkedList<Author> authors;
	private File path;
	private Repository repo;
	private int prevDays;
	private ObjectId since;
	private Calendar today;
	private boolean loaded = false;
	
	/*
	 * Query to git
	 * gitRecentFiles
	 */
	public GitParser(String pathstr, int prevDays) throws Exception{
		System.out.println("	GitParser: Initialize GitParser");
		System.out.println(today.toString());
		path = new File(pathstr);
		this.prevDays=prevDays;
		//This line brought over from old code and edited to fit
		repo =  new FileRepositoryBuilder().setGitDir(path).readEnvironment().findGitDir().build();
	}
	
	
	
	/**
	 * Run a git log command to get the most recent files, populating with author information
	 * 
	 * @return
	 */
	public List<SourceCodeFile> findRecentFiles() {
		
		System.out.println("	GitParser: Run a git log command to get the most recent files");
		//List of sourcecodefiles (scf)
		LinkedList scf = new LinkedList(); 
		
		if (!loaded) {
			Iterator<RevCommit> itr = loadRevWalk().iterator();
			while (itr.hasNext()) {
				RevCommit commit = itr.next();
				Author dev = new Author(commit.getAuthorIdent().getName());
				//TODO convert commit to file
				//SourceCodeFile sf = new SourceCodeFile(file);
				//dev.sourceFiles.add(sf);
				//sf.setAuthor(dev);
			}
		}		
		return new LinkedList<SourceCodeFile>();
		
	}
	
	public void dateHandling(){
		//TODO Create since from prevDays and today
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

	
	public Author getAuthor(String name){
		System.out.println("GitParser: Find the authors from each commit, create Author objects");
		Author a = new Author(name);
		//TODO connect author and SCF
		return a;
	}
	
	
	/**
	 * Given a list of source code files, remove the Glyphs that are not present in recent diffs
	 * @param files
	 */
	public void cull(List<SourceCodeFile> files) {
		System.out.println("	GitParser: Identify changed glyphs in each diff, remove all other glyphs from SCF");
		
		
	}
	
	private RevWalk loadRevWalk() {
		RevWalk rw = new RevWalk(repo);
		try {
			ObjectId head = repo.resolve(Constants.HEAD);
			rw.markStart(rw.parseCommit(head));
			rw.markUninteresting(rw.parseCommit(since));
		} catch (org.eclipse.jgit.errors.MissingObjectException e) {
			System.err.println("Error loading git repo.");
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			System.err.println("Error loading git repo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error loading git repo.");
			e.printStackTrace();
		}
		return rw;
	}

}
