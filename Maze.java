
public class Maze {
	
	private static int[][] maze = new int[10][10];  // 10x10 maze grid
    private static boolean[][] visited = new boolean[10][10]; // To track visited cells
    private static int startX, startY, endX, endY;
	
	public static void main(String[] args) {
		MazeGUI mazeGUI = new MazeGUI();
		
		mazeGUI.setVisible(true);
		
	}
	private static boolean solveMaze(int i, int j) {
        // Boundary and base conditions
        if (i < 0 || i >= 10 || j < 0 || j >= 10 || maze[i][j] == 0 || visited[i][j]) {
            return false;
        }

        // Mark the current cell as visited
        visited[i][j] = true;

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
}