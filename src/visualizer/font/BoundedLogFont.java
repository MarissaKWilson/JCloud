package visualizer.font;

import graphmap.WeightedEdge;
import graphmap.iToken;

import java.awt.Font;
import java.util.LinkedList;


public class BoundedLogFont implements IFontTransformer {
	private final double multiplier;
	private final Font initialFont;

	public BoundedLogFont(Font initialFont, double maxFontSize, LinkedList<Integer> weights) {
		this.initialFont = initialFont;
		double max = 0.0d;
		for (Integer i : weights ) {
			if (max < i){
				max = i;
			}
		}
		multiplier = maxFontSize / Math.log(max);
	}

	public Font transform(iToken token, WeightedEdge weightEdge) {
		double weight = weightEdge.getWeight();
		float fontSize = (float) (multiplier * (Math.log(weight + 1.0)));
		System.out.println(token.getName() + " gets font " + fontSize);
		return initialFont.deriveFont(fontSize);
	}
}
