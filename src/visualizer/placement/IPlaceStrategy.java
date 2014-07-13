package visualizer.placement;

import graphmap.iToken;

import java.awt.Shape;
import java.awt.geom.Point2D;

public interface IPlaceStrategy {

	abstract public Point2D getStartingPlace(iToken token, Shape shape);
}
