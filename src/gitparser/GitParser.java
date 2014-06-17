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
 * Retrieves all commits from present to the date specified
 * Find all authors from each commit, associates them with their SourceCodeFiles
 * Returns commits to Main
 * @author M
 *
 */

public class GitParser {	
	private LinkedList<Author> authors;
	private File path;
	private Repository repo;
	private int prevDays;
	//Total time from the git epoch until now
	private int totalTime;
	private ObjectId since;
	private Calendar today;
	private boolean loaded = false;
	
	/**
	 * Constructor
	 * Given a file path, creates new repo
	 * 
	 * @param pathstr path of repo, prevDays number of days to review
	 */
	public GitParser(String pathstr, int prevDays) throws Exception{
		System.out.println("	GitParser: Initialize GitParser");
		System.out.println(today.toString());
		path = new File(pathstr);
		this.prevDays=prevDays;
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
		LinkedList<SourceCodeFile> scf = new LinkedList<SourceCodeFile>(); 
		
		if (!loaded) {
			Iterator<RevCommit> itr = loadRevWalk().iterator();
			while (itr.hasNext()) {
				RevCommit commit = itr.next();
				if (dateOutsideRange(commit) == false){
					Author dev = new Author(commit.getAuthorIdent().getName());
					//TODO convert commit to file
					//Things may have to work with commit

					SourceCodeFile sf = new SourceCodeFile(commit);
					dev.setFile(sf);
					sf.setAuthor(dev);
					scf.add(sf);
				} else{
					break;
				}
			}
		}		
		return scf;
	}
	
	/**
	 * Checks a given commit to see if it is in the date range
	 * @param commit
	 * @return true if outside the range, false if within the range
	 */
	public boolean dateOutsideRange(RevCommit commit){
		int comDate = commit.getCommitTime();
		if(comDate >= totalTime-(prevDays*86400)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Given a list of source code files, remove the Glyphs that are not present in recent diffs
	 * @param files
	 */
	public void cull(List<SourceCodeFile> files) {
		System.out.println("	GitParser: Identify changed glyphs in each diff, remove all other glyphs from SCF");
		for(SourceCodeFile f : files){
			//Trying to figure out the logic here
			//May have to revisit after JParser
		}
	}
	
	/**
	 * Creates a new RevWalk and assigns a head
	 * Marks commits to be uninteresting of after the selected date
	 * @return RevWalk
	 */
	private RevWalk loadRevWalk() {
		RevWalk rw = new RevWalk(repo);
		try {
			ObjectId head = repo.resolve(Constants.HEAD);
			rw.markStart(rw.parseCommit(head));
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
