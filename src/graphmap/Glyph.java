package graphmap;

import java.awt.Font;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

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
	private String name;
	private LinkedList<SourceCodeFile> files = new LinkedList<SourceCodeFile>();
	private Font font;
	private Point2D.Double home = new Point2D.Double();
	//String file;
	/*
	 * Takes in the name of the glyph to be displayed.
	 * 
	 * This is a Java identifier that was populated in JParser
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
	public void setFont(Font font){
		System.out.println("Glyph: Set font for Glyph");
		this.font = font;
	}
	/*
	 * Getter for fontSize
	 */
	public Font getFont(){
		System.out.println("Glyph: Get font for Glyph");
		return font;
	}
	
	/*
	 * Goes through the Glyph's weight's
	 * Retrieves the largest weight
	 * Author associated becomes primary author
	 */
	public Author getPrimary(){
		return null;
	}
	
	public List<SourceCodeFile> getSourceCodeFiles(){
		System.out.println("Glyph: Get SCF");
		return files ;
	}
	
	public Point2D.Double getHome() {
		System.out.println("Glyph: Get home position");
		return home;
	}
	
	public void setHome(Point2D.Double home) {
		System.out.println("Glyph: Set home position");
		this.home = home;
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
		Glyph other = (Glyph) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
}
