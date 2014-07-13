package visualizer;

import graphmap.Author;
import graphmap.Glyph;

/**
 * Given a token, actually render the image onto the graphics - either as a shape or an avatar.
 * @author andy
 * 
 */
public class TokenRenderer implements ISummaryTokenVisitor<Boolean> {

	public Boolean visit(Glyph token) {
		throw new IllegalStateException("unimplemented!");
	}

	public Boolean visit(Author token) {
		throw new IllegalStateException("unimplemented!");
	}

//	public Boolean visit(DiffToken token) {
//		throw new IllegalStateException("unimplemented!");
//	}

}
