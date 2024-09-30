import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeGUI extends JFrame{

	JPanel mazePanel;
	

	public MazeGUI() {
		JFrame mainFrame = new JFrame("Maze Runner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setLayout(new BorderLayout());
		
		// Sub-panel for maze panel
		mazePanel = new JPanel();
		
		
		// Sub-panel for button panel
		JPanel buttonPanel = new JPanel();
		
		// JButton solve
		JButton loadButton = new JButton("Load Maze");
		JButton solveButton = new JButton("Solve Maze");
		JButton resetButton = new JButton("Reset Maze");
		
		// Add Components to button panel
		buttonPanel.add(loadButton);
		buttonPanel.add(solveButton);
		buttonPanel.add(resetButton);
		
		// Add sub-panel to mainFrame
		add(mazePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
	}
}
