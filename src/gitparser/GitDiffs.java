package gitparser;

import graphmap.Author;
import graphmap.FileSummaries;
import graphmap.SourceCodeFile;
import graphmap.iToken;

import java.io.File;
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
		return processTextLine(line, summarizable, sfc);
	}

	public ISummarizable processTextLine(String line,
			ISummarizable summarizable, SourceCodeFile sfc) {
		if (ignoreIt(line)){
			return summarizable;
		}
		String[] lineTokens = line.split(javaDelimiters);
//		System.out.println(lineTokens);
//		LinkedList<String> newTokens = jankySplit(line);
		System.out.println("GITDIFFS line " + line);
//		System.out.println(lineTokens.toString());
		for (String lineToken : lineTokens) {
			lineToken = lineToken.trim();
//			if (isWord(lineToken)) {
				summarizable.addToken(lineToken);
//			}
		}
		// System.out.println(summarizable.getTokens().toString());
		return summarizable;
	}

//	private LinkedList<String> jankySplit(String line) {
//		LinkedList<String> tokenList = new LinkedList<String>();
//		String tmp;
//		char[] lineChars =line.toCharArray();
//		for(char c : lineChars){
//			if(javaDelimiters.){
//				
//			}
//		}
//		return null;
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
