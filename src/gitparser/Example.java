package gitparser;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

public class Example {
	public static void main(String[] args) throws Exception {
		File gitDir = new File("C:\\Users\\M\\workspace\\JCloudMavenStinks");
		FileRepository repo=new FileRepository(gitDir);
		Git git = new Git(repo);
		RevWalk revwalk = new RevWalk(repo);
//		revwalk.
		Iterable<RevCommit> commits = git.log().add(repo.resolve(Constants.HEAD)).all().call();
		for (RevCommit revCommit : commits) {
			System.out.println(revCommit.getAuthorIdent());
			System.out.println(revCommit.getCommitTime());
		}
	}
}
