import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph {
	HashMap<State, ArrayList<State>> stateGraph;
	
	public Graph() {
		stateGraph = new HashMap<State, ArrayList<State>>();
	}
	
	void addState(State newState)
	{
		stateGraph.putIfAbsent(newState, new ArrayList<>());
	}
	
	void addEdge(State state1, State state2)
	{
		stateGraph.get(state1).add(state2);
	}
	
	ArrayList<State> getEdgedStates(State aState)
	{
		return stateGraph.get(aState);
	}
}



	