package visualizer.placement;

import edu.uci.ics.jung.graph.Graph;
import graphmap.iToken;

public interface INetworkBuilder {
	public Graph<iToken, Long> build();
}
