import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class MazeGUI extends JFrame{

	titleMazePanel titleMaze;
	
	JPanel mazePanel;
	JPanel titlePanel;
	
	
	// Used for File Chooser
	JFileChooser importFile = new JFileChooser("C\\",FileSystemView.getFileSystemView());
	

	public MazeGUI() {
		JFrame mainFrame = new JFrame("Maze Runner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(510, 530);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		
		// subPanel for title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.BLACK);
		
		// JLabels
		JLabel titleName = new JLabel("Maze Runner");
		titleName.setForeground(Color.WHITE);
		titleName.setFont(new Font("Times New Roman", Font.PLAIN, 75));
		
		// Sub-panel for maze panel
		mazePanel = new JPanel();
		mazePanel.setBackground(Color.BLACK); // For contrast

		// Sub-panel for button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);

		// JButton 
		JButton loadButton = new JButton("Load Maze");
		JButton solveButton = new JButton("Solve Maze");
		JButton resetButton = new JButton("Reset Maze");
		
		loadButton.setBackground(Color.BLACK);
		loadButton.setForeground(Color.WHITE);
		solveButton.setBackground(Color.BLACK);
		solveButton.setForeground(Color.WHITE);
		resetButton.setBackground(Color.BLACK);
		resetButton.setForeground(Color.WHITE);
		
		// Actions for Buttons
		loadButton.addActionListener(e -> importMaze());  // TODO Change to find a file through file explorer
		solveButton.addActionListener(e -> visulizeSolution(Maze.path));
		resetButton.addActionListener(e -> displayMaze());

		// Add Components to button panel
		buttonPanel.add(loadButton);
		buttonPanel.add(solveButton);
		buttonPanel.add(resetButton);

		//Add Components to title panel
		titlePanel.add(titleName);
		
		// Create and store reference to titleMazePanel
		titleMaze = new titleMazePanel();
		
		// Add sub-panel to mainFrame
		add(titlePanel, BorderLayout.NORTH);
		//add(mazePanel, BorderLayout.CENTER);
		add(titleMaze, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);		
	}
	
	private void displayMaze() {
		
		remove(titleMaze);   // Remove the animated panel
	    add(mazePanel, BorderLayout.CENTER);
		mazePanel.removeAll();
		mazePanel.setLayout(new GridLayout(10, 10));
		Border cellBorder = BorderFactory.createLineBorder(Color.DARK_GRAY); // Define cell borders
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JPanel cell = new JPanel();
                cell.setBorder(cellBorder);  // Set border for each cell
				if (Maze.maze[i][j] == 0) {
					cell.setBackground(Color.BLACK);
				}
				else {
					cell.setBackground(Color.WHITE);
				}
				mazePanel.add(cell);
			}
		}
		mazePanel.revalidate();
		mazePanel.repaint();
	}
	
	private void visulizeSolution(Stack<int[]> path) {
		for (int[] step : path) {
			int x = step[0];
			int y = step[1];
			
			JPanel cell = (JPanel) mazePanel.getComponent(x * 10 + y);
			cell.setBackground(Color.PINK);
		}
	}

	private void importMaze() {
		importFile.showSaveDialog(null);
		
		if (importFile.getSelectedFile() != null) {
			importFile.getSelectedFile().getAbsolutePath();
		}
	}
	
}
