package graphmap;

import java.io.File;
import java.util.ArrayList;

/**
 * Author class
 * Holds the name of the author
 * The vertex the author corresponds to
 * @author M
 *
 */
public class Author implements iToken {
	String name;
	ArrayList<SourceCodeFile> sourceFiles;
	//File picture; 
	//picture functionality will be added later
	
	public Author(String name){
		this.name = name;
	}
	/*
	 * (non-Javadoc)
	 * @see graphmap.iToken#getName()
	 * Returns the name associated with the Author
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * Returns the list of all source files
	 * associated with this author
	 */
	public ArrayList<SourceCodeFile> getFiles(){
		return sourceFiles;
	}
	
}
