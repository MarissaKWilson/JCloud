package display.font;

import graphmap.Glyph;
import graphmap.GlyphGraph;

import java.awt.Font;
import java.awt.font.GlyphVector;

public class LinearFontVectorizer implements IGlyphVectorizer {
	GlyphGraph g;

	public LinearFontVectorizer(GlyphGraph g){
	this.g=g;
	forge(new Glyph("gl"));
	}

	@Override
	public GlyphVector forge(Glyph glyph) {
		System.out.println("		LinearFontVectorizer: Create each glyph font size on a linear scale.");
		// Makes font size
		// FIXME Get weighted edge another way
		// int size = ceiling(log(((72*(glyph.Weight - min))/max-min)));
		// glyph.setSize(size);
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 34);
		//This code might help:
//		GlyphVector glyph = font.createGlyphVector(FONT_RENDER_CONTEXT, entry.getKey().getToken());
//		Point2D startingPlace = placeStrategy.getStartingPlace(entry.getKey(), glyph.getOutline());
//		Shape nextShape = glyph.getOutline((float) startingPlace.getX(), (float) startingPlace.getY())
		return null;
	}
	
	
}
