package gitparser;

import graphmap.Author;
import graphmap.FileSummaries;
import graphmap.SourceCodeFile;
import graphmap.iToken;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javaparser.JavaClassSummarizable;

public class GitDiffs {
	private final String javaDelimiters = "[ ,;\\(\\)\\[\\]<>\\{\\}\\.:&\\|\\/\\+\\-]";
	private final String[] ignorePrefixes = { "index", "diff", "@@" };
	private Set<Entry<iToken, Double>> entries;

	public GitDiffs(){

	}
	
	
	/**
	 * Requires that the line starts with +++ from the diff format
	 * @param plusLine
	 * @return
	 */
	public FileSummaries makeSummarizable(String plusLine) {
		return new FileSummaries(new File(plusLine.substring(6)));
	}
	
	
	public void processTextLine(String line, Map<Author, Set<ISummarizable>> contributions, 
			SourceCodeFile sfc, Author developer, ISummarizable summarizable) {
		if (isFile(line)) {
			addContribution(developer, contributions, line, sfc);
			return;
		}
		if(ignoreIt(line)){
			return;
		}
	}
	
	
	public boolean isFile(String line) {
		return line.startsWith("+++");
	}
	
	private void addContribution(Author developer, Map<Author, Set<ISummarizable>> 
	contributions, String line, SourceCodeFile sfc) {
		Set<ISummarizable> files = contributions.get(developer);
		if(files == null){
			files = new LinkedHashSet<ISummarizable>();
		}
		files.add(new FileSummaries(new File(line.substring(6))));
		contributions.put(developer, files);		
	}
	
	private boolean ignoreIt(String line) {
		for (String prefix : ignorePrefixes) {
			if (line.startsWith(prefix)){
				return true;
			}
		}
		return false;
	}

}
