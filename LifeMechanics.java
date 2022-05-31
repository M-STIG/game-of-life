import java.util.Random;
public class LifeMechanics {
	/* CONWAY'S GAME OF LIFE RULES
	 * 1. Any live cell with fewer than two live neighbors dies from underpopulation.
	 * 2. Any live cell with more than*/
	private int userX;
	private int userY;
	private int neighbors;

	private final int dimension;
	private final Random num = new Random();
	private int[][] currentIntPond;
	private int[][] nextIntPond;
	
	/* CONSTRUCTOR
	 * initializes neighbor count to zero
	 * initializes all pond values to 0*/
	public LifeMechanics(int size){
		neighbors = 0;
		dimension = size;
		currentIntPond = new int[dimension][dimension];
		nextIntPond = new int[dimension][dimension];

		for (int i=0; i<dimension; i++){
			for (int o=0; o<dimension; o++){
				currentIntPond[i][o] = 0;
			}
		}
	}

	// Generates a random pond
	public void randomPond(){
		for (int i=0; i<dimension; i++){
			for (int o=0; o<dimension; o++){
				currentIntPond[i][o] = num.nextInt(2);
			}
		}
	}
	
	// Resets all pond value to 0
	public void resetPond(){
		for (int i=0; i<dimension; i++){
			for (int o=0; o<dimension; o++){
				nextIntPond[i][o] = 0;
			}
		}
		for (int i=0; i<dimension; i++){
			System.arraycopy(nextIntPond[i], 0, currentIntPond[i], 0, dimension);
		}
	}

	// Check a location in the current pond, return true if there is life
	public boolean checkLocation(int x, int y){
		return currentIntPond[x][y] != 0;
	}

	// Force a single cell alive
	public void forceAlive(int inputX, int inputY) {
		try {
			currentIntPond[inputY][inputX] = 1;
		} catch (ArrayIndexOutOfBoundsException ignored) {
		}
	}

	// Force a single cell to die
	public void forceDead(int inputX, int inputY) {
		try {
			currentIntPond[inputY][inputX] = 0;
		} catch (ArrayIndexOutOfBoundsException ignored) {
		}
	}

	/* Inserts a blinker centered Oon the user's input
	 * three cells in a row that 'blink' indefinitely*/
	// Not yet implemented
	public void insertBlinker(int inputBlinkerX, int inputBlinkerY){
		userX = inputBlinkerX;
		userY = inputBlinkerY;
		
		//left (x-1, y)
		try{
			currentIntPond[userY-1][userX] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY-1) + ", " + (userX) + " is out of the grid.");
		}
		//center (x, y)
		try{
			currentIntPond[userY][userX] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY) + ", " + (userX) + " is out of the grid.");
		}
		//right (x+1, y)
		try{
			currentIntPond[userY+1][userX] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY+1) + ", " + (userX) + " is out of the grid.");
		}
	}
	
	/* Inserts a glider centered Oon the user's input
	 * five initial cells that will 'glide' across the screen*/
	// Not yet implemented
	public void insertGlider(int inputGliderX, int inputGliderY){
		userX = inputGliderX;
		userY = inputGliderY;
		
		//up [userY-1][userX]
		try{
			currentIntPond[userY-1][userX] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY-1) + ", " + (userX) + " is out of the grid.");
		}
		//right [userY][userX+1]
		try{
			currentIntPond[userY][userX+1] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY) + ", " + (userX+1) + " is out of the grid.");
		}
		//down[userY+1][userX]
		try{
			currentIntPond[userY+1][userX] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY+1) + ", " + (userX) + " is out of the grid.");
		}
		//lower right [userY+1][userX+1]
		try{
			currentIntPond[userY+1][userX+1] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY+1) + ", " + (userX+1) + " is out of the grid.");
		}
		//lower left [userY+1][userX-1]
		try{
			currentIntPond[userY+1][userX-1] = 1;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Point " + (userY+1) + ", " + (userX-1) + " is out of the grid.");
		}
	}

	// Advance the pond ecosystem by one generation
	public void entropy(){
		for (int i=0; i<dimension; i++){
			for (int o=0; o<dimension; o++){
				neighbors = 0;
				// Checks the population of all 8 spaces around each array slot
				//upper left (x-1, y-1)
				try{
					if (currentIntPond[i-1][o-1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//left (x-1, y)
				try{
					if (currentIntPond[i-1][o] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//lower left (x-1, y+1)
				try{
					if (currentIntPond[i-1][o+1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//up (x, y+1)
				try{
					if (currentIntPond[i][o+1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//down (x, y-1)
				try{
					if (currentIntPond[i][o-1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//upper right (x+1, y+1)
				try{
					if (currentIntPond[i+1][o+1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//right (x+1, y)
				try{
					if (currentIntPond[i+1][o] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				//lower right (x+1, y-1)
				try{
					if (currentIntPond[i+1][o-1] == 1){neighbors++;}
				}catch(ArrayIndexOutOfBoundsException ignored){
				}
				
				// Updates each space in the "nextPond" based on local population
				// A cell will die if it has less than 2 cells (underpopulation)
				// A cell will die if it has more than 3 cells (overpopulation)
				if (currentIntPond[i][o] == 1 && (neighbors < 2 || neighbors > 3)){
					nextIntPond[i][o] = 0;
				}
				// A cell with either 2 or 3 live neighbors will live, unchanged in the next generation
				else if (currentIntPond[i][o] == 1 && (neighbors == 2 || neighbors == 3)){
					nextIntPond[i][o] = 1;
				}
				// Any cell considered dead will come to life in the next generation if it has exactly three neighbors
				else if (currentIntPond[i][o] == 0 && neighbors == 3){
					nextIntPond[i][o] = 1;
				}			
			}
		}
		// Sets the "nextPond" values to the "currentPond" values
		for (int i=0; i<dimension; i++){
			System.arraycopy(nextIntPond[i], 0, currentIntPond[i], 0, dimension);
		}
	}
}