
public class States {
	State allStates[] = new State[50];
	
	public States() {
		int place = 0;
		for(int missionary = 0; missionary <= 4; missionary++)
		{
			for(int cannibal = 0; cannibal <= 4; cannibal++)
			{
				for(int boatSide = 0; boatSide <= 1; boatSide++)
				{	
					allStates[place] = new State(missionary, cannibal, boatSide);
					place++;
				}		
			}
		}
	}
	
	State getState(int missionary, int cannibal, int boatSide)
	{
		for(State aState : allStates)
		{
			if(aState.x == missionary && aState.y == cannibal && aState.b == boatSide)
				return aState;
		}
		return null;
	}
	
}
