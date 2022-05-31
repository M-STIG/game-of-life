import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConwayTester{
	// Variable for a main JFrame
	JFrame frame;
	// Variable for a grid of JButtons
	JButton[][] grid;
	// Variable for a LifeMechanics instance for our 'pond'
	LifeMechanics pond;
	// Variable for a timer
	Timer timer;
	// Variable for array dimensions
	int dimension;

	// Constructor allows user to specify the size of the 'pond'
	public ConwayTester(int size){
		// Set the default title, size, etc. of the main JFrame
		frame = new JFrame("John Conway's Game of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(750,800);

		// A local Dimension variable is set to the absolute value of the input size
		// This prevents negative input
		dimension = Math.abs(size);
		// To keep things visible, the upper limit for a pond size is 100
		// A minimum size of 10 is specified
		// If a value of 0 is entered, a random size between 10 and 100 is chosen
		if (dimension > 100) {
			dimension = 100;
		} else if (dimension < 10) {
			dimension = 10;
		}

		// Create an instance of LifeMechanics with the same dimensions
		pond = new LifeMechanics(dimension);
		// Fill the pond randomly
		pond.randomPond();

		// Create a JPanel for the 'pond'
		JPanel pondBoard = new JPanel(new GridLayout(dimension, dimension));
		pondBoard.setSize(750,750);
		// The 2D array will be an array of JButtons
		grid = new JButton[dimension][dimension]; // allocate the size of grid
		for (int y = 0; y < dimension; y++) {
			for (int x = 0; x < dimension; x++) {
				int finalX = x;
				int finalY = y;
				// Create a new JButton and add it to the grid
				grid[x][y] = new JButton();
				grid[x][y].addActionListener(e -> {
					updateCells();
					if (pond.checkLocation(finalY, finalX)){
						pond.forceDead(finalY, finalX);
					} else {
						pond.forceAlive(finalY, finalX);
					}
					updateCells();
				});

				pondBoard.add(grid[x][y]);
				// The default color will be white
				if (pond.checkLocation(x, y)) {
					grid[x][y].setBackground(Color.GREEN);
				} else {
					grid[x][y].setBackground(Color.WHITE);
				}
			}
		}
		// Add the 'pond' JPanel
		frame.add(pondBoard, BorderLayout.CENTER);

		// Create a JPanel containing "Play", "Pause", "Reset" and "Clear" buttons
		JPanel controls = new JPanel(new GridLayout(1, 2));
		JButton bPlay = new JButton("Play");
		bPlay.addActionListener(e -> run());
		controls.add(bPlay);

		JButton bPause = new JButton("Pause");
		bPause.addActionListener(e -> pause());
		controls.add(bPause);

		JButton bReset = new JButton("Reset");
		bReset.addActionListener(e -> reset());
		controls.add(bReset);

		JButton bClear = new JButton("Clear");
		bClear.addActionListener(e -> clear());
		controls.add(bClear);

		frame.add(controls, BorderLayout.SOUTH);
		frame.setVisible(true);

		int delay=500;
		timer = new Timer(delay, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				pond.entropy();
				updateCells();
			}
		});
	}

	// run() resets the entropy timer
	public void run() {
		timer.restart();
	}
	// pause() stops the active entropy timer
	public void pause() {
		timer.stop();
	}
	// reset() stops the entropy timer, resets, and then randomizes the pond contents
	public void reset(){
		timer.stop();
		pond.resetPond();
		pond.randomPond();
		updateCells();
	}
	// clear() stops the entropy timer and resets the pond
	public void clear(){
		timer.stop();
		pond.resetPond();
		updateCells();
	}
	// updateCells() updates the color of all grid buttons
	public void updateCells(){
		for (int y = 0; y < dimension; y++) {
			for (int x = 0; x < dimension; x++) {
				if (pond.checkLocation(x, y)) {
					grid[x][y].setBackground(Color.GREEN);
				} else {
					grid[x][y].setBackground(Color.WHITE);
				}
			}
		}
	}

	public static void main(String[] args){
		ConwayTester example = new ConwayTester(50);
	}
}