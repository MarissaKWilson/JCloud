package visualizer.font;

import gitparser.ISummarizable;
import graphmap.WeightedEdge;

import java.awt.Font;
import java.util.Map.Entry;
import java.util.Set;

public class BoundedLogFont implements IFontTransformer {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BoundedLogFont.class);
	private final double multiplier;
	private final Font initialFont;

	public BoundedLogFont(Font initialFont, WeightedEdge weights, double maxFontSize) {
		this.initialFont = initialFont;
		Set<Entry<ISummarizable, Double>> unsortedEntries = weights.unsortedEntries();
		double max = 0.0d;
		for (Entry<ISummarizable, Double> entry : unsortedEntries) {
			if (max < entry.getValue())
				max = entry.getValue();
		}
		multiplier = maxFontSize / Math.log(max);
	}

	public Font transform(ISummarizable token, Double weight) {
		float fontSize = (float) (multiplier * (Math.log(weight + 1.0)));
		log.trace(token.getToken() + " gets font " + fontSize);
		return initialFont.deriveFont(fontSize);
	}
}
