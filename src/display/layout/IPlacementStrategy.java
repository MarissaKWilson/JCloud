package display.layout;

import graphmap.GlyphGraph;

import java.awt.geom.Dimension2D;

public interface IPlacementStrategy{
	public void layout(Dimension2D resolution, GlyphGraph g);
}
