package graphmap;
/**
 * Author class
 * Holds the name of the author
 * The vertex the author corresponds to
 * @author M
 *
 */
public class Author implements iToken {
	String name;
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
	
}
