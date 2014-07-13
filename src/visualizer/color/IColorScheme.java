package visualizer.color;

import graphmap.iToken;

import java.awt.Color;

public interface IColorScheme {

	abstract public Color lookup(iToken token); 
	
}
