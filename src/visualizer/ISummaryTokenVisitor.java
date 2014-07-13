package visualizer;

import graphmap.Author;
import graphmap.Glyph;

public interface ISummaryTokenVisitor<T> {

	public T visit(Glyph token);
	
	public T visit(Author token);
	
	
}
