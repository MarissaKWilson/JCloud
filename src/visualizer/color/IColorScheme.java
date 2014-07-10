package visualizer.color;

import gitparser.ISummarizable;
import graphmap.WeightedEdge;

import java.awt.Color;

public interface IColorScheme {

	abstract public Color lookup(ISummarizable token, WeightedEdge weights); 
	
}
