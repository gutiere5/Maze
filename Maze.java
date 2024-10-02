import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Maze {
static Stack<int[]> path = new Stack<>();
	private static int[][] maze;
    private static boolean[][] visited; // To track visited cells
    private static int rows, cols;
    private static int startX, startY, endX, endY;
	

	public static void main(String[] args) {
		Maze ms = new Maze();
		
		String fileName = "maze.txt";
        
        if (ms.loadMaze(fileName)) {
            ms.printMaze();  // Print the loaded maze
            
            // Solve the maze
            if (solveMaze(startX, startY)) {
                System.out.println("Solved Maze:");
                ms.printMaze();
            } else {
                System.out.println("The maze could not be solved.");
            }
        } else {
            System.out.println("Failed to load the maze.");
        }

		MazeGUI mazeGUI = new MazeGUI();
		mazeGUI.setVisible(true);
    }
	 // Load the maze from the file
    private boolean loadMaze(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	//reads the dimensions 
        	String[] dimensions = br.readLine().split(" ");
            rows = Integer.parseInt(dimensions[0]);
            cols = Integer.parseInt(dimensions[1]);
            
            //visited  cells 
            maze = new int[rows][cols];
            visited = new boolean[rows][cols];
            
            //reads start point
            String[] start = br.readLine().split(" ");
            startX = Integer.parseInt(start[0]);
            startY = Integer.parseInt(start[1]);
            
            
           //reads end point
            String[] end = br.readLine().split(" ");
            endX = Integer.parseInt(end[0]);
            endY = Integer.parseInt(end[1]);
            
            //read grid based on rows and columns 
            for (int i = 0; i < rows; i++) {
                String[] line = br.readLine().split(" ");
          
                for (int j = 0; j < cols; j++) {
                	maze[i][j] = Integer.parseInt(line[j]);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error reading the maze file: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid maze format: " + e.getMessage());
            return false;
        }
	
	}
    //solve maze use DFS 
	private static boolean solveMaze(int i, int j) {
        // Boundary and base conditions
        if (i < 0 || i >= rows || j < 0 || j >= cols || maze[i][j] == 0 || visited[i][j]) {
            return false;
        }

		// Mark the current cell as visited
		visited[i][j] = true;
		
		// Push the current cell onto the path stack
		path.push(new int[] {i, j});

		// Check if we have reached the end point
		if (i == endX && j == endY) {
			maze[i][j] = 2;  // Mark the end point as part of the solution
			return true;
		}

		// Mark the current cell as part of the solution path
		maze[i][j] = 2;

		//(down, up, right, left)
		if (solveMaze(i + 1, j) || solveMaze(i - 1, j) || solveMaze(i, j + 1) || solveMaze(i, j - 1)) {
			return true;  // Return true if any path leads to the solution
		}

		// Backtrack: Unmark the cell if no path is found
		maze[i][j] = 1;
		path.pop(); 

		return false;
	}

	// Print the maze
	private static void printMaze() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printStackContents(Stack<int[]> path) {
        System.out.println("Path contents:");


        // Loop through each element (coordinate) in the stack
        for (int[] coordinate : path) {
            System.out.println("[" + coordinate[0] + ", " + coordinate[1] + "]");
    // Print the maze
    private static void printMaze() {
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

