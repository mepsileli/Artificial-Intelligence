import java.util.*;



public class Homework1 {
		
	public static void main(String[] args) {
		Graph graph = new Graph();
		States states = new States();
		
		//Add States to Graph
		for(int missionary = 0; missionary <= 4; missionary++)
		{
			for(int cannibal = 0; cannibal <= 4; cannibal++)
			{
				for(int boatSide = 0; boatSide <= 1; boatSide++)
				{	
					if(states.getState(missionary, cannibal, boatSide).safe )
						graph.addState(states.getState(missionary, cannibal, boatSide));
				}		
			}
		}
		
		//Add Edges between States
		for(int missionary = 0; missionary <= 4; missionary++)
		{
			for(int cannibal = 0; cannibal <= 4; cannibal++)
			{
				for(int boatSide = 0; boatSide <= 1; boatSide++)
				{	
					State state1 = states.getState(missionary,cannibal,boatSide);;
					if(boatSide == 0)
					{
						if( missionary <= 3) {
							State state2 = states.getState(missionary + 1,cannibal,boatSide + 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( missionary <= 2) {
							State state2 = states.getState(missionary + 2,cannibal,boatSide + 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal <= 3) {
							State state2 = states.getState(missionary,cannibal + 1,boatSide + 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal <= 2) {
							State state2 = states.getState(missionary,cannibal + 2,boatSide + 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal <= 3 && missionary <= 3) {
							State state2 = states.getState(missionary + 1,cannibal + 1,boatSide + 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
					}
					
					if(boatSide == 1)
					{
						if( missionary >= 1) {
							State state2 = states.getState(missionary - 1,cannibal,boatSide - 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( missionary >= 2) {
							State state2 = states.getState(missionary - 2,cannibal,boatSide - 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal >= 1) {
							State state2 = states.getState(missionary,cannibal - 1,boatSide - 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal >= 2) {
							State state2 = states.getState(missionary,cannibal - 2,boatSide - 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
						
						if( cannibal >= 1 && missionary >= 1) {
							State state2 = states.getState(missionary - 1,cannibal - 1,boatSide - 1);
							if(state1.safe && state2.safe)
								graph.addEdge(state1, state2);
						}
					}
				}		
			}
		}

		System.out.println("Graph representation \n" + graph.stateGraph.toString());
		System.out.println("\nTree representation");
		System.out.println("Searching Algorithms' Results \nBreadth First Search Result -> " + breadthFirstSearch(graph,states,states.getState(4, 4, 1)).toString());
		System.out.println("Depth First Search Result -> " + depthFirstSearch(graph,states,states.getState(4, 4, 1)));
		System.out.println("\nPaths");
		printPaths(graph,states,states.getState(4, 4, 1));
	}

	
	static ArrayList<String> depthFirstSearch(Graph graph,States states, State initial)
	{
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stateRepresentation = new Stack<String>();
		String representation = initial.x + "M" + initial.y + "C" + initial.b;
		stateRepresentation.push(representation);
		
		while(!stateRepresentation.empty())
		{
			String stateString = stateRepresentation.pop();
			if(!visited.contains(stateString)) {
				visited.add(stateString);
				for(State aState : graph.getEdgedStates(states.getState(Character.getNumericValue(stateString.charAt(0)), Character.getNumericValue(stateString.charAt(2)), Character.getNumericValue(stateString.charAt(4)))))
				{
					stateRepresentation.push(aState.x + "M" + aState.y + "C" + aState.b);
				}
			}					
		}
		return visited;
	}
	
	static void printPaths(Graph graph,States states, State initial)
	{
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stateRepresentation = new Stack<String>();
		String representation = initial.x + "M" + initial.y + "C" + initial.b;
		stateRepresentation.push(representation);
		
		while(!stateRepresentation.empty())
		{
			String stateString = stateRepresentation.pop();
			if(!visited.contains(stateString)) {
				if(!visited.isEmpty()) {					
					String currentLastState = visited.get(visited.size()-1);
					ArrayList<State> edgedStates = graph.getEdgedStates(states.getState(Character.getNumericValue(currentLastState.charAt(0)), Character.getNumericValue(currentLastState.charAt(2)), Character.getNumericValue(currentLastState.charAt(4))));
					while(!edgedStates.contains(states.getState(Character.getNumericValue(stateString.charAt(0)), Character.getNumericValue(stateString.charAt(2)), Character.getNumericValue(stateString.charAt(4)))))
					{
						visited.remove(visited.size()-1);
						currentLastState = visited.get(visited.size()-1);					
						edgedStates = graph.getEdgedStates(states.getState(Character.getNumericValue(currentLastState.charAt(0)), Character.getNumericValue(currentLastState.charAt(2)), Character.getNumericValue(currentLastState.charAt(4))));
					}

				}	
				visited.add(stateString);
				for(State aState : graph.getEdgedStates(states.getState(Character.getNumericValue(stateString.charAt(0)), Character.getNumericValue(stateString.charAt(2)), Character.getNumericValue(stateString.charAt(4)))))
				{
					stateRepresentation.push(aState.x + "M" + aState.y + "C" + aState.b);
				}
			}	
			else
			{
				ArrayList<String> visitedTemp = new ArrayList<String>();
				visitedTemp.addAll(visited);
				String currentLastState = visitedTemp.get(visitedTemp.size()-1);
				ArrayList<State> edgedStates = graph.getEdgedStates(states.getState(Character.getNumericValue(currentLastState.charAt(0)), Character.getNumericValue(currentLastState.charAt(2)), Character.getNumericValue(currentLastState.charAt(4))));
				while(!edgedStates.contains(states.getState(Character.getNumericValue(stateString.charAt(0)), Character.getNumericValue(stateString.charAt(2)), Character.getNumericValue(stateString.charAt(4)))))
				{
					visitedTemp.remove(visitedTemp.size()-1);
					currentLastState = visitedTemp.get(visitedTemp.size()-1);
					edgedStates = graph.getEdgedStates(states.getState(Character.getNumericValue(currentLastState.charAt(0)), Character.getNumericValue(currentLastState.charAt(2)), Character.getNumericValue(currentLastState.charAt(4))));
				}
				visitedTemp.add(stateString);
				System.out.println(visitedTemp.toString() + "->Repetition");
			}
					
		}
	}
	
	static Set<String> breadthFirstSearch(Graph graph,States states, State initial)
	{
		int layerCnt = 0;
		int currState = initial.b;
		
		Set<String> visited = new LinkedHashSet<String>();
		Queue<String> stateRepresentation = new LinkedList<String>();
		String representation = initial.x + "M" + initial.y + "C" + initial.b;
		stateRepresentation.add(representation);
		visited.add(representation);
		System.out.println("\nLayer " + layerCnt);
		System.out.print(representation + " ");
		
		while(!stateRepresentation.isEmpty())
		{
			
			String stateString = stateRepresentation.poll();
			for(State aState : graph.getEdgedStates(states.getState(Character.getNumericValue(stateString.charAt(0)), Character.getNumericValue(stateString.charAt(2)), Character.getNumericValue(stateString.charAt(4)))))
			{
				String newState = aState.x + "M" + aState.y + "C" + aState.b;
				if(!visited.contains(newState)) {
					if(aState.b == currState) {
						visited.add(newState);
						System.out.print(newState + " ");
						stateRepresentation.add(newState);
					}
					else {
						currState = aState.b;
						layerCnt++;
						System.out.println("\n\nLayer " + layerCnt);
						visited.add(newState);
						System.out.print(newState + " ");
						stateRepresentation.add(newState);
					}
				} else {
					if(aState.b == currState) {
						System.out.print(newState +  "(repetition) ");
					}
					else {
						currState = aState.b;
						layerCnt++;
						System.out.println("\n\nLayer " + layerCnt);
						System.out.print(newState + "(repetition) ");
					}
				}
			}
		}
		System.out.print("\n\n");
		return visited;
	}
}
