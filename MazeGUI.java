import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

public class MazeGUI extends JFrame {

	AnimatedBreathingMaze animatedMaze;

	// Panels for different sections of the GUI
	JPanel mazePanel;
	JPanel titlePanel;
	JPanel[][] cells;

	// File chooser to allow user to load maze files
	JFileChooser importFile = new JFileChooser("C\\", FileSystemView.getFileSystemView());

	// Constructor to set up the main GUI components
	public MazeGUI() {
		// Main frame settings
		JFrame mainFrame = new JFrame("Maze Runner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(510, 530);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		// Title panel for the top section of the GUI
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.BLACK);

		// JLabel for the title of the game
		JLabel titleName = new JLabel("Maze Runner");
		titleName.setForeground(Color.WHITE);
		titleName.setFont(new Font("Times New Roman", Font.PLAIN, 75));

		// Maze panel for displaying the maze
		mazePanel = new JPanel();
		mazePanel.setBackground(Color.BLACK); // Background color for contrast

		// Button panel at the bottom of the GUI
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);

		// Buttons for user interaction
		JButton loadButton = new JButton("Load Maze");
		JButton solveButton = new JButton("Solve Maze");
		JButton resetButton = new JButton("Reset Maze");

		// Button styles
		loadButton.setBackground(Color.BLACK);
		loadButton.setForeground(Color.WHITE);
		solveButton.setBackground(Color.BLACK);
		solveButton.setForeground(Color.WHITE);
		resetButton.setBackground(Color.BLACK);
		resetButton.setForeground(Color.WHITE);

		// Button actions (using lambda expressions for simplicity)
		loadButton.addActionListener(e -> importMaze()); // Load a maze from file
		solveButton.addActionListener(e -> visualizeSolution(Maze.path)); // Visualize the solution path
		resetButton.addActionListener(e -> displayInitialMaze(Maze.rows, Maze.cols)); // Reset the maze to the initial state

		// Add buttons to the button panel
		buttonPanel.add(loadButton);
		buttonPanel.add(solveButton);
		buttonPanel.add(resetButton);

		// Add the title to the title panel
		titlePanel.add(titleName);

		// Initialize the maze display when the app starts
		displayInitialMaze(Maze.rows, Maze.cols);

		// Create an instance of AnimatedBreathingMaze and start the breathing animation
		animatedMaze = new AnimatedBreathingMaze(cells, mazePanel);
		animatedMaze.startBreathingAnimation(Maze.maze); // Start animation on the current maze

		// Add panels to the frame
		add(titlePanel, BorderLayout.NORTH); // Title at the top
		add(mazePanel, BorderLayout.CENTER); // Maze in the center
		add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom
	}

	// Method to display the initial maze layout
	private void displayInitialMaze(int rows, int columns) {
		mazePanel.removeAll(); // Clear previous content
		mazePanel.setLayout(new GridLayout(rows, columns)); // Set up grid layout for the maze

		// Create a 2D array of cells representing the maze
		cells = new JPanel[rows][columns];

		// Define borders for each cell
		Border cellBorder = BorderFactory.createLineBorder(Color.WHITE);

		// Loop through the maze array and create cells
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JPanel cell = new JPanel();
				cell.setBorder(cellBorder); // Set border for each cell
				// Set the cell color based on whether it's a wall (0) or path (1)
				if (Maze.maze[i][j] == 0) {
					cell.setBackground(Color.BLACK); // Wall
				} else {
					cell.setBackground(Color.WHITE); // Path
				}
				mazePanel.add(cell); // Add the cell to the panel
				cells[i][j] = cell; // Store reference to the cell
			}
		}

		// Refresh the maze panel to show the changes
		mazePanel.revalidate();
		mazePanel.repaint();


	}

	// Method to visualize the solution path in the maze
	private void visualizeSolution(Stack<int[]> path) {
		displayInitialMaze(Maze.rows, Maze.cols); // Reset the maze to the initial state

		// Loop through the solution path and highlight each step
		for (int[] step : path) {
			int x = step[0];
			int y = step[1];
			JPanel cell = (JPanel) mazePanel.getComponent(x * Maze.cols + y); // Access the cell based on coordinates
			cell.setBackground(Color.PINK); // Highlight the solution path with pink
		}

		// Stop the breathing animation once the solution is displayed
		animatedMaze.stopBreathingAnimation();
	}

	// Method to load the maze from a file
	public void importMaze() {
		importFile.showSaveDialog(null); // Show file chooser dialog

		// If a file is selected, load the maze from the file
		if (importFile.getSelectedFile() != null) {
			String path = importFile.getSelectedFile().getAbsolutePath();
			Maze.loadMaze(path); // Load the maze from the file
			displayInitialMaze(Maze.rows, Maze.cols); // Display the maze after loading
		}

		// Stop the breathing animation
		animatedMaze.stopBreathingAnimation();
	}
}
