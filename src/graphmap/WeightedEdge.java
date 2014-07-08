package graphmap;
/**
 * Contains data for the edge of the vertices
 * 
 * @author M
 *
 */
public class WeightedEdge {
	int weight;
	
	/*
	 * For now just has weight as data
	 * upon construction sets weight to 1
	 */
	public WeightedEdge(){
		weight=1;
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
}
