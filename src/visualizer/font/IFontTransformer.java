package visualizer.font;

import graphmap.WeightedEdge;
import graphmap.iToken;

import java.awt.Font;

public interface IFontTransformer {

	abstract public Font transform(iToken token, WeightedEdge weight);

}
