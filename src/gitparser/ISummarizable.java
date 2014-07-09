package gitparser;

import java.io.File;
import java.util.LinkedList;

public interface ISummarizable {

	abstract public File getFile();
	
	public LinkedList<String> getTokens();

	public abstract void addToken(String lineToken);
}

