package visualizer.color;

import graphmap.Author;
import graphmap.Glyph;
import graphmap.GlyphGraph;
import graphmap.iToken;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class JavaColorScheme implements IColorScheme {

	private final Random randJitter;
	private final static Map<iToken, Color> colorMap = new HashMap<iToken, Color>();
	private final int jitterMax;
	private GlyphGraph g;

//	public JavaColorScheme() {
//		randJitter = null;
//		jitterMax = 0;
//		initColorMap();
//	}

	public JavaColorScheme(Random randJitter, int jitterMax, GlyphGraph g) {
		this.randJitter = randJitter;
		this.jitterMax = jitterMax;
		this.g=g;
		initColorMap(g.getAuthors());
	}

	private void initColorMap(Set<iToken> authors) {
		LinkedList<Color> colorList = new LinkedList<Color>();
		colorList.add(0, new Color(13,01,209)); //blue
		colorList.add(1, new Color(166,4,0)); //red
		colorList.add(2, new Color(0,130,9)); //green
		colorList.add(3, new Color(237,117,24)); //orange
		colorList.add(4, new Color(181,40,217)); //purple
		colorList.add(5, new Color(232,176,12)); //yellow
		Iterator<iToken> it = authors.iterator();
		int i = 0;
		iToken tmpAuth = new Author("");
		while(it.hasNext()){
			tmpAuth = it.next();
			colorMap.put(tmpAuth, colorList.get(i%6));
		}

	}

	public Color lookup(iToken token) {
		return jittered(lookupColor(token));

	}

	private Color jittered(Color color) {
		if (randJitter == null)
			return new Color(color.getRGB());
		int jitter = randJitter.nextInt(jitterMax) - jitterMax / 2;
		return new Color(inBounds(color.getRed() + jitter), inBounds(color.getGreen() + jitter), inBounds(color.getBlue() + jitter));
	}

	private int inBounds(int i) {
		return Math.min(Math.max(i, 0), 255);
	}

	private Color lookupColor(iToken glyph) {
		iToken dom = findDominateAuthor(glyph);
		Color color = colorMap.get(dom);
		if (color == null)
			color = new Color(100, 100, 100);
		return color;
	}

	private iToken findDominateAuthor(iToken glyph){
		return g.findDominateAuthor(glyph);
	}

}
