import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	static Stack<int[]> path = new Stack<>(); // Stack to store the solution path
	public static int[][] maze; // 2D array representing the maze structure
	private static boolean[][] visited; // To track visited cells during the maze solving process
	public static int rows, cols; // Number of rows and columns in the maze
	public static int startX, startY, endX, endY; // Starting and ending coordinates in the maze
	static Maze ms = new Maze(); // Create an instance of Maze

	public static void main(String[] args) {
		// Load default start up file 
		String fileName = "maze.txt"; 

		// Load the maze from the file
		loadMaze(fileName);

		// Create and display the MazeGUI interface
		MazeGUI mazeGUI = new MazeGUI();
		mazeGUI.setVisible(true);
	}

	// Load the maze structure from the specified file
	public static void loadMaze(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// Read the maze dimensions (rows and columns)
			String[] dimensions = br.readLine().split(" ");
			rows = Integer.parseInt(dimensions[0]);
			cols = Integer.parseInt(dimensions[1]);

			// Initialize the maze and visited arrays
			maze = new int[rows][cols];
			visited = new boolean[rows][cols];

			// Read the starting point (startX, startY)
			String[] start = br.readLine().split(" ");
			startX = Integer.parseInt(start[0]);
			startY = Integer.parseInt(start[1]);

			// Read the ending point (endX, endY)
			String[] end = br.readLine().split(" ");
			endX = Integer.parseInt(end[0]);
			endY = Integer.parseInt(end[1]);

			// Read the maze grid, each cell represents a path (1) or wall (0)
			for (int i = 0; i < rows; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < cols; j++) {
					maze[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			emptyStack();  	// Empty the stack before solving the maze
			solveMaze(startX, startY); // Solve the maze from the start point
						
		} catch (IOException e) {
			System.out.println("Error reading the maze file: " + e.getMessage());
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid maze format: " + e.getMessage());
		}

		
	}

	// Solve the maze using Depth-First Search (DFS)
	public static boolean solveMaze(int i, int j) {
		// Boundary and base conditions:
		// 1. Check if the current cell is out of bounds
		// 2. Check if the cell is a wall (0) or already visited
		if (i < 0 || i >= rows || j < 0 || j >= cols || maze[i][j] == 0 || visited[i][j]) {
			return false; // Invalid move, return false
		}

		// Mark the current cell as visited
		visited[i][j] = true;

		// Push the current cell onto the path stack
		path.push(new int[] { i, j });

		// Check if the current cell is the end point
		if (i == endX && j == endY) {
			maze[i][j] = 2; // Mark the end point as part of the solution
			return true; // Maze is solved
		}

		// Mark the current cell as part of the solution path
		maze[i][j] = 2;

		// Recursively explore the neighbors: down, up, right, left
		if (solveMaze(i + 1, j) || solveMaze(i - 1, j) || solveMaze(i, j + 1) || solveMaze(i, j - 1)) {
			return true; // Return true if any path leads to the solution
		}

		// Backtrack: unmark the current cell if no solution was found in this path
		maze[i][j] = 1;
		path.pop(); // Remove the cell from the solution path stack

		return false; // No solution found in this path
	}

	// Empty the stack after solving or resetting the maze
	public static void emptyStack() {
		// Pop all elements from the stack to clear it
		while (!path.isEmpty()) {
			path.pop();
		}
	}
	// Print the maze
	public static void printMaze() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == startX && j == startY) {
					System.out.print("S ");  // Mark start point as 'S'
				} else if (i == endX && j == endY) {
					System.out.print("E ");  // Mark end point as 'E'
				} else if (maze[i][j] == 2) {
					System.out.print("2 "); 
				} else {
					System.out.print(maze[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

}

