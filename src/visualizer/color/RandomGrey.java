package visualizer.color;

import gitparser.ISummarizable;
import graphmap.WeightedEdge;

import java.awt.Color;
import java.util.Random;

public class RandomGrey implements IColorScheme {

	private final Random rand;
	private final int min;
	private final int max;

	public RandomGrey(Random rand, int min, int max) {
		this.rand = rand;
		this.min = min;
		this.max = max;
	}

	public Color lookup(ISummarizable token, WeightedEdge weights) {
		int grey = rand.nextInt(max - min) + min;
		return new Color(grey, grey, grey);
	}

}
