package javaparser;

import gitparser.ISummarizable;
import graphmap.Author;
import graphmap.Glyph;
import graphmap.SourceCodeFile;
import japa.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Handles the parsing of the java Takes out Java keywords
 * 
 * Contains a Java keyword dictionary Checks each element within the parsed git
 * file to see if in the dictionary if not in dictionary create glyph
 * 
 * @author M
 *
 */
public class JParser {
	// java Dictionary?
	LinkedList<Glyph> unfilteredGlyphs = new LinkedList<Glyph>();
	Map<Author, Set<ISummarizable>> contributions = null;
	private final String javaKeyWords = "abstract,assert,boolean,break,byte,case,catch,char,class,const,continue,default,do,double,else,enum,extends,final,finally,float,for,goto,if,implements,import,instanceof,int,interface,long,native,new,package,private,protected,public,return,short,static,strictfp,super,switch,synchronized,this,throw,throws,transient,try,void,volatile,while,false,null,true";

	public JParser() {

	}

	/**
	 * Given all of these source code files, create the glyph objects. Link the
	 * files to the glyphs.
	 * 
	 * @param files
	 * @throws ParseException
	 * @throws IOException
	 */
	public void populateGlyphs(List<SourceCodeFile> sourceFiles)
			throws ParseException, IOException {
		System.out.println("	JParser: Parse file for keyword identification");

		for (SourceCodeFile f : sourceFiles) {
			contributions = f.getContributions();
			LinkedList<Author> authors = f.getAuthors();
			for (Author a : authors) {
				Set<ISummarizable> allFiles = contributions.get(a);
				Iterator<ISummarizable> itr = allFiles.iterator();
				while (itr.hasNext()) {
					ISummarizable fileSummary = itr.next();
					LinkedList<String> listofTokens = fileSummary.getTokens();
					for (String token : listofTokens) {
						if (!javaKeyWords.contains(token)) {
							
						}
						// compare to java dictionary
					}
				}
			}
		}
		// get each commit
		// new buffered reader
		// for each token
		// is !in ignore list
		// make new glyph
		// add to SCF glyph list
		// close buffered reader

		System.out
				.println("	JParser: For each SCF create new Glyph, add to SCF list");

	}

	public void makeTokens(LinkedList<String> tokens, Author a,
			SourceCodeFile sfc) {
		for (String t : tokens) {
			Glyph g = new Glyph(t);
			sfc.addGlyph(g);
			a.addWeight(g, 1);
		}
	}

}
