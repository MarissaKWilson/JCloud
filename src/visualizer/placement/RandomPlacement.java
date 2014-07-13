package visualizer.placement;

import graphmap.iToken;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class RandomPlacement implements IPlaceStrategy {

	private final Random rand;
	private final Rectangle2D boundary;

	public RandomPlacement(Random rand, Rectangle2D boundary) {
		this.rand = rand;
		this.boundary = boundary;
	}

	public Point2D getStartingPlace(iToken token, Shape shape) {
		return new Point2D.Double(boundary.getMinX() + rand.nextDouble() * boundary.getWidth(), boundary.getMinY() + rand.nextDouble()
				* boundary.getHeight());
	}

}
