package display;

import graphmap.Author;
import graphmap.Glyph;
import graphmap.WeightedEdge;

/**
 * The spiral algorithm
 * Gets a starting point, iteratively spirals until intersect algorithm 
 * returns false. (Does it intersect? Yes? Keep going. No? Place)
 * Creates starting point at the primary author, creates an offset
 * to a secondary or terchiary author based on the weighted edges
 * not used for creating the font size.
 * Stretch goal: Weight spiral towards more dominant author
 * @author M
 *
 */
public class Spiral {
	
	public static void spiralOut(Glyph glyph){
		//WeightedEdge weight = glyph.getEdge();
		
		if (Intersect.doesIntersect() == true){
			spiralOut(glyph);
		}
		else{
			return;
		}
	}

}
