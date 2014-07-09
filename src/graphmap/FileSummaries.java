package graphmap;

import gitparser.ISummarizable;

import java.io.File;
import java.util.LinkedList;

public class FileSummaries implements ISummarizable{
	File f = null;
	LinkedList<String> tokens = new LinkedList();
	
	public FileSummaries(File file){
		f = file;
	}
	
	@Override
	public File getFile() {
		return f;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSummaries other = (FileSummaries) obj;
		if (f == null) {
			if (other.f != null)
				return false;
		} else if (!f.equals(other.f))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return f.toString();
	}

	@Override
	public LinkedList<String> getTokens() {
		return tokens;
	}
	
	public void addToken(String tok){
		tokens.add(tok);
	}
		

}
