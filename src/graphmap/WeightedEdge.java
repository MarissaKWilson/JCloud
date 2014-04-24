package graphmap;
/**
 * Contains data for the edge of the vertices
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
	 * adds to the weight
	 * Generally will be 1, but leaves it generic in case bulk weight is ever needed
	 */
	public void addWeight(int amount){
		weight+=amount;
	}
	/*
	 * Returns weight
	 */
	public int getWeight(){
		return weight;
	}
}
