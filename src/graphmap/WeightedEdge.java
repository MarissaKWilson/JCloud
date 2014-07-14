package graphmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Contains data for the edge of the vertices
 * 
 * @author M
 *
 */
public class WeightedEdge {
	int weight;
	List<iToken> endPoints = new LinkedList<iToken>();
	
	
	/*
	 * For now just has weight as data
	 * upon construction sets weight to 1
	 */
	public WeightedEdge(){
		
	}
	public WeightedEdge(Glyph g, Author author){
		weight=1;
		endPoints.add(g);
		endPoints.add(author);
	}
	/*
	 * adds 1 to the weight
	 */
	public void addWeight(){
		weight++;
	}
	public void addWeight(int w){
		weight+=w;
	}
	/*
	 * Returns weight
	 */
	public int getWeight(){
		return weight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endPoints == null) ? 0 : endPoints.hashCode());
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
		WeightedEdge other = (WeightedEdge) obj;
		if (endPoints == null) {
			if (other.endPoints != null)
				return false;
		} else if (!endPoints.equals(other.endPoints))
			return false;
		return true;
	}
}
