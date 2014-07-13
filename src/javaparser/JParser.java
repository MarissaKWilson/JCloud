package javaparser;

import gitparser.ISummarizable;
import graphmap.Author;
import graphmap.Glyph;
import graphmap.SourceCodeFile;
import graphmap.WeightedEdge;
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
//			System.out.println("JPARSER got contributions");
			LinkedList<Author> authors = f.getAuthors();
			for (Author a : authors) {
//				System.out.println("JPARSER got an author");
				Set<ISummarizable> allFiles = contributions.get(a);
//				System.out.println("JPARSER got ISUM set");
				Iterator<ISummarizable> itr = allFiles.iterator();
//				System.out.println("JPARSER got itr");
				if(itr.equals(null)){
					System.out.println("JPARSER FOUND NULL");
				}
				while (itr.hasNext()) {
//					System.out.println("JPARSER itr has next");
					ISummarizable fileSummary = itr.next();
//					System.out.println("JPARSER got Filesum");
					LinkedList<String> listofTokens = fileSummary.getTokens();
//					System.out.println("JPARSER got tokenlist");
//					System.out.println(listofTokens.size());
					for (String token : listofTokens) {
//						System.out.println("JPARSER TOKENS " + token);
						if (!javaKeyWords.contains(token)) {
//							System.out.println("TOKEN" + token);
							makeToken(token, a, f);
						}
					}
				}
			//	System.out.println("JPARSER GLYPH" + a.getGlyphs().toString());
			}
		}
		System.out
				.println("	JParser: For each SCF create new Glyph, add to SCF list");

	}

	public void makeToken(String token, Author a, SourceCodeFile sfc) {
			Glyph g = new Glyph(token);
			sfc.addGlyph(g);
			a.addWeight(g, 1);
			a.addGlyph(g);
	}

	public void testPrint(Set<Author> authors) {
		Iterator<Author> authorIterator = authors.iterator();
		Author tmpAuthor = new Author(" ");
		while(authorIterator.hasNext()){
			tmpAuthor = authorIterator.next();
			System.out.println("Author is: " + tmpAuthor.getName());
			LinkedList<Glyph> tmpGlyphs = tmpAuthor.getGlyphs();
			for(Glyph g : tmpGlyphs){
				System.out.println("Glyph: " + g.getName() + " used " + tmpAuthor.getGlyphWeight(g).getWeight() + " times.");
			}
		}
		
	}

}
