package display;

import java.awt.Dimension;

import graphmap.GlyphGraph;

public class Rasterizer {

	/**
	 * Given a glyph graph, just render to the png
	 * @param resolution 
	 * @param graph
	 */
	public void rasterize(Dimension resolution, GlyphGraph graph){
		//This code might help:
//		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = bi.createGraphics();
//		g2d.setTransform(new AffineTransform()); // fixes upside down problem
//		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//		RenderingHints.VALUE_ANTIALIAS_ON);
//		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//	    g2d.setRenderingHints(renderHints);
//		nextShape = AffineTransform.getTranslateInstance(next.getX() - last.getX(), next.getY() - last.getY())
//				.createTransformedShape(nextShape);
//		g2d.setColor(colorScheme.lookup(entry.getKey(), weights));
//		g2d.fill(nextShape);
	}
}
