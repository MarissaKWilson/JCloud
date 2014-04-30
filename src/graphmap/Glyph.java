package graphmap;
/**
 * Glyph class
 * Holds the text of the glyph
 * Will hold the fontsize for easy use later
 * Will hold a numerical representation of the offset from it's primary author
 * 
 * @author M
 *
 */
public class Glyph implements iToken{
	String name;
	int fontSize;
	int offset;
	/*
	 * Takes in the name of the glyph to be displayed
	 */
	public Glyph(String name){
		this.name = name;
	}
	/*
	 * (non-Javadoc)
	 * @see graphmap.iToken#getName()
	 * Returns the name associated with the Glyph
	 */
	@Override
	public String getName() {
		return name;
	}
	/*
	 * Setter for fontSize
	 */
	public void setSize(int size){
		fontSize=size;
	}
	/*
	 * Getter for fontSize
	 */
	public int getSize(){
		return fontSize;
	}
	/*
	 * Goes through the Glyph's weight's
	 * Retrieves the largest weight
	 * Author associated becomes primary author
	 */
	public Author getPrimary(){
		return null;
	}
}
