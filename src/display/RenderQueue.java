package display;

import graphmap.GlyphGraph;

import java.awt.Dimension;

import display.font.LinearFontVectorizer;
import display.layout.CenterStrategy;
import display.layout.CircleAuthorStrategy;
import display.layout.NetworkLayoutStrategy;
import display.layout.SpiralStrategy;

public class RenderQueue {

	private GlyphGraph graph;
	private Dimension resolution;

	public RenderQueue(Dimension resolution, GlyphGraph graph) {
		this.resolution = resolution;
		this.graph = graph;
		
	}
	public void run() {
		//TODO This is hard-coded for now, but could be more flexible
		new CenterStrategy().layout(resolution, graph);
		new CircleAuthorStrategy().layout(resolution, graph);
		new NetworkLayoutStrategy().layout(resolution, graph);
		new LinearFontVectorizer(graph);
		System.out.println("Render Queue: Convert glyphs to vectors");
		new SpiralStrategy().layout(resolution, graph);
	}

}
