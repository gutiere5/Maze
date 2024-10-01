import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MazeGUI extends JFrame{

	JPanel mazePanel;
	

	public MazeGUI() {
		JFrame mainFrame = new JFrame("Maze Runner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setLayout(new BorderLayout());

		// Title 
		
		
		
		// Sub-panel for maze panel
		mazePanel = new JPanel();
		mazePanel.setBackground(Color.gray); // For contrast

		// Sub-panel for button panel
		JPanel buttonPanel = new JPanel();

		// JButton solve
		JButton loadButton = new JButton("Load Maze");
		JButton solveButton = new JButton("Solve Maze");
		JButton resetButton = new JButton("Reset Maze");

		// Actions for Buttons
		loadButton.addActionListener(e -> displayMaze());  // TODO Change to find a file through file explorer
		solveButton.addActionListener(e -> visulizeSolution(Maze.path));
		resetButton.addActionListener(e -> displayMaze());

		// Add Components to button panel
		buttonPanel.add(loadButton);
		buttonPanel.add(solveButton);
		buttonPanel.add(resetButton);

		// Add sub-panel to mainFrame
	//	TODO add(title(), BorderLayout.NORTH);
		add(mazePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);		
	}
	
	// TODO
	/*private void titlePanel extends  JPanel() {
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Maze Runner";
		g2.setColor(Color.WHITE);
		g2.drawString(text, 50, 70);
	} */
	

	
	private void displayMaze() {
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
}
