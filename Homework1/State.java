
public class State {
	boolean safe;
	int x; //Number of Missionaries
	int y; //Number of Cannibals
	int b; //Side of boat 1=west 0=east

	
	public State(int missionary, int cannibal, int boatSide)
	{
		if( missionary >= 0 && cannibal >= 0) {
			x = missionary;
			y = cannibal;
			b = boatSide;
			
			if (missionary == 0 || missionary == 4)
				safe = true;
			else if(missionary == cannibal)
				safe = true;
			else
				safe = false;
		}
	}
	
	@Override
	public String toString()
	{
		return "" + x + y + b;
	}
}
