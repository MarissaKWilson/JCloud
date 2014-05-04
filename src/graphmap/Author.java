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
	ArrayList<File> sourceFiles;
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
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * Adds file to the list of source files
	 * associated with this author
	 */
	public void addFile(File source){
		sourceFiles.add(source);
	}
	/*
	 * Returns the list of all source files
	 * associated with this author
	 */
	public ArrayList<File> getFiles(){
		return sourceFiles;
	}
	/*
	 * Returns the specific file associated
	 * with the index number provided
	 */
	public File getOneFile(int index){
		return sourceFiles.get(index);
	}
	
}
