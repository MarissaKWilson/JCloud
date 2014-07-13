package visualizer.placement;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graphmap.Glyph;
import graphmap.WeightedEdge;
import graphmap.iToken;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Set;

/**
 * Lays out tokens according to having the same parent summarizable, using graph
 * theory layout.
 * 
 * Based upon on all of the tokens, form a network of tokens (using the JUNG2
 * library) where tokens are connected if they have the same parent. Then run a
 * layout algorithm
 * 
 * @author andy
 * 
 */
public class ParentNetworkPlacement implements IPlaceStrategy {

	private final Set<Glyph> tokens;
	private AbstractLayout<Glyph, Long> layout;
	private final Dimension size;
	private final Point2D center;

	/**
	 * Requires all tokens up front - with parent summarizeables
	 * 
	 * @param allTokens
	 */
	public ParentNetworkPlacement(Set<Glyph> allTokens, Dimension size,
			Point2D center) {
		this.tokens = allTokens;
		this.size = size;
		this.center = center;
	}

	public Point2D getStartingPlace(Glyph token, Shape shape) {
		if (layout == null)
			computeLayout();
		return centered(token);
	}

	/*
	 * Layout will output this into the corner - we need to re-center it
	 */
	private Double centered(Glyph token) {
		double x = layout.getX(token) - size.width + center.getX();
		double y = layout.getY(token) - size.height + center.getY();
		return new Point2D.Double(x, y);
	}

	private void computeLayout() {
		layout = new SpringLayout<Glyph, WeightedEdge>(initGraph());
		layout.setSize(size);
	}

	private UndirectedGraph<iToken, WeightedEdge> initGraph() {
		UndirectedSparseGraph<iToken, WeightedEdge> g = new UndirectedSparseGraph<iToken, WeightedEdge>();
		long edge = 0;
		;
		for (iToken token : tokens)
			g.addVertex(token);
		for (iToken tokenA : tokens) {
			for (iToken tokenB : tokens) {
				if (tokenA != tokenB && sameParent(tokenA, tokenB)) {
					g.addEdge(edge++, tokenA, tokenB);
				}
			}
		}
		return g;
	}

	private boolean sameParent(iToken tokenA, iToken tokenB) {
		return tokenA.getParentSummarizable() != null
				&& tokenA.getParentSummarizable().equals(
						tokenB.getParentSummarizable());
	}

}
