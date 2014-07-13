package visualizer.command;

import gitparser.GitParser;
import graphmap.WeightedEdge;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import visualizer.AWTIntersector;
import visualizer.LayoutTokens;
import visualizer.font.BoundedLogFont;
import visualizer.font.IFontTransformer;
import visualizer.placement.CenteredTokenWrapper;
import visualizer.placement.ParentNetworkPlacement;
import visualizer.spiral.SpiralIterator;

/**
 * This is the command pattern-ish class that handles the actual calling of the
 * visualizer API. The main functionality of this class is to handle all of the
 * properties, defaults, algorithms, constructors, etc.
 * 
 * @author andy
 * 
 */
public class Visualize {
	private static final String PROPS_PREFIX = "collabcloud.visualize.";

	private final File srcTree;

	@Property
	private String since = "";
	// TODO create an auto-detect loader so users don't need to set it
	@Property
	private IVersionControlLoader loader = null;
	@Property
	private int width = 800;
	@Property
	private int height = 800;
	@Property
	private int maxTokens = 100;
	@Property
	private double leafCutoff = 1.0d;
	@Property
	private int spiralSteps = 500;
	// TODO Make this parameter computable from the width/height
	@Property
	private double spiralMaxRadius = 350.0d;
	// TODO Make this parameter computable from the width/height
	@Property
	private double squashdown = 1;
	@Property
	private long randSeed = System.nanoTime();
	@Property
	private int maxFontSize = 50;
	@Property
	private String font = "Lucida Sans";

	public Visualize(File srcTree) {
		this.srcTree = srcTree;
	}

	public BufferedImage call() throws VisualizerConfigException, IOException {
		validate();
		WeightedEdge weights = new JavaProjectSummarizer().summarize(srcTree);
		weights = new GitParser(new File(srcTree.getAbsolutePath() + "/.git"),
				since).crossWithDiff(weights, new MultiplyModifier(1.2));
		IFontTransformer fontTransformer = new BoundedLogFont(new Font(font,
				Font.BOLD, maxFontSize), weights, maxFontSize);
		Random rand = new Random(randSeed);

		AWTIntersector awtIntersectorTmp = new AWTIntersector(10, leafCutoff);
		Dimension parentNetworkDimension = new Dimension(width / 2, height / 2);
		Point2D.Double parentNetworkPoint = new Point2D.Double(3*width/4, 3*height/4);
		ParentNetworkPlacement parentNetworkPlacementTmp = new ParentNetworkPlacement(weights.tokens(),parentNetworkDimension, parentNetworkPoint);
		SpiralIterator spiralTmp = new SpiralIterator(spiralMaxRadius, spiralSteps, squashdown);
		CenteredTokenWrapper centeredTokenTmp = new CenteredTokenWrapper(parentNetworkPlacementTmp);//, spiralTmp, new JavaColorScheme(rand, 20));
		
		LayoutTokens layoutTokens = new LayoutTokens(width, height, maxTokens,
				fontTransformer, awtIntersectorTmp, 
				centeredTokenTmp, spiralTmp, new JavaColorScheme(rand, 20));
		BufferedImage bi = layoutTokens.makeImage(weights, new File(
				"output/summarizerepo.png"), "PNG");
		return bi;
	}

	private void validate() throws VisualizerConfigException {
		List<String> errors = new ArrayList<String>();
		if ("".equals(since))
			errors.add("since revision");
		if (loader == null)
			errors.add("SVN or Git");
		if (!errors.isEmpty())
			throw new VisualizerConfigException(
					"Missing the following properties: "
							+ errors.toString().substring(1,
									errors.toString().length() - 1));
	}

	public Visualize useGit() throws VisualizerConfigException {
		try {
			loader = new GitLoader(srcTree);
			return this;
		} catch (IOException e) {
			throw new VisualizerConfigException(e);
		}
	}

	public Visualize load(Properties props) throws VisualizerConfigException {
		try {
			Properties myProps = new Properties();
			Set<Entry<Object, Object>> set = props.entrySet();
			for (Entry<Object, Object> entry : set) {
				if (entry.getKey().toString().startsWith(PROPS_PREFIX))
					myProps.put(
							entry.getKey().toString()
									.substring(PROPS_PREFIX.length()),
							entry.getValue());
			}
			BeanUtils.populate(this, myProps);
			return this;
		} catch (Exception e) {
			throw new VisualizerConfigException(e);
		}
	}

	public Visualize since(String sinceCommit) {
		this.since = sinceCommit;
		return this;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public void setLoader(IVersionControlLoader loader) {
		this.loader = loader;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setMaxTokens(int maxTokens) {
		this.maxTokens = maxTokens;

	}

	public void setLeafCutoff(double leafCutoff) {
		this.leafCutoff = leafCutoff;
	}

	public void setSpiralSteps(int spiralSteps) {
		this.spiralSteps = spiralSteps;
	}

	public void setSpiralMaxRadius(double spiralMaxRadius) {
		this.spiralMaxRadius = spiralMaxRadius;
	}

	public void setSquashdown(double squashdown) {
		this.squashdown = squashdown;
	}

	public void setRandSeed(long randSeed) {
		this.randSeed = randSeed;
	}

	public void setMaxFontSize(int maxFontSize) {
		this.maxFontSize = maxFontSize;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public File getSrcTree() {
		return srcTree;
	}

	public String getSince() {
		return since;
	}

	public IVersionControlLoader getLoader() {
		return loader;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMaxTokens() {
		return maxTokens;
	}

	public double getLeafCutoff() {
		return leafCutoff;
	}

	public int getSpiralSteps() {
		return spiralSteps;
	}

	public double getSpiralMaxRadius() {
		return spiralMaxRadius;
	}

	public double getSquashdown() {
		return squashdown;
	}

	public long getRandSeed() {
		return randSeed;
	}

	public int getMaxFontSize() {
		return maxFontSize;
	}

	public String getFont() {
		return font;
	}

}
