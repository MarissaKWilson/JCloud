package gitparser;

import graphmap.Author;
import graphmap.SourceCodeFile;
import graphmap.WeightedEdge;
import graphmap.iToken;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import javaparser.JavaClassSummarizable;

public class gitDiffs {
	private final String javaDelimiters = "[ ,;\\(\\)\\[\\]<>\\{\\}\\.:&\\|\\/\\+\\-]";
	private final String[] ignorePrefixes = { "index", "diff", "@@" };
	private Set<Entry<iToken, Double>> entries;

	public gitDiffs(){

	}
	
	
	/**
	 * Requires that the line starts with +++ from the diff format
	 * @param plusLine
	 * @return
	 */
	public JavaClassSummarizable makeSummarizable(String plusLine) {
		return new JavaClassSummarizable(new File(plusLine.substring(6)));
	}
	
	/**
	 * Takes in a list of source code files
	 * Identifies the diffs, passes off diffed strings to
	 * JParser to be made into glyphs
	 * @param files
	 */
	public void filterDiffs(List<SourceCodeFile> files){
		for (SourceCodeFile f : files){
			
		}
	}
	
	public void processTextLine(String line, Map<Author, Set<ISummarizable>> contributions, 
			SourceCodeFile sfc, Author developer, ISummarizable summarizable) {
		if (isFile(line)) {
			addContribution(developer, contributions, line, sfc);
			
		}
//		return processTextLine(line, summarizable);
	}
	
	public boolean isFile(String line) {
		return line.startsWith("+++");
	}
	
	private void addContribution(Author developer, Map<Author, Set<ISummarizable>> 
	contributions, String line, SourceCodeFile sfc) {
		if (isFile(line)) {
			addContribution(developer, contributions, line);
			return;
		}
		if (ignoreIt(line)){
			return;
		}
		String[] lineTokens = line.split(javaDelimiters);
//		for (String lineToken : lineTokens) {
//			for (Entry<iToken, Double> entry : entries) {
//				if (lineToken.trim().equals(entry.getKey().getToken()))
//					weights.put(entry.getKey(), modifier.modify(entry.getValue()));
//			}
//		}
	}
	
	private boolean ignoreIt(String line) {
		for (String prefix : ignorePrefixes) {
			if (line.startsWith(prefix)){
				return true;
			}
		}
		return false;
	}
	
	private void addContribution(Author developer, Map<Author, Set<ISummarizable>> contributions, String line) {
		Set<ISummarizable> files = contributions.get(developer);
		if (files == null)
			files = new LinkedHashSet<ISummarizable>();
		files.add(new JavaClassSummarizable(new File(line.substring(6))));
		contributions.put(developer, files);
	}
}
