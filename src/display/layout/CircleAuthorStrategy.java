package display.layout;

import graphmap.GlyphGraph;

import java.awt.geom.Dimension2D;
/**
 * 
 * @author M
 *
 * Takes into account the number of authors, divides 360/number of authors to find their placement
 */
public class CircleAuthorStrategy implements IPlacementStrategy {

	@Override
	public void layout(Dimension2D resolution, GlyphGraph g) {
		System.out.println("CircleAuthorStrategy: Find each author's position, set as author's desk");
		// TODO Auto-generated method stub

	}

}
