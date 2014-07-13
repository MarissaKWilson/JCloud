package gitparser;

import graphmap.Author;
import graphmap.Glyph;
import graphmap.SourceCodeFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Takes in the date from the main method Retrieves all commits from present to
 * the date specified Find all authors from each commit, associates them with
 * their SourceCodeFiles Returns commits to Main
 * 
 * @author M
 *
 */

public class GitParser {
	private static final int DAY_IN_MS = 86400000;
	private Set<Author> authors = new HashSet<Author>();
	private File path;
	private Repository repo;
	private int prevDays;
	// Total time from the git epoch until now
	private int totalTime;
	private Date today = new Date();
	// private Date targetDate = new Date();
	private boolean loaded = false;
	GitDiffs diffs = new GitDiffs();
	private final Map<Author, Set<ISummarizable>> contributions = new LinkedHashMap<Author, Set<ISummarizable>>();

	/**
	 * Constructor Given a file path, creates new repo
	 * 
	 * @param pathstr
	 *            path of repo, prevDays number of days to review
	 */
	public GitParser(String pathstr, int prevDays) throws Exception {
		System.out.println("	GitParser: Initialize GitParser");
		path = new File(pathstr);
		this.prevDays = prevDays;
		repo = new FileRepositoryBuilder().setGitDir(path).readEnvironment()
				.findGitDir().build();

	}

	// @SuppressWarnings("deprecation")
	/**
	 * Sets target date for use in RevWalk Filter Using depreciated date
	 * functions as RevFilter expects a Date object
	 */
	private Date setTargetDate() {
		Date targetDate = new Date(System.currentTimeMillis()
				- (prevDays * DAY_IN_MS));
		return targetDate;
	}

	/**
	 * Run a git log command to get the most recent files, populating with
	 * author information
	 * 
	 * @return
	 */
	public LinkedList<SourceCodeFile> findRecentFiles() {
		System.out
				.println("	GitParser: Run a git log command to get the most recent files");
		// List of sourcecodefiles (scf)
		LinkedList<SourceCodeFile> scf = new LinkedList<SourceCodeFile>();

		if (!loaded) {
			Iterator<RevCommit> itr = loadRevWalk().iterator();
			while (itr.hasNext()) {
				RevCommit commit = itr.next();
				String aEmail = commit.getAuthorIdent().getEmailAddress();
				Author dev = new Author(aEmail);
				authors.add(dev);
				SourceCodeFile sf = new SourceCodeFile(commit);
				sf.addAuthor(dev);
				dev.setFile(sf);
				scf.add(sf);
				for (int parentIndex = 0; parentIndex < commit.getParentCount(); parentIndex++) {
					RevCommit parent = commit.getParent(parentIndex);
					ByteArrayOutputStream diff = new ByteArrayOutputStream();
					DiffFormatter formatter = new DiffFormatter(diff);
					formatter.setRepository(repo);
					ISummarizable currentSummarizable = null;
					try {
						formatter.format(commit, parent);
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(diff.toString());
						while (scanner.hasNextLine()) { // scan until the diff
														// part
							String line = scanner.nextLine();

							if (line.charAt(0) == '+' || line.charAt(0) == '-') {
								if (diffs.isFile(line)) {
									currentSummarizable = diffs.makeSummarizable(line);
									// UP TO HERE WORKS PROPERLY(mostly)
								}
								if (currentSummarizable != null){
									currentSummarizable = diffs.processTextLine(line, contributions,
											sf, dev, currentSummarizable);
								}
							}
						}
					} catch (IOException e) {
						System.err.println("IO Exception on commit "
								+ commit.getId().toString());
						e.printStackTrace();
					}
				}
			}	
			loaded = true;
		}
//		testPrint(scf);
		return scf;
	}

	public Set<Author> getAuthors(){
		return authors;
	}

	/**
	 * Creates a new RevWalk and assigns a head Marks commits to be
	 * uninteresting of after the selected date
	 * 
	 * @return RevWalk
	 */
	private RevWalk loadRevWalk() {
		System.out.println("	GitParser: Create new RevWalk");
		Date targetDate = setTargetDate();
		RevWalk rw = new RevWalk(repo);
		RevFilter after = CommitTimeRevFilter.after(targetDate);
		rw.setRevFilter(after);
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


	public String buildDiffString() throws IOException {
		RevWalk rw = loadRevWalk();
		StringBuilder builder = new StringBuilder();
		Iterator<RevCommit> itr = rw.iterator();
		while (itr.hasNext()) {
			RevCommit commit = itr.next();
			RevCommit parent = commit.getParent(0); // TODO Handle multiple
													// parents
			System.out.println("Building diffstring, visiting commit: "
					+ commit.getId().name());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DiffFormatter formatter = new DiffFormatter(out);
			formatter.setRepository(repo);
			formatter.format(commit, parent);
			builder.append(out.toString());
		}
		return builder.toString();
	}

	public void testPrint(LinkedList<SourceCodeFile> scf){
		for(SourceCodeFile f : scf){
			LinkedList<Author> authors = f.getAuthors();
			for(Author a:authors){
				Set<ISummarizable> fileSums = f.getContributions().get(a);
				Iterator<ISummarizable> itr = fileSums.iterator();
				while(itr.hasNext()){
					ISummarizable file = itr.next();
					System.out.println(file.getTokens());
				}
			}
		}
	}

}
