package visualizer.avatars;

import graphmap.Author;
import graphmap.WeightedEdge;
import graphmap.iToken;

import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import visualizer.TokenRenderer;
import visualizer.font.IFontTransformer;
import visualizer.placement.IPlaceStrategy;

/**
 * Given each kind of token, render a shape that represents its boundary. Needs to work in concert with
 * {@link TokenRenderer}
 * 
 * @author andy
 * 
 */
public class TokenBoundaryRenderer implements ISummaryTokenVisitor<Shape> {

	public static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT = new FontRenderContext(null, true, true);

	public static final String DIFF_NOT_SUPPORTED = "Diff token rendering not supported. Cross-reference with a source code token (e.g. Java)";

	private final IFontTransformer fontTrans;
	private final IPlaceStrategy placeStrategy;
	private final WeightedEdge weights;

	public TokenBoundaryRenderer(IFontTransformer fontTrans, IPlaceStrategy placeStrategy, WeightedEdge weights) {
		this.fontTrans = fontTrans;
		this.placeStrategy = placeStrategy;
		this.weights = weights;
	}

	public Shape visit(iToken token) {
		GlyphVector glyphVector = fontTrans.transform(token, weights.get(token)).createGlyphVector(DEFAULT_FONT_RENDER_CONTEXT, token.getToken());
		Shape shape = glyphVector.getOutline();
		Point2D start = placeStrategy.getStartingPlace(token, shape);
		return glyphVector.getOutline((float) start.getX(), (float) start.getY());
	}

	public Shape visit(Author token) {
		Point2D place = placeStrategy.getStartingPlace(token, null);// null?? Fix this.
		// TODO don't hardcode the developer avatars
		return new Rectangle2D.Double(place.getX(), place.getY(), 80, 80);
	}

//	public Shape visit(DiffToken token) {
//		throw new IllegalAccessError(DIFF_NOT_SUPPORTED);
//	}

}
