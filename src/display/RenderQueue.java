package display;

import gitparser.GitParser;
import graphmap.Author;
import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.WeightedEdge;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import visualizer.AWTIntersector;
import visualizer.LayoutTokens;
import visualizer.color.JavaColorScheme;
import visualizer.command.VisualizerConfigException;
import visualizer.font.BoundedLogFont;
import visualizer.font.IFontTransformer;
import visualizer.placement.CenteredTokenWrapper;
import visualizer.placement.ParentNetworkPlacement;
import visualizer.spiral.SpiralIterator;
import display.font.LinearFontVectorizer;
import display.layout.CenterStrategy;
import display.layout.CircleAuthorStrategy;
import display.layout.NetworkLayoutStrategy;
import display.layout.SpiralStrategy;

public class RenderQueue {

	private GlyphGraph graph;
	private Dimension resolution;
	Set<Author> authors;
	Set<Glyph> glyphs;
	Map<Author, Map<Glyph, WeightedEdge>> glyphsAndWeights;
	// Comes from resolution
	private int width = 800;
	private int height = 800;
	private int maxTokens = 100;
	private double leafCutoff = 1.0d;
	private int spiralSteps = 500;
	// TODO Make this parameter computable from the width/height
	private double spiralMaxRadius = 350.0d;
	// TODO Make this parameter computable from the width/height
	private double squashdown = 1;
	private long randSeed = System.nanoTime();
	private int maxFontSize = 50;
	private String font = "Lucida Sans";

	public RenderQueue(Dimension resolution, GlyphGraph graph) {
		this.resolution = resolution;
		this.graph = graph;
		authors = graph.getAuthors();
		glyphs = graph.getGlyphs();
		populateWeights();

	}

	private void populateWeights() {
		Iterator<Author> authIt = authors.iterator();
		Author tmpAuth;
		Map<Glyph, WeightedEdge> tmp;
		while (authIt.hasNext()) {
			tmpAuth = authIt.next();
			tmp = tmpAuth.getAllGlyphWeights();
			glyphsAndWeights.put(tmpAuth, tmp);
		}

	}

	public void run() {
		// TODO This is hard-coded for now, but could be more flexible
		System.out.println("	RenderQueue: Create new CenterStrategy");
		new CenterStrategy().layout(resolution, graph);
		System.out.println("	RenderQueue: Create new CircleAuthorStrategy");
		new CircleAuthorStrategy().layout(resolution, graph);
		System.out.println("	RenderQueue: Create new NetworkLayoutStrategy");
		new NetworkLayoutStrategy().layout(resolution, graph);
		System.out.println("	RenderQueue: Create new FontVectorizer");
		new LinearFontVectorizer(graph);
		System.out.println("	Render Queue: Convert glyphs to vectors");
		new SpiralStrategy().layout(resolution, graph);
	}

	public BufferedImage call() throws VisualizerConfigException, IOException {
//		weights = new GitParser(new File(srcTree.getAbsolutePath() + "/.git"),
//				since).crossWithDiff(weights, new MultiplyModifier(1.2));
		LinkedList<Integer> weights = getAllWeights();
		IFontTransformer fontTransformer = new BoundedLogFont(new Font(font,
				Font.BOLD, maxFontSize), maxFontSize , weights);
		Random rand = new Random(randSeed);

		AWTIntersector awtIntersectorTmp = new AWTIntersector(10, leafCutoff);
		Dimension parentNetworkDimension = new Dimension(width / 2, height / 2);
		Point2D.Double parentNetworkPoint = new Point2D.Double(3*width/4, 3*height/4);
		ParentNetworkPlacement parentNetworkPlacementTmp = new ParentNetworkPlacement(glyphs ,parentNetworkDimension, parentNetworkPoint);
		SpiralIterator spiralTmp = new SpiralIterator(spiralMaxRadius, spiralSteps, squashdown);
		CenteredTokenWrapper centeredTokenTmp = new CenteredTokenWrapper(parentNetworkPlacementTmp);//, spiralTmp, new JavaColorScheme(rand, 20));
		
		LayoutTokens layoutTokens = new LayoutTokens(width, height, maxTokens,
				fontTransformer, awtIntersectorTmp, 
				centeredTokenTmp, spiralTmp, new JavaColorScheme(rand, 20, graph));
		BufferedImage bi = layoutTokens.makeImage(graph, new File(
				"output/summarizerepo.png"), "PNG");
		return bi;
	}
	
	private LinkedList<Integer> getAllWeights(){
		LinkedList<Integer> weights = new LinkedList();
		Iterator<Author> authorIterator = authors.iterator();
		Author tmpAuthor = new Author(" ");
		while(authorIterator.hasNext()){
			tmpAuthor = authorIterator.next();
			LinkedList<Glyph> tmpGlyphs = tmpAuthor.getGlyphs();
			for(Glyph g : tmpGlyphs){
				weights.add(tmpAuthor.getGlyphWeight(g).getWeight());
			}
		}
		return weights;
	}
}
