import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class titleMazePanel extends JPanel {

	private final int rows = 8; // Number of rows in the grid
	private final int cols = 11; // Number of columns in the grid
	private final int cellSize = 45; // Size of each cell
	private int currentRow = 0, currentCol = 0; // Current highlighted cell
	private int direction = 0; // 0: right, 1: down, 2: left, 3: up

	public titleMazePanel() {
		Thread animationThread = new Thread(() -> {
			while (true) {
				moveCell();
				repaint();
				try { Thread.sleep(200); } catch (Exception ex) {}
			}
		});
		animationThread.start();
	}

	// Moves the highlighted cell around the outer edges of the grid in a square pattern
	private void moveCell() {
		switch (direction) {
		case 0: // Move right
			currentCol++;
			if (currentCol >= cols - 1) {
				direction = 1; // Switch to moving down
			}
			break;
		case 1: // Move down
			currentRow++;
			if (currentRow >= rows - 1) {
				direction = 2; // Switch to moving left
			}
			break;
		case 2: // Move left
			currentCol--;
			if (currentCol <= 0) {
				direction = 3; // Switch to moving up
			}
			break;
		case 3: // Move up
			currentRow--;
			if (currentRow <= 0) {
				direction = 0; // Switch back to moving right
			}
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Draw the grid
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (row == currentRow && col == currentCol) {
					// Highlight the current cell
					g2d.setColor(Color.PINK);
				} else {
					g2d.setColor(Color.BLACK);
				}
				g2d.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
				g2d.setColor(Color.WHITE);
				g2d.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
			}
		}
	}
}