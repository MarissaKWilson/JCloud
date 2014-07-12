package gitparser;

import graphmap.Author;
import graphmap.FileSummaries;
import graphmap.SourceCodeFile;
import graphmap.iToken;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

public class GitDiffs {
	private final String javaDelimiters = "[ ,;\\(\\)\\[\\]<>\\{\\}\\.:&\\|\\/\\+\\-]";
	private final String[] ignorePrefixes = { "index", "diff", "@@" };
	private Set<Entry<iToken, Double>> entries;
	private final Pattern wordRegex = Pattern.compile("[a-zA-Z][\\w]+");

	public GitDiffs() {

	}

	/**
	 * Requires that the line starts with +++ from the diff format
	 * 
	 * @param plusLine
	 * @return
	 */
	public FileSummaries makeSummarizable(String plusLine) {
		return new FileSummaries(new File(plusLine.substring(6)));
	}

	public ISummarizable processTextLine(String line,
			Map<Author, Set<ISummarizable>> contributions, SourceCodeFile sfc,
			Author developer, ISummarizable summarizable) {
		if (isFile(line)) {
			addContribution(developer, contributions, line, sfc);
		}

//		System.out.println("GITDIFFS " + summarizable.getTokens());
		ISummarizable sum = processTextLine(line, summarizable, sfc, developer);
		if(sum == null){
			System.out.println("GITDIFFS FOUND NULL");
		}
//		System.out.println(sum.getTokens().toString());
		sfc.addContribution(developer, sum);
		// Shows the problem
		Set<ISummarizable> tmpsums= sfc.getContributions().get(developer);
		Iterator<ISummarizable >it = tmpsums.iterator();
		while(it.hasNext()){
			ISummarizable tmp = it.next();
			System.out.print(tmp.getTokens());
		}
		return sum;
	}

	public ISummarizable processTextLine(String line,
			ISummarizable summarizable, SourceCodeFile sfc, Author developer) {
			 if (ignoreIt(line)){
				 return summarizable;
			 }
			String[] lineTokens = line.split(javaDelimiters);
			//System.out.println(lineTokens);
			// LinkedList<String> newTokens = jankySplit(line);
			 System.out.println("GITDIFFS line " + line);
			// System.out.println(lineTokens.toString());
			for (String lineToken : lineTokens) {
				lineToken = lineToken.trim();
				if (isWord(lineToken)) {
					summarizable.addToken(lineToken);
				}
			}
			System.out.println(summarizable.getTokens().toString());
			return summarizable;
			}
	
//	public ISummarizable processTextLine(String line,
//			ISummarizable summarizable, SourceCodeFile sfc, Author dev) {
//		if (ignoreIt(line)){
//			return summarizable;
//		}
//		String[] lineTokens = line.split(javaDelimiters);
////		System.out.println(lineTokens);
////		System.out.println("GITDIFFS line " + line);
////		System.out.println(lineTokens.toString());
//		for (String lineToken : lineTokens) {
//			lineToken = lineToken.trim();
//			if (isWord(lineToken)) {
//				summarizable.addToken(lineToken);	
////					System.out.println("GITDIFFS linetoken " + summarizable.getTokens());
//				
//			}
//		}
////		System.out.println("TOKENS " + summarizable.getTokens().toString());
//		// System.out.println(summarizable.getTokens().toString());
//		sfc.addContribution(dev, summarizable);
//		if(summarizable == null){
//			System.out.println("GIT DIFFS FOUND NULL");
//		}
//		return summarizable;
//	}



	private boolean isWord(String lineToken) {
		return wordRegex.matcher(lineToken).matches();
	}

	public boolean isFile(String line) {
		return line.startsWith("+++");
	}

	private ISummarizable addContribution(Author developer,
			Map<Author, Set<ISummarizable>> contributions, String line,
			SourceCodeFile sfc) {
		Set<ISummarizable> files = contributions.get(developer);
		if (files == null) {
			files = new LinkedHashSet<ISummarizable>();
		}
		FileSummaries filesum = new FileSummaries(new File(line.substring(6)));
		files.add(filesum);
		contributions.put(developer, files);
		sfc.addContribution(developer, contributions);
		return filesum;
	}

	private boolean ignoreIt(String line) {
		for (String prefix : ignorePrefixes) {
			if (line.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

}
