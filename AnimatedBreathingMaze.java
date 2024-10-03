import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class AnimatedBreathingMaze {

    private JPanel[][] cells;
    private JPanel mazePanel;
    private boolean keepAnimating = true;

    public AnimatedBreathingMaze(JPanel[][] cells, JPanel mazePanel) {
        this.cells = cells;
        this.mazePanel = mazePanel;
    }

    // Method to start the breathing animation
    public void startBreathingAnimation(int[][] maze) {
        new Thread(() -> {
            try {
                while (keepAnimating) {
                    // Simulate the breathing effect by varying the brightness
                    for (int brightness = 0; brightness <= 255; brightness += 5) {
                        updateCells(brightness, maze);
                        Thread.sleep(150); // Adjust the speed of the animation
                    }
                    for (int brightness = 255; brightness >= 0; brightness -= 5) {
                        updateCells(brightness, maze);
                        Thread.sleep(150);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Method to stop the animation
    public void stopBreathingAnimation() {
        keepAnimating = false;
    }

    // Method to update the cell colors based on the brightness value
    private void updateCells(int brightness, int[][] maze) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (maze[i][j] == 0) {
                        // Apply the brightness to black cells (making them darker or lighter)
                        cells[i][j].setBackground(new Color(255, 192, 203, brightness)); // Shades of gray
                    } else {
                        // Keep white cells unchanged or give a subtle change
                        cells[i][j].setBackground(new Color(255, 192, 203, 255 - brightness));
                    }
                }
            }
            mazePanel.revalidate();
            mazePanel.repaint();
        });
    }
}
