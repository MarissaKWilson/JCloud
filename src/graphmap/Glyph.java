package graphmap;
/**
 * Glyph class
 * Holds the text of the glyph
 * @author M
 *
 */
public class Glyph implements iToken{
	String name;
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

}
