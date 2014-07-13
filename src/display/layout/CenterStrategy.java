package display.layout;

import graphmap.GlyphGraph;
import graphmap.iToken;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CenterStrategy implements IPlacementStrategy {

	@Override
	public void layout(Dimension2D resolution, GlyphGraph g) {
		System.out.println("		CenterStrategy: Determine center of given area");
		//Do we really need the glyphgraph here if this is all this does?
		// TODO Auto-generated method stub

	}
	public Point2D getStartingPlace(iToken token, Shape shape) {
		Point2D ul = place.getStartingPlace(token, shape);
		Rectangle2D bounds = shape.getBounds2D();
		return new Point2D.Double(ul.getX() - bounds.getWidth() / 2, ul.getY() - bounds.getHeight() / 2);
	}

}
