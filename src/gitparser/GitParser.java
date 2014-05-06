package gitparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

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
	public GitParser(String path) throws Exception{
		File gitDir = new File(path);
		FileRepository repo=new FileRepository(gitDir);
		Git git = new Git(repo);
		RevWalk revwalk = new RevWalk(repo);
		Iterable<RevCommit> commits = git.log().add(repo.resolve(Constants.HEAD)).all().call();
		for (RevCommit revCommit : commits) {
			authors.add(revCommit.getAuthorIdent());
			System.out.println(revCommit.getCommitTime());
	}
	
	public ArrayList<String> getAuthors(){
		return authors;
	}
	
	/*
	 * Iterate over recent files
	 * Send to JParser
	 */
	

}
