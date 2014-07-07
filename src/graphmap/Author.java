package graphmap;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Author class
 * Holds the name of the author
 * The vertex the author corresponds to
 * @author M
 *
 */
public class Author implements iToken {
	String name;
	LinkedList<SourceCodeFile> sourceFiles = new LinkedList<SourceCodeFile>();
	private Map<Glyph, WeightedEdge> glyphWeights;
	//File picture; 
	//picture functionality will be added later
	//private Point2D.Double desk = new Point2D.Double();
	
	public Author(String name){
		System.out.println("		Author: Initialize new Author");
		this.name = name;
		System.out.println("		Author is " + name);
	}
	/*
	 * (non-Javadoc)
	 * @see graphmap.iToken#getName()
	 * Returns the name associated with the Author
	 */
	@Override
	public String getName() {
		System.out.println("Author: Get name of Author");
		return name;
	}

	/*
	 * Returns the list of all source files
	 * associated with this author
	 */
	public LinkedList<SourceCodeFile> getFiles(){
		System.out.println("Author: Get SCF of Author");
		return sourceFiles;
	}
	
	public void setFile(SourceCodeFile f){
		sourceFiles.add(f);
	}
	
	public Map<Glyph, WeightedEdge> getGlyphWeights(){
		return glyphWeights;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
