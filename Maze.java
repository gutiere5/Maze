import java.util.Stack;

public class Maze {

	static Stack<int[]> path = new Stack<>();
	static int[][] maze = {
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
			{0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 1, 1, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 0, 1, 0},
			{0, 1, 0, 1, 1, 1, 1, 0, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};  // 10x10 maze grid



	private static boolean[][] visited = new boolean[10][10]; // To track visited cells
	private static int startX, startY, endX, endY;

	public static void main(String[] args) {
		MazeGUI mazeGUI = new MazeGUI();
		mazeGUI.setVisible(true);

		startX = 0;
		startY = 1;
		endX = 8;
		endY = 9;
		printMaze();
		boolean solve = solveMaze(startX, startY);
		System.out.println(solve);

		printMaze();

		printStackContents(path);
		
	}

	private static boolean solveMaze(int i, int j) {
		// Boundary and base conditions
		if (i < 0 || i >= 10 || j < 0 || j >= 10 || maze[i][j] == 0 || visited[i][j]) {
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
        }
    }

}

